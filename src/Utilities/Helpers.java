/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.swing.JComboBox;

/**
 *
 * @author Administrator
 */
public class Helpers {
    public static int findIndex(JComboBox combo,String value){
        
        for(int i =0;i<combo.getModel().getSize();i++){
           if(combo.getModel().getElementAt(i).toString().equals(value))
               return i;
        }
        
        return -1;
    }

}
