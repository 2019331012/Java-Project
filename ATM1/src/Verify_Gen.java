
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fahim Uz Zaman
 */
public class Verify_Gen {
    
    Connection con;
    PreparedStatement pst;
    Statement st,st2;
    ResultSet rs;
    boolean i = true;
    
    int k = 0;
    
    void gen(){
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/depoverify", "root","");
                
                //String sql1 = "insert into Register_Page value ()";
                        
                String sql2 = "select * from forgetpass"; // where username=? and password=?
                
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);     ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
                //pst = con.prepareStatement(sql2);
                
                rs = st.executeQuery(sql2);
                
                String last="";
                
                if(rs.last()){
                    last = rs.getString("Verification");
                }
                    
                Verify_Gen obj = new Verify_Gen();
                String vf = obj.gen2(last);
                
                String sql = "insert into forgetpass values (?)" ;
                
                pst = con.prepareStatement(sql);
                
                pst.setString(1, vf);
                
                pst.executeUpdate();
                
                con.close();
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Verify_Gen.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }
    
    String gen2(String un){
        
        if (un.isEmpty()) {
            return "NewVerify00000001";
        }
        String s = un.substring(9);
        k = Integer.parseInt(s);
        k++;
        s = String.valueOf(k);
        for (int i = s.length(); i < 8; i++) {
            s = "0" + s;
        }
        un = "NewVerify" + s;
        return un;
    }
    
}
