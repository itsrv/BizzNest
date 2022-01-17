/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import Sql.*;
import TableController.Quo_Table_get;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import projectteck5.AutoCompleteComboBoxListener;
import projectteck5.PrintClass;

/**
 * FXML Controller class
 *
 * @author rohan
 */
public class QuatationController implements Initializable {

    @FXML private AnchorPane Quo_Main_anc;
    @FXML private AnchorPane Quo_report_anc;
    @FXML private AnchorPane Quo_First_Window;
    @FXML private AnchorPane Quo_Second_Window;
    @FXML private AnchorPane Add_Product_Anc;
    @FXML private AnchorPane Prompt_pane;
    
    
    @FXML private ComboBox Quo_State;
    @FXML private Label invoice_type_lbl;
    @FXML private ComboBox<?> Quo_Enquire_type;
    @FXML private ComboBox<?> Quo_Clint;
    @FXML private TextField Quo_Enquire_Ref_No;
    @FXML private DatePicker Quo_Date;
    @FXML private DatePicker Quo_Enqurie_Date;
    @FXML private Label Productlabel;
    @FXML private TextField Quo_remarks;
    @FXML private TextField Quo_UnitPrize;
    @FXML private Button Quo_Add_Into_Table;
    
    
   //First anc MAin table elements
    @FXML private TableView<Quo_Table_get> Quo_Main_Table;
    @FXML private TableColumn<Quo_Table_get, Integer> Quo_tno;
    @FXML private TableColumn<Quo_Table_get, String> Quo_tProduct;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tRate;
    @FXML private TableColumn<Quo_Table_get, Integer> Quo_tQty;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tAmount;
    @FXML private TableColumn<Quo_Table_get, String> Quo_tTax;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tCgst;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tSgst;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tIgst;
    @FXML private TableColumn<Quo_Table_get, Float> Quo_tTotal;
    @FXML private TableColumn<Quo_Table_get, String> Quo_tRemarks;
    
    
    @FXML private TextField Quo_ShippingCost;
    @FXML private ComboBox<?> Quo_PaymentMode;
    @FXML private Button Quo_Priview;
    @FXML private Button Quo_New_Clint;
    @FXML private Button Quo_New_Product;
    @FXML private ComboBox<?> Quo_Product;
    @FXML private Button Quo_Remove_From_Table;
    @FXML private Label Invoice_billno_lbl;
    @FXML private TextField Quo_no;
    @FXML private Label Quo_Igst_lbl;
    @FXML private Label Quo_Sgst_lbl;
    @FXML private Label Quo_Cgst_lbl;
    @FXML private Label Quo_SubTotal_lbl;
    @FXML private Label Quo_Grand_Total_lbl;
    @FXML private ComboBox<?> Quo_Tax;
    @FXML private Label invoice_type_lbl1;
    @FXML private TextField Quo_Qty;
   
    @FXML private Button Close_Add_product;
    @FXML private ComboBox<?> Pro_UOM;
    @FXML private TextField Pro_Product;
    @FXML private ComboBox<?> Pro_Tax;
    @FXML private Button Add_Product_Into_Db;
    @FXML private Label Product_Prompt;
    @FXML private TextField Pro_HSN;
    @FXML private TextField Pro_sp;
    @FXML private TextField Pro_cp;
    @FXML private AnchorPane Add_Client_Anc;
    @FXML private TextField Add_name;
    @FXML private TextField Add_Email;
    @FXML private TextField Add_GstIn;
    @FXML private TextField Add_Pan;
    @FXML private TextField Add_ContactName;
    @FXML private TextField Add_ContactNo;
    @FXML private TextArea Add_Address;
    @FXML private ComboBox<?> Add_State;
    @FXML private Button Add_Clint_Into_Db;
    @FXML private Button Close_Client;
    @FXML private ComboBox<?> Add_City;
    @FXML private Label Add_Prompt;
    
    @FXML private Label Prompt_lable;
    @FXML private Button Close_Prompt;

    
    
