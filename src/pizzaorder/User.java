/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaorder;

/**
 *
 * @author gaben
 */
class User {
    private int customerID;
    private String firstName, lastName, phoneNum, emailAddress, streetAddress, city, zipcode, state;
    
    public User(String firstName, String lastName, String phoneNum, String emailAddress, String streetAddress, String city, String zipcode, String state, int customerID) {      
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.emailAddress = emailAddress;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;  
        this.customerID = customerID;
    }
    public String getfirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }
    public String getphoneNum() {
        return phoneNum;
    }
    public String getemailAddress() {
        return emailAddress;
    }
    public String getstreetAddress() {
        return streetAddress;
    }
    public String getcity() {
        return city;
    }
    public String getzipcode() {
        return zipcode;
    }
    public String getstate() {
        return state;
    }
    public int getcustomerID() {
        return customerID;
    }
}
