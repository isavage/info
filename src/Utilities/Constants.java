/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Constants {
    public  HashMap<String,String> searchTypeToColumn =new HashMap<String, String>();

    public Constants() {
        searchTypeToColumn.put("First Name", "first_name");
        searchTypeToColumn.put("Last Name", "last_name");
        searchTypeToColumn.put("Address", "address");
        searchTypeToColumn.put("Telephone No", "tel_no");
        searchTypeToColumn.put("Mobile No", "mobile_no");
       
        
    }
    
}
