/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fahim Uz Zaman
 */
class AC_Gen {
    
    int k = 0;
    
    String gen(String un){
        
        if(un.isEmpty()){
            return "HY631.00000001";
        }
        String s = un.substring(6);
        k = Integer.parseInt(s);
        k++;
        s = String.valueOf(k);
        for(int i=s.length(); i<8; i++){
            s = "0"+s;
        }
        un = "HY631."+s;
        return un;
    }
    
}
