/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Sql.*;
import TableController.ClintDetailesView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author rohan
 */
public class View_ClinetController implements Initializable {

    @FXML
    private Button Search;
    @FXML
    private ComboBox<?> Name_search;
    @FXML
    private TextField Contact_no;
    @FXML
    private ComboBox<?> City_search;
    @FXML TableView<ClintDetailesView> Vc;
    @FXML
    private TableColumn<ClintDetailesView, Integer> Vc_tno;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_tName;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_Contactno;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_GstIn;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_Email;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_Address;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_State;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_City;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_PanNo;
    @FXML
    private TableColumn<ClintDetailesView, String> VC_ContactName;
    TableFilter<ClintDetailesView> filter ;
    
    @FXML
    private Button Edit;
    @FXML
    private Button AddNew;
    @FXML
    private AnchorPane add_new_anc;
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
    private Button Modify_Clint;
    @FXML
    private Button Clint_close;
    @FXML
    private ComboBox Add_City;
    @FXML
    private Label Add_Prompt;
    @FXML
    private AnchorPane Prompt_pane;
    @FXML
    private Label Prompt_lable;
    @FXML
    private Button Close_Prompt;
    @FXML
    private Button Refresh;

    View_Sql sql;
    Integer Client_no;
    Integer Client_no_Db;
    /**
     * Initializes the controller class.
     */
    public ObservableList list = FXCollections.observableArrayList();
    public ObservableList NamesList = FXCollections.observableArrayList();
    
    
    @FXML
    public void ButtonEventHandler(ActionEvent event) throws SQLException{
        if(event.getSource() == Edit){
            if(Vc.getSelectionModel().isEmpty()){
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("No Field Selected");
            }
            else{
                add_new_anc.setVisible(true);
                Modify_Clint.setText("Modify");
                on_Click_Edit();
            }
        }
        else if(event.getSource() == AddNew){
               Clint_window_set();
               Modify_Clint.setText("Add New");
               add_new_anc.setVisible(true);
        }
        else if(event.getSource() == Refresh){
            Add_into_client_Table();
        }
        else if(event.getSource() == Clint_close){
            add_new_anc.setVisible(false);
        }
        else if(event.getSource() == Close_Prompt){
            Prompt_pane.setVisible(false);
        }
        else if(event.getSource() == Modify_Clint){
            if(Modify_Clint.getText().equals("Add New")){
                On_add_Commit();
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("Client Added");
                add_new_anc.setVisible(false);
            }
            else if(Modify_Clint.getText().equals("Modify")){
                on_modify_Commit();
                Prompt_pane.setVisible(true);
                Prompt_lable.setText("Client Modified");
                add_new_anc.setVisible(false);
            }
        }
        
        
    }
    
    //Client Window elements set
    public void Clint_window_set(){
        Add_name.setText(null);
        Add_Address.setText("");
        Add_City.setValue("Udaipur");
        Add_State.setValue("Rajasthan");
        Add_ContactName.setText("");
        Add_ContactNo.setText(null);
        Add_Email.setText("");
        Add_GstIn.setText("");
        Add_Pan.setText("");
    }
    public void On_add_Commit() throws SQLException{
            get_Client_no();
            sql.Add_Client_into_db(Client_no_Db,Add_name.getText(),Add_ContactNo.getText(),Add_GstIn.getText(),Add_Email.getText(),
                Add_Address.getText(), Add_State.getSelectionModel().getSelectedItem().toString(),Add_City.getSelectionModel().getSelectedItem().toString(), 
                Add_ContactName.getText(),Add_Pan.getText());
    }
    public void on_modify_Commit() throws SQLException{
        
        sql.Add_Client_into_db(Client_no,Add_name.getText(),Add_ContactNo.getText(),Add_GstIn.getText(),Add_Email.getText(),
                Add_Address.getText(), Add_State.getSelectionModel().getSelectedItem().toString(),Add_City.getSelectionModel().getSelectedItem().toString(), 
                Add_ContactName.getText(),Add_Pan.getText());
    }
    public void on_Click_Edit(){
        ObservableList<ClintDetailesView> allproduct;
        allproduct = Vc.getItems();
        int Client_index = Vc.getSelectionModel().getSelectedIndex();
        Client_no = Vc_tno.getCellData(Client_index);
        Add_name.setText(VC_tName.getCellData(Client_index));
        Add_Address.setText(VC_Address.getCellData(Client_index));
        Add_City.setValue(VC_City.getCellData(Client_index));
        Add_State.setValue(VC_State.getCellData(Client_index));
        Add_ContactName.setText(VC_ContactName.getCellData(Client_index));
        Add_ContactNo.setText(VC_Contactno.getCellData(Client_index));
        Add_Email.setText(VC_Email.getCellData(Client_index));
        Add_GstIn.setText(VC_GstIn.getCellData(Client_index));
        Add_Pan.setText(VC_PanNo.getCellData(Client_index));
        
    }
    
    
      
    public void Add_into_client_Table(){
        try {
            Connection connection = sqliteconnection.Connector();
            list = FXCollections.observableArrayList();
            ResultSet rs = connection.createStatement().executeQuery("select * from Clintdetailes order by Name");
            while(rs.next()){
               list.addAll(new ClintDetailesView(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), 
                       rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), 
                       rs.getString(9), rs.getString(10)));
            }
        } catch (SQLException ex) {}
                Vc_tno.setCellValueFactory(new PropertyValueFactory<>("no"));
                VC_tName.setCellValueFactory(new PropertyValueFactory<>("name"));
                VC_Contactno.setCellValueFactory(new PropertyValueFactory<>("Contactno"));
                VC_GstIn.setCellValueFactory(new PropertyValueFactory<>("Gst"));
                VC_Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
                VC_Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
                VC_State.setCellValueFactory(new PropertyValueFactory<>("State"));
                VC_City.setCellValueFactory(new PropertyValueFactory<>("City"));
                VC_PanNo.setCellValueFactory(new PropertyValueFactory<>("Pan"));
                VC_ContactName.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
                Vc.setItems(list);
        //         this.filter =new TableFilter<>(Vc);
        //    filter.setSearchStrategy((input,target) -> {
        //     try {
        //     return target.matches(input);
        // } catch (Exception e) {
        //     return false;
        // }
        //     });
    }
    //Setting Client No
    public void get_Client_no() throws SQLException{
        if(sql.get_Client_no()==null){
            Client_no_Db = 1;
        }
        else{
            Client_no_Db = sql.get_Client_no()+1;
        }
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            sql = new View_Sql();
        } catch (SQLException ex) {
            Logger.getLogger(View_ClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Add_into_client_Table();
        
    }    

    @FXML
    private void closeadd_new(MouseEvent event) {
    }
    
}
