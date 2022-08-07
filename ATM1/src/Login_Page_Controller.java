import java.sql.ResultSet;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    //PreparedStatement pst;
    Statement st;
    ResultSet rs;
    boolean i=true;

    @FXML
    void login(MouseEvent event) {
        
        String username = username_textfield.getText();
        String password = password_passwordfield.getText();
        
        if(username.equals("") || password.equals("")){
            
            //Alert alert = new Alert(AlertType.ERROR);
            //alert.setTitle("Please fill out your Acc. No. and Pin approprietly");
            JOptionPane.showMessageDialog(null, "Please fill out your Acc. No. and Pin approprietly");
        }
//        else if(username.equals("1234567890") && password.equals("1234567890")) {
//            try {
//                root = FXMLLoader.load(getClass().getResource("Profile_Page.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
        else{
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root","");
                
                //String sql1 = "insert into Register_Page value ()";
                        
                String sql2 = "select * from login"; // where username=? and password=?
                
                st = con.createStatement();
                //pst = con.prepareStatement(sql2);
                
                rs = st.executeQuery(sql2);
                
                while(rs.next()){
                    
                    String un = rs.getString("Account No.");
                    String pw = rs.getString("Pin");
                    if(username.equals(un)){
                        
                        i=false;
                        if(password.equals(pw)){
            
                        //JOptionPane.showMessageDialog(null, "Please fill out your Acc. No. and Pin approprietly");
                        
                        try {
                                root = FXMLLoader.load(getClass().getResource("Profile_Page.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        //break;
                        }
                        
                        else{
                        
                        //Alert alert = new Alert(AlertType.ERROR);
                        //alert.setTitle("Account No. or Pin incorrect");
                        JOptionPane.showMessageDialog(null, "Account No. or Pin incorrect");
                        //break;
                        }
                    } 
                    break;
                }
                if(i /*!rs.next() */){          //!rs.next() should not be used cause if its the last case(rs.last()) in
                    //Alert alert = new Alert(AlertType.ERROR);    // the while loop then it will be incorrect in if
                    //alert.setTitle("Account No. or Pin incorrect");     // statement.
                    JOptionPane.showMessageDialog(null, "Account No. or Pin incorrect");
                }
                con.close();
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void register(MouseEvent event) {
        
        try {
                root = FXMLLoader.load(getClass().getResource("test2.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Login_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
