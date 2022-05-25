import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class Login_Page_Controller {

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
        else{
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/superpos", "root","");
                
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
