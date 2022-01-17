/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectteck5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rohan
 */
public class MainController implements Initializable {

    //Pans
    @FXML private AnchorPane BaseAnc;
    @FXML private AnchorPane FrontAnc;
    @FXML private AnchorPane SlideAnc;
    @FXML private AnchorPane Slide_menu_Open_Anc;
    @FXML public AnchorPane Genric_Anc;
    @FXML private AnchorPane Company_Detailes_anc;
    @FXML private AnchorPane View_Window_anc;

    //slide Window elements 
    @FXML private Button SlideButton;
    @FXML private Button NewSlide_Window_Btn;
    @FXML private Button Report_Slide_Window_btn;
    @FXML private Button View_Slide_window_btn;
    
    //slide menu window element 
    @FXML private Button Report_Slide_menu_btn;
    @FXML public Button New_Slide_Menu_btn;
    @FXML private Button View_Slide_Menu_btn;
 
    //new btn element
    @FXML private Button New_Invoice_btn;
    @FXML private Button New_Quotation_btn;
    @FXML private Button New_Challan_btn;
    @FXML private Button New_Purchase_btn;
    
    //view btn element
    
    @FXML private Button View_Clients_btn;
    @FXML private Button View_Products_btn;
    @FXML private Button View_Services_btn;
    @FXML private Button View_Invoice_Detailes;
    @FXML private Button View_Purchase_Detailes;
    @FXML private Button View_Challan_Detailes;
    @FXML private Button View_Quotation_Detailes;
    
    //company detailes element
    @FXML private TextField CompanyName;
    @FXML private TextArea Com_Address;
    @FXML private ComboBox<?> Com_State;
    @FXML private ComboBox<?> Com_city;
    @FXML private TextField Com_PinCode;
    @FXML private TextField Com_Phone_No;
    @FXML private TextField Com_Email_Id;
    @FXML private TextField Com_WebSite;
    @FXML private TextField Com_GstIn;
    @FXML private TextField Com_Pan;
    @FXML private TextField Com_Tin;
    @FXML private TextArea Com_Additional_Detailes;
    @FXML private Button SaveDetailes;
    
    
    //other varialbles
    public boolean On_slide_btn_click=true; 
    public Scene scene;

    //anchor pane to be loaded for new slide menu btn
    public AnchorPane New_Invoice_Pane;
    public AnchorPane New_Challan_Pane;
    public AnchorPane New_Quotation_Pane;
    public AnchorPane New_Purchase_Pane;
    
