/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewUI;

import Sql.newInvoiceSql;
import Sql.sqliteconnection;
import TableController.InvoiceTableGet;
import TableController.view_Invoice_table_get;
import UIController.InvoicePaneController;
import com.lowagie.text.Row;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.table.TableFilter;

import projectteck5.*;


/**
 * FXML Controller class
 *
 * @author rohan
 */
public class Invoice_ViewController implements Initializable {

    @FXML private TableView<view_Invoice_table_get> View_invoice_Table;
    @FXML private TableColumn<view_Invoice_table_get, Integer> Sno;
    @FXML private TableColumn<view_Invoice_table_get, Integer> tBillNO;
    @FXML private TableColumn<view_Invoice_table_get, String> tClient;
    @FXML private TableColumn<view_Invoice_table_get, String> tIssueDate;
    @FXML private TableColumn<view_Invoice_table_get, String> tState;
    @FXML private TableColumn<view_Invoice_table_get, String> tCgst;
    @FXML private TableColumn<view_Invoice_table_get, String> tSgst;
    @FXML private TableColumn<view_Invoice_table_get, String> tigst;
    @FXML private TableColumn<view_Invoice_table_get, String> tTotalAmount;
    @FXML private TableColumn<view_Invoice_table_get, String> tTotalCost;
    @FXML private TableColumn<view_Invoice_table_get, String> tShippingCost;
    @FXML private TableColumn<view_Invoice_table_get, String> tDisCount;
    @FXML private TableColumn<view_Invoice_table_get, String> tGstIn;
    @FXML private TableColumn<view_Invoice_table_get, String> tCgstPer;
    @FXML private TableColumn<view_Invoice_table_get, String> tSgstper;
    
    
    
    
    @FXML private Button Refresh;
    @FXML private Button OnEdit;
    
    @FXML private AnchorPane ViewInvoice_Base;
    @FXML private DatePicker FromDate;
    @FXML private DatePicker ToDate;
    @FXML private Button SearchButton;
    @FXML public Button GenrateBill;
    private TextField search;
    @FXML private Button Add_New_Invoice;
    @FXML private Button ExportToExcel;
    Mediator mid;
    
