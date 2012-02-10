/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;

import Utilities.Constants;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author Administrator
 */
public class CustomerDBHelper {

    Connection con = null;
    Statement statement;

    public CustomerDBHelper() {
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

    public void saveCustomer(Customer customer) {
        String query = "";




        query = "insert into customer(first_name,last_name,address,tel_no,mobile_no,date_of_birth,date_of_joining,membershipid,gender,active,marketing_channel_id) "
                + "values('&firstname','&lastname','&address','&tel_no','&mobile_no','&dob','&doj','&membershipid','&gender','&active','&marketing_channel_id');";

        query = query.replace("&firstname", customer.getFirst_name());
        query = query.replace("&lastname", customer.getLast_name());
        query = query.replace("&address", customer.getAddress());
        query = query.replace("&tel_no", customer.getTel());
        query = query.replace("&mobile_no", customer.getMobile());
        query = query.replace("&dob", customer.getDob() + "");
        query = query.replace("&doj", customer.getDoj() + "");
        query = query.replace("&membershipid", customer.getMembership().getId() + "");
        query = query.replace("&gender", customer.getGender());
        query = query.replace("&active", customer.isActive()+"");
        query = query.replace("&marketing_channel_id", customer.getMarketChannel().getId() + "");
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Customer> getCustomers(String searchType, String searchBy) {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        String query = "select * from customer where &searchtype like '%&searchBy%'";
        Constants constants = new Constants();
        HashMap<String, String> map = constants.searchTypeToColumn;
        if (searchType.equalsIgnoreCase("All")) {
            query = "select * from customer";
        } else {
            query = query.replace("&searchtype", map.get(searchType));
            query = query.replace("&searchBy", searchBy);

        }
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Customer cust = new Customer();
                cust.setId(rs.getInt(1));
                cust.setFirst_name(rs.getString(2));
                cust.setLast_name(rs.getString(3));
                cust.setAddress(rs.getString(4));
                cust.setTel(rs.getString(5));
                cust.setMobile(rs.getString(6));
                cust.setDob(rs.getTimestamp(7));
                cust.setDoj(rs.getTimestamp(8));


                customerList.add(cust);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }



        return customerList;
    }

    public Customer getCustomer(int id){
        Customer cust = new Customer();
        String query ="select * from customer where id = '&id'";
        query = query.replace("&id", id+"");
        try {
            ResultSet rs = statement.executeQuery(query);
            if(rs.next()){
                cust.setId(rs.getInt(1));
                cust.setFirst_name(rs.getString(2));
                cust.setLast_name(rs.getString(3));
                cust.setAddress(rs.getString(4));
                cust.setTel(rs.getString(5));
                cust.setMobile(rs.getString(6));
                cust.setDob(rs.getTimestamp(7));
                cust.setDoj(rs.getTimestamp(8)); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return cust;
    }
  
    public static void main(String[] args) {
        Customer cust = new Customer();
        cust.setFirst_name("ankit");

        cust.setLast_name("kaushal");
        cust.setAddress("dwarka");
        cust.setDob(new Timestamp(new Date().getTime()));
        cust.setDoj(new Timestamp(new Date().getTime()));
        cust.setTel("25085435");
        cust.setMobile("9818536576");
      //  cust.setMembership("yearly");
        CustomerDBHelper dbh = new CustomerDBHelper();
        dbh.saveCustomer(cust);



    }
}