    Integer Main_Table_Increment = 1;
    Float CGST;
    Float SGST;
    Float IGST;
    Float Rate;
    Float Amount;
    Float tTotal;
    int Client_no_genrated;
    int Product_no_genrated;
    int promptInc =0 ;
     //Last Calculation
    Float SubTotal = (float)0;
    Float CGSTTotal = (float)0;
    Float SGSTTotal = (float)0;
    Float IGSTTotal = (float)0;
    Float GrandTotal = (float)0;
    
    newInvoiceSql Invoicesql;
    View_Sql view_sql;
    QuotationSql Quo_sql;
    
    PrintClass print;
    
    //Lists used in combos and table
    public ObservableList Clintlist = FXCollections.observableArrayList();
    public ObservableList StatesList = FXCollections.observableArrayList();
    public ObservableList CityList = FXCollections.observableArrayList();
    public ObservableList Productlist = FXCollections.observableArrayList();
    public ObservableList Uom_List = FXCollections.observableArrayList();
    public ObservableList DiscountList = FXCollections.observableArrayList("%","RS","nil");
    public ObservableList Tax_List = FXCollections.observableArrayList("Gst 5%","Gst 12%","Gst 18%","Gst 28%");
    public ObservableList Payment_Mode_List = FXCollections.observableArrayList("Cash");
    public ObservableList Prefix_Name_List = FXCollections.observableArrayList("Mr","Mrs","Miss");
    public ObservableList Enquiry_Type_List = FXCollections.observableArrayList("Verbal","Textutal","Telephonic");
    public ObservableList Quo_Main_Table_list = FXCollections.observableArrayList();
    public ObservableList Quo_Type_list = FXCollections.observableArrayList("Services","Goods");
    @FXML
    private Button SaveQuo;
    
    
    
    
    
    
    
    
    
