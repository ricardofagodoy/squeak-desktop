/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package squeak.desktop.dao;

import squeak.desktop.model.*;

/**
 *
 * @author ricardo
 */
public class BDFactory {
    
    private static BD bd = null;
    
    private BDFactory(){}
    
    public static BD getInstance() {
        
        if(bd == null) {
            
           try {
                bd = new BD("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/squeak", "root", "root");
           } catch(Exception e) {              
           }
        }
        
        return bd;
    }
}
