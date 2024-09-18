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
import java.util.Random;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//this is a class for customer controller with all the attributes in them
public class CustomerController {
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
    private TextField promo;
//this will show all of the bookings in a table
    ObservableList<BookingHist> bookingsList = FXCollections.observableArrayList();
    public void showBookings(){
// fetch booking
        bookingsList = objDB.fetchBookingsTable(bookingsList);
//        set values
        colCarNo.setCellValueFactory((new PropertyValueFactory<>("carNo")));
        amount.setCellValueFactory((new PropertyValueFactory<>("amount")));
// display in table
        tableBookCarHst.setItems(bookingsList);
    }
    ObservableList<Rides> ridesList = FXCollections.observableArrayList();
    public void showRides(){
//function for showing rides
        ridesList = objDB.fetchRidesTable(ridesList);
//        fetch rides
        CarNo.setCellValueFactory((new PropertyValueFactory<Rides,Integer>("carNo")));
        Hour.setCellValueFactory((new PropertyValueFactory<>("hour")));
        Min.setCellValueFactory((new PropertyValueFactory<>("min")));
        Sec.setCellValueFactory((new PropertyValueFactory<>("sec")));
        Day.setCellValueFactory((new PropertyValueFactory<>("day")));
        Month.setCellValueFactory((new PropertyValueFactory<>("month")));
        Year.setCellValueFactory((new PropertyValueFactory<>("year")));
//        set values
        tableRideHst.setItems(ridesList);
//        show table
    }
    @FXML
    void signupPressed(ActionEvent event) {
        try {
//            a function to put values in mysql
            pst = objDB.setCustomerInfoTable();
// get an instance
            pst.setString(1, name.getText());
            pst.setString(2, password.getText());
            pst.setInt(3, Integer.parseInt(age.getText()));
            pst.setString(4, DOB.getText());
            pst.setString(5, number.getText());
            pst.setString(6, email.getText());
// get values from the ui and set it in pst
            pst.execute();
//            add to mysql
            JOptionPane.showMessageDialog(null, "Details Entered successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }
    @FXML
//    help line pressed function call
    void helplinePressed(ActionEvent event) {
        try {
            pst = objDB.setInvestHelpline();
            boolean alpha=false;
//            checking if the UserName/ID entered exists in the data base or not
            pst.setString(1, query.getText());
            pst.setString(2, custname.getText());
            File myObj = new File("users.txt");
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
//            if it does, execute the statement and send it to manager
//            else display an error
            if(alpha) {
                pst.execute();

                JOptionPane.showMessageDialog(null, "Details Entered successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Customer does not exist. Enter Again");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }
    @FXML
//    when signin pressed, show the sign in page
    public void signinPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/customerController.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
//    build the scene for helpline when helping is pressed
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
        //    build the scene for booking adv when book adv button is pressed
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
        //    build the scene for booking history when booking hist is pressed
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
        //    build the scene for book now when booking now is pressed
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
        //    build the scene for ride In Progress when ride In Progress is pressed
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
//     a detailed function in which multiple processes are occuring
//    whenever the end ride is pressed, there is a time stamp to it as well
//    when the ride end is pressed, we get that time stamp and get the current time stamp
    void endRidePressed(ActionEvent event) throws IOException{
        ObservableList<Rides> RidesList = FXCollections.observableArrayList();
        RidesList = objDB.fetchRidesTable(RidesList);
        int car=RidesList.get(0).carNo;
        int user=RidesList.get(0).user_id;
        int h=RidesList.get(0).hour;
        int m=RidesList.get(0).min;
        int s=RidesList.get(0).sec;
        int d=RidesList.get(0).day;
        int mo=RidesList.get(0).month;
        int y=RidesList.get(0).year;
//        we tokenize the data in the format for time and date
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String day=formattedDate.split("-")[0];
        String month=formattedDate.split("-")[1];
        String year=formattedDate.split(" ")[0].split("-")[2];
        String hour=formattedDate.split(" ")[1].split(":")[0];
        String min=formattedDate.split(" ")[1].split(":")[1];
        String sec=formattedDate.split(" ")[1].split(":")[2];
//        splitting the data in the format for time and date
        int d1=Integer.parseInt(day);
        int mo1=Integer.parseInt(month);
        int y1=Integer.parseInt(year);
        int h1=Integer.parseInt(hour);
        int m1=Integer.parseInt(min);
        int s1=Integer.parseInt(sec);
//        convert them to int
        double amount=0;
        int time=0;
//        algorithm to substract the two times and get the output in minutes
//        if dates are same
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
//        if day is different
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
        String twenty = "hc20";
        String thirty = "hc30";
        String forty = "hc40";
        String fifty = "hc50";
        String random = "hcRand";
//      cross checks for promos
        if (twenty.equals(new String((promo.getText())))) {
            amount -= amount *0.2;
        }
        else if (thirty.equals(new String((promo.getText())))) {
            amount = amount - amount * 0.3;
        }
        else if (forty.equals(new String((promo.getText())))) {
            amount = amount - (amount * 0.4) ;
        }
        else if (fifty.equals(new String((promo.getText())))) {
            amount = amount/2;
        }
        else if (random.equals(new String((promo.getText())))) {
            Random randint=new Random();
            Integer a = randint.nextInt(100);
            amount -= amount/a;
        }
//calulating the ammount.
//        send the amount to the calculate amount function
        pst=objDB.calculateAmount(car,user,h,m,s,d,mo,y,(int)amount );
        JOptionPane.showMessageDialog(null, "Ride Ended successfully");
//        build scene
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/customerController.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    @FXML
//    when adding promo, it validates if it is a correct promo pressed.

    void addPromoPressed(ActionEvent event) throws IOException {
        String twenty = "hc20";
        String thirty = "hc30";
        String forty = "hc40";
        String fifty = "hc50";
        String random = "hcRand";
//      cross checks for promos
        if (twenty.equals(new String((promo.getText())))) {
            JOptionPane.showMessageDialog(null, "Enjoy 20% Off Today");
        }
        else if (thirty.equals(new String((promo.getText())))) {
            JOptionPane.showMessageDialog(null, "Enjoy 30% Off Today");
        }
        else if (forty.equals(new String((promo.getText())))) {
            JOptionPane.showMessageDialog(null, "Enjoy 40% Off Today");
        }
        else if (fifty.equals(new String((promo.getText())))) {
            JOptionPane.showMessageDialog(null, "Enjoy 50% Off Today");
        }
        else if (random.equals(new String((promo.getText())))) {
            Random rand=new Random();
            Integer amount = rand.nextInt(100);
            JOptionPane.showMessageDialog(null, "Congratulations on being our Meme Customer of the Day! ");
            JOptionPane.showMessageDialog(null, "Enjoy a " + amount.toString() + " Discount Today");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Promo code doesn't exist");
        }
    }
    @FXML
//    creates a scene for checking promos
    void checkPromoPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/Promo.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    @FXML
//    when booking history back is pressed, it will take you to customer controller
    void bookHistBackPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/customerController.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
//    sample view for customer controller
    public void sample_view(ActionEvent event) throws IOException {
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
}
