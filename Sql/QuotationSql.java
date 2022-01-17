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
public class QuotationSql {
    Connection connection;
    
    public QuotationSql() throws SQLException{
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
    
    //get bill no .
  public Integer getQuotation_no() throws SQLException{
            PreparedStatement pre;
            String query = "select Quo from Quotation_no order by Quo desc limit 1";
            pre = connection.prepareStatement(query);
          
            ResultSet rs = pre.executeQuery();
            Integer r = null;
            
            //ObservableList list = FXCollections.observableArrayList();
            while (rs.next()){
                r = Integer.parseInt(rs.getString("Quo"));
            }
            return r;
  }
   public void SetQuotation_no(Integer Quo_no) throws SQLException{
      PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Quotation_no values (?)";
        
            create_query = connection.prepareStatement(query);
            create_query.setInt(1, Quo_no);
            create_query.executeUpdate();
    }
  
  
   public void Client_Quo_upload(int QuotationNo,String Date,String client,String EnquireType,String EDate,String erf,String State) throws SQLException{
       PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Client_quo_Detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, Date);
                create_query.setString(3, client);
                create_query.setString(4, EnquireType);
                create_query.setString(5, EDate);
                create_query.setString(6, erf);
                create_query.setString(7, State);
                create_query.executeUpdate();
       
   } 
  public void Temp_Client_Quo_upload(int QuotationNo,String Date,String client,String EnquireType,String EDate,String erf,String State) throws SQLException{
       PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_Client_quo_Detailes values(?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, Date);
                create_query.setString(3, client);
                create_query.setString(4, EnquireType);
                create_query.setString(5, EDate);
                create_query.setString(6, erf);
                create_query.setString(7,State);
                create_query.executeUpdate();
       
   }   
    
  
  public void Product_Quo_upload(int QuotationNo,String no,String Product,String Rate,String Qty,String Amount,
          String Tax,String CGST,String SGST,String IGST,String Total,String Remark) throws SQLException{
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Product_quo_Detailes values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, no);
                create_query.setString(3, Product);
                create_query.setString(4, Rate);
                create_query.setString(5, Qty);
                create_query.setString(6, Amount);
                create_query.setString(7, Tax);
                 create_query.setString(8, CGST);
                create_query.setString(9, SGST);
                create_query.setString(10, IGST);
                create_query.setString(11, Total);
                create_query.setString(12, Remark);
                create_query.executeUpdate();
                
    }
   
  public void Temp_Product_Quo_upload(int QuotationNo,String no,String Product,String Rate,String Qty,String Amount,
          String Tax,String CGST,String SGST,String IGST,String Total,String Remark) throws SQLException{
            PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_Product_quo_Detailes values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, no);
                create_query.setString(3, Product);
                create_query.setString(4, Rate);
                create_query.setString(5, Qty);
                create_query.setString(6, Amount);
                create_query.setString(7, Tax);
                 create_query.setString(8, CGST);
                create_query.setString(9, SGST);
                create_query.setString(10, IGST);
                create_query.setString(11, Total);
                create_query.setString(12, Remark);
                create_query.executeUpdate();
                
    }
  public void Amount_Quo_Upload(int QuotationNo,String Shipping,String PaymentType,String Subtotal,
            String Cgst,String Sgst,String Igst,String GrandTotal) throws SQLException{
       PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Amount_quo_Detailes values(?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, Shipping);
                create_query.setString(3, PaymentType);
                create_query.setString(4, Subtotal);
                create_query.setString(5, Cgst);
                create_query.setString(6, Sgst);
                create_query.setString(7, Igst);
                create_query.setString(8, GrandTotal);
           create_query.executeUpdate();
  }
  public void Temp_Amount_Quo_Upload(int QuotationNo,String Shipping,String PaymentType,String Subtotal,
            String Cgst,String Sgst,String Igst,String GrandTotal) throws SQLException{
       PreparedStatement preparedStatement;
            PreparedStatement create_query;
            String query = "Insert into Temp_Amount_quo_Detailes values(?,?,?,?,?,?,?,?)";
        
            create_query = connection.prepareStatement(query);
                create_query.setInt(1, QuotationNo);
                create_query.setString(2, Shipping);
                create_query.setString(3, PaymentType);
                create_query.setString(4, Subtotal);
                create_query.setString(5, Cgst);
                create_query.setString(6, Sgst);
                create_query.setString(7, Igst);
                create_query.setString(8, GrandTotal);
           create_query.executeUpdate();
  }
  
  
}
