/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Login;


/**
 *
 * @author Administrator
 */
public final class LoginDBHelper {

    Connection con = null;
    Statement statement;

    public LoginDBHelper() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:infogym.s3db");

            statement = con.createStatement();

            

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        createLoginDB();
    }

    public void addAdmin(Login login) {
        String query = "";




        query = "insert into login2(username,password) "
                + "values('&username','&password');";

        query = query.replace("&username", login.getUsername());
        query = query.replace("&password", login.getPassword());
        
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                statement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDBHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean validateLogin(Login login) {

        
        String query = "select * from login2 where username='&username' and password='&password';";

        
            query = query.replace("&username", login.getUsername());
            query = query.replace("&password", login.getPassword());
System.out.println(query);
         try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
                    return true;
                
            }
         
         catch (SQLException ex) {
                         Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            try {
                statement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDBHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return false;
    }

    public void createLoginDB() {

        String query = "";
        String query1 = "";
        String query2 = "";


        query= "select * from login2;";
        try {
            statement.execute(query);
                     }
         catch (SQLException ex) {
            query1 = "create table login2(username varchar2(10),password varchar2(10));";
            query2 = "insert into login2 values ('admin','admin');";
            try {
            statement.execute(query1);
            statement.execute(query2);
             }
            catch (SQLException ex1) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex1);
            }
          finally{
            try {
                statement.close();
                con.close();
            } catch (SQLException ex2) {
                Logger.getLogger(LoginDBHelper.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }
        }


    }

    
}
