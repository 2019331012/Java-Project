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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Change_Pin_Page_Controller {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Info info;
    String username, password;
    boolean i=false;
    
    void setInfo(Info info){
        
        this.info = info;
        username = info.getAcc();
        password = info.getPin();
    }
    
    

    @FXML
    private TextField newpin_textfield;

    @FXML
    private TextField oldpin_textfield;

    @FXML
    private TextField retypepin_textfield;
    
    Connection con;
    Statement st;
    PreparedStatement pst, pst2;
    ResultSet rs;
    
        @FXML
    void backtoProfile(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile_Page.fxml"));
            root = loader.load();
            Profile_Page_Controller pf = loader.getController();
            pf.setInfo(info);
            //root = FXMLLoader.load(getClass().getResource("Profile_Page.fxml"));
        } catch (IOException | ClassCastException ex) {
            Logger.getLogger(Withdraw_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pinChange(ActionEvent event) {
        
        String old = oldpin_textfield.getText();
        String newer = newpin_textfield.getText();
        String retype = retypepin_textfield.getText();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root", "");

            //String sql1 = "insert into Register_Page value ()";
            String sql2 = "select * from login"; // where username=? and password=?

            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
            rs = st.executeQuery(sql2);

            while (rs.next()) {

                String un = rs.getString("Account No.");
                String pw = rs.getString("Pin");
                if (username.equals(un) && password.equals(pw)) {
                    
                    if (!old.equals(pw)) {
                        
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Wrong Pin");
                        alert.setContentText("Your Have Typed The Wrong Pin");
                        alert.show();
                        return;
                    }
                    
                    if (newer.equals("") || retype.equals("")) {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("New Pin is Blank");
                        alert.setContentText("Please fill out your Pin approprietly");
                        alert.show();
                        return;
                    }

                    try {

                        int pin = Integer.parseInt(newer);
                        int conPin = Integer.parseInt(retype);

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
                    } else if (retype.length() < 4 || retype.length() > 8
                            || newer.length() < 4 || newer.length() > 8) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Pin must be 4-8 digits");
                        //alert.setContentText("Account No. or Pin incorrect");
                        alert.show();
                    } else if (!newer.equals(retype)) {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Wrong Pin");
                        alert.setContentText("The New Pins do not Match");
                        alert.show();
                    }
                    
                    else {
                        
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Final Confirmation");
                        alert.setHeaderText("Are you sure you want to Change the Pin");
                        alert.setContentText("Please Be Sure Before Changing");

                        if (alert.showAndWait().get() == ButtonType.OK) {

                            rs.updateString("Pin", newer);
                            rs.updateRow();
                            info.pin = newer;
                            try {
                                root = FXMLLoader.load(getClass().getResource("Login_Page.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(Profile_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    }

                    break;
                }
            }

            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Withdraw_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
