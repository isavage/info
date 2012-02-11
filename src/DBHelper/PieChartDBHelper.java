/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Administrator
 */
public final class PieChartDBHelper {

    Connection con = null;
    Statement statement;

    public PieChartDBHelper() {
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

    public int[] getMarketInternet() {
        
        int[] marketResult=new int[3];


        String query1 = "select count(*) from Customer where marketing_channel_id=1;";
        String query2 = "select count(*) from Customer where marketing_channel_id=2;";
        String query3 = "select count(*) from Customer where marketing_channel_id is null;";
        
               
        try {
            ResultSet rs1=statement.executeQuery(query1);
            while (rs1.next())
                marketResult[0]=rs1.getInt(1);
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            ResultSet rs2=statement.executeQuery(query2);
            while (rs2.next())
                marketResult[1]=rs2.getInt(1);
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            ResultSet rs3=statement.executeQuery(query3);
            while (rs3.next())
                marketResult[2]=rs3.getInt(1);
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            return marketResult;
            
        
    }

   
}
