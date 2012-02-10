/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class NewClass {
    public static void main(String args[]){
        Calendar cal = Calendar.getInstance();
        
        cal.set(2000, 11, 15);
         System.out.println(cal.getTime());
        cal.add(Calendar.YEAR,1);
        cal.add(Calendar.MONTH,0);
        cal.add(Calendar.DATE,0);
        System.out.println(cal.getTime());
        
        System.out.println(cal.getTime().getYear()+1900);
        System.out.println(cal.getTime().getMonth()+1);
        System.out.println(cal.getTime().getDate());
    }
}
