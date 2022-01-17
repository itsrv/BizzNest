/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectteck5;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author rohan
 */
public class Mediator {
    private Mediator(){};
    private static Mediator instance = new Mediator();
    
    public static Mediator getInstance(){
        return instance;
    }
    private AnchorPane GenricPane;
    private AnchorPane NewInvoicePane;
    private AnchorPane ChallanPane;
    private AnchorPane QuotationPane;
    private AnchorPane PurhasePane;

    
    
    public AnchorPane getPurhasePane() {
        return PurhasePane;
    }

    public void setPurhasePane(AnchorPane PurhasePane) {
        this.PurhasePane = PurhasePane;
    }
    
    public AnchorPane getQuotationPane() {
        return QuotationPane;
    }

    public void setQuotationPane(AnchorPane QuotationPane) {
        this.QuotationPane = QuotationPane;
    }
    
    public AnchorPane getChallanPane() {
        return ChallanPane;
    }

    public void setChallanPane(AnchorPane ChallanPane) {
        this.ChallanPane = ChallanPane;
    }
    
    public AnchorPane getNewInvoicePane() {
        return NewInvoicePane;
    }

    public void setNewInvoicePane(AnchorPane NewInvoicePane) {
        this.NewInvoicePane = NewInvoicePane;
    }
    
    public AnchorPane getGenricPane() {
        return GenricPane;
    }

    public void setGenricPane(AnchorPane GenricPane) {
        this.GenricPane = GenricPane;
    }
    

}
