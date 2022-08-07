import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register_Page2_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label acc_no_label;
    
    @FXML
    private Label warning_label;
    
    @FXML
    private TextField confirm_regpin_textfield;

    @FXML
    private TextField regpin_textfield;
    
    String acc;
    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    int pin,conPin;
    boolean i=false;
    
    void get_ac(String Ac){
        acc = Ac;
        acc_no_label.setText(Ac);
    }

    @FXML
    void pinCreate(ActionEvent event) {
        
        try{
            
            pin = Integer.parseInt(regpin_textfield.getText());
            conPin = Integer.parseInt(confirm_regpin_textfield.getText());
        
        } catch(NumberFormatException e){
            i = true;
        }
        catch(Exception e){
            i = true;
        }
        
        if(i){
            i = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pin must only be numbers");
        }
        else if(regpin_textfield.getText().length()<4 || regpin_textfield.getText().length()>8 
                || confirm_regpin_textfield.getText().length()<4 || confirm_regpin_textfield.getText().length()>8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pin must be 4-8 digits");
        }
        else if(regpin_textfield.getText().equals(confirm_regpin_textfield.getText())){
        
        try {
            
        Class.forName("com.mysql.jdbc.Driver");
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root","");
                
        String sql1 = "insert into login values (?,?)";
        //String sql2 = "select * from register"; // where username=? and password=?
                
        pst = con.prepareStatement(sql1);
        
        pst.setString(1, acc);
        pst.setString(2, confirm_regpin_textfield.getText()); 
        
        pst.executeUpdate();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your Pin has been set");
        
        try {
                root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Register_Page2_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Register_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The Pins Do Not Match");
        }

    }

}
