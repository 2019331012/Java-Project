
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fahim Uz Zaman
 */
public class Forget_Pass_Page3 {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    Info info;
    String acc;
    Connection con;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    int pin,conPin;
    boolean i=false;
    
    void setInfo(Info info){
        
        this.info = info;
    }
    
    @FXML
    private PasswordField con_passwordfield;

    @FXML
    private PasswordField yes_passwordfield;

    @FXML
    void pinReset(MouseEvent event) {

        String a, b;
        a = yes_passwordfield.getText();
        b = con_passwordfield.getText();

        if (a.equals("") || b.equals("")) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pin is Blank");
            alert.setContentText("Please fill out your Pin approprietly");
            alert.show();
            return;
        }

        try {

            pin = Integer.parseInt(a);
            conPin = Integer.parseInt(b);

        } catch (NumberFormatException e) {
            i = true;
        } catch (Exception e) {
            i = true;
        }

        if (i) {
            i = false;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pin must only be numbers");
            //alert.setContentText("Account No. or Pin incorrect");
            alert.show();
        } else if (yes_passwordfield.getText().length() < 4 || yes_passwordfield.getText().length() > 8
                || con_passwordfield.getText().length() < 4 || con_passwordfield.getText().length() > 8) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Pin must be 4-8 digits");
            //alert.setContentText("Account No. or Pin incorrect");
            alert.show();
        } else if (yes_passwordfield.getText().equals(con_passwordfield.getText())) {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root", "");

                //String sql1 = "insert into login values (?,?,?)";
                String sql2 = "select * from login"; // where username=? and password=?

                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery(sql2);

                while (rs.next()) {

                    String un = rs.getString("Account No.");
                    String pw = rs.getString("Pin");
                    if (info.acc.equals(un)) {

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Final Confirmation");
                        alert.setHeaderText("Are you sure you want to Change the Pin");
                        alert.setContentText("Please Be Sure Before Changing");

                        if (alert.showAndWait().get() == ButtonType.OK) {

                            rs.updateString("Pin", a);
                            rs.updateRow();
                            info.pin = a;
                            Alert alert2 = new Alert(AlertType.INFORMATION);
                            alert2.setTitle("Success");
                            alert2.setHeaderText("Your Pin has been Reset");
                            alert2.setContentText("Keep Staying With Us");
                            alert2.show();
                            try {
                                root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(Forget_Pass_Page3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }

                        break;
                    }
                }

                con.close();

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Withdraw_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else{
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The Pins Do Not Match");
            //alert.setContentText("Account No. or Pin incorrect");
            alert.show();
        }

    }
    
}
