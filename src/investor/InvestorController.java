package investor;

import BookCar.BookNowController;
import BookCar.Car;
import BookCar.bookDB;
import customer.CustomerInfo;
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

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Scanner;
//investor controller class with its attributes
public class InvestorController {

    private investorDB objDB = new investorDB();
    @FXML
    private TableView<Car> tableIsbCar;
    @FXML
    private TableView<Investor> tableInvestor;
    @FXML
    private TableColumn<Investor, Integer> colEarning;
    @FXML
    private TableColumn<Car, String> colSector;
    @FXML
    private TableColumn<Car, String> colType;
    @FXML
    private TableColumn<Car, Integer> colModel;
    @FXML
    private TableColumn<Car, Integer> colNumber;
    @FXML
    private TableColumn<Car, String> colColor;
    @FXML
    private TextField txtSector;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtColor;
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
    @FXML
    private TableColumn<InvestorInfo, String> colName;
    @FXML
    private TableColumn<InvestorInfo, String> colpass;
    PreparedStatement pst = null;

    ObservableList<Car> isbCarList = FXCollections.observableArrayList();
    public void showInvestedCarList(){
//fetch the rows from the mysql server
//add the values to the coloumns
//set the table
        isbCarList = objDB.fetchInvestedCarTable(isbCarList);
        colSector.setCellValueFactory((new PropertyValueFactory<>("sector")));
        colType.setCellValueFactory((new PropertyValueFactory<>("type")));
        colModel.setCellValueFactory((new PropertyValueFactory<>("model")));
        colNumber.setCellValueFactory((new PropertyValueFactory<>("number")));
        colColor.setCellValueFactory((new PropertyValueFactory<>("color")));

        tableIsbCar.setItems(isbCarList);
    }
    ObservableList<Investor> earningList = FXCollections.observableArrayList();
    public void showEarning(){
//show earnings for the investor
        earningList = objDB.fetchEarnings(earningList);
        colEarning.setCellValueFactory((new PropertyValueFactory<>("earning")));
        /*int earning=0;
        for (int i=0 ; i<earningList.size(); i++){
            earning+=earningList.get(i).getEarning();
        }
        tableInvestor.getColumns().add(new Investor(1,earning) );*/
        tableInvestor.setItems(earningList);
    }

    @FXML
    void investPressed(ActionEvent event) {
        try {
//            when invest pressed, get all the values from the gui
            pst = objDB.setIsbCarsTable();
//get instance of object from db
            pst.setString(1, txtSector.getText());
            pst.setString(2, txtType.getText());
            pst.setInt(3, Integer.parseInt(txtModel.getText()));
            pst.setInt(4, Integer.parseInt(txtNumber.getText()));
            pst.setString(5, txtColor.getText());
//set values
//            add to mysql
            pst.execute();

            JOptionPane.showMessageDialog(null, "Car Invested successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }


    @FXML
    void investCarPressed(ActionEvent event) throws IOException {
//        when invest in car pressed, build a scene for invest car view
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/investCarView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
    void showInvestmentsPressed(ActionEvent event) throws IOException{
//        when investor pressed, build a scene for invested car view fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/investedCarsView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        InvestorController obj = loader.getController();
        obj.showInvestedCarList();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
    void checkEarningsPressed(ActionEvent event) throws IOException{
//        when check earnings pressed, create a view for earnings view table
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/earningsView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        InvestorController obj = loader.getController();
        obj.showEarning();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    boolean checkEmail(String email){
//        validation criteria or email
        if(email.split("@").length > 1 ){
                return true;
        }
        else
            return false;

    }
    @FXML
    void signupPressed(ActionEvent event) {
        try {
//            when signup pressed, get all values
            pst = objDB.setInvestorInfoTable();

            pst.setString(1, name.getText());
            pst.setString(2, password.getText());
            pst.setInt(3, Integer.parseInt(age.getText()));
            pst.setString(4, DOB.getText());
            pst.setString(5, number.getText());
            pst.setString(6, email.getText());
            try
            {
                if(!checkEmail(email.getText())){
//                    validate email
//if unvalid
                    JOptionPane.showMessageDialog(null, "Incorrect Email Format!");
                }
                else{
//                    if valid
                    String filename= "users.txt";
                    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                    fw.write(name.getText() + ":" + password.getText() + "\n");//appends the string to the file
                    fw.close();
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Details Entered successfully");
                }

            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }
    public boolean checkInfo(String password,String name) throws FileNotFoundException {
//        validate username/id and password from data base
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
//when sign in pressed, validate username/id and password
        String name1 = name_1.getText();
        String password = pass.getText();
        boolean toreturn= checkInfo(password,name1);
//        validate
        if(toreturn){
//            if valid
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../investor/investorView.fxml"));
            Parent sampleViewParent = loader.load();
            Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();    //getting the previous paren scene
            window.setTitle("Hamari Car");
            window.setScene(passwordViewScene);
            window.show();

        }
        else{
//            if unvalid
            JOptionPane.showMessageDialog(null, "Invalid username or password");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../investor/SignIn.fxml"));
            Parent sampleViewParent = loader.load();
            Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();    //getting the previous paren scene
            window.setTitle("Hamari Car");
            window.setScene(passwordViewScene);
            window.show();
        }
    }
    @FXML
    void HelplinePressed(ActionEvent event) throws IOException{
//        when helpine pressed, create a scene for investor helpline
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/InvestorHelpline.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
    void helplinePressed(ActionEvent event) {
        try {
//            when helpline pressed, validate the username and id
            pst = objDB.setInvestHelpline();
            boolean alpha=false;
            pst.setString(1, query.getText());
            pst.setString(2, custname.getText());
            File myObj = new File("users.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String res[] = data.split(":");
//                validate
                if (res[0].compareTo(custname.getText()) == 0) {
                    alpha=true;
                    break;
                }
                else
                    continue;
            }
//            if valid
            if(alpha) {
                pst.execute();

                JOptionPane.showMessageDialog(null, "Details Entered successfully");
            }
            else{
//                if unvalid
                JOptionPane.showMessageDialog(null, "Customer does not exist. Enter Again");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }
    @FXML
    void investBackPressed(ActionEvent event) throws IOException{
//        when back pressed, build the scene for the investor view
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../investor/investorView.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }

    public void sample_view(ActionEvent event) throws IOException {
//        sample view for investor controller
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
}
