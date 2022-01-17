/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Sql.*;
import TableController.ProductDetailesView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author rohan
 */
public class View_ProductController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField HSNCode;
    @FXML
    private Button Search_Product;
    @FXML
    private TableView<ProductDetailesView> Product_Detailes_Table;
    @FXML
    private TableColumn<ProductDetailesView, Integer> tno;
    @FXML
    private TableColumn<ProductDetailesView, String> tproduct;
    @FXML
    private TableColumn<ProductDetailesView, String> thsn;
    @FXML
    private TableColumn<ProductDetailesView, String> tUom;
    @FXML
    private TableColumn<ProductDetailesView, String> ttax;
    @FXML
    private TableColumn<ProductDetailesView, String> tCost_Price;
    @FXML
    private TableColumn<ProductDetailesView, String> tSales_Price;
    TableFilter<ProductDetailesView> filter ;
    
    @FXML
    private Button Edit;
    @FXML
    private Button Add_New_product;
    @FXML
    private AnchorPane Prompt_pane;
    @FXML
    private Label Prompt_lable;
    @FXML
    private Button Close_Prompt;
    @FXML
    private AnchorPane Product_Edit;
    @FXML
    private Button Close_Add_product;
    @FXML
    private ComboBox Pro_UOM;
    @FXML
    private TextField Pro_Product;
    @FXML
    private ComboBox Pro_Tax;
    @FXML
    private Button Add_into_DB;
    @FXML
    private Label Product_Prompt;
    @FXML
    private TextField Pro_HSN;
    @FXML
    private TextField Pro_sp;
    @FXML
    private TextField Pro_cp;
    @FXML Button Refresh;
    //For product S/n
    String Product_no;
    //forsaving product no
    String Product_no_genrated;
    
    public View_Sql sql;
    
    public ObservableList<ProductDetailesView> list = FXCollections.observableArrayList();
    
    public ObservableList<ProductDetailesView> filtered_list = FXCollections.observableArrayList();
    SortedList<ProductDetailesView> sorted;

    ProductDetailesView person;

    public View_ProductController() {
       
    }
    /**
     * Initializes the controller class.
     * 
     * 
     */
    @FXML
    public void On_NameInput(){
         tno.setCellValueFactory(new PropertyValueFactory<>("no"));
                tproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
                thsn.setCellValueFactory(new PropertyValueFactory<>("Hsn"));
             tUom.setCellValueFactory(new PropertyValueFactory<>("uom"));
                ttax.setCellValueFactory(new PropertyValueFactory<>("tax"));
                tCost_Price.setCellValueFactory(new PropertyValueFactory<>("Cost"));
                tSales_Price.setCellValueFactory(new PropertyValueFactory<>("Sales_price"));
            
        String Lover_Case = Name.getText().toLowerCase();
       
        
        
    }
    
    
   
   
    
    
    
    
    @FXML
    public void ButtonEventHandler(ActionEvent event) throws SQLException {
        if(event.getSource()==Edit){
            if(Product_Detailes_Table.getSelectionModel().isEmpty()){
                
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("Please Select product");
            }
            else{
                On_Click_Edit();
                Product_Edit.setVisible(true);
                Add_into_DB.setText("Modify");
            }
            
        }
        else if(event.getSource()== Add_New_product){
            
            Product_Edit.setVisible(true);
            product_reset();
            Add_into_DB.setText("Add Product");
        }
        else if(event.getSource()== Add_into_DB){
            if(Add_into_DB.getText().equals("Modify")){
                On_Modify_Commit();
                Product_Edit.setVisible(false);
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("Product is Modified");
                Addinto_product_Table();
            }
            else if(Add_into_DB.getText().equals("Add Product")){
                
                On_Add_Commit();
                Product_Edit.setVisible(false);
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("Product is Added");
                Addinto_product_Table();
            }
        }
        else if(event.getSource() == Close_Add_product){
            Product_Edit.setVisible(false);
        }
        else if(event.getSource() == Close_Prompt){
            Prompt_pane.setVisible(false);
        }

        else if(event.getSource() == Search_Product){
            
        }
        else if(event.getSource() == Refresh ){
            
            Addinto_product_Table();
           
        }
        
      
    }
    
    public void Search_product_In_table(){
        if(Name.getText()==null && HSNCode.getText() == null){
            //search bt alpha batical order
            Addinto_product_Table();    
        }
        else if(Name.getText()!=null && HSNCode.getText() == null){
                // Search by name
        }
        else if(Name.getText()==null && HSNCode.getText() != null){
                // Search by Hsn Code
        }
        else if(Name.getText()!=null && HSNCode.getText() != null){
            // SeARCH by both
        }
    }
    
    @FXML 
    public void On_Cost_Change(){
        if(Float.parseFloat(Pro_cp.getText())>Float.parseFloat(Pro_sp.getText())){
             Prompt_pane.setVisible(true);
                Prompt_lable.setText("Cost Price is Greater than Sales Price");
                Prompt_pane.toFront();
        }
    }
    void On_Sales_Change(){
        if(Float.parseFloat(Pro_cp.getText())>Float.parseFloat(Pro_sp.getText())){
             Prompt_pane.setVisible(true);
             Prompt_lable.setText("Cost Price is Greater than Sales Price");
             Prompt_pane.toFront();
        }
    }
    
    
    //Prodact pane reset
    public void product_reset(){
        Product_no = ""+Product_no_genrated;
        Pro_Product.setText(null);
        Pro_HSN.setText(null);
        Pro_UOM.setValue("Pices");
        Pro_Tax.setValue("Gst");
        Pro_cp.setText("");
        Pro_sp.setText("");
    }
    //Product Table add
    public void Addinto_product_Table(){
        try {
            Connection connection = sqliteconnection.Connector();
            list = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery("select * from Product_Detailes order by product");
            while(rs.next()){
               list.addAll(new ProductDetailesView(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {}
                tno.setCellValueFactory(new PropertyValueFactory<>("no"));
                tproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
                thsn.setCellValueFactory(new PropertyValueFactory<>("Hsn"));
                tUom.setCellValueFactory(new PropertyValueFactory<>("uom"));
                ttax.setCellValueFactory(new PropertyValueFactory<>("tax"));
                tCost_Price.setCellValueFactory(new PropertyValueFactory<>("Cost"));
                tSales_Price.setCellValueFactory(new PropertyValueFactory<>("Sales_price"));
        
         Product_Detailes_Table.setItems(list);
         filtered_list.addAll(list);
        //  this.filter =new TableFilter<>(Product_Detailes_Table);
        //    filter.setSearchStrategy((input,target) -> {
        //     try {
        //     return target.matches(input);
        // } catch (Exception e) {
        //     return false;
        // }
        //     });
          
    }
      //Edit product from database
    public void On_Click_Edit(){
        ObservableList<ProductDetailesView> allproduct;
        allproduct = Product_Detailes_Table.getItems();
        int Product_index = Product_Detailes_Table.getSelectionModel().getSelectedIndex();
        Product_no = ""+tno.getCellData(Product_index);
        Pro_Product.setText(tproduct.getCellData(Product_index));
        Pro_HSN.setText(thsn.getCellData(Product_index));
        Pro_UOM.setValue(tUom.getCellData(Product_index));
        Pro_Tax.setValue(ttax.getCellData(Product_index));
        Pro_cp.setText(tCost_Price.getCellData(Product_index));
        Pro_sp.setText(tSales_Price.getCellData(Product_index));
    }
    
    //Product no increment
    public void Product_no_genrate() throws SQLException{
        if(sql.get_Product_no()==null){
            Product_no_genrated = "1";
        }
        else {
           Product_no_genrated = ""+ (sql.get_Product_no()+1);
        }
    }
    public void On_Modify_Commit() throws SQLException{
        sql.Modify_Product_Detailes(Integer.parseInt(Product_no),Pro_Product.getText(), Pro_HSN.getText(),
                Pro_UOM.getSelectionModel().getSelectedItem().toString(),
                Pro_Tax.getSelectionModel().getSelectedItem().toString(),
                Pro_cp.getText(),Pro_sp.getText());
    }
    public void On_Add_Commit() throws SQLException{
        sql.Add_Product_Detailes(Integer.parseInt(Product_no_genrated),
                Pro_Product.getText(),
                Pro_HSN.getText(),
                Pro_UOM.getSelectionModel().getSelectedItem().toString(),
                Pro_Tax.getSelectionModel().getSelectedItem().toString(),
                Pro_cp.getText(),Pro_sp.getText());
    }
    
    public void Search(String name ,String HsnCode){
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sql = new View_Sql();
             
        } catch (SQLException ex) {
            Logger.getLogger(View_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Product_no_genrate();
        } catch (SQLException ex) {
            Logger.getLogger(View_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Addinto_product_Table();
    }    
    
}
