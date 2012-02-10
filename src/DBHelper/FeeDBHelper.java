/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;

import infogym.FeeForm;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Fee;

/**
 *
 * @author Administrator
 */
public class FeeDBHelper {

    Connection con = null;
    Statement statement;

    public FeeDBHelper() {
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

    public boolean saveFees(Fee fee) {

        String query = "insert into fee(start_date,end_date,amount,customerid) values ('&start_date','&end_date','&amount','&customerid')";

        query = query.replace("&start_date", fee.getStart_date() + "");
        query = query.replace("&end_date", fee.getEnd_date() + "");
        query = query.replace("&amount", fee.getAmount() + "");
        query = query.replace("&customerid", fee.getCustomer().getId() + "");
      

        String customerQuery = "update  customer set membershipid = '&membershipid' where id = '&id'";
        customerQuery = customerQuery.replace("&membershipid", fee.getMembership().getId()+"");
        customerQuery = customerQuery.replace("&id", fee.getCustomer().getId()+"");
        
        System.out.println(customerQuery);
        try {
            statement.execute(query);
            statement.execute(customerQuery);
        } catch (SQLException ex) {
            Logger.getLogger(FeeDBHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public ArrayList<Fee> getLateNotifications() {
       ArrayList<Fee> lateList = new ArrayList<Fee>();
       
        try {
           
            String query = " select id,first_name,last_name,max(due_date) from fee,customer "
                    + "where id = customerid  group by id   having max(due_date)<current_timestamp";

            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
           Fee fee = new Fee();
           
           
           
           Customer customer = new Customer();
           customer.setId(rs.getInt(1));
           customer.setFirst_name(rs.getString(2));
           customer.setLast_name(rs.getString(3));
           
           
           
          
           lateList.add(fee);
            }


        } catch (SQLException ex) {
            Logger.getLogger(FeeDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lateList;
    }

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setId(10);
        Fee fee = new Fee();
        fee.setAmount(1000);
        fee.setCustomer(customer);
        fee.setStart_date(new Timestamp(10, 10, 10, 0, 0, 0, 0));
        fee.setEnd_date(new Timestamp(30, 10, 10, 0, 0, 0, 0));
       

        FeeDBHelper helper = new FeeDBHelper();
        helper.saveFees(fee);
        
    }
}
