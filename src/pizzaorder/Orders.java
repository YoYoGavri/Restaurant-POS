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
public class Orders {
    private int orderID, customerID, pizzaQTY, friesQTY, burgersQTY, calzonesQTY, saladQTY, soupQTY, dessertQTY, drinksQTY;
    private String pizzaType, friesType, burgersType, calzonesType, saladType, soupType, dessertType, drinksType, cashierName, totalCost;
    
    public Orders(int orderID, int customerID, String pizzaType, int pizzaQTY, String friesType, int friesQTY, String burgersType, int burgersQTY, String calzonesType, int calzonesQTY, String saladType, int saladQTY, String soupType, int soupQTY, String dessertType, int dessertQTY, String drinksType, int drinksQTY, String cashierName, String totalCost) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.pizzaQTY = pizzaQTY;
        this.friesQTY = friesQTY;
        this.burgersQTY = burgersQTY;
        this.calzonesQTY = calzonesQTY;
        this.saladQTY = saladQTY;
        this.dessertQTY = dessertQTY;
        this.drinksQTY = drinksQTY;
        this.pizzaType = pizzaType;
        this.friesType = friesType;
        this.burgersType = burgersType;
        this.calzonesType = calzonesType;
        this.saladType = saladType;
        this.dessertType = dessertType;
        this.drinksType = drinksType;
        this.cashierName = cashierName;
        this.totalCost = totalCost;
        this.soupType = soupType;
        }
        
        public int getorderID() {
            return orderID;
        }
        public int getcustomerID() {
            return customerID;
        }
        public String getpizzaType() {
            return pizzaType;
        }
        public int getpizzaQTY() {
            return pizzaQTY;
        }
        public String getfriesType() {
            return friesType;
        }
        public int getfriesQTY() {
            return friesQTY;
        }
        public String getburgersType() {
            return burgersType;
        }
        public int getburgersQTY() {
            return burgersQTY;
        }        
        public String getcalzonesType() {
            return calzonesType;
        } 
        public int getcalzonesQTY() {
            return calzonesQTY;
        }
        public String getsaladType() {
            return saladType;
        }
        public int getsaladQTY() {
            return saladQTY;
        }
        public String getsoupType() {
            return soupType;
        }
        public int getsoupQTY() {
            return soupQTY;
        }
        public String getdessertType() {
            return dessertType;
        }
        public int getdessertQTY() {
            return dessertQTY;
        }
        public String getdrinksType() {
            return drinksType;
        }
        public int getdrinksQTY() {
            return drinksQTY;
        }    
        public String getcashierName() {
            return cashierName;
        }
        public String gettotalCost() {
            return totalCost;
    }
    
}
