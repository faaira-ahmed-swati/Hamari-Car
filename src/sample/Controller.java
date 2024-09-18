package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;

public class Controller {

    public void submitCustPressed(ActionEvent event) throws IOException {
//when submit customer is pressed, open to sign in
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/SignIn.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void submitCust1Pressed(ActionEvent event) throws IOException {
//when sign up is pressed build a scene for sign up fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../customer/SignUp.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void submitInvestPressed(ActionEvent event) throws IOException {
//when investor is pressed, open investor sign up
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/SignUp.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void submitInvestPressed1(ActionEvent event) throws IOException {
//when sign in for investor is pressed, build a scene for sign in for investor
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../investor/SignIn.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void submitManagerPressed(ActionEvent event) throws IOException {
//when manager is pressed, open manager view
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../manager/managerView.fxml"));

        Parent sampleViewParent = loader.load();
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void passwordCustomer(ActionEvent event) throws IOException {
//        get the password view when prompted
        Parent sampleViewParent = FXMLLoader.load(getClass().getResource("PasswordView.fxml"));
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void passwordManager(ActionEvent event) throws IOException {
//        get the manager password view when prompted
        Parent sampleViewParent = FXMLLoader.load(getClass().getResource("passwordViewManager.fxml"));
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }
    public void passwordInvestor(ActionEvent event) throws IOException {
        //        get the investor password view when prompted
        Parent sampleViewParent = FXMLLoader.load(getClass().getResource("passwordViewInvestor.fxml"));
        Scene passwordViewScene = new Scene(sampleViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(passwordViewScene);
        window.show();
    }

    public void sample_view(ActionEvent event) throws IOException {
//sample view for the controller class
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
    public void main_pressed(ActionEvent event) throws IOException {
//sample view for the controller class
        Parent passwordViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene sampleViewScene = new Scene(passwordViewParent, 900, 600);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();    //getting the previous paren scene
        window.setTitle("Hamari Car");
        window.setScene(sampleViewScene);
        window.show();

    }
}
