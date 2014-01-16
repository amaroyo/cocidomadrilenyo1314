/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Asier
 */
public class DB {
    
    private String recipeName;
    
    private final String userName = "root";
    private final String passWord = "";
    private final String dataBase = "lookAndCook";

    
    public DB () {
        
    }
    
    protected void setRecipeName (String recipeName) {
        this.recipeName = recipeName;
    }
    
    protected void accessDataBase () {
        Connection conn = null;
            try {


                    Class.forName("com.mysql.jdbc.Driver").newInstance();

                    conn = DriverManager.getConnection("jdbc:mysql:///"+dataBase, userName, passWord);

                    if (!conn.isClosed()) {
                            Statement s = conn.createStatement ( );
                            s.executeQuery ("SELECT * FROM recipe WHERE recipeName = '"+recipeName+"'");
                            ResultSet rs = s.getResultSet ( );
                            String query;
                            if(rs.first()) {
                                int points = rs.getInt("points");
                                query = "UPDATE recipe SET points="+(points+1)+" WHERE recipeName='"+recipeName+"'";
                                s.executeUpdate(query);
                            } else {
                                query = "INSERT INTO recipe VALUES ('"+recipeName+"', "+(1)+")";
                                s.executeUpdate(query);
                            }
                            rs.close(); // close result set
                            s.close(); // close statement
                    }
            }
            catch (Exception e) {
                    System.err.println("Exception: " + e.getMessage());
            } finally {
                    try {
                        if (conn != null) {
                                conn.close();
                        }
                    } catch (SQLException e){ }
            }
    }
    
    protected ArrayList <String> getTops () {
        Connection conn = null;
        try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                conn = DriverManager.getConnection("jdbc:mysql:///"+dataBase, userName, passWord);
                ArrayList <String> tops = new ArrayList <> ();
                if (!conn.isClosed()) {
                        Statement s = conn.createStatement ( );
                        s.executeQuery ("SELECT * FROM recipe ORDER BY points DESC");
                        ResultSet rs = s.getResultSet ( );
                        int i = 0;
                        if(rs.next() && i != 10) {
                            tops.add(rs.getString("recipeName"));
                        }
                        rs.close(); // close result set
                        s.close(); // close statement
                }
                return tops;
        }
        catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
        } finally {
                try {
                    if (conn != null) {
                            conn.close();
                    }
                } catch (SQLException e){ }
        }
        return null;
    }
}
