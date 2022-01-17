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
public class ClintDetailesView {

    Integer no;
    String name;
    String Contactno;
    String Gst;
    String Email;
    String Address;
    String State;
    String City;
    String Pan;
    String ContactName;

    public ClintDetailesView(Integer no, String name, String Contactno, String Gst, String Email, String Address, String State, String City, String Pan, String ContactName) {
        this.no = no;
        this.name = name;
        this.Contactno = Contactno;
        this.Gst = Gst;
        this.Email = Email;
        this.Address = Address;
        this.State = State;
        this.City = City;
        this.Pan = Pan;
        this.ContactName = ContactName;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return Contactno;
    }

    public void setContactno(String Contactno) {
        this.Contactno = Contactno;
    }

    public String getGst() {
        return Gst;
    }

    public void setGst(String Gst) {
        this.Gst = Gst;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPan() {
        return Pan;
    }

    public void setPan(String Pan) {
        this.Pan = Pan;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }


}
