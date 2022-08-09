
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fahim Uz Zaman
 */
public class Test {
    
    Connection con, con2;
    PreparedStatement pst, pst2;
    Statement st, st2;
    ResultSet rs, rs2;
    boolean i = true;
    
    public void keen() throws SQLException{
        
        con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/depoverify", "root", "");
        st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); ///*ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY*/
        String sql = "select * from 500s";
        rs2 = st2.executeQuery(sql);
        
        while (rs2.next()) {
            
            String un = rs2.getString("Verification");
            //String pw = rs2.getString("100");
            System.out.println("Verify Codes : "+un+" || ");
        }
    }
    
    public static void main(String[] args){
        
        Test test = new Test();
        try {
            test.keen();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        AC_Gen obj = new AC_Gen();
        String tst = obj.gen("HY631.00000001");
        System.out.println("The coded message : "+tst+" || ");
    }
    

    
}
