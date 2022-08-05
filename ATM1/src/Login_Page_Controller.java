import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Login_Page_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password_passwordfield;

    @FXML
    private Label register_label;

    @FXML
    private TextField username_textfield;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void login(MouseEvent event) {
        
        String username = username_textfield.getText();
        String password = password_passwordfield.getText();
        
        if(username.equals("") && password.equals("")){
            
            JOptionPane.showMessageDialog(null, "Please fill out your Acc. No. and Pin approprietly");
        }
        else if(username.equals("1234567890") && password.equals("1234567890")) {
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
        else{
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root","colourfulJellyBean012058");
                
                pst = con.prepareStatement("select * from cashier where username=? and password=?");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void register(MouseEvent event) {

    }

}
