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

/**
 *
 * @author Asier
 */
public class DB {
    
    String recipeName;
    
    public DB () {
        
    }
    
    protected void setRecipeName (String recipeName) {
        this.recipeName = recipeName;
    }
    
    protected void accessDataBase () {
        Connection conn = null;
            try {
                    String userName = "root";
                    String passWord = "";
                    String dataBase = "test";

                    Class.forName("com.mysql.jdbc.Driver").newInstance();

                    conn = DriverManager.getConnection("jdbc:mysql:///"+dataBase, userName, passWord);

                    if (!conn.isClosed()) {
                            Statement s = conn.createStatement ( );
                            s.executeQuery ("SELECT * FROM recipe WHERE recipeName = '"+recipeName+"'");
                            ResultSet rs = s.getResultSet ( );
                            String query;
                            if(rs.first()) {
                                int points = rs.getInt("likes");
                                query = "UPDATE recipe SET likes="+(points+1)+" WHERE recipeName='"+recipeName+"'";
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
}
