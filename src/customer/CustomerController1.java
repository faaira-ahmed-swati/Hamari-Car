package customer;


import BookCar.BookNowController;
import BookCar.Car;
import BookCar.bookDB;
import connection.ConnectionClass;
import investor.Investor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerController1 {
    private customerDB objDB = new customerDB();
    @FXML
    private TableView<BookingHist> tableBookCarHst;
    @FXML
    private TableView<Rides> tableRideHst;
    @FXML
    private TableColumn<BookingHist, Integer> colCarNo;
    @FXML
    private TableColumn<BookingHist, Integer> amount;
    @FXML
    private TableColumn<Rides, Integer> CarNo;
    @FXML
    private TableColumn<Rides, Integer> UserId;
    @FXML
    private TableColumn<Rides, Integer> Hour;
    @FXML
    private TableColumn<Rides, Integer> Min;
    @FXML
    private TableColumn<Rides, Integer> Sec;
    @FXML
    private TableColumn<Rides, Integer> Day;
    @FXML
    private TableColumn<Rides, Integer> Month;
    @FXML
    private TableColumn<Rides, Integer> Year;
    private TableView<CustomerInfo> tableCust;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField DOB;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField query;
    @FXML
    private TextField custname;
    @FXML
    private TextField name_1;
    @FXML
    private TextField pass;

    ObservableList<BookingHist> bookingsList = FXCollections.observableArrayList();
//    this function will show the booking history
    public void showBookings(){

        bookingsList = objDB.fetchBookingsTable(bookingsList);
//        get booking histories
//        add values
        colCarNo.setCellValueFactory((new PropertyValueFactory<>("carNo")));
        amount.setCellValueFactory((new PropertyValueFactory<>("amount")));
//        set table
        tableBookCarHst.setItems(bookingsList);
    }
    ObservableList<Rides> ridesList = FXCollections.observableArrayList();
    public void showRides(){
//get rides
        ridesList = objDB.fetchRidesTable(ridesList);
        CarNo.setCellValueFactory((new PropertyValueFactory<Rides,Integer>("carNo")));
        Hour.setCellValueFactory((new PropertyValueFactory<>("hour")));
        Min.setCellValueFactory((new PropertyValueFactory<>("min")));
        Sec.setCellValueFactory((new PropertyValueFactory<>("sec")));
        Day.setCellValueFactory((new PropertyValueFactory<>("day")));
        Month.setCellValueFactory((new PropertyValueFactory<>("month")));
        Year.setCellValueFactory((new PropertyValueFactory<>("year")));
//        set values
        tableRideHst.setItems(ridesList);
//        set table
    }
//    checker for email
    boolean checkEmail(String email){
        if(email.split("@").length > 1 ){
            return true;
        }
        else
            return false;

    }
    @FXML
//    when sign up is pressed this function is called
    void signupPressed(ActionEvent event) {
        try {
            pst = objDB.setCustomerInfoTable();
// get an instance
            pst.setString(1, name.getText());
            pst.setString(2, password.getText());
            pst.setInt(3, Integer.parseInt(age.getText()));
            pst.setString(4, DOB.getText());
            pst.setString(5, number.getText());
            pst.setString(6, email.getText());
//            set values
            try
            {
//                if email is not verified
                if(!checkEmail(email.getText())){
                    JOptionPane.showMessageDialog(null, "Incorrect Email Format!");
                }
//write in data base
                else{
                    String filename= "users.txt";
                    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                    fw.write(name.getText() + ":" + password.getText() + "\n");//appends the string to the file
                    fw.close();
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Details Entered successfully");
                }

            }
//            in case of error
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }
    @FXML
//    when helpline is pressed. check for user name/id validation
    void helplinePressed(ActionEvent event) {
        try {
            pst = objDB.setInvestHelpline();
            boolean alpha=false;
            pst.setString(1, query.getText());
            pst.setString(2, custname.getText());
            File myObj = new File("users.txt");
//            validate username/id
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String res[] = data.split(":");
                if (res[0].compareTo(custname.getText()) == 0) {
                    alpha=true;
                    break;
                }
                else
                    continue;
            }
//            if valid,
            if(alpha) {
                pst.execute();

                JOptionPane.showMessageDialog(null, "Details Entered successfully");
            }
//            if unvalid, show error and create the same page again
            else{
                JOptionPane.showMessageDialog(null, "Customer does not exist. Enter Again");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    checks for login validation
    public boolean checkInfo(String password,String name) throws FileNotFoundException {
        File myObj = new File("users.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String res[] = data.split(":");
            if (res[0].compareTo(name) == 0 && res[1].compareTo(password) == 0) {
                return true;
            }
        }
        return false;
    }
    @FXML
    public void signinPressed(ActionEvent event) throws IOException {
//when sign in is pressed.

        String name1 = name_1.getText();
        String password = pass.getText();
//        get name and password
//        and validate
        boolean toreturn= checkInfo(password,name1);
        if(toreturn){
//            if valid load customer controller
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../customer/customerController.fxml"));
            Parent sampleViewParent = loader.load();
            Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();    //getting the previous paren scene
            window.setTitle("Hamari Car");
            window.setScene(passwordViewScene);
            window.show();

        }
        else{
//            else reload the page showing a dialogue
            JOptionPane.showMessageDialog(null, "Invalid username or password");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../customer/SignIn.fxml"));
            Parent sampleViewParent = loader.load();
            Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();    //getting the previous paren scene
            window.setTitle("Hamari Car");
            window.setScene(passwordViewScene);
            window.show();
        }
    }
    @FXML
//    when helpline is pressed. create a customer helpline
    void HelplinePressed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/CustHelpline.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
//    when booking advanced is pressed create a scene
    void bookAdvPressed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../BookCar/bookAdv.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }

    @FXML
//    when booking hist is pressed, create a scene for booking istory
    void bookHistPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/bookingHistory.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        CustomerController obj = loader.getController();
        obj.showBookings();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
//    when booking hist is pressed, create a scene for booking istory
    void bookNowPressed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../BookCar/bookNow.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
//    when rides in progress is pressed, create a scene for rides in progress
    void rideInProgressPressed(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/rideProgress.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        CustomerController obj = loader.getController();
        obj.showRides();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    PreparedStatement pst = null;
    @FXML
//    when end ride is pressed
    void endRidePressed(ActionEvent event) throws IOException{
        ObservableList<Rides> RidesList = FXCollections.observableArrayList();
        RidesList = objDB.fetchRidesTable(RidesList);
//        fetch rides
        int car=RidesList.get(0).carNo;
        int user=RidesList.get(0).user_id;
        int h=RidesList.get(0).hour;
        int m=RidesList.get(0).min;
        int s=RidesList.get(0).sec;
        int d=RidesList.get(0).day;
        int mo=RidesList.get(0).month;
        int y=RidesList.get(0).year;

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String day=formattedDate.split("-")[0];
        String month=formattedDate.split("-")[1];
        String year=formattedDate.split(" ")[0].split("-")[2];
        String hour=formattedDate.split(" ")[1].split(":")[0];
        String min=formattedDate.split(" ")[1].split(":")[1];
        String sec=formattedDate.split(" ")[1].split(":")[2];
        int d1=Integer.parseInt(day);
        int mo1=Integer.parseInt(month);
        int y1=Integer.parseInt(year);
        int h1=Integer.parseInt(hour);
        int m1=Integer.parseInt(min);
        int s1=Integer.parseInt(sec);
        int amount=0,time=0;
        if(d1==d && mo1==mo && y1==y ){
            int new_hour= (h-h1)*60;
            int new_min= m-m1;
            int new_sec=(s-s1)/60;
            time=new_hour+new_min+new_sec;
            int time1=time;
            while(time1>0){
                amount=amount+1;
                time1=time1-1;
            }
        }
        else if(d1!=d && mo1==mo && y1==y ){
            int new_hour= (h-h1)*60;
            int new_min= m-m1;
            int new_sec=(s-s1)/60;
            time=(new_hour+new_min+new_sec)+24*60;
            int time1=time;
            while(time1>0){
                amount=amount+1;
                time1=time1-1;
            }
        }
        Random rand=new Random();
        amount=rand.nextInt(1000);
        while(amount<500) {
            amount = rand.nextInt(1000);
        }
        pst=objDB.calculateAmount(car,user,h,m,s,d,mo,y,amount);
        JOptionPane.showMessageDialog(null, "Ride Ended successfully");
        /*Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/customerController.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();*/

    }
    @FXML
    void bookHistBackPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/customerController.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    public void sample_view(ActionEvent event) throws IOException {
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
}
