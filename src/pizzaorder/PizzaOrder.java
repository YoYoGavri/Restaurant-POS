/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaorder;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author gaben
 */
public class PizzaOrder extends javax.swing.JFrame {
  int pizzaQty, friesQty, burgersQty, calzonesQty, saladsQty, soupQty, dessertsQty, drinksQty, orderID, custID = 0;
  double totalCost, totalPrice, pizzaCost, friesCost, burgersCost, calzonesCost, saladsCost, soupCost, dessertsCost, drinksCost = 0.00;
  double tax = 0.00;
  //double orderedPrice, orderedTotalPrice, deliveryFee, distanceFromShop;

  public static final String DBURL = "jdbc:derby://localhost:1527/PizzaOrder;create=true;user=PizzaOrder;password=PizzaOrder"; //Create DB on NetBeans
  public static Connection conn = null;
    /**
     * Creates new form pizzaForm
     */
    public PizzaOrder() {
    initComponents();
    establishConnection();
  }
  public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PizzaOrder().setVisible(true);
            }
        });
      
      
       
      

  }
  
  public static void establishConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(DBURL);

        }
        catch (Exception e)
        {
            System.err.println("Connection Failed!");
            System.err.println(e.getMessage());
        }
    }

  
  public int dbUserInsertion () {
      try {
          try {
              fnameTXT.getText();
          } catch (Exception e) {
              fnameTXT.setText("");
          }
          
          try {
              lnameTXT.getText();
          } catch (Exception e) {
              lnameTXT.setText("");
          }
          
          try {
              phoneNumberTXT.getText();
          } catch (Exception e) {
              phoneNumberTXT.setText("");
          }
          
          try {
              eaddressTXT.getText();
          } catch (Exception e) {
              eaddressTXT.setText("");
          }
          
          try {
              streetAddressTXT.getText();
          } catch (Exception e) {
              streetAddressTXT.setText("");
          }
          
          try {
              cityTXT.getText();
          } catch (Exception e) {
              cityTXT.setText("");
          }
          
          try {
              zipcodeTXT.getText();
          } catch (Exception e) {
              zipcodeTXT.setText("");
          }
          
          try {
              stateTXT.getText();
          } catch (Exception e) {
              stateTXT.setText("");
          }
        
          try {
              idTXT.getText();
          } catch (Exception e) {
              idTXT.setText("");
          }       
   
        String query = "select customerID from userInfo";
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(query);
        while (rs.next()) {
            orderID = rs.getInt("customerID");
            custID = orderID; }
        String sql = "INSERT INTO USERINFO (firstName, lastName, phoneNum, emailAddress, streetAddress, city, zipcode, state) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, fnameTXT.getText());
        stmt.setString(2, lnameTXT.getText());
        stmt.setString(3, phoneNumberTXT.getText());
        stmt.setString(4, eaddressTXT.getText());
        stmt.setString(5, streetAddressTXT.getText());
        stmt.setString(6, cityTXT.getText());
        stmt.setString(7, zipcodeTXT.getText());
        stmt.setString(8, stateTXT.getText());
        //stmt.setInt(9, custID);
        stmt.executeUpdate();
        DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
        model.setRowCount(0);
        show_customers();
        
        stmt.close();
            
    }
      
    catch (SQLException e) {
        System.err.println("Customer Insert Failed");
        System.err.println(e.getMessage());
    } 
    return orderID;
  }
  
   public void dbOrderInsertion () {
       String pizza, fries, burgers, calzones, salads, soup, desserts, drinks, cashierName; 
       int pizzaAmt, friesAmt, burgersAmt, calzonesAmt, saladsAmt, soupAmt, dessertsAmt, drinksAmt;

       
       
       try {
        String orderInfo = "INSERT INTO ORDERINFO (customerID, pizzaType, pizzaQTY, friesType, friesQTY, burgersType, burgersQTY, calzonesType, calzonesQTY, saladType, saladQTY, soupType, soupQTY, dessertType, dessertQTY, drinksType, drinksQTY, cashierName, totalCost) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(orderInfo);
        ps.setString(1, idTXT.getText());
        
        Object pizzaSelect = pizzaCB.getSelectedItem();
        if (pizzaSelect.toString().equals("Traditional Pizza")) {
            pizza = "Traditional Pizza";
            ps.setString(2, pizza); }
            //System.out.println(pizza);    }
        else if (pizzaSelect.toString().equals("Sicilian")) {
            pizza = "Sicilian"; 
            ps.setString(2, pizza); }
            //System.out.println(pizza);    }
        else if (pizzaSelect.toString().equals("Pepperoni")) {
            pizza = "Pepperoni"; 
            ps.setString(2, pizza);  }
            //System.out.println(pizza);    }
        else if (pizzaSelect.toString().equals("Mushroom")) {
            pizza = "Mushroom"; 
            ps.setString(2, pizza); }
            //System.out.println(pizza);    }
        else if (pizzaSelect.toString().equals("Pineapple")) {
            pizza = "Pineapple"; 
            ps.setString(2, pizza); }
            //System.out.println(pizza);    }
        
        String pizzaQty = pizzaSpinner.getValue().toString();
        pizzaAmt = Integer.parseInt(pizzaQty);
        ps.setInt(3, pizzaAmt);
        
        Object friesSelect = friesCB.getSelectedItem();
        if (friesSelect.toString().equals("French Fries")) {
            fries = "French Fries";
            ps.setString(4, fries);}
        else if (friesSelect.toString().equals("Cheese Fries")) {
            fries = "Cheese Fries"; 
            ps.setString(4, fries); }
        else if (friesSelect.toString().equals("Steak")) {
            fries = "Steak"; 
            ps.setString(4, fries); }
        else if (friesSelect.toString().equals("Sweet Potato")) {
            fries = "Sweet Potato"; 
            ps.setString(4, fries); }
        
        String friesQty = friesSpinner.getValue().toString();
        friesAmt = Integer.parseInt(friesQty);
        ps.setInt(5, friesAmt);
        
        Object burgersSelect = burgersCB.getSelectedItem();
        if (burgersSelect.toString().equals("Beef Burger")) {
            burgers = "Beef Burger";
            ps.setString(6, burgers);}
        else if (burgersSelect.toString().equals("Cheeseburger")) {
            burgers = "Cheeseburger"; 
            ps.setString(6, burgers); }
        else if (burgersSelect.toString().equals("Bacon Burger")) {
            burgers = "Bacon Burger"; 
            ps.setString(6, burgers); }
        else if (burgersSelect.toString().equals("Bacon Cheeseburger")) {
            burgers = "Bacon Cheese Burger"; 
            ps.setString(6, burgers); }
        
        String burgersQty = burgersSpinner.getValue().toString();
        burgersAmt = Integer.parseInt(burgersQty);
        ps.setInt(7, burgersAmt);
        
        Object calzonesSelect = calzonesCB.getSelectedItem();
        if (calzonesSelect.toString().equals("Veggie")) {
            calzones = "Veggie";
            ps.setString(8, calzones);}
        else if (calzonesSelect.toString().equals("Steak")) {
            calzones = "Steak"; 
            ps.setString(8, calzones); }
        else if (calzonesSelect.toString().equals("Chicken")) {
            calzones = "Bacon Burger"; 
            ps.setString(8, calzones); }
        
        String calzonesQty = calzonesSpinner.getValue().toString();
        calzonesAmt = Integer.parseInt(calzonesQty);
        ps.setInt(9, calzonesAmt);

        Object saladsSelect = saladsCB.getSelectedItem();
        if (saladsSelect.toString().equals("Tuna")) {
            salads = "Tuna";
            ps.setString(10, salads);}
        else if (saladsSelect.toString().equals("Egg")) {
            salads = "Egg"; 
            ps.setString(10, salads); }
        else if (saladsSelect.toString().equals("Caesar")) {
            salads = "Caesar"; 
            ps.setString(10, salads); }
        else if (saladsSelect.toString().equals("Greek")) {
            salads = "Greek"; 
            ps.setString(10, salads); }
        
        String saladsQty = saladsSpinner.getValue().toString();
        saladsAmt = Integer.parseInt(saladsQty);
        ps.setInt(11, saladsAmt);
        
        Object soupsSelect = soupCB.getSelectedItem();
        if (soupsSelect.toString().equals("Chicken Noodle")) {
            soup = "Chicken Noodle";
            ps.setString(12, soup);}
        else if (soupsSelect.toString().equals("Butternut Squash")) {
            soup = "Butternut Squash"; 
            ps.setString(12, soup); }
        else if (soupsSelect.toString().equals("Tomato")) {
            soup = "Tomato"; 
            ps.setString(12, soup); }
        
        String soupsQty = soupSpinner.getValue().toString();
        soupAmt = Integer.parseInt(soupsQty);
        ps.setInt(13, soupAmt);

        Object dessertsSelect = dessertCB.getSelectedItem();
        if (dessertsSelect.toString().equals("Vanilla Ice Cream")) {
            desserts = "Vanilla Ice Cream";
            ps.setString(14, desserts);}
        else if (dessertsSelect.toString().equals("Chocolate Ice Cream")) {
            desserts = "Chocolate Ice Cream"; 
            ps.setString(14, desserts); }
        else if (dessertsSelect.toString().equals("Vanilla Cake")) {
            desserts = "Vanilla Cake"; 
            ps.setString(14, desserts); }
        else if (dessertsSelect.toString().equals("Chocolate Cake")) {
            desserts = "Chocolate Cake"; 
            ps.setString(14, desserts); }
        
        String dessertsQty = dessertsSpinner.getValue().toString();
        dessertsAmt = Integer.parseInt(dessertsQty);
        ps.setInt(15, dessertsAmt);
        
        Object drinksSelect = drinksCB.getSelectedItem();
        if (drinksSelect.toString().equals("Coca-Cola")) {
            drinks = "Coca-Cola";
            ps.setString(16, drinks);}
        else if (drinksSelect.toString().equals("Pepsi")) {
            drinks = "Pepsi"; 
            ps.setString(16, drinks); }
        else if (drinksSelect.toString().equals("Canada Dry")) {
            drinks = "Canada Dry"; 
            ps.setString(16, drinks); }
        else if (drinksSelect.toString().equals("Orange Juice")) {
            drinks = "Orange Juice"; 
            ps.setString(16, drinks); }
        
        String drinksQty = drinksSpinner.getValue().toString();
        drinksAmt = Integer.parseInt(drinksQty);
        ps.setInt(17, drinksAmt);
        
        Object cashierSelect = cashierCB.getSelectedItem();
        if (cashierSelect.toString().equals("Jason")) {
            cashierName = "Jason";
            ps.setString(18, cashierName);}
        else if (cashierSelect.toString().equals("John")) {
            cashierName = "John"; 
            ps.setString(18, cashierName); }
        else if (cashierSelect.toString().equals("Louis")) {
            cashierName = "Louis"; 
            ps.setString(18, cashierName); }
        else if (cashierSelect.toString().equals("Kyle")) {
            cashierName = "Kyle"; 
            ps.setString(18, cashierName); }
        
        double totalPrice = pizzaCost + friesCost + burgersCost + calzonesCost + saladsCost + soupCost + dessertsCost + drinksCost;
        String totalCost = String.valueOf(totalPrice + (0.06 * totalPrice));
        
        ps.setString(19, ("$" + totalCost + ""));
        
        ps.executeUpdate();
        DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
        model.setRowCount(0);
        show_customers();
        DefaultTableModel orderModel = (DefaultTableModel)orderTable.getModel();
        orderModel.setRowCount(0);
        show_orders();
        ps.close();

    } catch (SQLException e) {
    //} catch (Exception e) {
        System.err.println("Order Insert Failed");
        System.err.println(e.getMessage());
    } 
       
  }
 
    
    public ArrayList<User> userList () {
        ArrayList<User> customerList = new ArrayList<>();
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection conn = DriverManager.getConnection(DBURL);
            String query = "SELECT * FROM userInfo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user;
            while(rs.next()) {
                user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNum"),rs.getString("emailAddress"), rs.getString("streetAddress"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getInt("customerID"));
                customerList.add(user);
                
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return customerList;
    }
    
    public ArrayList<Orders> orderList () {
        ArrayList<Orders> orderList = new ArrayList<>();
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            Connection conn = DriverManager.getConnection(DBURL);
            String query = "SELECT * FROM orderInfo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Orders order;
            while(rs.next()) {
                order = new Orders(rs.getInt("orderID"), rs.getInt("customerID"), rs.getString("pizzaType"),rs.getInt("pizzaQTY"), rs.getString("friesType"), rs.getInt("friesQTY"), rs.getString("burgersType"), rs.getInt("burgersQTY"), rs.getString("calzonesType"), rs.getInt("calzonesQTY"), rs.getString("saladType"), rs.getInt("saladQTY"), rs.getString("soupType"), rs.getInt("soupQTY"), rs.getString("dessertType"), rs.getInt("dessertQTY"), rs.getString("drinksType"), rs.getInt("drinksQTY"), rs.getString("cashierName"), rs.getString("totalCost"));
                orderList.add(order);
                
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return orderList;
    }
    
    public void show_customers() {
        ArrayList<User> list = userList();
        DefaultTableModel model = (DefaultTableModel) dbTable.getModel();
        Object [] rows = new Object [9];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = list.get(i).getfirstName();
            rows[1] = list.get(i).getlastName();
            rows[2] = list.get(i).getphoneNum();
            rows[3] = list.get(i).getemailAddress();
            rows[4] = list.get(i).getstreetAddress();
            rows[5] = list.get(i).getcity();
            rows[6] = list.get(i).getzipcode();
            rows[7] = list.get(i).getstate();
            rows[8] = list.get(i).getcustomerID();
            model.addRow(rows);
        }
        
    }
    
    public void show_orders() {
        ArrayList<Orders> list = orderList();
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        Object [] rows = new Object [20];
        for (int i = 0; i < list.size(); i++) {
            rows[0] = list.get(i).getorderID();
            rows[1] = list.get(i).getcustomerID();
            rows[2] = list.get(i).getpizzaType();
            rows[3] = list.get(i).getpizzaQTY();
            rows[4] = list.get(i).getfriesType();
            rows[5] = list.get(i).getfriesQTY();
            rows[6] = list.get(i).getburgersType();
            rows[7] = list.get(i).getburgersQTY();
            rows[8] = list.get(i).getcalzonesType();
            rows[9] = list.get(i).getcalzonesQTY();
            rows[10] = list.get(i).getsaladType();
            rows[11] = list.get(i).getsaladQTY();
            rows[12] = list.get(i).getsoupType();
            rows[13] = list.get(i).getsoupQTY();
            rows[14] = list.get(i).getdessertType();
            rows[15] = list.get(i).getdessertQTY();
            rows[16] = list.get(i).getdrinksType();
            rows[17] = list.get(i).getdrinksQTY();
            rows[18] = list.get(i).getcashierName();
            rows[19] = list.get(i).gettotalCost();


            model.addRow(rows);
        }
        
    }

    private void filter (String query) {
            DefaultTableModel model = (DefaultTableModel) dbTable.getModel();
            TableRowSorter<DefaultTableModel> filter = new TableRowSorter<DefaultTableModel>(model);
            dbTable.setRowSorter(filter);
        
            filter.setRowFilter(RowFilter.regexFilter(query));
        }
    private void orderFilter (String query) {
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            TableRowSorter<DefaultTableModel> filter = new TableRowSorter<DefaultTableModel>(model);
            orderTable.setRowSorter(filter);
        
            filter.setRowFilter(RowFilter.regexFilter(query));
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fnameTXT = new javax.swing.JTextField();
        lnameTXT = new javax.swing.JTextField();
        phoneNumberTXT = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        pizzaCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        pizzaPrice = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        friesCB = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        friesPrice = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        burgersCB = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        burgersPrice = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        calzonesCB = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        calzonesPrice = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        saladsCB = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        saladsPrice = new javax.swing.JLabel();
        saladsSpinner = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        dessertCB = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        dessertsPrice = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        dessertsSpinner = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        drinksCB = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        drinksPrice = new javax.swing.JLabel();
        drinksSpinner = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        soupCB = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        soupsPrice = new javax.swing.JLabel();
        soupSpinner = new javax.swing.JSpinner();
        calzonesSpinner = new javax.swing.JSpinner();
        burgersSpinner = new javax.swing.JSpinner();
        friesSpinner = new javax.swing.JSpinner();
        pizzaSpinner = new javax.swing.JSpinner();
        eaddressTXT = new javax.swing.JTextField();
        stateTXT = new javax.swing.JTextField();
        zipcodeTXT = new javax.swing.JTextField();
        streetAddressTXT = new javax.swing.JTextField();
        cityTXT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        idTXT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cashierCB = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        taxLabel = new javax.swing.JLabel();
        gratuityLabel = new javax.swing.JLabel();
        deleteOrderbtn = new javax.swing.JButton();
        confirmBtn = new javax.swing.JButton();
        displayReceiptbtn = new javax.swing.JButton();
        receiptBtn = new javax.swing.JButton();
        clearBtn1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        searchPhoneNumberTXT = new javax.swing.JTextField();
        updateCustomerbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dbTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        deleteCustomerbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("Krusty Krabs Pizza");

        jLabel1.setText("First Name:");

        jLabel2.setText("Last Name:");

        jLabel3.setText("Phone Number:");

        jLabel4.setText("Street Address:");

        jLabel5.setText("Email Address:");

        jLabel6.setText("City");

        jLabel7.setText("State:");

        jLabel8.setText("Zipcode:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Order Details (Choose Qty first)");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Customer Info");

        fnameTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fnameTXT.setName("fnameTXT"); // NOI18N
        fnameTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTXTActionPerformed(evt);
            }
        });
        fnameTXT.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fnameTXTPropertyChange(evt);
            }
        });
        fnameTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fnameTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fnameTXTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fnameTXTKeyTyped(evt);
            }
        });

        lnameTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lnameTXT.setName("lnameTXT"); // NOI18N
        lnameTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameTXTActionPerformed(evt);
            }
        });
        lnameTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lnameTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lnameTXTKeyReleased(evt);
            }
        });

        phoneNumberTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneNumberTXT.setText("(xxx)xxx-xxxx");
        phoneNumberTXT.setName("lnameTXT"); // NOI18N
        phoneNumberTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phoneNumberTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneNumberTXTFocusLost(evt);
            }
        });
        phoneNumberTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTXTActionPerformed(evt);
            }
        });
        phoneNumberTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoneNumberTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNumberTXTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneNumberTXTKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Pizza");

        pizzaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Traditional Pizza", "Sicilian", "Pepperoni", "Mushroom", "Pineapple" }));
        pizzaCB.setName("pizzaCB"); // NOI18N
        pizzaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pizzaCBActionPerformed(evt);
            }
        });

        jLabel13.setText("PRICE:");

        pizzaPrice.setText("$0.00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Fries");

        friesCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "French Fries", "Cheese Fries", "Crinkle-Cut", "Steak", "Sweet Potato" }));
        friesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friesCBActionPerformed(evt);
            }
        });

        jLabel20.setText("PRICE:");

        friesPrice.setText("$0.00");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Burgers");

        burgersCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Beef Burger", "Cheeseburger", "Bacon Burger", "Bacon Cheeseburger" }));
        burgersCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burgersCBActionPerformed(evt);
            }
        });

        jLabel24.setText("PRICE:");

        burgersPrice.setText("$0.00");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Calzones");

        calzonesCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Veggie", "Steak", "Chicken" }));
        calzonesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calzonesCBActionPerformed(evt);
            }
        });
        calzonesCB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calzonesCBPropertyChange(evt);
            }
        });

        jLabel28.setText("PRICE:");

        calzonesPrice.setText("$0.00");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Salads");

        saladsCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Tuna", "Egg", "Caesar", "Greek" }));
        saladsCB.setToolTipText("");
        saladsCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saladsCBActionPerformed(evt);
            }
        });

        jLabel32.setText("PRICE:");

        saladsPrice.setText("$0.00");

        saladsSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saladsSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        saladsSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                saladsSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(saladsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(saladsPrice, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saladsCB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saladsCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saladsPrice))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(saladsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        dessertCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Vanilla Ice Cream", "Chocolate Ice Cream", "Vanilla Cake", "Chocolate Cake" }));
        dessertCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dessertCBActionPerformed(evt);
            }
        });

        jLabel40.setText("PRICE:");

        dessertsPrice.setText("$0.00");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Desserts");

        dessertsSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dessertsSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        dessertsSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dessertsSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dessertsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dessertsPrice, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(dessertCB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dessertCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dessertsPrice))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(dessertsSpinner)
                        .addContainerGap())))
        );

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Drinks");

        drinksCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Coca-Cola", "Pepsi", "Canada Dry", "Orange Juice" }));
        drinksCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinksCBActionPerformed(evt);
            }
        });

        jLabel36.setText("PRICE:");

        drinksPrice.setText("$0.00");

        drinksSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        drinksSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        drinksSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                drinksSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drinksCB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(drinksSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(drinksPrice)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drinksCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(drinksPrice))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(drinksSpinner)
                        .addContainerGap())))
        );

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Soup");

        soupCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Chicken Noodle", "Butternut Squash", "Tomato" }));
        soupCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soupCBActionPerformed(evt);
            }
        });

        jLabel44.setText("PRICE:");

        soupsPrice.setText("$0.00");

        soupSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        soupSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        soupSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                soupSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soupCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(soupSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44)
                            .addComponent(soupsPrice)))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soupCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(soupsPrice))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(soupSpinner)
                        .addContainerGap())))
        );

        calzonesSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        calzonesSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        calzonesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                calzonesSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calzonesCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(calzonesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calzonesPrice, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calzonesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calzonesPrice)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(calzonesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        burgersSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        burgersSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        burgersSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                burgersSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(burgersCB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(burgersSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(burgersPrice, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(burgersCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(burgersPrice))
                    .addComponent(burgersSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        friesSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        friesSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        friesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                friesSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(friesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(friesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(friesPrice, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(friesPrice)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(friesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pizzaSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pizzaSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        pizzaSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pizzaSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pizzaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pizzaPrice, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pizzaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pizzaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pizzaPrice))
                            .addComponent(pizzaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        eaddressTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        eaddressTXT.setName("lnameTXT"); // NOI18N
        eaddressTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eaddressTXTFocusLost(evt);
            }
        });
        eaddressTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                eaddressTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eaddressTXTKeyReleased(evt);
            }
        });

        stateTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stateTXT.setName("lnameTXT"); // NOI18N
        stateTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stateTXTKeyPressed(evt);
            }
        });

        zipcodeTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        zipcodeTXT.setName("lnameTXT"); // NOI18N
        zipcodeTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipcodeTXTActionPerformed(evt);
            }
        });
        zipcodeTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zipcodeTXTKeyPressed(evt);
            }
        });

        streetAddressTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        streetAddressTXT.setName("lnameTXT"); // NOI18N
        streetAddressTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                streetAddressTXTKeyPressed(evt);
            }
        });

        cityTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cityTXT.setName("lnameTXT"); // NOI18N
        cityTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cityTXTKeyPressed(evt);
            }
        });

        jButton1.setText("New Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Existing Customer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        idTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idTXTKeyReleased(evt);
            }
        });

        jLabel15.setText("Cashier Name:");

        cashierCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jason", "John", "Louis", "Kyle" }));

        taxLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        gratuityLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        deleteOrderbtn.setText("Delete Order");
        deleteOrderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderbtnActionPerformed(evt);
            }
        });

        confirmBtn.setText("Confirm Order");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        displayReceiptbtn.setText("Display Receipt");
        displayReceiptbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayReceiptbtnActionPerformed(evt);
            }
        });

        receiptBtn.setText("Print Receipt");
        receiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptBtnActionPerformed(evt);
            }
        });

        clearBtn1.setText("Update Order");
        clearBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(clearBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(taxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(deleteOrderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(gratuityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(displayReceiptbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(receiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gratuityLabel)
                    .addComponent(taxLabel))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(confirmBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(deleteOrderbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(displayReceiptbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(receiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jLabel18.setText("Phone Number:");

        searchPhoneNumberTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchPhoneNumberTXT.setText("(xxx)xxx-xxxx");
        searchPhoneNumberTXT.setName("lnameTXT"); // NOI18N
        searchPhoneNumberTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchPhoneNumberTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchPhoneNumberTXTFocusLost(evt);
            }
        });
        searchPhoneNumberTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPhoneNumberTXTActionPerformed(evt);
            }
        });
        searchPhoneNumberTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchPhoneNumberTXTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchPhoneNumberTXTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchPhoneNumberTXTKeyTyped(evt);
            }
        });

        updateCustomerbtn.setText("Update Customer");
        updateCustomerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCustomerbtnActionPerformed(evt);
            }
        });

        dbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Phone Number", "Email Address", "Street Address", "City", "Zipcode", "State", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dbTable.getTableHeader().setReorderingAllowed(false);
        dbTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dbTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dbTable);
        if (dbTable.getColumnModel().getColumnCount() > 0) {
            dbTable.getColumnModel().getColumn(6).setPreferredWidth(20);
            dbTable.getColumnModel().getColumn(7).setPreferredWidth(10);
            dbTable.getColumnModel().getColumn(8).setPreferredWidth(0);
        }

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        receiptArea.setColumns(20);
        receiptArea.setRows(5);
        jScrollPane1.setViewportView(receiptArea);

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Customer ID", "Pizza Type", "Pizza QTY", "Fries Type", "Fries QTY", "Burgers Type", "Burgers QTY", "Calzone Type", "Calzone QTY", "Salad Type", "Salad QTY", "Soup Type", "Soup QTY", "Dessert Type", "Dessert QTY", "Drinks Type", "Drinks QTY", "Cashier", "Total Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(orderTable);

        deleteCustomerbtn.setText("Delete Customer");
        deleteCustomerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(idTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(streetAddressTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel8))
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(fnameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lnameTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(phoneNumberTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(eaddressTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(cityTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(zipcodeTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(stateTXT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateCustomerbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteCustomerbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel9)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
                                        .addComponent(jLabel18)
                                        .addGap(20, 20, 20)
                                        .addComponent(searchPhoneNumberTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cashierCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(257, 257, 257))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1449, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(fnameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(lnameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(phoneNumberTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(eaddressTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(streetAddressTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cityTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(zipcodeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(stateTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateCustomerbtn)
                                    .addComponent(jButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2)
                                    .addComponent(deleteCustomerbtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cashierCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(searchPhoneNumberTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(470, 470, 470)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        label1.getAccessibleContext().setAccessibleName("title");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fnameTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameTXTActionPerformed

    private void pizzaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pizzaCBActionPerformed
        JComboBox pizzaCB = (JComboBox)evt.getSource();
        Object selected = pizzaCB.getSelectedItem();
        pizzaCost = 0;
        

        if (selected.toString().equals("Traditional Pizza")) {
            pizzaPrice.setText("$7.50");
            pizzaCost = (7.00 * pizzaQty); 
            pizzaPrice.setText("$" + pizzaCost);
       

        } else if (selected.toString().equals("Sicilian")) {
            pizzaPrice.setText("$16.00");
            pizzaCost = 16.00 * pizzaQty;
            pizzaPrice.setText("$" + pizzaCost);
            

        } else if (selected.toString().equals("Pepperoni")) {
            pizzaPrice.setText("$12.00");
            pizzaCost = 12.00 * pizzaQty;
            pizzaPrice.setText("$" + pizzaCost);

        } else if (selected.toString().equals("Pineapple")) {
            pizzaPrice.setText("$9.00");
            pizzaCost = 9.00 * pizzaQty;
            pizzaPrice.setText("$" + pizzaCost);

        } else if (selected.toString().equals("Mushroom")) {
            pizzaPrice.setText("$8.50");
            pizzaCost = 8.50 * pizzaQty;
            pizzaPrice.setText("$" + pizzaCost);

        }
        
        

        
      
    }//GEN-LAST:event_pizzaCBActionPerformed

    private void friesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friesCBActionPerformed
        JComboBox friesCB = (JComboBox)evt.getSource();
        Object selected = friesCB.getSelectedItem();
        friesCost = 0;
        
        if (selected.toString().equals("French Fries")) {
            friesPrice.setText("$3.00");
            friesCost = 3.00 * friesQty;
            friesPrice.setText("$" + friesCost);
        } else if (selected.toString().equals("Cheese Fries")) {
            friesPrice.setText("$3.50");
            friesCost = 3.50 * friesQty; 
            friesPrice.setText("$" + friesCost);
        } else if (selected.toString().equals("Crinkle-Cut")) {
            friesPrice.setText("$4.00");
            friesCost = 4.00 * friesQty; 
            friesPrice.setText("$" + friesCost);
        } else if (selected.toString().equals("Steak")) {
            friesPrice.setText("$4.00");
            friesCost = 4.00 * friesQty; 
            friesPrice.setText("$" + friesCost);
        } else if (selected.toString().equals("Sweet Potato")) {
            friesPrice.setText("$5.00");
            friesCost = 5.00 * friesQty;
            friesPrice.setText("$" + friesCost);}

    }//GEN-LAST:event_friesCBActionPerformed

    private void burgersCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burgersCBActionPerformed
        JComboBox burgersCB = (JComboBox)evt.getSource();
        Object selected = burgersCB.getSelectedItem();
        burgersCost = 0;
        
        if (selected.toString().equals("Beef Burger")) {
            burgersPrice.setText("$5.00");
            burgersCost = 5.00 * burgersQty;
            burgersPrice.setText("$" + burgersCost);
        } else if (selected.toString().equals("Cheeseburger")) {
            burgersPrice.setText("$5.50");
            burgersCost = 5.50 * burgersQty; 
            burgersPrice.setText("$" + burgersCost);
        } else if (selected.toString().equals("Bacon Burger")) {
            burgersPrice.setText("$5.50");
            burgersCost = 5.50 * burgersQty;
            burgersPrice.setText("$" + burgersCost);
        } else if (selected.toString().equals("Bacon Cheeseburger")) {
            burgersPrice.setText("$6.00");
            burgersCost = 6.00 * burgersQty; 
            burgersPrice.setText("$" + burgersCost);
        } 

    }//GEN-LAST:event_burgersCBActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!(fnameTXT.getText().equals("") && lnameTXT.getText().equals("") && eaddressTXT.getText().equals("") && streetAddressTXT.getText().equals("") && cityTXT.getText().equals("") && cityTXT.getText().equals("") && zipcodeTXT.getText().equals("") && stateTXT.getText().equals("") && idTXT.getText().equals(""))) {
            dbUserInsertion();
        } else 
            JOptionPane.showMessageDialog(null,"At least one textbox is empty", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void calzonesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calzonesCBActionPerformed
        JComboBox calzonesCB = (JComboBox)evt.getSource();
        Object selected = calzonesCB.getSelectedItem();
        calzonesCost = 0;
        
        if (selected.toString().equals("Veggie")) {
            calzonesPrice.setText("$7.00");
            calzonesCost = 7.00 * calzonesQty;
            calzonesPrice.setText("$" + calzonesCost);
        } else if (selected.toString().equals("Steak")) {
            calzonesPrice.setText("$9.50");
            calzonesCost = 9.50 * calzonesQty;
            calzonesPrice.setText("$" + calzonesCost);
        } else if (selected.toString().equals("Chicken")) {
            calzonesPrice.setText("$9.50");
            calzonesCost = 9.50 * calzonesQty; 
            calzonesPrice.setText("$" + calzonesCost);
        }

    }//GEN-LAST:event_calzonesCBActionPerformed

    private void drinksCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drinksCBActionPerformed
        JComboBox drinksCB = (JComboBox)evt.getSource();
        Object selected = drinksCB.getSelectedItem();
        drinksCost = 0;
        
        if (selected.toString().equals("Coca-Cola")) {
            drinksPrice.setText("$2.00");
            drinksCost = 2.00 * drinksQty; 
            drinksPrice.setText("$" + drinksCost);
        } else if (selected.toString().equals("Pepsi")) {
            drinksPrice.setText("$2.00");
            drinksCost = 2.00 * drinksQty;  
            drinksPrice.setText("$" + drinksCost);
        } else if (selected.toString().equals("Canada Dry")) {
            drinksPrice.setText("$2.00");
            drinksCost = 2.00 * drinksQty;  
            drinksPrice.setText("$" + drinksCost);
        } else if (selected.toString().equals("Orange Juice")) {
            drinksPrice.setText("$3.00");
            drinksCost = 2.00 * drinksQty; 
            drinksPrice.setText("$" + drinksCost);
        }

    }//GEN-LAST:event_drinksCBActionPerformed

    private void saladsCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saladsCBActionPerformed
        JComboBox saladsCB = (JComboBox)evt.getSource();
        Object selected = saladsCB.getSelectedItem();
        saladsCost = 0;
        
        if (selected.toString().equals("Tuna")) {
            saladsPrice.setText("$6.00");
            saladsCost = 7.00 * saladsQty;
            saladsPrice.setText("$" + saladsCost);
        } else if (selected.toString().equals("Egg")) {
            saladsPrice.setText("$4.50");
            saladsCost = 4.50 * saladsQty; 
            saladsPrice.setText("$" + saladsCost);
        } else if (selected.toString().equals("Caesar")) {
            saladsPrice.setText("$5.50");
            saladsCost = 5.50 * saladsQty;
            saladsPrice.setText("$" + saladsCost);
        } else if (selected.toString().equals("Greek")) {
            saladsPrice.setText("$6.50");
            saladsCost = 6.50 * saladsQty;
            saladsPrice.setText("$" + saladsCost);}

    }//GEN-LAST:event_saladsCBActionPerformed

    private void dessertCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dessertCBActionPerformed
        JComboBox dessertCB = (JComboBox)evt.getSource();
        Object selected = dessertCB.getSelectedItem();
        dessertsCost = 0;
        
        if (selected.toString().equals("Vanilla Ice Cream")) {
            dessertsPrice.setText("$3.00");
            dessertsCost = 3.00 * dessertsQty; 
            dessertsPrice.setText("$" + dessertsCost);
        } else if (selected.toString().equals("Chocolate Ice Cream")) {
            dessertsPrice.setText("$3.00");
            dessertsCost = 3.00 * dessertsQty; 
            dessertsPrice.setText("$" + dessertsCost);
        } else if (selected.toString().equals("Vanilla Cake")) {
            dessertsPrice.setText("$3.00");
            dessertsCost = 3.00 * dessertsQty; 
            dessertsPrice.setText("$" + dessertsCost);
        } else if (selected.toString().equals("Chocolate Cake")) {
            dessertsPrice.setText("$3.00");
            dessertsCost = 3.00 * dessertsQty; 
            dessertsPrice.setText("$" + dessertsCost);
        }

    }//GEN-LAST:event_dessertCBActionPerformed

    private void soupCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soupCBActionPerformed
        JComboBox soupCB = (JComboBox)evt.getSource();
        Object selected = soupCB.getSelectedItem();
        soupCost = 0;
        
        if (selected.toString().equals("Chicken Noodle")) {
            soupsPrice.setText("$3.50");
            soupCost = 3.50 * soupQty; 
            soupsPrice.setText("$" + soupCost);
        } else if (selected.toString().equals("Butternut Squash")) {
            soupsPrice.setText("$4.50");
            soupCost = 4.50 * soupQty;  
            soupsPrice.setText("$" + soupCost);
        } else if (selected.toString().equals("Tomato")) {
            soupsPrice.setText("$3.00");
            soupCost = 3.00 * soupQty; 
            soupsPrice.setText("$" + soupCost);
        } 

    }//GEN-LAST:event_soupCBActionPerformed

    private void fnameTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameTXTKeyPressed
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
            fnameTXT.setEditable(true);
        } else {
            fnameTXT.setEditable(false);
        }
        
        if (fnameTXT.getText().length() > 29) {    
            fnameTXT.setText(""); }
    }//GEN-LAST:event_fnameTXTKeyPressed

    private void lnameTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameTXTActionPerformed
        
    }//GEN-LAST:event_lnameTXTActionPerformed

    private void lnameTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnameTXTKeyPressed
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
            lnameTXT.setEditable(true);
        } else {
            lnameTXT.setEditable(false);
        }
        if (lnameTXT.getText().length() > 29) {    
            lnameTXT.setText(""); }
        
    }//GEN-LAST:event_lnameTXTKeyPressed

    private void phoneNumberTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTXTKeyPressed
        char c = evt.getKeyChar();
        
        if(c >= '0' && c<= '9' || c == '(' || c == '-' || c == ')' || Character.isISOControl(c)) {
            phoneNumberTXT.setEditable(true);
        } else if (Character.isWhitespace(c)){
            phoneNumberTXT.setEditable(false);            
        }
        else {
            phoneNumberTXT.setEditable(false);
        }
        if (phoneNumberTXT.getText().length() > 12) {    
            phoneNumberTXT.setText("");
            }
        
    }//GEN-LAST:event_phoneNumberTXTKeyPressed

    private void phoneNumberTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTXTActionPerformed

    private void zipcodeTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zipcodeTXTActionPerformed

    }//GEN-LAST:event_zipcodeTXTActionPerformed

    private void zipcodeTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipcodeTXTKeyPressed
        char c = evt.getKeyChar();
        
        if(c >= '0' && c <= '9' || Character.isISOControl(c)) {
            zipcodeTXT.setEditable(true);
            if (zipcodeTXT.getText().length() > 4) {
                zipcodeTXT.setText("");
            }
        } else {
            zipcodeTXT.setEditable(false);
        }
    }//GEN-LAST:event_zipcodeTXTKeyPressed

    private void phoneNumberTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberTXTFocusGained
        if(phoneNumberTXT.getText().trim().equals("(xxx)xxx-xxxx")) {
            phoneNumberTXT.setText("");
        }
        if (phoneNumberTXT.getText().length() > 12) {    
            phoneNumberTXT.setText("");
            }
        phoneNumberTXT.setForeground(Color.BLACK);
    }//GEN-LAST:event_phoneNumberTXTFocusGained

    private void phoneNumberTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNumberTXTFocusLost

    }//GEN-LAST:event_phoneNumberTXTFocusLost

    private void fnameTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameTXTKeyReleased

        
    }//GEN-LAST:event_fnameTXTKeyReleased

    private void fnameTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fnameTXTKeyTyped

    }//GEN-LAST:event_fnameTXTKeyTyped

    private void stateTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stateTXTKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isISOControl(c)) {
            stateTXT.setEditable(true);
            if (stateTXT.getText().length() > 1) {
                stateTXT.setText("");
            }
        } else {
            stateTXT.setEditable(false);
        }
    }//GEN-LAST:event_stateTXTKeyPressed

    private void eaddressTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eaddressTXTFocusLost
        eaddressTXT.setEditable(true);
        if (!(eaddressTXT.getText().contains("@") && eaddressTXT.getText().contains(".com") || eaddressTXT.getText().contains("@") && eaddressTXT.getText().contains(".net") || eaddressTXT.getText().contains("@") && eaddressTXT.getText().contains(".org") || eaddressTXT.getText().contains("@") && eaddressTXT.getText().contains(".edu") || eaddressTXT.getText().contains("@") && eaddressTXT.getText().contains(".gov"))) {
            //JOptionPane.showMessageDialog(null,"Please enter a valid email", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
            eaddressTXT.setText("");
        }
        
    }//GEN-LAST:event_eaddressTXTFocusLost

    private void calzonesCBPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calzonesCBPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_calzonesCBPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (fnameTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid first name", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (lnameTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid last name", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (phoneNumberTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid phone number", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (eaddressTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid email address", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (streetAddressTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid street address", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (cityTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid city", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (zipcodeTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid zipcode", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else if (stateTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Please enter a valid state", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
        } else {
            dbUserInsertion(); }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        show_customers();
        show_orders();
    }//GEN-LAST:event_formWindowOpened

    private void idTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idTXTKeyPressed
        char c = evt.getKeyChar();
        if(c >= '0' && c <= '9' || Character.isISOControl(c)) {
            idTXT.setEditable(true); }
        String query = idTXT.getText();
        filter(query);
        
    }//GEN-LAST:event_idTXTKeyPressed

    private void phoneNumberTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTXTKeyTyped
        
        
        
        
        
    }//GEN-LAST:event_phoneNumberTXTKeyTyped

    private void fnameTXTPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fnameTXTPropertyChange
        
    }//GEN-LAST:event_fnameTXTPropertyChange

    private void phoneNumberTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTXTKeyReleased

    }//GEN-LAST:event_phoneNumberTXTKeyReleased

    private void lnameTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnameTXTKeyReleased
        
    }//GEN-LAST:event_lnameTXTKeyReleased

    private void eaddressTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eaddressTXTKeyReleased

    }//GEN-LAST:event_eaddressTXTKeyReleased

    private void idTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idTXTKeyReleased
        String query = idTXT.getText();
        orderFilter(query);
    }//GEN-LAST:event_idTXTKeyReleased

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        dbOrderInsertion();
    }//GEN-LAST:event_confirmBtnActionPerformed

    private void calzonesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_calzonesSpinnerStateChanged
        String value = calzonesSpinner.getValue().toString();
        calzonesQty = Integer.parseInt(value);  
    }//GEN-LAST:event_calzonesSpinnerStateChanged

    private void soupSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_soupSpinnerStateChanged
        String value = soupSpinner.getValue().toString();
        soupQty = Integer.parseInt(value); 
    }//GEN-LAST:event_soupSpinnerStateChanged

    private void burgersSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_burgersSpinnerStateChanged
        String value = burgersSpinner.getValue().toString();
        burgersQty = Integer.parseInt(value);   
    }//GEN-LAST:event_burgersSpinnerStateChanged

    private void saladsSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_saladsSpinnerStateChanged
        String value = saladsSpinner.getValue().toString();
        saladsQty = Integer.parseInt(value);      
    }//GEN-LAST:event_saladsSpinnerStateChanged

    private void dessertsSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dessertsSpinnerStateChanged
        String value = dessertsSpinner.getValue().toString();
        dessertsQty = Integer.parseInt(value);      
    }//GEN-LAST:event_dessertsSpinnerStateChanged

    private void drinksSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_drinksSpinnerStateChanged
        String value = drinksSpinner.getValue().toString();
        drinksQty = Integer.parseInt(value); 
    }//GEN-LAST:event_drinksSpinnerStateChanged

    private void friesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_friesSpinnerStateChanged
        String value = friesSpinner.getValue().toString();
        friesQty = Integer.parseInt(value);   
    }//GEN-LAST:event_friesSpinnerStateChanged

    private void pizzaSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pizzaSpinnerStateChanged
        String value = pizzaSpinner.getValue().toString();
        pizzaQty = Integer.parseInt(value);        

        
        
    }//GEN-LAST:event_pizzaSpinnerStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchPhoneNumberTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTFocusGained
        if(searchPhoneNumberTXT.getText().trim().equals("(xxx)xxx-xxxx")) {
            searchPhoneNumberTXT.setText("");
        }
        if (searchPhoneNumberTXT.getText().length() > 12) {    
            searchPhoneNumberTXT.setText("");
            }
        searchPhoneNumberTXT.setForeground(Color.BLACK);
    }//GEN-LAST:event_searchPhoneNumberTXTFocusGained

    private void searchPhoneNumberTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPhoneNumberTXTFocusLost

    private void searchPhoneNumberTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPhoneNumberTXTActionPerformed

    private void searchPhoneNumberTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTKeyPressed
        char c = evt.getKeyChar();
        
        if(c >= '0' && c<= '9' || c == '(' || c == '-' || c == ')' || Character.isISOControl(c)) {
            searchPhoneNumberTXT.setEditable(true);
            String query = searchPhoneNumberTXT.getText();
            filter(query);
        } 
        else {
            searchPhoneNumberTXT.setEditable(false);
            String query = searchPhoneNumberTXT.getText();
            filter(query);
        }
        if (searchPhoneNumberTXT.getText().length() > 12) {    
            searchPhoneNumberTXT.setText("");
            }
        
    }//GEN-LAST:event_searchPhoneNumberTXTKeyPressed

    private void searchPhoneNumberTXTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPhoneNumberTXTKeyReleased

    private void searchPhoneNumberTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchPhoneNumberTXTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPhoneNumberTXTKeyTyped

    private void updateCustomerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCustomerbtnActionPerformed
        try {
            String sql = "UPDATE USERINFO SET firstName =?, lastName=?, phoneNum=?, emailAddress=?, streetAddress=?, city=?, zipcode=?, state=? WHERE customerID =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fnameTXT.getText());
            stmt.setString(2, lnameTXT.getText());
            stmt.setString(3, phoneNumberTXT.getText());
            stmt.setString(4, eaddressTXT.getText());
            stmt.setString(5, streetAddressTXT.getText());
            stmt.setString(6, cityTXT.getText());
            stmt.setString(7, zipcodeTXT.getText());
            stmt.setString(8, stateTXT.getText());
            int index = Integer.parseInt(idTXT.getText());
            stmt.setInt(9, index);
            stmt.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
            model.setRowCount(0);
            show_customers();
            
            
        } catch (SQLException e) {
            System.out.println("Failed");
        }
        
        
    }//GEN-LAST:event_updateCustomerbtnActionPerformed

    private void eaddressTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eaddressTXTKeyPressed
        if (eaddressTXT.getText().length() > 29) {    
            eaddressTXT.setText(""); }
    }//GEN-LAST:event_eaddressTXTKeyPressed

    private void streetAddressTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_streetAddressTXTKeyPressed
        if (streetAddressTXT.getText().length() > 29) {    
            streetAddressTXT.setText(""); }
    }//GEN-LAST:event_streetAddressTXTKeyPressed

    private void cityTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityTXTKeyPressed
        if (cityTXT.getText().length() > 29) {    
            cityTXT.setText(""); }
    }//GEN-LAST:event_cityTXTKeyPressed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        fnameTXT.setText("");
        lnameTXT.setText("");
        phoneNumberTXT.setText("");
        eaddressTXT.setText("");
        streetAddressTXT.setText("");
        cityTXT.setText("");
        zipcodeTXT.setText("");
        stateTXT.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void dbTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dbTableMouseClicked
        int row = dbTable.getSelectedRow();
        TableModel model = dbTable.getModel();
        fnameTXT.setText(model.getValueAt(row, 0).toString());
        lnameTXT.setText(model.getValueAt(row, 1).toString());
        phoneNumberTXT.setText(model.getValueAt(row, 2).toString());
        eaddressTXT.setText(model.getValueAt(row, 3).toString());
        streetAddressTXT.setText(model.getValueAt(row, 4).toString());
        cityTXT.setText(model.getValueAt(row, 5).toString());
        zipcodeTXT.setText(model.getValueAt(row, 6).toString());
        stateTXT.setText(model.getValueAt(row, 7).toString());
        idTXT.setText(model.getValueAt(row, 8).toString());
        
        
    }//GEN-LAST:event_dbTableMouseClicked

    private void deleteOrderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderbtnActionPerformed
        try {
            String sql = "DELETE FROM orderInfo WHERE orderID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            int index = Integer.parseInt(idTXT.getText());
            stmt.setInt(1, index);
            stmt.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
            model.setRowCount(0);
            show_customers();
            DefaultTableModel orderModel = (DefaultTableModel)orderTable.getModel();
            orderModel.setRowCount(0);
            show_orders();
            
            JOptionPane.showMessageDialog(null,"Order deleted at Order: #" + index);
            
        } catch (SQLException e) {
            System.out.println("Failed");
        }
    }//GEN-LAST:event_deleteOrderbtnActionPerformed

    private void receiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptBtnActionPerformed
        totalPrice = pizzaCost + friesCost + burgersCost + calzonesCost + saladsCost + soupCost + dessertsCost + drinksCost;
        totalCost = (totalPrice + (0.06 * totalPrice));
        String subtotal = String.format("%,.2f" ,totalPrice);
        String total = String.format("%,.2f" ,totalCost);
        
        try { 
            boolean print = receiptArea.print();
            if (print) {
                JOptionPane.showMessageDialog(null, "Done");
            } else {
                JOptionPane.showMessageDialog(null, "Printing");
            }
       } catch (PrinterException ex) {
           
       }
        
        
        
        

        
    }//GEN-LAST:event_receiptBtnActionPerformed

    private void displayReceiptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayReceiptbtnActionPerformed
        String fname = String.format(fnameTXT.getText());
        String lname = String.format(lnameTXT.getText());
        totalPrice = pizzaCost + friesCost + burgersCost + calzonesCost + saladsCost + soupCost + dessertsCost + drinksCost;
        totalCost = (totalPrice + (0.06 * totalPrice));
        String price = String.format("%,.2f" ,totalPrice);
        String cost = String.format("%,.2f" ,totalCost);
        tax = totalCost - totalPrice;
        String taxFormat = String.format("%,.2f" ,tax);
        Date date = new Date();
        
        receiptArea.append("*************************\n**Krusty Krab's Pizza**\n*************************\n********Receipt********\n\n" + date +"\n" + fname + " " + lname + "\n\n"
                + pizzaCB.getSelectedItem() + "    -    " + pizzaQty + "    -    " + pizzaPrice.getText() + "\n"
                + friesCB.getSelectedItem() + "    -    " + friesQty + "    -    " + friesPrice.getText() + "\n"
                + burgersCB.getSelectedItem() + "    -    " + burgersQty + "    -    " + burgersPrice.getText() + "\n"
                + calzonesCB.getSelectedItem() + "    -    " + calzonesQty + "    -    " + calzonesPrice.getText() + "\n"
                + saladsCB.getSelectedItem() + "    -    " + saladsQty + "    -    " + saladsPrice.getText() + "\n"
                + soupCB.getSelectedItem() + "    -    " + soupQty + "    -    " + soupsPrice.getText() + "\n"
                + dessertCB.getSelectedItem() + "    -    " + dessertsQty + "    -    " + dessertsPrice.getText() + "\n"
                + drinksCB.getSelectedItem() + "    -    " + drinksQty + "    -    " + drinksPrice.getText() + "\n"
                + "***********************Tax: $" + (taxFormat) + "\n"
                + "***********************Total Cost: $" + cost);
        
        
    }//GEN-LAST:event_displayReceiptbtnActionPerformed

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked

    }//GEN-LAST:event_orderTableMouseClicked

    private void deleteCustomerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerbtnActionPerformed
        try {
            String query = "DELETE FROM orderInfo WHERE customerID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            int index = Integer.parseInt(idTXT.getText());
            stmt.setInt(1, index);
            stmt.executeUpdate();
            
            String sql = "DELETE FROM userInfo WHERE customerID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            ps.executeUpdate();
            
            
            DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
            model.setRowCount(0);
            show_customers();
            DefaultTableModel orderModel = (DefaultTableModel)orderTable.getModel();
            orderModel.setRowCount(0);
            show_orders();
            
            //JOptionPane.showMessageDialog(null,"Customer deleted at Customer ID: #" + index);
            
        } catch (SQLException e) {
            System.out.println("Failed");
            System.err.println(e);
        }
                          
    }//GEN-LAST:event_deleteCustomerbtnActionPerformed

    private void clearBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn1ActionPerformed
        try {
            String sql = "UPDATE orderInfo SET pizzaType = ?, pizzaQTY = ?, friesType = ?, friesQTY = ?, burgersType = ?, burgersQTY = ?, calzonesType = ?, calzonesQTY = ?, saladType = ?, saladQTY = ?, soupType = ?, soupQTY = ?, dessertType = ?, dessertQTY = ?, drinksType = ?, drinksQTY = ?, cashierName = ?, totalCost = ? WHERE orderID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            Object pizzaSelect = pizzaCB.getSelectedItem();
            Object friesSelect = pizzaCB.getSelectedItem();
            Object burgersSelect = pizzaCB.getSelectedItem();
            Object calzonesSelect = pizzaCB.getSelectedItem();
            Object saladSelect = pizzaCB.getSelectedItem();
            Object soupSelect = pizzaCB.getSelectedItem();
            Object dessertSelect = pizzaCB.getSelectedItem();
            Object drinksSelect = pizzaCB.getSelectedItem();
            Object cashierSelect = cashierCB.getSelectedItem();
            
            stmt.setString(1, pizzaSelect.toString());
            stmt.setInt(2, pizzaQty);
            stmt.setString(3, friesSelect.toString());
            stmt.setInt(4, friesQty);
            stmt.setString(5, burgersSelect.toString());
            stmt.setInt(6, burgersQty);
            stmt.setString(7, calzonesSelect.toString());
            stmt.setInt(8, calzonesQty);
            stmt.setString(9, saladSelect.toString());
            stmt.setInt(10, saladsQty);
            stmt.setString(11, soupSelect.toString());
            stmt.setInt(12, soupQty);
            stmt.setString(13, dessertSelect.toString());
            stmt.setInt(14, dessertsQty);
            stmt.setString(15, drinksSelect.toString());
            stmt.setInt(16, drinksQty);
            stmt.setString(17, cashierSelect.toString());
            String TC = String.valueOf(totalCost);
            stmt.setString(18, TC);
            int index = Integer.parseInt(idTXT.getText());
            stmt.setInt(19, index);
            stmt.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)dbTable.getModel();
            model.setRowCount(0);
            show_customers();
            DefaultTableModel orderModel = (DefaultTableModel)orderTable.getModel();
            orderModel.setRowCount(0);
            show_orders();
            
        } catch (SQLException e) {
            System.out.println("Failed");
            System.err.println(e);
        }
    }//GEN-LAST:event_clearBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> burgersCB;
    private javax.swing.JLabel burgersPrice;
    private javax.swing.JSpinner burgersSpinner;
    private javax.swing.JComboBox<String> calzonesCB;
    private javax.swing.JLabel calzonesPrice;
    private javax.swing.JSpinner calzonesSpinner;
    private javax.swing.JComboBox<String> cashierCB;
    private javax.swing.JTextField cityTXT;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton clearBtn1;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JTable dbTable;
    private javax.swing.JButton deleteCustomerbtn;
    private javax.swing.JButton deleteOrderbtn;
    private javax.swing.JComboBox<String> dessertCB;
    private javax.swing.JLabel dessertsPrice;
    private javax.swing.JSpinner dessertsSpinner;
    private javax.swing.JButton displayReceiptbtn;
    private javax.swing.JComboBox<String> drinksCB;
    private javax.swing.JLabel drinksPrice;
    private javax.swing.JSpinner drinksSpinner;
    private javax.swing.JTextField eaddressTXT;
    private javax.swing.JTextField fnameTXT;
    private javax.swing.JComboBox<String> friesCB;
    private javax.swing.JLabel friesPrice;
    private javax.swing.JSpinner friesSpinner;
    private javax.swing.JLabel gratuityLabel;
    private javax.swing.JTextField idTXT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JTextField lnameTXT;
    private javax.swing.JTable orderTable;
    private javax.swing.JTextField phoneNumberTXT;
    private javax.swing.JComboBox<String> pizzaCB;
    private javax.swing.JLabel pizzaPrice;
    private javax.swing.JSpinner pizzaSpinner;
    private javax.swing.JTextArea receiptArea;
    private javax.swing.JButton receiptBtn;
    private javax.swing.JComboBox<String> saladsCB;
    private javax.swing.JLabel saladsPrice;
    private javax.swing.JSpinner saladsSpinner;
    private javax.swing.JTextField searchPhoneNumberTXT;
    private javax.swing.JComboBox<String> soupCB;
    private javax.swing.JSpinner soupSpinner;
    private javax.swing.JLabel soupsPrice;
    private javax.swing.JTextField stateTXT;
    private javax.swing.JTextField streetAddressTXT;
    private javax.swing.JLabel taxLabel;
    private javax.swing.JButton updateCustomerbtn;
    private javax.swing.JTextField zipcodeTXT;
    // End of variables declaration//GEN-END:variables
}