    @FXML
    private void ButtonEventHandler(ActionEvent event) throws SQLException {
    
        if(event.getSource() == Quo_New_Clint ){
            Add_Client_Anc.setVisible(true);
            Quo_First_Window.setDisable(true);
            try {
                Set_Client_Pane();
            } catch (SQLException ex) {
                Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(event.getSource() == Close_Client){
            Add_Client_Anc.setVisible(false);
            Quo_First_Window.setDisable(false);
        }
        else if(event.getSource() == Add_Clint_Into_Db){
            try {
                On_Client_Add_Into_Db();
            } catch (SQLException ex) {
                Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Add_Product_Anc.setVisible(false);
            Quo_First_Window.setDisable(false);
        }
        else if(event.getSource() == Quo_New_Product){
            Add_Product_Anc.setVisible(true);
            Quo_First_Window.setDisable(true);
            SetproductPane();
        }
        else if(event.getSource() == Close_Add_product){
            Add_Product_Anc.setVisible(false);
            Quo_First_Window.setDisable(false);
        }
        else if(event.getSource()==Add_Product_Into_Db){
            try {
                On_Product_Add_Into_DB();
                
            } catch (SQLException ex) {
                Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Add_Product_Anc.setVisible(false);
            Quo_First_Window.setDisable(false);
        }
        else if(event.getSource() == Quo_Add_Into_Table){
            Add_Into_Quo_Table();
        }
        else if(event.getSource() == Quo_Remove_From_Table){
            Delete_From_Quo_Table();
            Quo_Table_Data_Calc();
        }
        else if(event.getSource() == Quo_Priview){
            priviewQuo();
        }
        else if(event.getSource() == SaveQuo){
            saveQuo();
        }
        else if (event.getSource()==Close_Prompt){
            Prompt_pane.setVisible(false);
        }
    
    }
    
    
    public void set_quotation_pane() throws SQLException{
        Quo_Date.setValue(LocalDate.now());
        Quo_Clint.getSelectionModel().clearSelection();
        Quo_Enquire_type.getSelectionModel().clearSelection();
        Quo_Enquire_Ref_No.setText("");
        Quo_Enqurie_Date.setValue(LocalDate.now());
        Quo_State.getSelectionModel().clearSelection();
        Quo_Product.getSelectionModel().getSelectedItem();
        Quo_UnitPrize.setText(null);
        Quo_Qty.setText(null);
        Quo_Tax.getSelectionModel().clearSelection();
        Quo_remarks.setText("");
        Quo_ShippingCost.setText("0");
        Quo_PaymentMode.getSelectionModel().clearSelection();
        
        if(view_sql.get_Client_no()==null){
           Client_no_genrated = 1;
        }
        else{
           Client_no_genrated = view_sql.get_Client_no()+1;
        }
        
        
         new AutoCompleteComboBoxListener<>(Quo_State);
          new AutoCompleteComboBoxListener<>(Quo_Product);
           new AutoCompleteComboBoxListener<>(Quo_Clint);
           
        try {
            setQuotation_Ccombos();
        } catch (SQLException ex) {
            Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(Quo_sql.getQuotation_no() == null){
            Quo_no.setText("1");
        }
        else{
            Quo_no.setText(""+(Quo_sql.getQuotation_no()+1));
        }
        
        
           
    }
    public void setQuotation_Ccombos() throws SQLException{
         StatesList.clear();
        StatesList.addAll(Invoicesql.getStatename());
        Clintlist.clear();
        Clintlist.addAll(Invoicesql.getClintnames());
        CityList.clear();
        CityList.addAll(Invoicesql.getCityname());
        Productlist.clear();
        Productlist.addAll(Invoicesql.getProductName());
            
        Uom_List.clear();
        Uom_List.addAll(Invoicesql.get_Unit_Of_messures());
        
        
        Quo_State.setItems(StatesList);
        Quo_Clint.setItems(Clintlist);
        Quo_Product.setItems(Productlist);
        Quo_Enquire_type.setItems(Enquiry_Type_List);
        Quo_Tax.setItems(Tax_List);
        Quo_PaymentMode.setItems(Payment_Mode_List);
        
        new AutoCompleteComboBoxListener<>(Quo_State);
          new AutoCompleteComboBoxListener<>(Quo_Product);
           new AutoCompleteComboBoxListener<>(Quo_Clint);   
    }
    
    
    
    //Doing Calculation on table data input and out put
    public void Quo_Table_Data_Calc(){
        if(Quo_State.getSelectionModel().isEmpty()){
           
        
        }
        else if(Quo_State.getSelectionModel().getSelectedItem().toString().equals("Rajasthan")){
            if(Quo_Tax.getSelectionModel().isEmpty()) {
              
            } 
            else{
            switch(Quo_Tax.getSelectionModel().getSelectedItem().toString()){
            
                case "Gst 5%":
                    Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.05;
                    Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                    CGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    SGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    IGST = (float)0;
                    tTotal = Amount + CGST +SGST+IGST;
                    break;
                case "Gst 12%":
                    Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.12;
                    Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                    CGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    SGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    IGST = (float)0;
                    tTotal = Amount + CGST +SGST+IGST;
                    break;
                case "Gst 18%":
                    Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.18;
                    Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                    CGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    SGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    IGST = (float)0;
                    tTotal = Amount + CGST +SGST+IGST;
                    break;
                case "Gst 28%":
                    Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.28;
                    Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                    CGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    SGST = ((Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText()))/2;
                    IGST = (float)0;
                    tTotal = Amount + CGST +SGST+IGST;
                    break;
            }
        }
        }
        else{
            if(Quo_Tax.getSelectionModel().isEmpty()) {
             
            }
            else{
                switch(Quo_Tax.getSelectionModel().getSelectedItem().toString()){
                    case "Gst 5%":
                        Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.05;
                        Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                        CGST = (float)0;
                        SGST = (float)0;
                        IGST = (Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText());
                        tTotal = Amount + CGST +SGST+IGST;
                        break;
                    case "Gst 12%":
                        Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.12;
                        Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                        CGST = (float)0;
                        SGST = (float)0;
                        IGST = (Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText());
                        tTotal = Amount + CGST +SGST+IGST;
                        break;
                    case "Gst 18%":
                        Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.18;
                        Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                        CGST = (float)0;
                        SGST = (float)0;
                        IGST = (Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText());
                        tTotal = Amount + CGST +SGST+IGST;
                        break;
                    case "Gst 28%":
                        Rate = Float.parseFloat(Quo_UnitPrize.getText())/(float)1.28;
                        Amount = Rate * Float.parseFloat(Quo_Qty.getText());
                        CGST = (float)0;
                        SGST = (float)0;
                        IGST = (Float.parseFloat(Quo_UnitPrize.getText()) - Rate)*Float.parseFloat(Quo_Qty.getText());
                        tTotal = Amount + CGST +SGST+IGST;
                        break;
                }
            }
        }   
    }
    //Disabling First Window Elements on Adding to table
    public void Set_First_Window_Fields_On_Table_Action(boolean Check){
        Quo_Date.setDisable(Check);
        Quo_Clint.setDisable(Check);
        Quo_Enquire_type.setDisable(Check);
        Quo_Enquire_Ref_No.setDisable(Check);
        Quo_Enqurie_Date.setDisable(Check);
        Quo_State.setDisable(Check);
    }
    
    public void Add_Into_Quo_Table(){
        if(Quo_State.getSelectionModel().isEmpty()||Quo_Product.getSelectionModel().isEmpty()||
           Quo_UnitPrize.getText().isEmpty()||Quo_Clint.getSelectionModel().isEmpty()||
                Quo_Enquire_type.getSelectionModel().isEmpty()||
                Quo_Enqurie_Date.getValue() == null){
            
            
        }   
        else
        {
            Quo_Table_Data_Calc();
            Quo_Main_Table_list.addAll(new Quo_Table_get(Main_Table_Increment, Quo_Product.getSelectionModel().getSelectedItem().toString(),
                   Rate,Integer.parseInt(Quo_Qty.getText()),Amount,Quo_Tax.getSelectionModel().getSelectedItem().toString(),
                    CGST,SGST,IGST,tTotal,Quo_remarks.getText()));
            Quo_tno.setCellValueFactory(new PropertyValueFactory<>("no"));
                Quo_tProduct.setCellValueFactory(new PropertyValueFactory<>("Product"));
                Quo_tRate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
                Quo_tQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
                Quo_tAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
                Quo_tTax.setCellValueFactory(new PropertyValueFactory<>("Tax"));
                Quo_tCgst.setCellValueFactory(new PropertyValueFactory<>("Cgst"));
                Quo_tSgst.setCellValueFactory(new PropertyValueFactory<>("Sgst"));
                Quo_tIgst.setCellValueFactory(new PropertyValueFactory<>("Igst"));
                Quo_tTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
                Quo_tRemarks.setCellValueFactory(new PropertyValueFactory<>("Remarks"));
            Quo_Main_Table.setItems(Quo_Main_Table_list);
            Main_Table_Increment++;
            Set_First_Window_Fields_On_Table_Action(true);
           for(int i = 0;i<Quo_Main_Table_list.size();i++){
                SubTotal = SubTotal + Quo_tAmount.getCellData(i);
                CGSTTotal = CGSTTotal + Quo_tCgst.getCellData(i);
                SGSTTotal = SGSTTotal + Quo_tSgst.getCellData(i);
                IGSTTotal = IGSTTotal + Quo_tIgst.getCellData(i);
                GrandTotal = GrandTotal + Quo_tTotal.getCellData(i);
           }
           Quo_SubTotal_lbl.setText(""+SubTotal);
           Quo_Cgst_lbl.setText(""+CGSTTotal);
           Quo_Sgst_lbl.setText(""+SGSTTotal);
           Quo_Igst_lbl.setText(""+IGSTTotal);
           Quo_Grand_Total_lbl.setText(""+GrandTotal);
            
        }
    }
    //Delete from Quotation table
    public void Delete_From_Quo_Table(){
        ObservableList<Quo_Table_get> product,allproduct;
        allproduct = Quo_Main_Table.getItems();
        product = Quo_Main_Table.getSelectionModel().getSelectedItems();
        product.forEach(allproduct::remove);
        Quo_Main_Table_list.remove(product);
        if(Quo_Main_Table_list.isEmpty()){
            Set_First_Window_Fields_On_Table_Action(false);
            Main_Table_Increment = 1;
        }
        
    }
    
    //Setting Product Pane Elements 
    public void SetproductPane() throws SQLException{
        if(view_sql.get_Product_no()==null){
            Product_no_genrated = 1;
        }
        else{
            Product_no_genrated = view_sql.get_Product_no()+1;
        }
        Pro_Product.setText(null);
        Pro_HSN.setText(null);
        Pro_UOM.setItems(Uom_List);
        Pro_Tax.setItems(Tax_List);
        Pro_cp.setText("");
        Pro_sp.setText("");
        promptInc = 0;
        new AutoCompleteComboBoxListener<>(Pro_UOM);
         
        
    }
    //Setting Client pane Element
    public void Set_Client_Pane() throws SQLException{
        if(view_sql.get_Client_no()==null){
           Client_no_genrated = 1;
        }
        else{
           Client_no_genrated = view_sql.get_Client_no()+1;
        }
        Add_name.setText(null);
        Add_Address.setText("");
        Add_City.setItems(CityList);
        Add_State.setItems(StatesList);
        Add_ContactName.setText("");
        Add_ContactNo.setText(null);
        Add_Email.setText("");
        Add_GstIn.setText("");
        Add_Pan.setText("");
        
        new AutoCompleteComboBoxListener<>(Add_State);
          new AutoCompleteComboBoxListener<>(Add_City);
    }
    
    
    //Add data to Client Data Base
    public void On_Client_Add_Into_Db() throws SQLException{
        view_sql.Add_Client_into_db(Client_no_genrated,Add_name.getText(),Add_ContactNo.getText(),Add_GstIn.getText(),Add_Email.getText(),
                Add_Address.getText(),
                Add_State.getSelectionModel().getSelectedItem().toString(),
                Add_City.getSelectionModel().getSelectedItem().toString(), 
                Add_ContactName.getText(),Add_Pan.getText());
        
        Add_Client_Anc.setVisible(false);
        
    }
    //Add data to Product Data Base
    public void On_Product_Add_Into_DB() throws SQLException{
        
    if(Pro_UOM.getSelectionModel().isEmpty()||Pro_Tax.getSelectionModel().isEmpty()){
        Prompt_pane.setVisible(true);
        Prompt_lable.setText("Please Fill All Fields");
        Prompt_pane.toFront();
        
        
    }
    else if(promptInc ==0 ){
        try{ 
        if(Float.parseFloat(Pro_cp.getText())>Float.parseFloat(Pro_sp.getText())){
             Prompt_pane.setVisible(true);
             Prompt_lable.setText("Cost Price Can`t Be Grater Than Sales Price");
             promptInc = 1;
         }
        }
        catch(NumberFormatException e){
            
        }
    }
        
    else{
                view_sql.Add_Product_Detailes(Product_no_genrated,
                Pro_Product.getText(),
                Pro_HSN.getText(),
                Pro_UOM.getSelectionModel().getSelectedItem().toString(),
                Pro_Tax.getSelectionModel().getSelectedItem().toString(),
                Pro_cp.getText(),Pro_sp.getText());
                
                Add_Product_Anc.setVisible(false);
            

        }
    }
    
    @FXML
    private void Shipping_Cost_Action(KeyEvent event) {
         
        try{
            Quo_Grand_Total_lbl.setText(""+(GrandTotal + Float.parseFloat(Quo_ShippingCost.getText())));
        }
        catch(NumberFormatException e){
            Quo_Grand_Total_lbl.setText(""+GrandTotal);
        }
       
    }
    
    
    public void priviewQuo() throws SQLException{
        
       
     
     Quo_sql.Temp_Client_Quo_upload(Integer.parseInt(Quo_no.getText()),Quo_Date.getValue().toString(), 
               Quo_Clint.getSelectionModel().getSelectedItem().toString(),
               Quo_Enquire_type.getSelectionModel().getSelectedItem().toString(),
               Quo_Enqurie_Date.getValue().toString(),Quo_Enquire_Ref_No.getText(),
               Quo_State.getSelectionModel().getSelectedItem().toString());
    
     for(int i=0;i<Quo_Main_Table_list.size();i++){
     Quo_sql.Temp_Product_Quo_upload(Integer.parseInt(Quo_no.getText()),
             ""+Quo_tno.getCellData(i), 
             Quo_tProduct.getCellData(i),
             ""+Quo_tRate.getCellData(i),
             ""+Quo_tQty.getCellData(i),
             ""+Quo_tAmount.getCellData(i),
             Quo_tTax.getCellData(i),
             ""+Quo_tCgst.getCellData(i),
             ""+Quo_tSgst.getCellData(i), 
             ""+Quo_tIgst.getCellData(i),
             ""+Quo_tTotal.getCellData(i),
             Quo_tRemarks.getCellData(i));
     }
     Quo_sql.Temp_Amount_Quo_Upload(Integer.parseInt(Quo_no.getText()),Quo_ShippingCost.getText(),
             Quo_PaymentMode.getSelectionModel().getSelectedItem().toString(),
             Quo_SubTotal_lbl.getText(),Quo_Cgst_lbl.getText(),Quo_Sgst_lbl.getText(),Quo_Igst_lbl.getText(),
             Quo_Grand_Total_lbl.getText());
     
       
        
       print.showReport("C:\\Users\\rohan\\Documents\\NetBeansProjects\\projectTeck4\\src\\projectteck3\\Quotation.jrxml");
    }
    public void saveQuo() throws SQLException{
     
        Quo_sql.SetQuotation_no(Integer.parseInt(Quo_no.getText()));
     
        Quo_sql.Client_Quo_upload(Integer.parseInt(Quo_no.getText()),Quo_Date.getValue().toString(), 
               Quo_Clint.getSelectionModel().getSelectedItem().toString(),
               Quo_Enquire_type.getSelectionModel().getSelectedItem().toString(),
               Quo_Enqurie_Date.getValue().toString(),Quo_Enquire_Ref_No.getText(),
               Quo_State.getSelectionModel().getSelectedItem().toString());
    
     for(int i=0;i<Quo_Main_Table_list.size();i++){
     
         Quo_sql.Product_Quo_upload(Integer.parseInt(Quo_no.getText()),
             ""+Quo_tno.getCellData(i), 
             Quo_tProduct.getCellData(i),
             ""+Quo_tRate.getCellData(i),
             ""+Quo_tQty.getCellData(i),
             ""+Quo_tAmount.getCellData(i),
             Quo_tTax.getCellData(i),
             ""+Quo_tCgst.getCellData(i),
             ""+Quo_tSgst.getCellData(i), 
             ""+Quo_tIgst.getCellData(i),
             ""+Quo_tTotal.getCellData(i),
             Quo_tRemarks.getCellData(i));
     }
     
         Quo_sql.Amount_Quo_Upload(Integer.parseInt(Quo_no.getText()),Quo_ShippingCost.getText(),
             Quo_PaymentMode.getSelectionModel().getSelectedItem().toString(),
             Quo_SubTotal_lbl.getText(),Quo_Cgst_lbl.getText(),Quo_Sgst_lbl.getText(),Quo_Igst_lbl.getText(),
             Quo_Grand_Total_lbl.getText());
     
     
    }
    
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Invoicesql = new newInvoiceSql();
            view_sql = new  View_Sql();
            Quo_sql = new QuotationSql();
            print= new PrintClass();
        } catch (SQLException ex) {
            Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            set_quotation_pane();
        } catch (SQLException ex) {
            Logger.getLogger(QuatationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }    
 
    


    

   
}
