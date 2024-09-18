package BookCar;

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
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.PreparedStatement;
// following is book now controller class
// all of the code is strictly made from the concepts of software development and design and software
// engineering, hence there are controller classes which will communicate with the GUIs

public class BookNowController {

    private bookDB objDB = new bookDB();
    @FXML
    private TableView<Car> tableIsbCar;
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
    private TextField txtCarNo;


    ObservableList<Car> isbCarList = FXCollections.observableArrayList();
// this will show all the islamabad cars on the table
//
    public void showIsbCarList(){
// setting entires
        isbCarList = objDB.fetchIsbCarTable(isbCarList);
        colSector.setCellValueFactory((new PropertyValueFactory<>("sector")));
        colType.setCellValueFactory((new PropertyValueFactory<>("type")));
        colModel.setCellValueFactory((new PropertyValueFactory<>("model")));
        colNumber.setCellValueFactory((new PropertyValueFactory<>("number")));
        colColor.setCellValueFactory((new PropertyValueFactory<>("color")));
//setting table
        tableIsbCar.setItems(isbCarList);
    }

    PreparedStatement pst = null;

    @FXML
//    this function will be provoked whenever the book car now is pressed
//    the following steps will be taken after the button is pressed
    void bookCarNowPressed(ActionEvent event) {
        try {
            int toRet= objDB.fetchRidesTable();
//            checking if there already a entry in the current rides table
//            if yes, a dialogue will be displayed
            if(toRet>0){
                JOptionPane.showMessageDialog(null, "You already have a Booking!!");
            }
//            else that particular car will be booked
            else
            {
                pst = objDB.setBookTable(txtCarNo.getText());
                JOptionPane.showMessageDialog(null, "Car Booked successfully");
            }
//            in case of error, a dialogue will be printed
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        // add(event);
    }

    @FXML
//    whenever advanced booking is pressed, the fxml will be loaded, which will create a new resource
    void islamabadAdvPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../BookCar/carsIsbAdv.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        BookNowController obj = loader.getController();
        obj.showIsbCarList();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }

    @FXML
//    the scene will be build whenenever the advanced entires are supposed to shown, when prompted by clicking on them

    void islamabadPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../BookCar/carsIslamabad.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        BookNowController obj = loader.getController();
        obj.showIsbCarList();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }


    @FXML
//    whenever the back buttom is pressed, this function will be called, which will ensure that it goes back to
//    the book now scene
    void bookBackPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../BookCar/bookNow.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }

    @FXML
//    whenever the back button is pressed in the booking advanced controller, this will be taken into account and
//    a scene will be prompted

    void bookAdvBackPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../BookCar/bookAdv.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }

    @FXML
//    whenever back is pressed, this function is prompted, which will take you back to the customer controller .fxml
    void backPressed(ActionEvent event) throws IOException{
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../customer/customerController.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
//this is a sample view scene builder
    public void sample_view(ActionEvent event) throws IOException {
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
}
