package manager;

import BookCar.BookNowController;
import BookCar.Car;
import BookCar.bookDB;
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
import java.io.IOException;
import java.sql.PreparedStatement;

//class for manager controller and all its attributes
public class ManagerController {
    private managerDB objDB = new managerDB();
    @FXML
    private TableView<Manager> tableManager;
    @FXML
    private TableView<ManagerHelpline> tableHelpline;
    @FXML
    private TableColumn<ManagerHelpline, String> colName;
    @FXML
    private TableColumn<ManagerHelpline, String> colQuery;
    @FXML
    private TableColumn<Manager, Integer> colEarning;

    ObservableList<Manager> earningList = FXCollections.observableArrayList();
    public void showEarning(){
//get instance of db object
//add values
//set table
        earningList = objDB.fetchEarnings(earningList);
        colEarning.setCellValueFactory((new PropertyValueFactory<>("earning")));
        tableManager.setItems(earningList);
    }
    ObservableList<ManagerHelpline> queryList = FXCollections.observableArrayList();
    public void showQuery(){
//get instance of db object
//add values
//set table

        queryList = objDB.fetchHelpline(queryList);
        colName.setCellValueFactory((new PropertyValueFactory<>("name")));
        colQuery.setCellValueFactory((new PropertyValueFactory<>("queryy")));
        tableHelpline.setItems(queryList);
    }
    @FXML
    void checkEarningsPressed(ActionEvent event) throws IOException{
//        when earning presssed, build a scene for earning view
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../manager/earningView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        ManagerController obj = loader.getController();
        obj.showEarning();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    @FXML
    void managerBackPressed(ActionEvent event) throws IOException{
//        when back pressed, build a scene for manager view
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../manager/managerView.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    public void sample_view(ActionEvent event) throws IOException {
//        sample view builder for manager controller
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    @FXML
    void queryPressed(ActionEvent event) throws IOException{
//        when pressed build a scene for query view
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../manager/queryView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        ManagerController obj = loader.getController();
        obj.showQuery();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }

}
