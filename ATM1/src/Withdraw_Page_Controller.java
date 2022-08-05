import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Withdraw_Page_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Label balance_label;

    @FXML
    private TextField withdraw_textfield;

    @FXML
    private Label withdraw_warning_label;

    @FXML
    void backtoProfile(MouseEvent event) {
        try {
                root = FXMLLoader.load(getClass().getResource("Profile_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void withdrawConfirm(MouseEvent event) {

    }

}
