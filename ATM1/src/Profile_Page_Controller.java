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
    
    String bal;
    Info info;
    
    void setInfo(Info info){
        
        this.info = info;
//        info.setInfo(bal);
    }


    @FXML
    void changePin(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Change_Pin_Page.fxml"));
                root = loader.load();
                Change_Pin_Page_Controller obj = loader.getController();
                obj.setInfo(info);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Deposit_Page.fxml"));
                root = loader.load();
                Deposit_Page_Controller obj = loader.getController();
                obj.setInfo(info);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FastCash_Page.fxml"));
                root = loader.load();
                FastCash_Page_Controller obj = loader.getController();
                obj.setInfo(info);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Account_Balance_Page.fxml"));
                root = loader.load();
                Account_Balance_Page_Controller acc = loader.getController();
                acc.setInfo(info);
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
//        try {
//                root = FXMLLoader.load(getClass().getResource("Withdraw_Page.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Withdraw_Page.fxml"));
                root = loader.load();
                Withdraw_Page_Controller obj = loader.getController();
                obj.setInfo(info);
            } catch (IOException ex) {
                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
