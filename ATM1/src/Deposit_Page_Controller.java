import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Deposit_Page_Controller {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Info info;
    String username, password,bal,authenticate;
    
    Connection con,con2;
    PreparedStatement pst,pst2;
    Statement st,st2;
    ResultSet rs,rs2;
    boolean i=true;

    
    void setInfo(Info info){
        
        this.info = info;
        username = info.getAcc();
        password = info.getPin();
    }

    @FXML
    private TextField deposit_amount_textfield;

    @FXML
    private TextField deposit_confirm_textfield;

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
    void confirmDeposit(ActionEvent event) throws SQLException {
        
        i = true;
        String depo = deposit_amount_textfield.getText();
        String verify = deposit_confirm_textfield.getText();
        
        int curBal=0,deposit=0,amountLeft=0;
                    try{
                        deposit = Integer.parseInt(depo);
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
        
        if (Integer.parseInt(depo) % 500 != 0) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Deposit Amount Must be 500 or it's Multiples");
            alert.setContentText("Please Deposit Accordingly");
            alert.show();
            return;
        }
        
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
                    
                    try{
                        curBal = Integer.parseInt(bal);
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
                    
                    if(deposit<=0){
                        
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Deposit Amount Can not be Smaller than Zero");
                        //alert.setContentText("Account No. or Pin incorrect");
                        alert.show();
                        return;
                    }
                    
                    amountLeft = curBal + deposit;

                    String sql = "select * from "+depo+"s" ;

                    con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/depoverify", "root", "");
                    st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
                    rs2 = st2.executeQuery(sql);

                    while (rs2.next()) {

                        authenticate = rs2.getString("Verification");

                        if (authenticate.equals(verify)) {
                            
                            i = false;
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Final Confirmation");
                            alert.setHeaderText("Are you sure you want to Deposit this Amount");
                            alert.setContentText("Please Be Sure Before Depositing");

                            if (alert.showAndWait().get() == ButtonType.OK) {

                                rs.updateString("Deposit", Integer.toString(amountLeft));
                                rs.updateRow();
                                rs2.deleteRow();
                                info.bal = Integer.toString(amountLeft);
                                bal = Integer.toString(amountLeft);
                                
                            }
                            break;
                        }

                    }

                    if (i) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Code Incorrect");
                        alert.setContentText("Code Incorrect, Your Deposit was not Made");
                        alert.show();
                    }
                    break;
                }
            }

            con.close();
            con2.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Code Incorrect");
            alert.setContentText("Code Incorrect, Your Deposit was not Made");
            alert.show();
            Logger.getLogger(Withdraw_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
