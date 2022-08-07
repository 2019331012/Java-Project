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
            return "1";
        }
        
        k = Integer.parseInt(un);
        k++;
        un = String.valueOf(k);
        return un;
    }
    
}
