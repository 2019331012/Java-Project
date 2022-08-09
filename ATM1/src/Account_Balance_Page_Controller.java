
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Account_Balance_Page_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    String bal;
    Info info;

//    Connection con;
//    //PreparedStatement pst;
//    Statement st;
//    ResultSet rs;
//    boolean i=true;
    void setInfo(Info info) {
        this.info = info;
        
        balance_label.setText(info.getBalance());
    }
    
    @FXML
    private Label balance_label;
    
    @FXML
    void backtoProfile(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_Page.fxml"));
            root = loader.load();
            Profile_Page_Controller pf = loader.getController();
            pf.setInfo(info);
            //root = FXMLLoader.load(getClass().getResource("Profile_Page.fxml"));
        } catch (IOException | ClassCastException ex) {
            Logger.getLogger(Account_Balance_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoWithdraw(ActionEvent event) {
        
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Withdraw_Page.fxml"));
                root = loader.load();
                Withdraw_Page_Controller obj = loader.getController();
                obj.setInfo(info);
            } catch (IOException | ClassCastException ex) {
                Logger.getLogger(Account_Balance_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
