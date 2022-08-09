
import javafx.scene.Parent;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fahim Uz Zaman
 */
public class Info {
    
    String bal,acc,pin;
    
//    Info(Info obj){
//        
//        this = obj;
//    }
    
    void setInfo(String bal, String acc, String pin) {
        
        this.bal = bal;
        this.acc = acc;
        this.pin = pin;
    }
    
    String getBalance(){
        String s = "BDT " + bal + " TK";
        return s;
    }
    
    String getAcc(){
        return acc;
    }
    
    String getPin(){
        return pin;
    }
}