    TableFilter<view_Invoice_table_get> filter;
    Parent purchase;
    FXMLLoader loader = new FXMLLoader();
    Stage stage = new Stage();
    
    
    InvoicePaneController InvoicePane;
    AnchorPane NewInvoiceAnc;
    //PrintClass print;
    newInvoiceSql sql; 
    AnchorPane GenricPane;
    
    
    //other variable
    Integer TableIncrementor = 0;
    Double CgstTax;
    Double SgstTax;
    DecimalFormat Dformat = new DecimalFormat("0.00");
    Date date= new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    Alert alert = new Alert(AlertType.INFORMATION);
    
    
    public ObservableList table_List = FXCollections.observableArrayList();
   
    
    @FXML
    public void ButtonEventHandler(ActionEvent event) throws SQLException{
       if(event.getSource() == Refresh){
            On_Refresh_Click();
       }
       else if(event.getSource() == OnEdit){
           try {
               On_Edit_Click(event);
           } catch (IOException ex) {
               Logger.getLogger(Invoice_ViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       else if(event.getSource() == SearchButton){
           onSearch_Btn_Click();
       }
       else if(event.getSource() == GenrateBill){
           On_Genrate_Bill_Click();
       }
       else if(event.getSource() == Add_New_Invoice){
           
           On_Add_New_Invoice_Click();
            
       }
       else if(event.getSource() == ExportToExcel){
           try {
               On_Export_to_Excel_Click();
           } catch (IOException ex) {
               Logger.getLogger(Invoice_ViewController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    
    
    public void On_textfield_Change(KeyEvent ke){
        
        FilteredList<view_Invoice_table_get> filterData = new FilteredList<>(table_List,p->true);
        search.textProperty().addListener((observable,oldvalue,newvalue)-> {
            filterData.setPredicate(pers -> {
                
                if(newvalue == null || newvalue.isEmpty()){
                    return true;
                }
                
                String typedTxet = newvalue.toLowerCase();
               
                if(pers.getClient().toLowerCase().contains(typedTxet)){
                    return true;
                }
                        
                        
                return false;
             });
            SortedList<view_Invoice_table_get> sorted = new SortedList<>(filterData);
            sorted.comparatorProperty().bind(View_invoice_Table.comparatorProperty());
            View_invoice_Table.setItems(sorted);
            
            
        });     
   }
    
    public void On_Export_to_Excel_Click() throws IOException, IOException, IOException{
        
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        HSSFSheet spreadsheet = workbook.createSheet("sample");

        HSSFRow row = spreadsheet.createRow(0);

        for (int j = 0; j < View_invoice_Table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(View_invoice_Table.getColumns().get(j).getText());
        }

        for (int i = 0; i < View_invoice_Table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < View_invoice_Table.getColumns().size(); j++) {
                if(View_invoice_Table.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(View_invoice_Table.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        workbook.write(fileOut);
        fileOut.close();
        
    }
    
    public void onSearch_Btn_Click(){
        
            //btn.setVisible(false);
           
    }
    public void On_Add_New_Invoice_Click(){
        
        GenricPane.getChildren().setAll(NewInvoiceAnc);
                
    }
    
  
    
    public void On_Refresh_Click(){
        
        try {
            TableIncrementor = 1;
            Connection connection = sqliteconnection.Connector();
            table_List = FXCollections.observableArrayList();
            
           ResultSet rs = connection.createStatement().executeQuery("select InvoiceClint_detailes.Billno,"
                   + "InvoiceClint_detailes.Clint_name,Clintdetailes.GstIn,InvoiceClint_detailes.issue_Date,"
                   + "InvoiceClint_detailes.State,Invoice_Amount_Detailes.CGST,Invoice_Amount_Detailes.SGST,"
                   + "Invoice_Amount_Detailes.IGST,Invoice_Amount_Detailes.Total_amount,"
                   + "Invoice_Amount_Detailes.total_cost,Invoice_Amount_Detailes.shipping_cost,"
                   + "Invoice_Amount_Detailes.Discount from InvoiceClint_detailes,Invoice_Amount_Detailes,"
                   + "Clintdetailes where InvoiceClint_detailes.Billno == Invoice_Amount_Detailes.Billno and "
                   + "InvoiceClint_detailes.Clint_name == Clintdetailes.Name");
            
            while(rs.next()){
                if("0.0".equals(rs.getString(6))){
                   CgstTax= 0.0;
                   SgstTax = 0.0;
                }
                else{
                    CgstTax =Double.parseDouble(Dformat.format((Double.parseDouble(rs.getString(6))*100)/Double.parseDouble(rs.getString(9))));
                    SgstTax =Double.parseDouble(Dformat.format((Double.parseDouble(rs.getString(6))*100)/Double.parseDouble(rs.getString(9))));
                }
                
                
             table_List.addAll(new view_Invoice_table_get(TableIncrementor,rs.getInt(1),rs.getString(2), rs.getString(3), 
                       rs.getString(4),rs.getString(5),""+CgstTax+"%",rs.getString(6),
                        ""+SgstTax+"%",rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)));
               
             TableIncrementor++;
                
            }
                
                
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
          //  Logger.getLogger(Invoice_ViewController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        
        Sno.setCellValueFactory(new PropertyValueFactory<>("no"));
        tBillNO.setCellValueFactory(new PropertyValueFactory<>("Billno"));
        tClient.setCellValueFactory(new PropertyValueFactory<>("Client"));
        tIssueDate.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
        tState.setCellValueFactory(new PropertyValueFactory<>("State"));
        tCgst.setCellValueFactory(new PropertyValueFactory<>("CGST"));
        tSgst.setCellValueFactory(new PropertyValueFactory<>("SGST"));
        tigst.setCellValueFactory(new PropertyValueFactory<>("IGST"));
        tTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
        tTotalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        tShippingCost.setCellValueFactory(new PropertyValueFactory<>("ShippingCost"));
        tDisCount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        tCgstPer.setCellValueFactory(new PropertyValueFactory<>("Cgstper"));
        tSgstper.setCellValueFactory(new PropertyValueFactory<>("Sgstper"));
        tGstIn.setCellValueFactory(new PropertyValueFactory<>("GstIn"));
        
        View_invoice_Table.setItems(table_List);
        // this.filter =new TableFilter<>(View_invoice_Table);
        //    filter.setSearchStrategy((input,target) -> {
        //     try {
        //     return target.matches(input);
        //         } 
        //     catch (Exception e) {
        //     return false;
        //     }
        //     });
    }
    
    public void On_Edit_Click(ActionEvent event) throws IOException, SQLException{
        
        
        InvoicePane =(InvoicePaneController) loader.getController();
        
        
        stage.show();
        setInvoice_Pane();
   }
    //setting invoice Pane
    public void setInvoice_Pane() throws SQLException{
        
        
        
        Connection connection = sqliteconnection.Connector();
            table_List = FXCollections.observableArrayList();
            int Index = View_invoice_Table.getSelectionModel().getSelectedIndex();
            ResultSet rs = connection.createStatement().executeQuery("select * from InvoiceClint_detailes where BillNo== "+tBillNO.getCellData(Index));
            
        
        
        InvoicePane.Invoicetype.getSelectionModel().clearSelection();
        InvoicePane.Invoicetype.setDisable(false);
        InvoicePane.Invoicetype.setItems(InvoicePane.Invoice_Type_List);
        InvoicePane.Invoicetype.setValue(rs.getString(2));
        
        
        InvoicePane.Clint_List.clear();
        try {
            InvoicePane.Clint_List.addAll(InvoicePane.Invoice_Sql.getClintnames());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        InvoicePane.Clint.setDisable(false);
        InvoicePane.Clint.getSelectionModel().clearSelection();
        InvoicePane.Clint.setItems(InvoicePane.Clint_List);
        InvoicePane.Clint.setValue(rs.getString(3));
        
        InvoicePane.pono.setText(rs.getString(4));
        InvoicePane.pono.setDisable(false);
       
        InvoicePane.podate.setValue(null);
        InvoicePane.podate.setDisable(false);
        
        InvoicePane.issuedate.setDisable(false);
        InvoicePane.issuedate.setValue(LocalDate.now());
        InvoicePane.States_List.clear();
        try {
            InvoicePane.States_List.addAll(InvoicePane.Invoice_Sql.getStatename());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        InvoicePane.state.getSelectionModel().clearSelection();
        InvoicePane.state.setDisable(false);
        InvoicePane.state.setItems(InvoicePane.States_List);
        InvoicePane.state.setValue(rs.getString(7));
        
        
         
        try{
        InvoicePane.Set_GenrateBill_no();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        InvoicePane.Product_List.clear();
        try {
            InvoicePane.Product_List.addAll(InvoicePane.Invoice_Sql.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        InvoicePane.product.getSelectionModel().clearSelection();
        InvoicePane.product.setItems(InvoicePane.Product_List);
        
        
        
        InvoicePane.Desc.setText("");
        InvoicePane.uom.getSelectionModel().clearSelection();
        InvoicePane.uom.setItems(InvoicePane.Uom_List);
        
        InvoicePane.qty.setText("1");
        InvoicePane.unitprize.setText(null);
        InvoicePane.tax.getSelectionModel().clearSelection();
        InvoicePane.tax.setDisable(false);
        InvoicePane.tax.setItems(InvoicePane.Tax_List);
        
        rs = connection.createStatement().executeQuery("select * from Invoice_product_detailes where Billno ="+tBillNO.getCellData(Index));
       
            while(rs.next()){
                InvoicePane.Invoice_Table_List.add(new InvoiceTableGet(rs.getInt(2),rs.getString(3),
                       rs.getString(4),rs.getString(5),rs.getString(6),
                      rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
                InvoicePane.ino.setCellValueFactory(new PropertyValueFactory<>("i"));
                InvoicePane.itproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
                InvoicePane.itdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
                InvoicePane.ituom.setCellValueFactory(new PropertyValueFactory<>("uom"));
                InvoicePane.itqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                InvoicePane.itrate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
                InvoicePane.ittotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
                InvoicePane.itunitprize.setCellValueFactory(new PropertyValueFactory<>("unitprize"));
                InvoicePane.itprize.setCellValueFactory(new PropertyValueFactory<>("prize"));
                InvoicePane.InvoiceTable.setItems(InvoicePane.Invoice_Table_List);
            }       
                
                
        rs = connection.createStatement().executeQuery("select * from Invoice_Amount_Detailes where Billno ="+tBillNO.getCellData(Index));
           
            
           
       
        
        InvoicePane.Invoice_discount_combo.setItems(InvoicePane.Discount_List);
        InvoicePane.Invoice_discount_combo.getSelectionModel().isSelected(1);
        InvoicePane.discountAmount.setText(rs.getString(2));
       
        InvoicePane.paymenttype.getSelectionModel().clearSelection();
        InvoicePane.paymenttype.setItems(InvoicePane.Payment_Type_List);
        InvoicePane.paymenttype.setValue(rs.getString(3));
        
        InvoicePane.amountpaid.setText(rs.getString(4));
        InvoicePane.dueamount.setText(rs.getString(5));
        InvoicePane.due_date.setValue(null);
        InvoicePane.shippingp.setText(rs.getString(13));
        InvoicePane.vehicleno.setText(rs.getString(7));
        
        InvoicePane.Invoice_shipping_amount.setText(rs.getString(13));
        InvoicePane.Dateofdeparture.setValue(null);
        InvoicePane.Invoice_CGST_lbl.setText(rs.getString(9));
        InvoicePane.Invoice_SGST_lbl.setText(rs.getString(10));
        InvoicePane.Invoice_IGST_lbl.setText(rs.getString(11));
        InvoicePane.Totalamount.setText(rs.getString(12));
        InvoicePane.Invoice_Totalcost.setText(rs.getString(14));
       
        
        new AutoCompleteComboBoxListener<>(InvoicePane.Clint);
        new AutoCompleteComboBoxListener<>(InvoicePane.product);
        new AutoCompleteComboBoxListener<>(InvoicePane.state);
   }
   
   public void On_Genrate_Bill_Click() throws SQLException{
         
       int Index = View_invoice_Table.getSelectionModel().getSelectedIndex();
       
       sql.Temp_Invoice_ClintDetailes_clear();
       sql.Temp_Invoice_Product_detailes_clear();
       sql.Temp_Invoice_Amount_detailes_clear();
       
       
       sql.Temp_Invoice_Product_detailes(tBillNO.getCellData(Index));
       sql.Temp_Invoice_ClintDetailes(tBillNO.getCellData(Index));
       sql.Temp_Invoice_Amount_detailes(tBillNO.getCellData(Index));
       
       PrintClass print = new PrintClass();
       print.showReport("/Reports/InvoiceBill.jasper");
              
   } 
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.mid = Mediator.getInstance();
        this.NewInvoiceAnc = mid.getNewInvoicePane();
        this.GenricPane = mid.getGenricPane();
        
        On_Refresh_Click();
        try {
            this.sql = new newInvoiceSql();
            //this.print = new PrintClass();
        } catch (SQLException ex) {
            Logger.getLogger(Invoice_ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loader.setLocation(getClass().getResource("/UI/InvoicePane.fxml"));
         try {
            this.purchase =(Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
         this.InvoicePane = (InvoicePaneController) loader.getController();
        
        stage.setScene(new Scene(purchase));  
    }    
    
}
