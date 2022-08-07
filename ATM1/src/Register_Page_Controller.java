import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Register_Page_Controller {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField email_textfield;

    @FXML
    private TextField name_textfield;

    @FXML
    private TextField nid_textfield;

    @FXML
    private TextField nominee_email_textfield;

    @FXML
    private TextField nominee_name_textfield;

    @FXML
    private TextField nominee_nid_textfield;

    @FXML
    private TextField nominee_phone_textfield;

    @FXML
    private TextField nominee_relations_textfield;

    @FXML
    private TextField phone_textfield;

    @FXML
    private TextField sourceofincome_textfield;
    
     @FXML
    private TextField nominee_sharepercentage_textfield;
    
    Connection con;
    Statement st,st2;
    PreparedStatement pst,pst2;
    ResultSet rs,rs2;
    String Ac;

    
    @FXML
    void register(MouseEvent event) {
        
        
        try {
            
        Class.forName("com.mysql.jdbc.Driver");
            
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "root","");
                
        String sql1 = "insert into register values (?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "select * from register"; // where username=? and password=?
        
        st = con.createStatement();
        rs = st.executeQuery(sql2);
        if(rs.last()){
        String un = rs.getString("Account No.");
        AC_Gen ac = new AC_Gen();
        Ac = ac.gen(un);
        }
        else{
            Ac = "1";
        }
                
        pst = con.prepareStatement(sql1);
        
        pst.setString(1, name_textfield.getText());
        pst.setString(2, nid_textfield.getText());
        pst.setString(3, phone_textfield.getText());
        pst.setString(4, email_textfield.getText());
        pst.setString(5, sourceofincome_textfield.getText());
        pst.setString(6, nominee_name_textfield.getText());
        pst.setString(7, nominee_nid_textfield.getText());
        pst.setString(8, nominee_phone_textfield.getText());
        pst.setString(9, nominee_email_textfield.getText());
        pst.setString(10, nominee_relations_textfield.getText());
        pst.setString(11, nominee_sharepercentage_textfield.getText());
        pst.setString(12, Ac);
        
        pst.executeUpdate();
        
        try {
                FXMLLoader loader = FXMLLoader.load(getClass().getResource("Register_Page2.fxml"));
                root = loader.load();
                Register_Page2_Controller rp2 = loader.getController();
                rp2.get_ac(Ac);
                //root = FXMLLoader.load(getClass().getResource("Register_Page2.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Register_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Register_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }


}
