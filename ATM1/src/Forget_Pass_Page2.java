
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
public class Forget_Pass_Page2 {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Info info;
    
    Connection con;
    //PreparedStatement pst;
    Statement st,st2;
    ResultSet rs;
    boolean i = true;
    
    void setInfo(Info info){
        
        this.info = info;
    }
    
    @FXML
    private TextField verify_textfield;

    @FXML
    void fpass3(MouseEvent event) {
        
        String vf = verify_textfield.getText();
        try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/depoverify", "root","");
                
                //String sql1 = "insert into Register_Page value ()";
                        
                String sql2 = "select * from forgetpass"; // where username=? and password=?
                
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);     ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
                //pst = con.prepareStatement(sql2);
                
                rs = st.executeQuery(sql2);
                
                while(rs.next()){
                    
                    String un = rs.getString("Verification");
                    
                    if (vf.equals(un)){
                        
                        i = false;
                        rs.deleteRow();

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("forget_pass3.fxml"));
                            root = loader.load();
                            Forget_Pass_Page3 obj = loader.getController();
                            obj.setInfo(info);
                        } catch (IOException ex) {
                            Logger.getLogger(Forget_Pass_Page2.class.getName()).log(Level.SEVERE, null, ex);
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
                    alert.setHeaderText("Verification incorrect");
                    alert.setContentText("Please Type the Correct Verification Code");
                    alert.show();
                    //JOptionPane.showMessageDialog(null, "Account No. or Pin incorrect");
                }
                
                con.close();
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Forget_Pass_Page2.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    
}
