/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Membership;

/**
 *
 * @author Administrator
 */
public class MemDBHelper {

    Connection con = null;
    Statement statement;

    public MemDBHelper() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:infogym.s3db");

            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Membership[] getMemberships() {

        Membership[] memList = new Membership[25];
        int count = 0;
        String query = "select * from membership";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Membership mem = new Membership();
                mem.setId(rs.getInt(1));
                mem.setName(rs.getString(2));
                mem.setAmount(rs.getInt(3));
                mem.setDays(rs.getInt(4));
                mem.setMonths(rs.getInt(5));
                mem.setYears(rs.getInt(6));
                memList[count++]= mem;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        Membership[] newList = new Membership[count];
        System.arraycopy(memList, 0, newList, 0, count);
        return newList;
    }
    public static void main(String args[]){
       
        for(Membership m:new MemDBHelper().getMemberships()){
            System.out.println(m);
        }
    }
}
