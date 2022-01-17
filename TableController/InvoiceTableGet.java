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
public class InvoiceTableGet {
Integer i;
String product;
String desc;
String uom;
String qty;
String Rate;
String Total;
String unitprize;
String prize;


    public InvoiceTableGet(Integer i, String product, String desc, String uom, String qty, String Rate,String total,String unitprize,String prize) {
        this.i = i;
        this.product = product;
        this.desc = desc;
        this.uom = uom;
        this.qty = qty;
        this.Rate=Rate;
        this.Total =total;
        this.unitprize = unitprize;
        this.prize= prize;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String Rate) {
        this.Rate = Rate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getUnitprize() {
        return unitprize;
    }

    public void setUnitprize(String unitprize) {
        this.unitprize = unitprize;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    
    
    
    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    
    
}
