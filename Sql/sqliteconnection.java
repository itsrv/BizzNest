/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sql;

import java.sql.*;

public class sqliteconnection {
    
    public static Connection Connector(){
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            return conn;
        } 
        catch(Exception e) {
            System.out.println(e);
            return null;
   
        }
    }
}


