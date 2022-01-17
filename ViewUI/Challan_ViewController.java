/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewUI;

import Sql.newInvoiceSql;
import Sql.sqliteconnection;
import TableController.*;
import UIController.ChallanPaneController;
import UIController.InvoicePaneController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.table.TableFilter;
import projectteck5.AutoCompleteComboBoxListener;
import projectteck5.Mediator;
import projectteck5.PrintClass;

/**
 * FXML Controller class
 *
 * @author rohan
 */
public class Challan_ViewController implements Initializable {

    @FXML private AnchorPane ViewInvoice_Base;
    
    @FXML private TableView<View_Challan_table_get> View_invoice_Table;
    @FXML private TableColumn<View_Challan_table_get, Integer> Sno;
    @FXML private TableColumn<View_Challan_table_get, Integer> tBillNO;
    @FXML private TableColumn<View_Challan_table_get, String> tClient;
    @FXML private TableColumn<View_Challan_table_get, String> tIssueDate;
    @FXML private TableColumn<View_Challan_table_get, String> tState;
    @FXML private TableColumn<View_Challan_table_get, String> tCgst;
    @FXML private TableColumn<View_Challan_table_get, String> tSgst;
    @FXML private TableColumn<View_Challan_table_get, String> tigst;
    @FXML private TableColumn<View_Challan_table_get, String> tTotalAmount;
    @FXML private TableColumn<View_Challan_table_get, String> tTotalCost;
    @FXML private TableColumn<View_Challan_table_get, String> tShippingCost;
    @FXML private TableColumn<View_Challan_table_get, String> tDisCount;
    @FXML private TableColumn<View_Challan_table_get, String> tGstIn;
    @FXML private TableColumn<View_Challan_table_get, String> tCgstPer;
    @FXML private TableColumn<View_Challan_table_get, String> tSgstper;
    
    @FXML private Button Refresh;
    @FXML private Button OnEdit;
    
    @FXML private DatePicker FromDate;
    @FXML private DatePicker ToDate;
    @FXML private Button SearchButton;
    @FXML public Button GenrateBill;
    private TextField search;
    @FXML private Button Add_New_Invoice;
    @FXML private Button ExportToExcel;
    Mediator mid;
    
    TableFilter<View_Challan_table_get> filter;

    FXMLLoader loader = new FXMLLoader();
    Stage stage = new Stage();
    Parent Challan;
    