    //anchor pane to be loaded for view slide window btn
    public AnchorPane Clint_ViewPane;
    public AnchorPane Product_ViewPane;
    public AnchorPane Services_ViewPane;
    public AnchorPane New_Invoice_ViewPane;
    public AnchorPane New_Challan_View_Pane;
    public AnchorPane New_Quotation_View_Pane;
    public AnchorPane New_Purchase_View_Pane;
    
    
    
    
    Mediator mid;
   
    
   
    
    @FXML
    public void ButtonEventHandler(ActionEvent event){
        scene = BaseAnc.getScene();
        if(event.getSource() == SlideButton){
            
            OnSlide_Menu_Open();
        }   
        else if(event.getSource() == NewSlide_Window_Btn|| event.getSource() == New_Slide_Menu_btn){
            FrontAnc.setVisible(true);
            View_Window_anc.setVisible(false);
            FrontAnc.toFront();
        }
        else if(event.getSource() == Report_Slide_Window_btn|| event.getSource() == Report_Slide_menu_btn){
            
            
        }
        else if(event.getSource() == View_Slide_window_btn || event.getSource() == View_Slide_Menu_btn){
            FrontAnc.setVisible(true);
            FrontAnc.toFront();
            View_Window_anc.setVisible(true);
            View_Window_anc.toFront();
        }
        else if(event.getSource() == New_Invoice_btn){
            doTitle("New Invoice");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Invoice_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
            
        }
        else if(event.getSource() == New_Challan_btn){
            doTitle("New Challan");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Challan_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == New_Quotation_btn){
            doTitle("New Quotation");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Quotation_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == New_Purchase_btn){
            doTitle("New Purchase");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Purchase_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Clients_btn){
            doTitle("Clients");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(Clint_ViewPane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Products_btn){
            doTitle("Products");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(Product_ViewPane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Services_btn){
            doTitle("Services");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(Product_ViewPane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Invoice_Detailes){
           
            doTitle("Invoices");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Invoice_ViewPane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
            
        }
        else if(event.getSource() == View_Challan_Detailes){
            doTitle("Challan");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Challan_View_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Quotation_Detailes){
            doTitle("Quotation");
            Genric_Anc.getChildren().clear();
            Genric_Anc.getChildren().setAll(New_Quotation_View_Pane);
            Genric_Anc.setVisible(true);
            Genric_Anc.toFront();
            Slide_menu_Open_Anc.setVisible(false);
            On_slide_btn_click =false;
            OnSlide_Menu_Open();
        }
        else if(event.getSource() == View_Purchase_Detailes){
            
        }
        
    }
    
   
    //call on slide menu open btn clicked
    public void OnSlide_Menu_Open(){
        if(On_slide_btn_click == true){
            
            Slide_menu_Open_Anc.setVisible(true);
            Genric_Anc.setPrefWidth(scene.getWidth()-301);
            FrontAnc.setPrefWidth(scene.getWidth()-301);
            Clint_ViewPane.setPrefWidth(scene.getWidth()-301);
            Product_ViewPane.setPrefWidth(scene.getWidth()-301);
            
            New_Invoice_Pane.setPrefWidth(scene.getWidth()-301);
            New_Challan_Pane.setPrefWidth(scene.getWidth()-301);
            New_Quotation_Pane.setPrefWidth(scene.getWidth()-301);
            
            New_Invoice_ViewPane.setPrefWidth(scene.getWidth()-301);
            New_Challan_View_Pane.setPrefWidth(scene.getWidth()-301);
            New_Quotation_View_Pane.setPrefWidth(scene.getWidth()-301);
            
            SlideAnc.toFront();
            On_slide_btn_click =false;
        }
        else{
            
            Slide_menu_Open_Anc.setVisible(false);
            Genric_Anc.setPrefWidth(scene.getWidth()-70);
            FrontAnc.setPrefWidth(scene.getWidth()-70);
            Clint_ViewPane.setPrefWidth(scene.getWidth()-70);
            Product_ViewPane.setPrefWidth(scene.getWidth()-70);
            
            New_Invoice_Pane.setPrefWidth(scene.getWidth()-70);
            New_Challan_Pane.setPrefWidth(scene.getWidth()-70);
            New_Quotation_Pane.setPrefWidth(scene.getWidth()-70);
            
            New_Invoice_ViewPane.setPrefWidth(scene.getWidth()-70);
            New_Challan_View_Pane.setPrefWidth(scene.getWidth()-70);
            New_Quotation_View_Pane.setPrefWidth(scene.getWidth()-70);
            SlideAnc.toFront();
            On_slide_btn_click =true;
        }
        
    }
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mid = Mediator.getInstance();
        mid.setGenricPane(Genric_Anc);
        this.scene = BaseAnc.getScene();
        
        //loading new slide menu btn pans
        try {
            this.New_Invoice_Pane = FXMLLoader.load(getClass().getResource("/UI/InvoicePane.fxml"));
             mid.setNewInvoicePane(New_Invoice_Pane);
            this.New_Challan_Pane = FXMLLoader.load(getClass().getResource("/UI/ChallanPane.fxml"));
            mid.setChallanPane(New_Challan_Pane);
            this.New_Quotation_Pane = FXMLLoader.load(getClass().getResource("/UI/Quatation.fxml"));
            mid.setQuotationPane(New_Quotation_Pane);
            this.New_Purchase_Pane = FXMLLoader.load(getClass().getResource("/UI/PurchasePane.fxml"));
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        //loading View Slide menu btn Pane
        try {
            this.Clint_ViewPane = FXMLLoader.load(getClass().getResource("/ViewUI/View_Clinet.fxml"));
            this.Product_ViewPane = FXMLLoader.load(getClass().getResource("/ViewUI/View_Product.fxml"));
            this.New_Invoice_ViewPane = FXMLLoader.load(getClass().getResource("/ViewUI/Invoice_View.fxml"));
            this.New_Challan_View_Pane = FXMLLoader.load(getClass().getResource("/ViewUI/Challan_View.fxml"));
            this.New_Quotation_View_Pane = FXMLLoader.load(getClass().getResource("/ViewUI/Quotation_View.fxml"));
            
            this.Services_ViewPane = FXMLLoader.load(getClass().getResource(""));
            
        } catch (IOException ex) {
           System.out.print(ex.getMessage());
        }
        
       
    }
    
    
    public void doTitle(String Title){
        
        Stage stage;
        stage = (Stage) BaseAnc.getScene().getWindow();
        stage.setTitle(Title);


    }

    
    
}
