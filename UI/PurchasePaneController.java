/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Sql.View_Sql;
import Sql.newInvoiceSql;
import Sql.sqliteconnection;
import TableController.InvoiceTableGet;
import UIController.InvoicePaneController;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class PurchasePaneController implements Initializable {

    @FXML private AnchorPane Main_inovice_Pane;
    @FXML private AnchorPane Invoicepane;
    @FXML private Label invoice_type_lbl;
    @FXML
    private ComboBox Invoicetype;
    @FXML
    private ComboBox Supplier;
    @FXML
    private TextField pono;
    @FXML
    private DatePicker podate;
    @FXML
    private DatePicker issuedate;
    @FXML
    private Label Productlabel;
    @FXML
    private TextField Desc;
    @FXML
    private ComboBox uom;
    @FXML
    private TextField qty;
    @FXML
    private TextField unitprize;
    @FXML
    private ComboBox Invoice_discount_combo;
    @FXML
    private TextField discountAmount;
    @FXML
    private ComboBox tax;
    @FXML
    private Button Add_Into_Table;
    
    
    @FXML private TableView<InvoiceTableGet> InvoiceTable;
    @FXML private TableColumn<InvoiceTableGet, Integer> ino;
    @FXML private TableColumn<InvoiceTableGet, String> itproduct;
    @FXML private TableColumn<InvoiceTableGet, String> itdesc;
    @FXML private TableColumn<InvoiceTableGet, String> ituom;
    @FXML private TableColumn<InvoiceTableGet, String> itqty;
    @FXML private TableColumn<InvoiceTableGet, String> itrate;
    @FXML private TableColumn<InvoiceTableGet, String> ittotal;
    @FXML private TableColumn<InvoiceTableGet, String> itunitprize;
    @FXML private TableColumn<InvoiceTableGet, String> itprize;
    @FXML private TextField shippingp;
    @FXML private TextField amountpaid;
    
    
    @FXML private ComboBox paymenttype;
    @FXML private TextField dueamount;
    @FXML private Label Totalamount;
    @FXML
    private Button Save;
    @FXML
    private ComboBox state;
    @FXML
    private Button Add_Client;
    @FXML
    private Button Add_Product;
    @FXML
    private ComboBox product;
    @FXML
    private Button Remove_From_Table;
    @FXML
    private Label invoice_prompt_text;
    @FXML
    private DatePicker due_date;
    @FXML
    private Label Invoice_billno_lbl;
    @FXML
    private TextField Invoice_No;
    @FXML
    private Label Invoice_IGST_lbl;
    @FXML
    private Label Invoice_SGST_lbl;
    @FXML
    private Label Invoice_CGST_lbl;
    @FXML
    private Label Invoice_shipping_amount;
    @FXML
    private Label Invoice_Totalcost;
    @FXML
    private Button Priview;
    @FXML
    private Label Discount_Amount_lbl;
    @FXML
    private Label Tax_payble_lbl;
    @FXML
    private AnchorPane Add_Client_Anc;
    @FXML
    private TextField Add_name;
    @FXML
    private TextField Add_Email;
    @FXML
    private TextField Add_GstIn;
    @FXML
    private TextField Add_Pan;
    @FXML
    private TextField Add_ContactName;
    @FXML
    private TextField Add_ContactNo;
    @FXML
    private TextArea Add_Address;
    @FXML
    private ComboBox Add_State;
    @FXML
    private Button Add_Clint_Into_Db;
    @FXML
    private Button Close_Client;
    @FXML
    private ComboBox Add_City;
    @FXML
    private Label Add_Prompt;
    @FXML
    private AnchorPane Add_Product_Anc;
    @FXML
    private Button Close_Add_product;
    @FXML
    private ComboBox Pro_UOM;
    @FXML
    private TextField Pro_Product;
    @FXML
    private ComboBox Pro_Tax;
    @FXML
    private Button Add_Product_Into_Db;
    @FXML
    private Label Product_Prompt;
    @FXML
    private TextField Pro_HSN;
    @FXML
    private TextField Pro_sp;
    @FXML
    private TextField Pro_cp;
    @FXML
    private AnchorPane Prompt_pane;
    @FXML
    private Label Prompt_lable;
    @FXML
    private Button Close_Prompt;

    
     //Sql Class
     public  View_Sql view_sql;
     public newInvoiceSql Invoice_Sql;
     public PrintClass print = new PrintClass();
   
    
    //Variables Used 
    Integer Product_no_genrated;
    Integer Client_no_genrated;
    Integer Table_no_Genrator = 1;
    DecimalFormat Dformat = new DecimalFormat("0.00");
    
    //Table Elements calculation 
    
    Float Total_row_Amount;
    Float UnitPrize_row_Amount;
    Float Price_row_Amount;
    Float Total_CGST;
    Float Total_SGST;
    Float Total_IGST;
    Float SubTotal;
    Float PriceTotal;
    Float TotalTax;
    Float Total_Discount_Amount;
    Float GrandTotal;
    
    //
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    
    
    
     
    public ObservableList Invoice_Type_List = FXCollections.observableArrayList("Services","Goods");
    public ObservableList Clint_List = FXCollections.observableArrayList();
    public ObservableList Uom_List = FXCollections.observableArrayList();
    public ObservableList Discount_List = FXCollections.observableArrayList("%","RS","nil");
    public ObservableList Tax_List = FXCollections.observableArrayList("Gst 5%","Gst 12%","Gst 18%","Gst 28%");
    public ObservableList Payment_Type_List = FXCollections.observableArrayList("Cash");
    public ObservableList States_List = FXCollections.observableArrayList("");
    public ObservableList Product_List = FXCollections.observableArrayList();
    public ObservableList Invoice_Table_List = FXCollections.observableArrayList();
    public ObservableList City_List = FXCollections.observableArrayList();
    
    
    
    
    @FXML
    private void ButtonEventHandler(ActionEvent event){
        if(event.getSource() == Add_Client){
            
        }
        else if(event.getSource()==Add_Product){
            
        }
        else if(event.getSource()== Close_Prompt){
            
        }
        else if(event.getSource()== Close_Add_product){
            
        }
        else if(event.getSource()== Close_Client){
            
        }
        else if(event.getSource()== Add_Clint_Into_Db){
            
        }
        else if(event.getSource()== Add_Product_Into_Db){
            
           
        }
        else if(event.getSource()== Remove_From_Table){
          On_Remove_Clicked();
        }
        else if(event.getSource()== Add_Into_Table){
          On_Table_Add_Click();
        }
        else if(event.getSource()== Save){
           
        }
        else if(event.getSource()== Priview){
          
            
        }
    }
    //setting invoice Combos
    public void setInvoice_Combos(){
        Clint_List.clear();
        try {
            Clint_List.addAll(Invoice_Sql.getClintnames());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Supplier.setDisable(false);
        Supplier.getSelectionModel().clearSelection();
        Supplier.setItems(Clint_List);
        
        Product_List.clear();
        try {
            Product_List.addAll(Invoice_Sql.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        product.getSelectionModel().clearSelection();
        product.setItems(Product_List);
        
        City_List.clear();
        try {
            City_List.addAll(Invoice_Sql.getCityname());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Add_City.setDisable(false);
        Add_City.getSelectionModel().clearSelection();
        Add_City.setItems(Clint_List);
        Uom_List.clear();
        try {
           Uom_List.addAll(Invoice_Sql.get_Unit_Of_messures());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        uom.setDisable(false);
        uom.getSelectionModel().clearSelection();
        uom.setItems(Uom_List);
        
        new AutoCompleteComboBoxListener<>(Supplier);
        new AutoCompleteComboBoxListener<>(product);
        new AutoCompleteComboBoxListener<>(uom);
        new AutoCompleteComboBoxListener<>(state);
    }
    public void Set_Purchase_Pane(){
        Invoicetype.getSelectionModel().clearSelection();
        Invoicetype.setDisable(false);
        Invoicetype.setItems(Invoice_Type_List);
        
        Clint_List.clear();
        try {
            Clint_List.addAll(Invoice_Sql.getClintnames());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Supplier.setDisable(false);
        Supplier.getSelectionModel().clearSelection();
        Supplier.setItems(Clint_List);
        
        pono.setText("");
        pono.setDisable(false);
       
        podate.setValue(null);
        podate.setDisable(false);
        
        issuedate.setDisable(false);
        issuedate.setValue(LocalDate.now());
        States_List.clear();
        try {
            States_List.addAll(Invoice_Sql.getStatename());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        state.getSelectionModel().clearSelection();
        state.setDisable(false);
        state.setItems(States_List);
        
        Product_List.clear();
        try {
            Product_List.addAll(Invoice_Sql.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        product.getSelectionModel().clearSelection();
        product.setItems(Product_List);
        
        Desc.setText("");
        uom.getSelectionModel().clearSelection();
        uom.setItems(Uom_List);
        
        qty.setText("1");
        unitprize.setText(null);
        tax.getSelectionModel().clearSelection();
        tax.setDisable(false);
        tax.setItems(Tax_List);
        
        Invoice_discount_combo.setItems(Discount_List);
        Invoice_discount_combo.getSelectionModel().isSelected(2);
        
        
        discountAmount.setText("0");
        paymenttype.getSelectionModel().clearSelection();
        paymenttype.setItems(Payment_Type_List);
        paymenttype.getSelectionModel().select(0);
        
        amountpaid.setText("0");
        dueamount.setText("0");
        due_date.setValue(null);
        shippingp.setText("0");
        
        
        Invoice_shipping_amount.setText("0");
      
        Invoice_CGST_lbl.setText("0");
        Invoice_SGST_lbl.setText("0");
        Invoice_IGST_lbl.setText("0");
        Totalamount.setText("0");
        Invoice_CGST_lbl.setText("0");
        Invoice_Totalcost.setText("0");
        Invoice_Table_List.clear();
        
        new AutoCompleteComboBoxListener<>(Supplier);
        new AutoCompleteComboBoxListener<>(product);
        new AutoCompleteComboBoxListener<>(state);
    }
    
    public void Table_click_Calc(){
        Total_row_Amount = Float.parseFloat(unitprize.getText()) * Float.parseFloat(qty.getText());
        
        Connection connection = sqliteconnection.Connector();
        try {
            ResultSet rs = connection.createStatement().executeQuery("select State from CompanyDetailes desc limit 1");
                    
         
        
        if(state.getSelectionModel().isEmpty()){
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all Fields");
                alert.showAndWait();
           }
        else if(state.getSelectionModel().getSelectedItem().toString().equals(rs.getString(1))){
            if(tax.getSelectionModel().isEmpty()) {
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all Fields");
                alert.showAndWait();
            }
            else{
            switch(tax.getSelectionModel().getSelectedItem().toString()){
            
                case "Gst 5%":
                   UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.05)+Float.parseFloat(unitprize.getText()))));
                    break;
                case "Gst 12%":
                   UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.12)+Float.parseFloat(unitprize.getText()))));
                    break;
                case "Gst 18%":
                   UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.18)+Float.parseFloat(unitprize.getText()))));
                    break;
                case "Gst 28%":
                   UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.28)+Float.parseFloat(unitprize.getText()))));
                   break;
            }
        }
        }
        else{
            if(tax.getSelectionModel().isEmpty()) {
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all Fields");
                alert.showAndWait();
            }
            else{
                switch(tax.getSelectionModel().getSelectedItem().toString()){
                    case "Gst 5%":
            UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.05)+Float.parseFloat(unitprize.getText()))));
                          
                        break;
                    case "Gst 12%":
            UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.12)+Float.parseFloat(unitprize.getText()))));
                          
                        break;
                    case "Gst 18%":
            UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.18)+Float.parseFloat(unitprize.getText()))));
                          
                        break;
                    case "Gst 28%":
            UnitPrize_row_Amount = Float.parseFloat(Dformat.format(Math.round(Float.parseFloat(unitprize.getText()) - (Float.parseFloat(unitprize.getText())/(float)1.28)+Float.parseFloat(unitprize.getText()))));
                          
                        break;
                }
            }
        }   
        
        
        } catch (SQLException ex) {
            Logger.getLogger(PurchasePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Price_row_Amount = UnitPrize_row_Amount*Float.parseFloat(qty.getText());
            
    }
    
    
    public void On_Table_Add_Click(){
    
         
         if((product.getSelectionModel().isEmpty())||(uom.getSelectionModel().isEmpty())||(unitprize.getText().isEmpty())||(tax.getSelectionModel().isEmpty())||issuedate.getValue()==null){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Please Fill all Fields");
             alert.showAndWait();
        }
        else{
            Table_click_Calc();
            Invoice_Table_List.add(new InvoiceTableGet(Table_no_Genrator,
                    product.getSelectionModel().getSelectedItem().toString(),Desc.getText(),
                    uom.getSelectionModel().getSelectedItem().toString(),qty.getText(),
                    unitprize.getText(),BigDecimal.valueOf(Total_row_Amount).toPlainString(),
                    BigDecimal.valueOf(UnitPrize_row_Amount).toPlainString(),
                    BigDecimal.valueOf(Price_row_Amount).toPlainString()));
            
                ino.setCellValueFactory(new PropertyValueFactory<>("i"));
                itproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
                itdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
                ituom.setCellValueFactory(new PropertyValueFactory<>("uom"));
                itqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                itrate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
                ittotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
                itunitprize.setCellValueFactory(new PropertyValueFactory<>("unitprize"));
                itprize.setCellValueFactory(new PropertyValueFactory<>("prize"));
                
            InvoiceTable.setItems(Invoice_Table_List);
            After_Table_Add();
       }
    }
    
    //after adding items to table disable items
    public void After_Table_Add(){
        Table_no_Genrator++;
        
        product.getSelectionModel().clearSelection();
        uom.getSelectionModel().clearSelection();
        Desc.setText("");
        qty.setText("1");
        unitprize.setText(null);
        tax.setDisable(true);
        Invoicetype.setDisable(true);
        Supplier.setDisable(true);
        pono.setDisable(true);
        podate.setDisable(true);
        issuedate.setDisable(true);
        state.setDisable(true);
        Invoice_No.setDisable(true);
        
        
        SubTotal=(float)0;
            PriceTotal = (float)0;
            TotalTax = (float)0;
            for(int i =0;i<Invoice_Table_List.size();i++){
               SubTotal = SubTotal + Float.parseFloat(ittotal.getCellData(i));
               PriceTotal = PriceTotal + Float.parseFloat(itprize.getCellData(i));
            }
            TotalTax = PriceTotal-SubTotal;
            
            
            Invoice_discount_combo.setValue("nil");
            amountpaid.setText(""+BigDecimal.valueOf(PriceTotal).toPlainString());
           
            set_Remaining_items_After_table_Add();
    }
  
    //Setting remaining items
    public void set_Remaining_items_After_table_Add(){
        Invoice_discount_combo.setValue("nil");
        
        if(Invoice_discount_combo.getSelectionModel().isEmpty()){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Please Fill all Fields");
             alert.showAndWait();
        }
        else if(Invoice_discount_combo.getSelectionModel().getSelectedItem().toString().equals("%")){
              Total_Discount_Amount = ((float)(Float.parseFloat(discountAmount.getText())/(float)GrandTotal)*(float)100);
        }
        else if(Invoice_discount_combo.getSelectionModel().getSelectedItem().toString().equals("RS")){
              Total_Discount_Amount = Float.parseFloat(discountAmount.getText());
        }
        else if(Invoice_discount_combo.getSelectionModel().getSelectedItem().toString().equals("nil")){
              discountAmount.setText("0");
              discountAmount.setDisable(true);
              Total_Discount_Amount = Float.parseFloat(discountAmount.getText());
        }
      
        if(state.getSelectionModel().isEmpty()){
        Prompt_pane.setVisible(true);
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Please Fill all Fields");
             alert.showAndWait();
        }
        else if(state.getSelectionModel().getSelectedItem().toString().equals("Rajasthan")){
            if(tax.getSelectionModel().isEmpty()) {
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all Fields");
                alert.showAndWait();
            } 
            else{
                    Total_CGST = TotalTax/2;
                    Total_SGST = TotalTax/2;
                    Total_IGST = (float)0;
                    GrandTotal = SubTotal +Total_CGST+Total_SGST-Total_Discount_Amount;
                    Totalamount.setText(""+BigDecimal.valueOf(SubTotal).toPlainString());
                    Tax_payble_lbl.setText(""+BigDecimal.valueOf(SubTotal).toPlainString());
                    Invoice_CGST_lbl.setText(""+BigDecimal.valueOf(Total_CGST).toPlainString());
                    Invoice_SGST_lbl.setText(""+BigDecimal.valueOf(Total_SGST).toPlainString());
                    Invoice_IGST_lbl.setText(""+BigDecimal.valueOf(Total_IGST).toPlainString());
                    Invoice_Totalcost.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                }
        }
        else if(!(state.getSelectionModel().getSelectedItem().toString().equals("Rajasthan"))){
            if(tax.getSelectionModel().isEmpty()) {
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all Fields");
                alert.showAndWait();
            }
            else{
                    Total_CGST = (float)0;
                    Total_SGST= (float)0;
                    Total_IGST = TotalTax;
                    GrandTotal = SubTotal +Total_IGST-Total_Discount_Amount;
                    Totalamount.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                    Tax_payble_lbl.setText(""+BigDecimal.valueOf(SubTotal).toPlainString());
                    Invoice_CGST_lbl.setText(""+BigDecimal.valueOf(Total_CGST).toPlainString());
                    Invoice_SGST_lbl.setText(""+BigDecimal.valueOf(Total_SGST).toPlainString());
                    Invoice_IGST_lbl.setText(""+BigDecimal.valueOf(Total_IGST).toPlainString());
                    Invoice_Totalcost.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                    
            }
        }
        
        if(Integer.parseInt(dueamount.getText())==0 && Float.parseFloat(amountpaid.getText())!=Float.parseFloat(Totalamount.getText())){
                amountpaid.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
        }
    }
    public void On_Remove_Clicked(){
        
        if(Invoice_Table_List.isEmpty()){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("There Is No Item In Table To Remove");
             alert.showAndWait();
        }
        else if(InvoiceTable.getSelectionModel().isEmpty()){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Please Select a Item From Table");
             alert.showAndWait();
        }
        else{
        ObservableList<InvoiceTableGet> TableProduct,allproduct;
        
        allproduct = InvoiceTable.getItems();
        TableProduct = InvoiceTable.getSelectionModel().getSelectedItems();
        TableProduct.forEach(allproduct::remove);
        Invoice_Table_List.remove(product);
        
        After_Table_Remove();
        }
    } 
      //after adding items to table disable items
    public void After_Table_Remove(){
        SubTotal=(float)0;
        PriceTotal=(float)0;
        for(int i =0;i<Invoice_Table_List.size();i++){
                SubTotal = SubTotal + Float.parseFloat(ittotal.getCellData(i));
                PriceTotal = PriceTotal + Float.parseFloat(itprize.getCellData(i));
            }
        TotalTax = PriceTotal-SubTotal;
            Invoice_discount_combo.setValue("nil");
            amountpaid.setText(""+BigDecimal.valueOf(SubTotal).toPlainString());
        set_Remaining_items_After_table_Add();
        if(Invoice_Table_List.isEmpty()){
            tax.setDisable(false);
            Invoicetype.setDisable(false);
            Supplier.setDisable(false);
            pono.setDisable(false);
            podate.setDisable(false);
            issuedate.setDisable(false);
            state.setDisable(false);
            Invoice_No.setDisable(false);
           
        }
    }
    @FXML
    private void Invoice_discont_combo_change(ActionEvent event) {
        switch (Invoice_discount_combo.getSelectionModel().getSelectedItem().toString()) {
            case "nil":
                discountAmount.setText("0");
                discountAmount.setDisable(true);
                break;
            case "%":
                discountAmount.setText("0");
                discountAmount.setDisable(false);
                break;
            case "RS":
                discountAmount.setText("0");
                discountAmount.setDisable(false);
                break;
            default:
                break;
        }
    }

    @FXML
    private void On_Discount_Amount_Change(KeyEvent event) {
        if(Float.parseFloat(discountAmount.getText())> Float.parseFloat(Totalamount.getText())){
            alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Amount paid cant be bigger than due amount");
             alert.showAndWait();
            
       }
       else{
       Invoice_Totalcost.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText()) - Float.parseFloat(discountAmount.getText())).toPlainString());
       invoice_prompt_text.setText("");
       }    
    }

    

    @FXML
    private void On_Shipping_Cost_Change(KeyEvent event) {
        Invoice_shipping_amount.setText(shippingp.getText());
        try{
            Invoice_Totalcost.setText(""+BigDecimal.valueOf(Float.parseFloat(shippingp.getText())+Float.parseFloat(Totalamount.getText())).toPlainString());
        }
        catch(NumberFormatException e){
            
            Invoice_Totalcost.setText(Totalamount.getText());
            Invoice_shipping_amount.setText("0");
        }
    }

    @FXML
    private void On_Amount_Paid_Change(KeyEvent event) {
        if(Float.parseFloat(amountpaid.getText())> Float.parseFloat(Totalamount.getText())){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Amount paid cant be bigger than Total Amount amount");
             alert.showAndWait();
            
        }
        else{
        
        dueamount.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText())- Float.parseFloat(amountpaid.getText())).toPlainString());
        invoice_prompt_text.setText("");
        }
    }

    @FXML
    private void On_Due_Amount_Change(KeyEvent event) {
        try{
        if(Float.parseFloat(dueamount.getText())> Float.parseFloat(Totalamount.getText())){
            alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Due Amount Cant Be Bigger Than Total Amount");
             alert.showAndWait();
            }
        else if (Float.parseFloat(dueamount.getText())<Float.parseFloat(Totalamount.getText())){
            amountpaid.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText())- Float.parseFloat(dueamount.getText())).toPlainString());
            invoice_prompt_text.setText("");
            }
        }
        catch(NumberFormatException e){
                amountpaid.setText(Totalamount.getText());
        }
    }

    @FXML
    private void On_Due_Date_Change(ActionEvent event) {
        System.out.print(issuedate.getValue().getMonthValue());
        if(due_date.getValue().getYear() < issuedate.getValue().getYear()||
                due_date.getValue().getMonthValue() < issuedate.getValue().getMonthValue()
                || due_date.getValue().getDayOfMonth() < issuedate.getValue().getDayOfMonth()
                ){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Due Date Is Before Than Issue Date");
             alert.showAndWait();
            due_date.setValue(null);
            
        } 
    }

    

    @FXML
    private void On_Cost_Change(KeyEvent event) {
        if(Float.parseFloat(Pro_cp.getText())>Float.parseFloat(Pro_sp.getText())){
             alert.setTitle("Alert");
             alert.setHeaderText(null);
             alert.setContentText("Cost Prize Is Grater Than Sales Price");
             alert.showAndWait();
        }
    }
    
    @FXML
    private void SaveBill(ActionEvent event){
        
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            view_sql = new View_Sql();
            Invoice_Sql = new newInvoiceSql();
       
        } catch (SQLException ex) {
            Logger.getLogger(PurchasePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setInvoice_Combos();
            Set_Purchase_Pane();
        
    }    

    
    
}