    ChallanPaneController ChallanPane;
    AnchorPane NewChallanAnc;
    newInvoiceSql sql; 
    AnchorPane GenricPane;
    
    
    //other variable
    Integer TableIncrementor = 0;
    Double CgstTax;
    Double SgstTax;
    DecimalFormat Dformat = new DecimalFormat("0.00");
    Date date= new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    
    public ObservableList table_List = FXCollections.observableArrayList();
   
   
    @FXML
    private void ButtonEventHandler(ActionEvent event) {
       if(event.getSource() == Refresh){
            On_Refresh_Click();
       }
       else if(event.getSource() == OnEdit){
           try {
               On_Edit_Click();
           } catch (IOException | SQLException ex) {
               Logger.getLogger(Invoice_ViewController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.print(ex.getMessage());
           }
       }
       else if(event.getSource() == SearchButton){
           
       }
       else if(event.getSource() == GenrateBill){
           try {
               On_Genrate_Challan_Click();
           } catch (SQLException ex) {
               Logger.getLogger(Challan_ViewController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.print(ex.getMessage());
           }
       }
       else if(event.getSource() == Add_New_Invoice){
          On_Add_New_Challan_Click();
       }
       else if(event.getSource() == ExportToExcel){
           try {
               On_Export_to_Excel_Click();
           } catch (IOException ex) {
               Logger.getLogger(Challan_ViewController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.print(ex.getMessage());
           }
       }
    
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
    
    
     public void On_Refresh_Click(){
        
        try {
            TableIncrementor = 1;
            Connection connection = sqliteconnection.Connector();
            table_List = FXCollections.observableArrayList();
            
           ResultSet rs = connection.createStatement().executeQuery("select ChallanClint_detailes.Billno,"
                   + "ChallanClint_detailes.Clint_name,Clintdetailes.GstIn,ChallanClint_detailes.issue_Date,"
                   + "ChallanClint_detailes.State,Challan_Amount_Detailes.CGST,Challan_Amount_Detailes.SGST,"
                   + "Challan_Amount_Detailes.IGST,Challan_Amount_Detailes.Total_amount,"
                   + "Challan_Amount_Detailes.total_cost,Challan_Amount_Detailes.shipping_cost,"
                   + "Challan_Amount_Detailes.Discount from ChallanClint_detailes,Challan_Amount_Detailes,"
                   + "Clintdetailes where ChallanClint_detailes.Billno == Challan_Amount_Detailes.Billno and "
                   + "ChallanClint_detailes.Clint_name == Clintdetailes.Name");
            
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
    
     public void On_Genrate_Challan_Click() throws SQLException{
         
       int Index = View_invoice_Table.getSelectionModel().getSelectedIndex();
       
       sql.Temp_Challan_ClintDetailes_clear();
       sql.Temp_Challan_Product_detailes_clear();
       sql.Temp_Challan_Amount_detailes_clear();
       
       
       sql.Temp_Challan_Product_detailes(tBillNO.getCellData(Index));
       sql.Temp_Challan_ClintDetailes(tBillNO.getCellData(Index));
       sql.Temp_Challan_Amount_detailes(tBillNO.getCellData(Index));
       PrintClass print = new PrintClass();
       print.showReport("/Reports/Challan.jasper");
              
   } 
    public void On_Add_New_Challan_Click(){
        
        GenricPane.getChildren().setAll(NewChallanAnc);
                
    } 
   
    public void On_Edit_Click() throws IOException, SQLException{
        
        
        ChallanPane =(ChallanPaneController) loader.getController();
        
        
        stage.show();
        Set_Challan_Pane();
    }
    
    public void Set_Challan_Pane() throws SQLException{
        
         
        Connection connection = sqliteconnection.Connector();
            table_List = FXCollections.observableArrayList();
            int Index = View_invoice_Table.getSelectionModel().getSelectedIndex();
            ResultSet rs = connection.createStatement().executeQuery("select * from ChallanClint_detailes where BillNo== "+tBillNO.getCellData(Index));
            
        
        
        ChallanPane.Invoicetype.getSelectionModel().clearSelection();
        ChallanPane.Invoicetype.setDisable(false);
        ChallanPane.Invoicetype.setItems(ChallanPane.Invoice_Type_List);
        ChallanPane.Invoicetype.setValue(rs.getString(2));
        
        
        ChallanPane.Clint_List.clear();
        
        try {
            ChallanPane.Clint_List.addAll(ChallanPane.Invoice_Sql.getClintnames());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChallanPane.Clint.setDisable(false);
        ChallanPane.Clint.getSelectionModel().clearSelection();
        ChallanPane.Clint.setItems(ChallanPane.Clint_List);
        ChallanPane.Clint.setValue(rs.getString(3));
        
        ChallanPane.pono.setText(rs.getString(4));
        
        ChallanPane.pono.setDisable(false);
       
        ChallanPane.podate.setValue(null);
        ChallanPane.podate.setDisable(false);
        
        ChallanPane.issuedate.setValue(null);
        ChallanPane.issuedate.setDisable(false);
        
        ChallanPane.States_List.clear();
        try {
            ChallanPane.States_List.addAll(ChallanPane.Invoice_Sql.getStatename());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChallanPane.state.getSelectionModel().clearSelection();
        ChallanPane.state.setDisable(false);
        ChallanPane.state.setItems(ChallanPane.States_List);
        ChallanPane.state.setValue(rs.getString(7));
        try{
        ChallanPane.Set_Genrate_Challan_no();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        ChallanPane.Product_List.clear();
        try {
            ChallanPane.Product_List.addAll(ChallanPane.Invoice_Sql.getProductName());
        } catch (SQLException ex) {
            Logger.getLogger(InvoicePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChallanPane.product.getSelectionModel().clearSelection();
        ChallanPane.product.setItems(ChallanPane.Product_List);
        
        ChallanPane.Desc.setText("");
        ChallanPane.uom.getSelectionModel().clearSelection();
        ChallanPane.uom.setItems(ChallanPane.Uom_List);
        
        ChallanPane.qty.setText("1");
        ChallanPane.unitprize.setText(null);
        ChallanPane.tax.getSelectionModel().clearSelection();
        ChallanPane.tax.setDisable(false);
        ChallanPane.tax.setItems(ChallanPane.Tax_List);
        
         rs = connection.createStatement().executeQuery("select * from Challan_product_detailes where Billno ="+tBillNO.getCellData(Index));
       ChallanPane.Invoice_Table_List.clear();
        while(rs.next()){
                ChallanPane.Invoice_Table_List.add(new InvoiceTableGet(rs.getInt(2),rs.getString(3),
                       rs.getString(4),rs.getString(5),rs.getString(6),
                      rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
                ChallanPane.ino.setCellValueFactory(new PropertyValueFactory<>("i"));
                ChallanPane.itproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
                ChallanPane.itdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
                ChallanPane.ituom.setCellValueFactory(new PropertyValueFactory<>("uom"));
                ChallanPane.itqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                ChallanPane.itrate.setCellValueFactory(new PropertyValueFactory<>("Rate"));
                ChallanPane.ittotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
                ChallanPane.itunitprize.setCellValueFactory(new PropertyValueFactory<>("unitprize"));
                ChallanPane.itprize.setCellValueFactory(new PropertyValueFactory<>("prize"));
                ChallanPane.InvoiceTable.setItems(ChallanPane.Invoice_Table_List);
            }       
        
        
        
         rs = connection.createStatement().executeQuery("select * from Challan_Amount_Detailes where Billno ="+tBillNO.getCellData(Index));
       
        
        
        ChallanPane.Invoice_discount_combo.setItems(ChallanPane.Discount_List);
        ChallanPane.Invoice_discount_combo.getSelectionModel().isSelected(2);
        ChallanPane.discountAmount.setText(rs.getString(2));
        
       
        ChallanPane.paymenttype.getSelectionModel().clearSelection();
        ChallanPane.paymenttype.setItems(ChallanPane.Payment_Type_List);
        ChallanPane.paymenttype.setValue(rs.getString(3));
        
        
        ChallanPane.amountpaid.setText(rs.getString(4));
        ChallanPane.dueamount.setText(rs.getString(5));
        ChallanPane.due_date.setValue(null);
        ChallanPane.shippingp.setText(rs.getString(13));
        ChallanPane.vehicleno.setText(rs.getString(7));
        
        ChallanPane.Invoice_shipping_amount.setText(rs.getString(13));
        ChallanPane.Dateofdeparture.setValue(null);
        ChallanPane.Invoice_CGST_lbl.setText(rs.getString(9));
        ChallanPane.Invoice_SGST_lbl.setText(rs.getString(10));
        ChallanPane.Invoice_IGST_lbl.setText(rs.getString(11));
        ChallanPane.Totalamount.setText(rs.getString(12));
        ChallanPane.Invoice_Totalcost.setText(rs.getString(14));
       
        
        new AutoCompleteComboBoxListener<>(ChallanPane.Clint);
        new AutoCompleteComboBoxListener<>(ChallanPane.product);
        new AutoCompleteComboBoxListener<>(ChallanPane.state);
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mid = Mediator.getInstance();
        GenricPane = mid.getGenricPane();
        NewChallanAnc = mid.getChallanPane();
        
        On_Refresh_Click();
        
        
        try {
            this.sql = new newInvoiceSql();
        } catch (SQLException ex) {
            Logger.getLogger(Challan_ViewController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
        
        loader.setLocation(getClass().getResource("/UI/ChallanPane.fxml"));
        
        try {
            Challan = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Challan_ViewController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
        this.ChallanPane = (ChallanPaneController) loader.getController();
        stage.setScene(new Scene(Challan));
    }    

    
    
}
