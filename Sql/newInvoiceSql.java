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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rohan
 */
public class newInvoiceSql {
    
    Connection connection;
    
    public newInvoiceSql() throws SQLException{
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
   
    /*
    //uploading State names to database
    public void UploadStates() throws SQLException{
       
        PreparedStatement preparedStatement;
         PreparedStatement create_Table;
        
        String create = "CREATE TABLE IF NOT EXISTS States(State Text UNIQUE,PRIMARY KEY(`State`))";
       String query = "replace into States (state) values(?)";
        create_Table = connection.prepareStatement(create);
        create_Table.execute();
        for(String string : Statesupload){
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, string);
           preparedStatement.executeUpdate();
        }
    }*/
    
    //getting state names from data base       
    public ObservableList getStatename() throws SQLException{
            PreparedStatement pre;
            String query = "Select State from States";
            pre = connection.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                 list.add(rs.getString("State"));
            }
            return list;
    }
     //getting City names from data base       
    public ObservableList getCityname() throws SQLException{
            PreparedStatement pre;
            String query = "Select City from Cities";
            pre = connection.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                 list.add(rs.getString("City"));
            }
            return list;
    }
  //getting state names from data base       
    public ObservableList get_Unit_Of_messures() throws SQLException{
            PreparedStatement pre;
            String query = "Select Uom from Uom";
            pre = connection.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                 list.add(rs.getString("Uom"));
            }
            return list;
    }
  
  
  
    //get clint names in clint list
  public ObservableList getClintnames() throws SQLException{
        PreparedStatement pre;
            String query = "Select Name from Clintdetailes";
            pre = connection.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                 list.add(rs.getString("Name"));
            }
            return list;
  }
  
  // get product name
  public ObservableList getProductName() throws SQLException{
            PreparedStatement pre;
            String query = "Select product from Product_Detailes";
            pre = connection.prepareStatement(query);
            
            ResultSet rs = pre.executeQuery();
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                 list.add(rs.getString("product"));
            }
            return list; 
  }
  //get Product detailtes
  public ObservableList getProductDetailes(String string) throws SQLException{
            PreparedStatement pre;
            String query = "Select * from Product_Detailes where product = (?)";
            pre = connection.prepareStatement(query);
            pre.setString(1, string);
            ResultSet rs = pre.executeQuery();
            
            ObservableList list = FXCollections.observableArrayList();
       
            while (rs.next()) {
                list.add(rs.getString("description"));
            }
            return list; 
  }
  
  
  //get bill no .
  public Integer getInvoice_Billno() throws SQLException{
            PreparedStatement pre;
            String query = "select * from Invoice_Billno order by Billno desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            
            //ObservableList list = FXCollections.observableArrayList();
            while (rs.next()){
                r = Integer.parseInt(rs.getString("Billno"));
            }
            return r;
  }
  public void SetInvoice_Billno(Integer billno) throws SQLException{
      PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Invoice_Billno values (?)";
        
            create_query = connection.prepareStatement(query);
            create_query.setInt(1, billno);
            create_query.executeUpdate();
    }
  //get bill no .
  public Integer get_ChallanNo() throws SQLException{
            PreparedStatement pre;
            String query = "select * from Challanno order by Challanno desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            
            //ObservableList list = FXCollections.observableArrayList();
            while (rs.next()){
                r = Integer.parseInt(rs.getString("Challanno"));
            }
            return r;
  }
  //get bill no .
  public Integer getQuotationNo() throws SQLException{
            PreparedStatement pre;
            String query = "select * from Challanno order by Challanno desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            
            //ObservableList list = FXCollections.observableArrayList();
            while (rs.next()){
                r = Integer.parseInt(rs.getString("Challanno"));
            }
            return r;
  }
  public void Set_ChallanNo(Integer ChallanNo) throws SQLException{
      PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Challanno values (?)";
        
            create_query = connection.prepareStatement(query);
            create_query.setInt(1, ChallanNo);
            create_query.executeUpdate();
    }

   //Uploading Clint names after invoice
   public void Upload_InvoiceClint_detailes(Integer Billno, String Invoicetype, String Clintname,
            String pono, String podate, String issueDate, String state) throws SQLException {
            
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into InvoiceClint_detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Invoicetype);
                create_query.setString(3, Clintname);
                create_query.setString(4, pono);
                create_query.setString(5, podate);
                create_query.setString(6, issueDate);
                create_query.setString(7, state);
                create_query.executeUpdate();
                
            Temp_Invoice_ClintDetailes(Billno);
    }

   public void Upload_Invoice_product_detailes(Integer Billno, String no, String product, String Desc, String uom, 
            String qty,String Rate,String total,Float unitprize,Float prize) throws SQLException {
                PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Invoice_product_detailes values(?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, no);
                create_query.setString(3, product);
                create_query.setString(4, Desc);
                create_query.setString(5, uom);
                create_query.setString(6, qty);
                create_query.setString(7, Rate);
                create_query.setString(8, total);
                create_query.setFloat(9, unitprize);
                create_query.setFloat(10, prize);
                create_query.executeUpdate();
                
    }

   public void Upload_Invoice_Amount_Detailes(Integer Billno,String Discount, String paymenttype, String amountpaid,
            String dueamount, String duedate, String vicheal, String dod,String CGST,String SGST,
            String IGST,String Total_amount,String Shipping,String total_cost,String SubTotal) throws SQLException
    {
          
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Invoice_Amount_Detailes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Discount);
                create_query.setString(3, paymenttype);
                create_query.setString(4, amountpaid);
                create_query.setString(5, dueamount);
                create_query.setString(6, duedate);
                create_query.setString(7, vicheal);
                create_query.setString(8, dod);
                create_query.setString(9, CGST);
                create_query.setString(10, SGST);
                create_query.setString(11, IGST);
                create_query.setString(12, Total_amount);
                create_query.setString(13, Shipping);
                create_query.setString(14, total_cost);
                create_query.setString(15, SubTotal);
                create_query.executeUpdate();
                Temp_Invoice_Amount_detailes(Billno);
    }
    
    //Uploading Clint names after invoice
   public void Temp_Upload_InvoiceClint_detailes(Integer Billno, String Invoicetype, String Clintname,
            String pono, String podate, String issueDate, String state) throws SQLException {
            
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_InvoiceClint_detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Invoicetype);
                create_query.setString(3, Clintname);
                create_query.setString(4, pono);
                create_query.setString(5, podate);
                create_query.setString(6, issueDate);
                create_query.setString(7, state);
                create_query.executeUpdate();
                
            Temp_Invoice_ClintDetailes(Billno);
    }

   public void Temp_Upload_Invoice_product_detailes(Integer Billno, String no, String product, String Desc, String uom, 
            String qty,String Rate,String total,Float unitprize,Float prize) throws SQLException {
                PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_Invoice_product_detailes values(?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, no);
                create_query.setString(3, product);
                create_query.setString(4, Desc);
                create_query.setString(5, uom);
                create_query.setString(6, qty);
                create_query.setString(7, Rate);
                create_query.setString(8, total);
                create_query.setFloat(9, unitprize);
                create_query.setFloat(10, prize);
                create_query.executeUpdate();
                
    }

   public void Temp_Upload_Invoice_Amount_Detailes(Integer Billno,String Discount, String paymenttype, String amountpaid,
            String dueamount, String duedate, String vicheal, String dod,String CGST,String SGST,
            String IGST,String Total_amount,String Shipping,String total_cost,String SubTotal) throws SQLException
    {
          
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_Invoice_Amount_Detailes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Discount);
                create_query.setString(3, paymenttype);
                create_query.setString(4, amountpaid);
                create_query.setString(5, dueamount);
                create_query.setString(6, duedate);
                create_query.setString(7, vicheal);
                create_query.setString(8, dod);
                create_query.setString(9, CGST);
                create_query.setString(10, SGST);
                create_query.setString(11, IGST);
                create_query.setString(12, Total_amount);
                create_query.setString(13, Shipping);
                create_query.setString(14, total_cost);
                create_query.setString(15, SubTotal);
                create_query.executeUpdate();
                Temp_Invoice_Amount_detailes(Billno);
    }
    
    
    //Uploading Clint names after invoice
   public void Upload_Challan_Clint_detailes(Integer Billno, String Invoicetype, String Clintname,
            String pono, String podate, String issueDate, String state) throws SQLException {
            
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into ChallanClint_detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Invoicetype);
                create_query.setString(3, Clintname);
                create_query.setString(4, pono);
                create_query.setString(5, podate);
                create_query.setString(6, issueDate);
                create_query.setString(7, state);
                create_query.executeUpdate();
                
            Temp_Challan_ClintDetailes(Billno);
    }

  public void Upload_Challan_product_detailes(Integer Billno, String no, String product, String Desc, String uom, 
            String qty,String Rate,String total,Float unitprize,Float prize) throws SQLException {
                PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Challan_product_detailes values(?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, no);
                create_query.setString(3, product);
                create_query.setString(4, Desc);
                create_query.setString(5, uom);
                create_query.setString(6, qty);
                create_query.setString(7, Rate);
                create_query.setString(8, total);
                create_query.setFloat(9, unitprize);
                create_query.setFloat(10, prize);
                create_query.executeUpdate();
                
    }

   public void Upload_Challan_Amount_Detailes(Integer Billno,String Discount, String paymenttype, String amountpaid,
            String dueamount, String duedate, String vicheal, String dod,String CGST,String SGST,
            String IGST,String Total_amount,String Shipping,String total_cost,String SubTotal) throws SQLException
    {
          
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Challan_Amount_Detailes values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, Billno);
                create_query.setString(2, Discount);
                create_query.setString(3, paymenttype);
                create_query.setString(4, amountpaid);
                create_query.setString(5, dueamount);
                create_query.setString(6, duedate);
                create_query.setString(7, vicheal);
                create_query.setString(8, dod);
                create_query.setString(9, CGST);
                create_query.setString(10, SGST);
                create_query.setString(11, IGST);
                create_query.setString(12, Total_amount);
                create_query.setString(13, Shipping);
                create_query.setString(14, total_cost);
                create_query.setString(15, SubTotal);
                create_query.executeUpdate();
                Temp_Challan_Amount_detailes(Billno);
    }
    
    // Temp Invoice_Clint_Detailes 
    public void Temp_Invoice_ClintDetailes(Integer billno) throws SQLException{
       try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "insert into Temp_InvoiceClint_detailes(`Billno`, `Invoice_type`, `Clint_name`, `pono`,`poDate`,"
                + " `issue_Date`,`State`) select `Billno`, `Invoice_type`,`Clint_name`, `pono`,`poDate`, `issue_Date`, "
                + "`State` from InvoiceClint_detailes where InvoiceClint_detailes.Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    public void Temp_Invoice_Product_detailes(Integer billno) throws SQLException{
      try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "insert into Temp_Invoice_product_detailes( `Billno`, `No`, "
                + "`product`, `Description`, `Uom`,`Quantity`, `Rate`, `TotalAmount`, `Unitprize`, `Prize`) "
                + "select `Billno`, `No`, `product`,`Description`, `Uom`, `Quantity`, `Rate`, `TotalAmount`, "
                + "`Unitprize`, `Prize` from Invoice_product_detailes where Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
        }
        catch(SQLException e){
          System.out.print(e.getMessage());
        }
    }
    public void Temp_Invoice_Amount_detailes(Integer billno) throws SQLException{
       try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "Insert into Temp_Invoice_Amount_Detailes( `Billno`, `Discount`, `Payment_type`, `AmountPaid`,"
                + " `Due_Amount`,`Due_Date`, `Vehicle_no`, `dod`, `CGST`, `SGST`, `IGST`, `Total_amount`,"
                + " `shipping_cost`, `total_cost`,`Subtotal`) select `Billno`, `Discount`, `Payment_type`, "
                + "`AmountPaid`, `Due_Amount`, `Due_Date`, `Vehicle_no`, `dod`, `CGST`,`SGST`,`IGST`, `Total_amount`, "
                + "`shipping_cost`, `total_cost`,`SubTotal` from Invoice_Amount_Detailes where Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    // Temp Invoice_Clint_Detailes Clear 
    public void Temp_Invoice_ClintDetailes_clear() throws SQLException{
       try{
        
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_InvoiceClint_detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
        }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    public void Temp_Invoice_Product_detailes_clear() throws SQLException{
      try{
        
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_Invoice_product_detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
        }
      catch(SQLException e){
          System.out.print(e.getMessage());
       }
    }
    public void Temp_Invoice_Amount_detailes_clear() throws SQLException{
       try{
 
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_Invoice_Amount_Detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    
    
     // Temp Challan_Clint_Detailes 
    public void Temp_Challan_ClintDetailes(Integer billno) throws SQLException{
       try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "insert into Temp_InvoiceClint_detailes(`Billno`, `Invoice_type`, `Clint_name`, `pono`,`poDate`, `issue_Date`,`State`) select `Billno`, `Invoice_type`,`Clint_name`, `pono`,`poDate`, `issue_Date`, `State` from InvoiceClint_detailes where InvoiceClint_detailes.Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    public void Temp_Challan_Product_detailes(Integer billno) throws SQLException{
      try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "insert into Temp_Invoice_product_detailes( `Billno`, `No`, `product`, `Description`, `Uom`,`Quantity`, `Rate`, `TotalAmount`, `Unitprize`, `Prize`) select `Billno`, `No`, `product`,`Description`, `Uom`, `Quantity`, `Rate`, `TotalAmount`, `Unitprize`, `Prize` from Invoice_product_detailes where Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
        }
        catch(SQLException e){
          System.out.print(e.getMessage());
        }
    }
    public void Temp_Challan_Amount_detailes(Integer billno) throws SQLException{
       try{
        PreparedStatement preparedStatement;
        PreparedStatement create_query;
        String query = "Insert into Temp_Invoice_Amount_Detailes( `Billno`, `Discount`, `Payment_type`, `AmountPaid`, `Due_Amount`,`Due_Date`, `Vehicle_no`, `dod`, `CGST`, `SGST`, `IGST`, `Total_amount`, `shipping_cost`, `total_cost`,`Subtotal`) select `Billno`, `Discount`, `Payment_type`, `AmountPaid`, `Due_Amount`, `Due_Date`, `Vehicle_no`, `dod`, `CGST`,`SGST`,`IGST`, `Total_amount`, `shipping_cost`, `total_cost`,`SubTotal` from Invoice_Amount_Detailes where Billno = (?)";
        create_query = connection.prepareStatement(query);
        create_query.setInt(1,billno);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    // Temp Invoice_Clint_Detailes Clear 
    public void Temp_Challan_ClintDetailes_clear() throws SQLException{
       try{
       
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_InvoiceClint_detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
        }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    public void Temp_Challan_Product_detailes_clear() throws SQLException{
      try{
       
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_Invoice_product_detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
        }
      catch(SQLException e){
          System.out.print(e.getMessage());
       }
    }
    public void Temp_Challan_Amount_detailes_clear() throws SQLException{
       try{
     
        PreparedStatement create_query;
        String Deletequery = "Delete from Temp_Invoice_Amount_Detailes";    
        create_query = connection.prepareStatement(Deletequery);
        create_query.executeUpdate();
       }
       catch(SQLException e){
           System.out.print(e.getMessage());
       }
    }
    
    
    
}    


   
    
    

