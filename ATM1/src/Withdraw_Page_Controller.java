import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Withdraw_Page_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Info info;
    boolean i = false;
    String username, password,bal;
 //   String sql = "update login SET Deposit = '";
    int minDepo = 0;

    Connection con;
    Statement st;
    PreparedStatement pst, pst2;
    ResultSet rs;

    void setInfo(Info info) {

        this.info = info;

        balance_label.setText(info.getBalance());
        username = info.getAcc();
        password = info.getPin();
    }

    @FXML
    private Label balance_label;

    @FXML
    private TextField withdraw_textfield;

    @FXML
    private Label withdraw_warning_label;

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
    void withdrawConfirm(MouseEvent event) throws IOException {

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

                    bal = rs.getString("Deposit");
                    
                    int curBal=0,withdraw=0,amountLeft=0;
                    try{
                        curBal = Integer.parseInt(bal);
                        withdraw = Integer.parseInt(withdraw_textfield.getText());
                    }catch(NumberFormatException e){
                        i = true;
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Withdraw Amount Can only be numbers");
                        //alert.setContentText("Account No. or Pin incorrect");
                        alert.show();
                        return;
                    }
                    catch(Exception e){
                        i = true;
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Withdraw Amount Can only be numbers");
                        //alert.setContentText("Account No. or Pin incorrect");
                        alert.show();
                        return;
                    }
                    
                    if(withdraw<=0){
                        
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Withdraw Amount Can not be Smaller than or Equal to Zero");
                        //alert.setContentText("Account No. or Pin incorrect");
                        alert.show();
                        return;
                    }
                    
                    amountLeft = curBal - withdraw;

                    if (amountLeft >= minDepo) {
                        
                        withdraw_warning_label.setText("");
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Final Confirmation");
                        alert.setHeaderText("Are you sure you want to Withdraw this Amount");
                        alert.setContentText("Please Be Sure Before Withdrawing");

                        if (alert.showAndWait().get() == ButtonType.OK) {
                            
                            rs.updateString("Deposit", Integer.toString(amountLeft));
                            rs.updateRow();
                            info.bal = Integer.toString(amountLeft);
                            bal = Integer.toString(amountLeft);
                            balance_label.setText(info.getBalance());
                        }
                        
                        
                        
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Insufficient Balance");
                        alert.setContentText("Your Account Does Not Have Sufficient Balance to Withdraw This Amount");
                        alert.show();
                        withdraw_warning_label.setText("Your Account currently does not have sufficient balance for the Withdrawal !!");
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
