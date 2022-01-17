/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableController;

import java.util.Date;

/**
 *
 * @author rohan
 */
public class view_Invoice_table_get {
    Integer no;
    Integer Billno;
    String Client;
    String GstIn;
    String IssueDate;
    String State;
    String Cgstper;
    String CGST;
    String Sgstper;
    String SGST;
    String IGST;
    String TotalAmount;
    String TotalCost;
    String ShippingCost;
    String Discount;

    public view_Invoice_table_get(Integer no,Integer Billno, String Client,String gstIn, String IssueDate, 
            String State,String Cgstper,String CGST,String Sgstper,String SGST,String IGST,
            String TotalAmount,String TotalCost, String ShippingCost, String Discount) {
        
        this.no = no;
        this.Billno = Billno;
        this.Client = Client;
        this.GstIn = gstIn;
        this.IssueDate = IssueDate;
        this.State = State;
        this.Cgstper = Cgstper;
        this.CGST = CGST;
        this.Sgstper = Sgstper;
        this.SGST = SGST;
        this.IGST = IGST;
        this.TotalAmount = TotalAmount;
        this.TotalCost = TotalCost;
        this.ShippingCost = ShippingCost;
        this.Discount = Discount;
        
    }

    public Integer getBillno() {
        return Billno;
    }

    public void setBillno(Integer Billno) {
        this.Billno = Billno;
    }

    public String getGstIn() {
        return GstIn;
    }

    public void setGstIn(String GstIn) {
        this.GstIn = GstIn;
    }

    public String getCgstper() {
        return Cgstper;
    }

    public void setCgstper(String Cgstper) {
        this.Cgstper = Cgstper;
    }

    public String getSgstper() {
        return Sgstper;
    }

    public void setSgstper(String Sgstper) {
        this.Sgstper = Sgstper;
    }

    
    public String getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(String TotalCost) {
        this.TotalCost = TotalCost;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    
    
    
    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String IssueDate) {
        this.IssueDate = IssueDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCGST() {
        return CGST;
    }

    public void setCGST(String CGST) {
        this.CGST = CGST;
    }

    public String getSGST() {
        return SGST;
    }

    public void setSGST(String SGST) {
        this.SGST = SGST;
    }

    public String getIGST() {
        return IGST;
    }

    public void setIGST(String IGST) {
        this.IGST = IGST;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getShippingCost() {
        return ShippingCost;
    }

    public void setShippingCost(String ShippingCost) {
        this.ShippingCost = ShippingCost;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }
 
}
