/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import Sql.*;
import TableController.InvoiceTableGet;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
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
public class ChallanPaneController implements Initializable {

   
    @FXML public AnchorPane Main_inovice_Pane;
    @FXML public AnchorPane Invoicepane;
    @FXML public Label invoice_type_lbl;
    @FXML public ComboBox Invoicetype;
    @FXML public ComboBox Clint;
    @FXML public TextField pono;
    @FXML public DatePicker podate;
    @FXML public DatePicker issuedate;
    @FXML public Label Productlabel;
    @FXML public TextField Desc;
    @FXML public ComboBox uom;
    @FXML public TextField qty;
    @FXML public TextField unitprize;
    @FXML public ComboBox Invoice_discount_combo;
    @FXML public TextField discountAmount;
    @FXML public ComboBox tax;
    
    @FXML public TableView<InvoiceTableGet> InvoiceTable;
    @FXML public TableColumn<InvoiceTableGet, Integer> ino;
    @FXML public TableColumn<InvoiceTableGet, String> itproduct;
    @FXML public TableColumn<InvoiceTableGet, String> itdesc;
    @FXML public TableColumn<InvoiceTableGet, String> ituom;
    @FXML public TableColumn<InvoiceTableGet, String> itqty;
    @FXML public TableColumn<InvoiceTableGet, String> itrate;
    @FXML public TableColumn<InvoiceTableGet, String> ittotal;
    @FXML public TableColumn<InvoiceTableGet,String> itunitprize;
    @FXML public TableColumn<InvoiceTableGet,String> itprize;
    
    @FXML public TextField shippingp;
    @FXML public TextField amountpaid;
    @FXML public ComboBox paymenttype;
    @FXML public TextField dueamount;
    @FXML public TextField vehicleno;
    @FXML public DatePicker Dateofdeparture;
    @FXML public Label Totalamount;
    @FXML public ComboBox state;
    @FXML public ComboBox product;
    @FXML public Label invoice_prompt_text;
    @FXML public DatePicker due_date;
    @FXML public Label Invoice_billno_lbl;
    @FXML public TextField Invoice_billno;
    @FXML public Label Invoice_IGST_lbl;
    @FXML public Label Invoice_SGST_lbl;
    @FXML public Label Invoice_CGST_lbl;
    @FXML public Label Invoice_shipping_amount;
    @FXML public Label Invoice_Totalcost;
    @FXML public Button Priview;
    @FXML public TextField Add_name;
    @FXML public TextField Add_Email;
    @FXML public TextField Add_GstIn;
    @FXML public TextField Add_Pan;
    @FXML public TextField Add_ContactName;
    @FXML public TextField Add_ContactNo;
    @FXML public TextArea Add_Address;
    @FXML public ComboBox Add_State;
    @FXML public ComboBox Add_City;
    @FXML public Label Add_Prompt;
    @FXML public Button Close_Add_product;
    @FXML public ComboBox Pro_UOM;
    @FXML public TextField Pro_Product;
    @FXML public ComboBox Pro_Tax;
    @FXML public Label Product_Prompt;
    @FXML public TextField Pro_HSN;
    @FXML public TextField Pro_sp;
    @FXML public TextField Pro_cp;
    @FXML public Button Add_Into_Table;
    
    @FXML public Button Preview_Bill;
    @FXML public Button Add_Client;
    @FXML public Button Add_Product;
    @FXML public Button Remove_From_Table;
    @FXML public AnchorPane Add_Client_Anc;
    @FXML public AnchorPane Add_Product_Anc;
    @FXML public AnchorPane Prompt_pane;
    @FXML public Label Prompt_lable;
    @FXML public Button Close_Prompt;
    @FXML public Button Close_Client;
    @FXML public Button Add_Clint_Into_Db;
    @FXML public Button Add_Product_Into_Db;
    
    
    //Sql Class
    public View_Sql view_sql;
    public newInvoiceSql Invoice_Sql;
    public PrintClass print = new PrintClass();
    
    
    //Variables Used 
    Integer Product_no_genrated;
    Integer Client_no_genrated;
    Integer Table_no_Genrator = 1;
    
    
    //Table Elements calculation 
    Float Rate_row_Amount;
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
    
    
    
