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
public class ProductDetailesView {
    
    Integer no;
    String product;
    String Hsn;
    String uom;
    String tax;
    String Cost;
    String Sales_price;
    

    public ProductDetailesView(Integer no,String product, String Hsn, String uom, String tax,String Cost,String Sales) {
        this.no=no;
        this.product = product;
        this.Hsn = Hsn;
        this.uom = uom;
        
        this.tax = tax;
        this.Cost = Cost;
        this.Sales_price =Sales;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getHsn() {
        return Hsn;
    }

    public void setHsn(String Hsn) {
        this.Hsn = Hsn;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

   

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String Cost) {
        this.Cost = Cost;
    }

    public String getSales_price() {
        return Sales_price;
    }

    public void setSales_price(String Sales_price) {
        this.Sales_price = Sales_price;
    }
    
    
}
