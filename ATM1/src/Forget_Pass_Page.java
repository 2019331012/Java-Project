
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
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
public class Forget_Pass_Page {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Info info = new Info();
            
    Connection con;
    //PreparedStatement pst;
    Statement st;
    ResultSet rs;
    boolean i = true;

    @FXML
    private TextField acc_textfield;

    @FXML
    void fpass2(MouseEvent event){
        
        String acc = acc_textfield.getText();
        info.setInfo("", acc, "");
        
        if(acc.equals("")){
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Acc. No. Blank");
            alert.setContentText("Please fill out your Acc. No. approprietly");
            alert.show();
            //JOptionPane.showMessageDialog(null, "Please fill out your Acc. No. and Pin approprietly");
        }
        
        else {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root", "");

                //String sql1 = "insert into Register_Page value ()";
                String sql2 = "select * from login"; // where username=? and password=?

                st = con.createStatement();     ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
                //pst = con.prepareStatement(sql2);

                rs = st.executeQuery(sql2);

                while (rs.next()) {

                    String un = rs.getString("Account No.");

                    if (acc.equals(un)) {

                        i = false;
                        Verify_Gen obj = new Verify_Gen();
                        obj.gen();
                        String pw = rs.getString("Pin");
                        info.setInfo("", acc, pw);

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("forget_pass2.fxml"));
                            root = loader.load();
                            Forget_Pass_Page2 obj2 = loader.getController();
                            obj2.setInfo(info);
                        } catch (IOException ex) {
                            Logger.getLogger(Forget_Pass_Page.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        break;
                    }
                    
                } 
                
                if(i){          //!rs.next() should not be used cause if its the last case(rs.last()) in
                    
                    Alert alert = new Alert(AlertType.ERROR);        // the while loop then it will be incorrect in if
                    alert.setTitle("Incorrect input");           // statement.
                    alert.setHeaderText("Account No. incorrect");
                    alert.setContentText("There is no Such Account No. in our Registry");
                    alert.show();
                    //JOptionPane.showMessageDialog(null, "Account No. or Pin incorrect");
                }
                con.close();
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Forget_Pass_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
