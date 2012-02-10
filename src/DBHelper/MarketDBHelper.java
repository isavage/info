/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBHelper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MarketChannel;
import model.Membership;

/**
 *
 * @author Administrator
 */
public class MarketDBHelper {
    
    Connection con = null;
    Statement statement;

    public MarketDBHelper() {
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
   
     public MarketChannel[] getMarketChannels() {

        MarketChannel[] marketList = new MarketChannel[25];
        int count = 0;
        String query = "select * from market_channels";
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                MarketChannel market = new MarketChannel();
                market.setId(rs.getInt(1));
                market.setName(rs.getString(2));
               
                marketList[count++]= market;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        MarketChannel[] newList = new MarketChannel[count];
        System.arraycopy(marketList, 0, newList, 0, count);
        return newList;
    }
    public static void main(String args[]){
       
        for(MarketChannel m:new MarketDBHelper().getMarketChannels()){
            System.out.println(m);
        }
    }
    
}