    public ObservableList Invoice_Type_List = FXCollections.observableArrayList("Services","Goods");
    public ObservableList Clint_List = FXCollections.observableArrayList();
    public ObservableList Uom_List = FXCollections.observableArrayList("Pices");
    public ObservableList Discount_List = FXCollections.observableArrayList("%","RS","nil");
    public ObservableList Tax_List = FXCollections.observableArrayList("Gst 5%","Gst 12%","Gst 18%","Gst 28%");
    public ObservableList Payment_Type_List = FXCollections.observableArrayList("Cash");
    public ObservableList States_List = FXCollections.observableArrayList("");
    public ObservableList Product_List = FXCollections.observableArrayList();
    public ObservableList Invoice_Table_List = FXCollections.observableArrayList();
   
    
    
    
    @FXML
    public void ButtonEventHandler(ActionEvent event){
        if(event.getSource() == Add_Client){
            try {
                //open clint window
                Set_Client_Pane();
            } catch (SQLException ex) {
                Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Add_Client_Anc.setVisible(true);
            Invoicepane.setDisable(true);
            new AutoCompleteComboBoxListener<>(Add_State);
            new AutoCompleteComboBoxListener<>(Add_City);
            
        }
        else if(event.getSource()==Add_Product){
            try {
                //open product window
                SetproductPane();
            } catch (SQLException ex) {
                Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Pro_UOM.setItems(Uom_List);
            Pro_Tax.setItems(Tax_List);
            Add_Product_Anc.setVisible(true);
            Invoicepane.setDisable(true);
            new AutoCompleteComboBoxListener<>(Pro_UOM);
            
        }
        else if(event.getSource()== Close_Prompt){
            //Close Prompt 
            Prompt_pane.setVisible(false);
        }
        else if(event.getSource()== Close_Add_product){
            //Close Add new Product
            Add_Product_Anc.setVisible(false);
            Invoicepane.setDisable(false);
        }
        else if(event.getSource()== Close_Client){
            //Close Add new Client
            Add_Client_Anc.setVisible(false);
            Invoicepane.setDisable(false);
        }
        else if(event.getSource()== Add_Clint_Into_Db){
            try {
                //Save into Database Clint
                On_Client_Add_Into_Db();
            } catch (SQLException ex) {
                Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setInvoice_Combos();
        }
        else if(event.getSource()== Add_Product_Into_Db){
            //Save into Product database
            try {
                On_Product_Add_Into_DB();
            } catch (SQLException ex) {
                Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Add_Product_Anc.setVisible(false);
            Invoicepane.setDisable(false);
            setInvoice_Combos();
        }
        else if(event.getSource()== Remove_From_Table){
            //remove element from tavle
            On_Remove_Clicked();
        }
        else if(event.getSource()== Add_Into_Table){
            // add data to table
            On_Table_Add_Click();
        }
        else if(event.getSource()== Preview_Bill){
            //taking assurance
            Priview.setVisible(true);
            Prompt_pane.setVisible(true);
            Prompt_lable.setText("Please check All Fiels For Last Time");
            
        }
        else if(event.getSource()== Priview){
            try {
                //Saving bill database

                Genrate_Save_Bill();
            } catch (SQLException ex) {
                Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            setInvoice_Pane();
        }
    }
    //genrate Billno
    public void Set_Genrate_Challan_no() throws SQLException{
        if(Invoice_Sql.get_ChallanNo()== null){
                Invoice_billno.setText("1");
        }
                else{
                Invoice_billno.setText(""+(Invoice_Sql.get_ChallanNo()+1));
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
        Clint.setDisable(false);
        Clint.getSelectionModel().clearSelection();
        Clint.setItems(Clint_List);
        
        Product_List.clear();
        try {
            Product_List.addAll(Invoice_Sql.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        product.getSelectionModel().clearSelection();
        product.setItems(Product_List);
        
        new AutoCompleteComboBoxListener<>(Clint);
        new AutoCompleteComboBoxListener<>(product);
    }
    //setting invoice Pane
    public void setInvoice_Pane(){
        Invoicetype.getSelectionModel().clearSelection();
        Invoicetype.setDisable(false);
        Invoicetype.setItems(Invoice_Type_List);
        
        Clint_List.clear();
        
        try {
            Clint_List.addAll(Invoice_Sql.getClintnames());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Clint.setDisable(false);
        Clint.getSelectionModel().clearSelection();
        Clint.setItems(Clint_List);
        
        pono.setText("");
        pono.setDisable(false);
       
        podate.setValue(null);
        podate.setDisable(false);
        
        issuedate.setValue(null);
        issuedate.setDisable(false);
        
        States_List.clear();
        try {
            States_List.addAll(Invoice_Sql.getStatename());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        state.getSelectionModel().clearSelection();
        state.setDisable(false);
        state.setItems(States_List);
         
        try{
        Set_Genrate_Challan_no();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
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
        try{
         InvoiceTable.setItems(null);
        }
        catch(Exception e){}
        Invoice_discount_combo.setItems(Discount_List);
        Invoice_discount_combo.getSelectionModel().isSelected(2);
        
        
        discountAmount.setText("0");
        paymenttype.getSelectionModel().clearSelection();
        paymenttype.setItems(Payment_Type_List);
        
        amountpaid.setText("0");
        dueamount.setText("0");
        due_date.setValue(null);
        shippingp.setText("0");
        vehicleno.setText("");
        
        Invoice_shipping_amount.setText("0");
        Dateofdeparture.setValue(null);
        Invoice_CGST_lbl.setText("0");
        Invoice_SGST_lbl.setText("0");
        Invoice_IGST_lbl.setText("0");
        Totalamount.setText("0");
        Invoice_CGST_lbl.setText("0");
        Invoice_Totalcost.setText("0");
        Invoice_Table_List.clear();
        
        new AutoCompleteComboBoxListener<>(Clint);
        new AutoCompleteComboBoxListener<>(product);
        new AutoCompleteComboBoxListener<>(state);
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
        Add_City.setItems(States_List);
        Add_State.setItems(States_List);
        Add_ContactName.setText("");
        Add_ContactNo.setText(null);
        Add_Email.setText("");
        Add_GstIn.setText("");
        Add_Pan.setText("");
    }
    
    public void On_Remove_Clicked(){
        ObservableList<InvoiceTableGet> TableProduct,allproduct;
        
        allproduct = InvoiceTable.getItems();
        TableProduct = InvoiceTable.getSelectionModel().getSelectedItems();
        TableProduct.forEach(allproduct::remove);
        Invoice_Table_List.remove(product);
        
        After_Table_Remove();
        
    }
    public void On_Table_Add_Click(){
         if((product.getSelectionModel().isEmpty())||(uom.getSelectionModel().isEmpty())||(unitprize.getText().isEmpty())||(tax.getSelectionModel().isEmpty())||issuedate.getValue()==null){
             Prompt_pane.setVisible(true);
             Prompt_lable.setText("Please Fill all Fields");
             Prompt_pane.toFront();
            }
        else{
            Table_Add_Calc();
            Total_row_Amount = Rate_row_Amount*Float.parseFloat(qty.getText());
            Price_row_Amount = Float.parseFloat(unitprize.getText())*Float.parseFloat(qty.getText());
            Invoice_Table_List.add(new InvoiceTableGet(Table_no_Genrator,product.getSelectionModel().getSelectedItem().toString(),Desc.getText(),uom.getSelectionModel().getSelectedItem().toString(),qty.getText(),BigDecimal.valueOf(Rate_row_Amount).toPlainString(),BigDecimal.valueOf(Total_row_Amount).toPlainString(),unitprize.getText(),BigDecimal.valueOf(Price_row_Amount).toPlainString()));
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
        Clint.setDisable(true);
        pono.setDisable(true);
        podate.setDisable(true);
        issuedate.setDisable(true);
        state.setDisable(true);
        Invoice_billno.setDisable(true);
        
        
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
             Prompt_pane.setVisible(true);
             Prompt_lable.setText("Please Fill all Fields");
             Prompt_pane.toFront();
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
             Prompt_lable.setText("Please Fill all Fields");
             Prompt_pane.toFront();
        }
        else if(state.getSelectionModel().getSelectedItem().toString().equals("Rajasthan")){
            if(tax.getSelectionModel().isEmpty()) {
                Prompt_pane.setVisible(true);
             Prompt_lable.setText("Please Fill all Fields");
             Prompt_pane.toFront();
            } 
            else{
                    Total_CGST = TotalTax/2;
                    Total_SGST = TotalTax/2;
                    Total_IGST = (float)0;
                    GrandTotal = SubTotal +Total_CGST+Total_SGST-Total_Discount_Amount;
                    Totalamount.setText(""+BigDecimal.valueOf(SubTotal).toPlainString());
                    Invoice_CGST_lbl.setText(""+BigDecimal.valueOf(Total_CGST).toPlainString());
                    Invoice_SGST_lbl.setText(""+BigDecimal.valueOf(Total_SGST).toPlainString());
                    Invoice_IGST_lbl.setText(""+BigDecimal.valueOf(Total_IGST).toPlainString());
                    Invoice_Totalcost.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                }
        }
        else{
            if(tax.getSelectionModel().isEmpty()) {
            Prompt_pane.setVisible(true);
             Prompt_lable.setText("Please Fill all Fields");
             Prompt_pane.toFront();
            }
            else{
                
                    Total_CGST = (float)0;
                    Total_SGST= (float)0;
                    Total_IGST = TotalTax;
                    GrandTotal = SubTotal +Total_IGST-Total_Discount_Amount;
                    Totalamount.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                    Invoice_CGST_lbl.setText(""+BigDecimal.valueOf(Total_CGST).toPlainString());
                    Invoice_SGST_lbl.setText(""+BigDecimal.valueOf(Total_SGST).toPlainString());
                    Invoice_IGST_lbl.setText(""+BigDecimal.valueOf(Total_IGST).toPlainString());
                    Invoice_Totalcost.setText(""+BigDecimal.valueOf(GrandTotal).toPlainString());
                    
            }
        }
        
        if(Integer.parseInt(dueamount.getText())==0 && Float.parseFloat(amountpaid.getText())!=Float.parseFloat(Totalamount.getText())){
                amountpaid.setText(Totalamount.getText());
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
            Clint.setDisable(false);
            pono.setDisable(false);
            podate.setDisable(false);
            issuedate.setDisable(false);
            state.setDisable(false);
            Invoice_billno.setDisable(false);
           
        }
    }
    
    //Add data to Product Data Base
    public void On_Product_Add_Into_DB() throws SQLException{
        
    if(Pro_UOM.getSelectionModel().isEmpty()||Pro_Tax.getSelectionModel().isEmpty()){
        Prompt_pane.setVisible(true);
        Prompt_lable.setText("Please Fill All Fields");
        Prompt_pane.toFront();
        
        
    }
    else{
                view_sql.Add_Product_Detailes(Product_no_genrated,
                Pro_Product.getText(),
                Pro_HSN.getText(),
                Pro_UOM.getSelectionModel().getSelectedItem().toString(),
                Pro_Tax.getSelectionModel().getSelectedItem().toString(),
                Pro_cp.getText(),Pro_sp.getText());
                
                Add_Client_Anc.setVisible(false);
            Invoicepane.setDisable(false);

        }
    }
    //Add data to Client Data Base
    public void On_Client_Add_Into_Db() throws SQLException{
        view_sql.Add_Client_into_db(Client_no_genrated,Add_name.getText(),Add_ContactNo.getText(),Add_GstIn.getText(),Add_Email.getText(),
                Add_Address.getText(), Add_State.getSelectionModel().getSelectedItem().toString(),Add_City.getSelectionModel().getSelectedItem().toString(), 
                Add_ContactName.getText(),Add_Pan.getText());
    }
    
    //Calculation for Table add
    public void Table_Add_Calc(){
           if(state.getSelectionModel().isEmpty()){
               Prompt_pane.setVisible(true);
               Prompt_lable.setText("Please fill all the fields");
               Prompt_pane.toFront();
           }
        else if(state.getSelectionModel().getSelectedItem().toString().equals("Rajasthan")){
            if(tax.getSelectionModel().isEmpty()) {
               Prompt_pane.setVisible(true);
               Prompt_lable.setText("Please fill all the fields");
               Prompt_pane.toFront();
            }
            else{
            switch(tax.getSelectionModel().getSelectedItem().toString()){
            
                case "Gst 5%":
                    Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.05;
                    
                    break;
                case "Gst 12%":
                    Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.12;
                    break;
                case "Gst 18%":
                    Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.18;
                    break;
                case "Gst 28%":
                    Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.28;
                    break;
            }
        }
        }
        else{
            if(tax.getSelectionModel().isEmpty()) {
               Prompt_pane.setVisible(true);
               Prompt_lable.setText("Please fill all the fields");
               Prompt_pane.toFront();
            }
            else{
                switch(tax.getSelectionModel().getSelectedItem().toString()){
                    case "Gst 5%":
                        Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.05;
                        break;
                    case "Gst 12%":
                        Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.12;
                        break;
                    case "Gst 18%":
                        Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.18;
                        break;
                    case "Gst 28%":
                        Rate_row_Amount = Float.parseFloat(unitprize.getText())/(float)1.28;
                        break;
                }
            }
        }   
        
    }
    
    
    @FXML
    public void On_Discount_Amount_Change(KeyEvent event) {
        if(Float.parseFloat(discountAmount.getText())> Float.parseFloat(Totalamount.getText())){
            Prompt_pane.setVisible(true);
            Prompt_pane.toFront();
            Prompt_lable.setText("Amount paid cant be bigger than due amount");
            
       }
       else{
       Invoice_Totalcost.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText()) - Float.parseFloat(discountAmount.getText())).toPlainString());
       invoice_prompt_text.setText("");
       }    
    }
    @FXML 
    public void On_Amount_Paid_Change(){
        if(Float.parseFloat(amountpaid.getText())> Float.parseFloat(Totalamount.getText())){
            Prompt_pane.setVisible(true);
            Prompt_pane.toFront();
            Prompt_lable.setText("Amount paid cant be bigger than due amount");
            
        }
        else{
        
        dueamount.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText())- Float.parseFloat(amountpaid.getText())).toPlainString());
        invoice_prompt_text.setText("");
        }
    }
    @FXML 
    public void On_Due_Amount_Change(){
        if(Float.parseFloat(dueamount.getText())> Float.parseFloat(Totalamount.getText())){
            Prompt_pane.setVisible(true);
            Prompt_pane.toFront();
            Prompt_lable.setText("Dueamount cant be bigger than Total amount");
        }else{
        
        amountpaid.setText(""+BigDecimal.valueOf(Float.parseFloat(Totalamount.getText())- Float.parseFloat(dueamount.getText())).toPlainString());
        invoice_prompt_text.setText("");
        }
    }
    @FXML
    public void On_Due_Date_Change(){
        System.out.print(issuedate.getValue().getMonthValue());
        if(due_date.getValue().getYear() < issuedate.getValue().getYear()||
                due_date.getValue().getMonthValue() < issuedate.getValue().getMonthValue()
                || due_date.getValue().getDayOfMonth() < issuedate.getValue().getDayOfMonth()
                ){
            Prompt_pane.setVisible(true);
            Prompt_pane.toFront();
            Prompt_lable.setText("Due date is before issue date");
            due_date.setValue(null);
            
        } 
    }
    @FXML
    public void On_Shipping_Cost_Change(){
         Invoice_shipping_amount.setText(shippingp.getText());
        try{
            Invoice_Totalcost.setText(""+BigDecimal.valueOf(Float.parseFloat(shippingp.getText())+Float.parseFloat(Totalamount.getText())).toPlainString());
        }
        catch(NumberFormatException e){
            shippingp.setText("0");
            Invoice_Totalcost.setText(""+BigDecimal.valueOf(Float.parseFloat(shippingp.getText())+Float.parseFloat(Totalamount.getText())).toPlainString());
            Invoice_shipping_amount.setText(shippingp.getText());
        }
    }
    @FXML
    public void On_Cost_Change(KeyEvent event) {
         if(Float.parseFloat(Pro_cp.getText())>Float.parseFloat(Pro_sp.getText())){
             Prompt_pane.setVisible(true);
                Prompt_lable.setText("Cost Price is Greater than Sales Price");
                Prompt_pane.toFront();
        }
    }
    @FXML 
    public void Invoice_discont_combo_change(ActionEvent event){
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
   //Challan no. search in database
    public Boolean ChallanNoSearch() throws SQLException{
        ObservableList list =FXCollections.observableArrayList();
        list.add(Invoice_Sql.get_ChallanNo());
        for(int j = 0;j<list.size();j++){
            if(Invoice_billno.getText() == list.get(j)){
                return true;
            }
            
        }
        return false;
    }
    
    //genrating and Saving Bill
    @FXML
    public void Genrate_Save_Bill() throws SQLException
    {
        if(paymenttype.getSelectionModel().isEmpty()){
                    Prompt_pane.setVisible(true);
                    Prompt_pane.toFront();
                    Prompt_lable.setText("Please fill all Fields");
        }
        else{
            
        }
         boolean Challancheck = ChallanNoSearch();
                 //Checking bill no is present in list or not 
                boolean Billcheck = ChallanNoSearch();
                if(Billcheck == true)
                {
                    Prompt_pane.setVisible(true);
                    Prompt_pane.toFront();
                    Prompt_lable.setText("Challan no. already exist please try anothor Challan no.");
                }
                else
                {
                    Invoice_Sql.Temp_Challan_ClintDetailes_clear();
                    Invoice_Sql.Temp_Challan_Product_detailes_clear();
                    Invoice_Sql.Temp_Challan_Amount_detailes_clear();
                    //Saving bill no in Bill table
                    Invoice_Sql.Set_ChallanNo(Integer.parseInt(Invoice_billno.getText()));
                    //

                    //Saving clint data in db
                    if(Invoicetype.getSelectionModel().isEmpty() || Clint.getSelectionModel().isEmpty()
                           ||issuedate.getValue() == null || state.getSelectionModel().isEmpty())
                    {
                        Prompt_pane.setVisible(true);
                        Prompt_pane.toFront();
                        Prompt_lable.setText("Please fill all Fields : " + " Invoice type "+"Clint Name "+"Issue Date "
                        + "State");
                    }
                    else if(pono.getText() == null || podate.getValue() ==null)
                    {
                            Invoice_Sql.Upload_Challan_Clint_detailes(
                            Integer.parseInt(Invoice_billno.getText()),
                            Invoicetype.getSelectionModel().getSelectedItem().toString(),
                            Clint.getSelectionModel().getSelectedItem().toString(),
                            "",
                            "",
                            issuedate.getValue().toString(),
                            state.getSelectionModel().getSelectedItem().toString());
                    }
                    else
                    {
                            Invoice_Sql.Upload_Challan_Clint_detailes(
                            Integer.parseInt(Invoice_billno.getText()),
                            Invoicetype.getSelectionModel().getSelectedItem().toString(),
                            Clint.getSelectionModel().getSelectedItem().toString(),
                            pono.getText(),
                            podate.getValue().toString(),
                            issuedate.getValue().toString(),
                            state.getSelectionModel().getSelectedItem().toString());
                    }
                    //

                    //saving product data in db
                    for(int i=0;i<Invoice_Table_List.size();i++)
                    {
                              Invoice_Sql.Upload_Challan_product_detailes(
                              Integer.parseInt(Invoice_billno.getText()), 
                              ino.getCellData(i).toString(),
                              itproduct.getCellData(i),
                              itdesc.getCellData(i),
                              ituom.getCellData(i),
                              itqty.getCellData(i),
                              itrate.getCellData(i), 
                              ittotal.getCellData(i),
                              Float.parseFloat(itunitprize.getCellData(i)),
                              Float.parseFloat(itprize.getCellData(i)));
                    }
                    //

                   //saving amount data in db 
                   if(Invoice_discount_combo.getSelectionModel().isEmpty()
                      || due_date.getValue() == null || vehicleno.getText()== null 
                      || Dateofdeparture.getValue() == null)
                   {
                           Invoice_Sql.Upload_Challan_Amount_Detailes(
                           Integer.parseInt(Invoice_billno.getText()),
                           "0",
                           paymenttype.getSelectionModel().getSelectedItem().toString(),
                           amountpaid.getText(),
                           dueamount.getText(),
                           "",
                           "",
                           "",
                           Invoice_CGST_lbl.getText(),
                           Invoice_SGST_lbl.getText(),
                           Invoice_IGST_lbl.getText(),
                           Totalamount.getText(),
                           Invoice_shipping_amount.getText(),
                           Invoice_Totalcost.getText(),
                           SubTotal.toString());
                    }
                   else
                   {
                           Invoice_Sql.Upload_Challan_Amount_Detailes(
                           Integer.parseInt(Invoice_billno.getText()),
                           discountAmount.getText(),
                           paymenttype.getSelectionModel().getSelectedItem().toString(),
                           amountpaid.getText(),
                           dueamount.getText(),
                           due_date.getValue().toString(),
                           vehicleno.getText(),
                           Dateofdeparture.getValue().toString(),
                           Invoice_CGST_lbl.getText(),
                           Invoice_SGST_lbl.getText(),
                           Invoice_IGST_lbl.getText(),
                           Totalamount.getText(),
                           Invoice_shipping_amount.getText(),
                           Invoice_Totalcost.getText(),
                           SubTotal.toString());
                    }
                    //

                }
                Prompt_pane.setVisible(true);
                Prompt_pane.toFront();
                Prompt_lable.setText("Detailes are added");
                Invoice_Sql.Temp_Challan_Product_detailes(Integer.parseInt(Invoice_billno.getText()));
                Table_no_Genrator=1;
                print.showReport("/Reports/Challan.jasper");
                setInvoice_Pane();
    }
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Intitialize sql class
        try {
            view_sql = new View_Sql();
            Invoice_Sql = new newInvoiceSql();
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
        
        setInvoice_Pane();
        
    }
}
