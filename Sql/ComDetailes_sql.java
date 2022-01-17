/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rohan
 */
public class ComDetailes_sql {
    Connection connection;
    
    public ComDetailes_sql() throws SQLException{
        connection = sqliteconnection.Connector();
        
        if(connection == null){
            System.out.println("Connection not Successful");
            System.exit(1);
        }
        
    }
    
  
    
    //Saving Company Detailes
    public void save_com_Detailes(String Com_name,String Address,String City,String State,
            String Pincode,String phoneno,String Email,String WebSite,String GstIn,String Pan,String Tin,String Additional) throws SQLException{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "Insert into CompanyDetailes values(?,?,?,?,?,?,?,?,?,?,?,?)";
        create_query = connection.prepareStatement(query);
                create_query.setString(1, Com_name);
                create_query.setString(2, Address);
                create_query.setString(3, City);
                create_query.setString(4, State);
                create_query.setString(5, Pincode);
                create_query.setString(6, phoneno);
                create_query.setString(7, Email);
                create_query.setString(8, WebSite);
                create_query.setString(9, GstIn);
                create_query.setString(10, Pan);
                create_query.setString(11, Tin);
                create_query.setString(12, Additional);
                create_query.executeUpdate();
    }
    //Modefy company Detailes
    public void Modify_com_Detailes(String Com_name,String Address,String City,String State,
            String Pincode,String phoneno,String Email,String WebSite,String GstIn,String Pan,String Tin,String Additional) throws SQLException{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "update CompanyDetailes set Name = ?,Address = ?,City =?,State=?,Pin_Code=?,Phone_no=?,Email_Id=?,Website =?,GstIn = ?,Pan=?,Tin=?,Additional_Detailes=?";
        create_query = connection.prepareStatement(query);
                create_query.setString(1, Com_name);
                create_query.setString(2, Address);
                create_query.setString(3, City);
                create_query.setString(4, State);
                create_query.setString(5, Pincode);
                create_query.setString(6, phoneno);
                create_query.setString(7, Email);
                create_query.setString(8, WebSite);
                create_query.setString(9, GstIn);
                create_query.setString(10, Pan);
                create_query.setString(11, Tin);
                create_query.setString(12, Additional);
                create_query.executeUpdate();
    }
    
    public String get_company_detailes() throws SQLException{
        PreparedStatement pre;
            String query = "select * from CompanyDetailes";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            String r = null;
            
            
            while (rs.next()){
                r = rs.getString("Name");
            }
            return r;
            
    }
            
    
    
    
    
}
