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
public class View_Sql {
     Connection connection;
    
    public View_Sql() throws SQLException{
        connection = sqliteconnection.Connector();
        
        if(connection == null){
            System.out.println("Connection not Successful");
            System.exit(1);
        }
        
    }
    public boolean isDbconnected(){
        try{
            return !connection.isClosed();
                    
        }
        catch(SQLException e){
            return false;
        }
    }
    
    public void Modify_Product_Detailes(Integer no,String product,String HSN,String uom,String tax,String Cost,String Sales) throws SQLException{
        PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "update Product_Detailes set product=?,HSN_Code=?,Uom=?,tax=?,Cost_Price = ?,Sale_Price=? where No = "+no;
        
            create_query = connection.prepareStatement(query);
                
                create_query.setString(1, product);
                create_query.setString(2, HSN);
                create_query.setString(3, uom);
                create_query.setString(4, tax);
                create_query.setString(5, Cost);
                create_query.setString(6, Sales);
                
                create_query.executeUpdate();
    }
    public void Add_Product_Detailes(Integer no,String product,String HSN,String uom,String tax,String Cost,String Sales) throws SQLException{
        PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Product_Detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, no);
                create_query.setString(2, product);
                create_query.setString(3, HSN);
                create_query.setString(4, uom);
                create_query.setString(5, tax);
                create_query.setString(6, Cost);
                create_query.setString(7, Sales);
                create_query.executeUpdate();
    }
    
    
    //get product no .
    public Integer get_Product_no() throws SQLException{
            PreparedStatement pre;
            String query = "select No from Product_Detailes order by No desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            while (rs.next()){
                r = Integer.parseInt(rs.getString("No"));
            }
            return r;
    }
    //get Client  no .
    public Integer get_Client_no() throws SQLException{
            PreparedStatement pre;
            String query = "select No from Clintdetailes order by No desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            while (rs.next()){
                r = Integer.parseInt(rs.getString("No"));
            }
            return r;
    }
    
    
    //add New Client
    public void Add_Client_into_db(Integer no,String name,String ContactNo,String GstIn,String Email,String Address,
            String State,String City,String ContactName,String PanNo) throws SQLException{

       
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "Insert into Clintdetailes values(?,?,?,?,?,?,?,?,?,?)";
        
        create_query = connection.prepareStatement(query);
                create_query.setInt(1, no);
                create_query.setString(2, name);
                create_query.setString(3, ContactNo);
                create_query.setString(4, GstIn);
                create_query.setString(5, Email);
                create_query.setString(6, Address);
                create_query.setString(7, State);
                create_query.setString(8, City);
                create_query.setString(9, ContactName);
                create_query.setString(10, PanNo);
                create_query.executeUpdate();
    }
    //Modify Client
    public void Modify_Client_into_db(Integer no,String name,String ContactNo,String GstIn,String Email,String Address,
            String State,String City,String ContactName,String PanNo) throws SQLException{

       
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "update Clintdetailes set Name =?,Email = ?,GstIn =?,pan = ?,State = ?,Address = ?,ContactName =  ?,ContactNo = ?,City = ? ,no="+no;
        
        create_query = connection.prepareStatement(query);
                
                create_query.setString(1, name);
                create_query.setString(2, ContactNo);
                create_query.setString(3, GstIn);
                create_query.setString(4, Email);
                create_query.setString(5, Address);
                create_query.setString(6, State);
                create_query.setString(7, City);
                create_query.setString(8, ContactName);
                create_query.setString(9, PanNo);
                create_query.setInt(10, no);
                create_query.executeUpdate();
    }
    
    
}
