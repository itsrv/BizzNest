/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableController;

/**
 *
 * @author rohan
 */
public class Quo_Table_get {
    
    
    Integer no;
    String Product;
    Float Rate;
    Integer Qty;
    Float Amount;
    String Tax;
    Float Cgst;
    Float Sgst;
    Float Igst;
    Float Total;
    String Remarks;

    public Quo_Table_get(Integer no, String Product, Float Rate, Integer Qty, Float Amount,String Tax, Float Cgst,
            Float Sgst, Float Igst, Float Total, String Remarks) {
        this.no = no;
        this.Product = Product;
        this.Rate = Rate;
        this.Qty = Qty;
        this.Amount = Amount;
        this.Cgst = Cgst;
        this.Sgst = Sgst;
        this.Igst = Igst;
        this.Total = Total;
        this.Remarks = Remarks;
        this.Tax = Tax;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

    public Float getRate() {
        return Rate;
    }

    public void setRate(Float Rate) {
        this.Rate = Rate;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer Qty) {
        this.Qty = Qty;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float Amount) {
        this.Amount = Amount;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String Tax) {
        this.Tax = Tax;
    }
    

    public Float getCgst() {
        return Cgst;
    }

    public void setCgst(Float Cgst) {
        this.Cgst = Cgst;
    }

    public Float getSgst() {
        return Sgst;
    }

    public void setSgst(Float Sgst) {
        this.Sgst = Sgst;
    }

    public Float getIgst() {
        return Igst;
    }

    public void setIgst(Float Igst) {
        this.Igst = Igst;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }
    
    
    
}
