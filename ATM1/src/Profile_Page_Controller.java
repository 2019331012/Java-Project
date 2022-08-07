import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile_Page_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void changePin(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Change_Pin_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void deposit(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Deposit_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fastcash(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("FastCash_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logout(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showBalance(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Account_Balance_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void withdraw(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Withdraw_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
