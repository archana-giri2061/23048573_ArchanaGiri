/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.foodie.view;
// Import necessary classes and packages
import com.Foodie.Controllers.algorithms.InsertSort;
import com.Foodie.Controllers.algorithms.MergeSort;
import com.Foodie.Controllers.algorithms.SelectionSort;
import com.Foodie.Controllers.algorithms.BinarySearch;
import com.Foodie.Controllers.calculateBills;
import com.Foodie.model.food;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;
/**
 *  Main class for the Food Order application.
 * Handles the GUI and core functionality for food ordering, billing, and navigation.
 * 
 * @author 23028573_ArchanaGiri
 */
public class foodOrder extends javax.swing.JFrame {
    // Class variables declaration
    private CardLayout cardLayout; //For switching between different screens
    private LinkedList<food> billList;  // List to store food items added to the bill
    private DefaultTableModel billTableModel;  // Model for managing table data
    private calculateBills controller;  // Controller for bill calculations
    private double total = 0.0;  // Total amount for the bill
    private int x = 0; // Counter for any additional functionality
    // Sorting and searching algorithms
    private final SelectionSort selectionSort;
    private final InsertSort InsertSort;
    private final MergeSort MergeSort;
    private final BinarySearch BinarySearch;
    /**
     * Constructor for the foodOrder class. Initializes components and data.
     */
    public foodOrder() {
     /**
      * Initialize GUI components
      * Initialize the bill list
      * set the current time on the interface
      * load initial food data
      * Start the loading progress bar
      */
        initComponents();
        billList = new LinkedList<>();
        setTime();
        AddLayout();
        AddData();
        startProgress();
        // Initialize sorting and searching algorithms
        selectionSort = new SelectionSort();
        InsertSort = new InsertSort();
        MergeSort = new MergeSort();
        BinarySearch = new BinarySearch();
    }
    /**
     * Returns the current list of food items in the bill.
     * @return LinkedList of food items
     */
    public LinkedList<food> getBillList() {
        return billList;
    }
    /**
     * Validates if the quantity of an item is greater than zero.
     * @param qty Quantity of the item
     * @return true if valid, false otherwise
     */
    public boolean qtyIsZero(int qty) {
        if (qty == 0) {
            JOptionPane.showMessageDialog(null, "Item Quantity is not added", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
     /**
     * Initializes the card layout for screen navigation.
     */
    private void AddLayout() {
        cardLayout = new java.awt.CardLayout();
        getContentPane().setLayout(cardLayout);
        
        // Add different screens to the layout
        getContentPane().add(loadingPage, "loadingScreen");
        getContentPane().add(login, "LoginScreen");
        getContentPane().add(dashboard, "MainScreen");

        loadScreen("loadingScreen"); // Display the loading screen initially
    }
     /**
     * Loads initial food data into the application.
     */
    private void AddData() {
        billList = new LinkedList<>();// Initialize the bill list
        // Add sample food items to the list
        billList.add(new food("Pizza", 850.0, 0, "Tomato, Onion, Cheese", 250, false, "Extra Cheese", "Medium"));
        billList.add(new food("burger", 500.0, 0, "Chicken, Onion, Tomato, Cheese", 300, false, "Extra Patty", "Large"));
        billList.add(new food("Spaghetti", 750.0, 0, "Noodles, White cheese", 400, true, "Extra Sauce", "Small"));
        billList.add(new food("Mo:Mo", 250.0, 0, "cabbagge, Onion, Chicken", 200, false, "Extra Chilli Sauce", "Small"));
        billList.add(new food("Fried Rice", 400.0, 0, "Tomato, Onion, Cheese", 350, true, "Add Egg", "Large"));
        
        loadListToTable(billList);// Add sample food items to the list
    }
     /**
     * Loads a list of food items into the table.
     * @param billList List of food items
     */
    private void loadListToTable(List<food> billList){
        DefaultTableModel model = (DefaultTableModel) billTable.getModel();
        model.setRowCount(0);// Clear existing rows
        // Add each food item to the table
        billList.forEach(food -> model.addRow(new Object[]{
            food.getName(),
            food.getPrice(),
            food.getQuantity(),
            food.getIngredients(),
            food.getCalorie(),
            food.getIsVegetrain(),
            food.getCustomAdd(),
            food.getPortionSize()
        }));
    }
        /**
     * Adds a new food item to the bill and updates the table.
     * @param bill Food item to add
     */
    private void billing(food bill) {
        billList.add(bill);// Add the item to the bill list
        DefaultTableModel model = (DefaultTableModel) billTable.getModel();
        // Add the item to the table
        model.addRow(new Object[]{bill.getName(),
            bill.getPrice(),
            bill.getQuantity(),
            bill.getIngredients(),
            bill.getCalorie(),
            bill.getIsVegetrain(),
            bill.getCustomAdd(),
            bill.getPortionSize()
        });
    }
     /**
     * Starts the loading progress bar and transitions to the login screen.
     */
    private void startProgress() {
        javax.swing.SwingWorker<Void, Integer> worker = new javax.swing.SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(30);
                    publish(i);  // Publish progress value
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Update progress bar value
                int progress = chunks.get(chunks.size() - 1);
                loading.setValue(progress);  // Update the loading progress bar
            }

            @Override
            protected void done() {
                loadScreen("LoginScreen"); // Switch to login screen or other actions after progress is complete
            }
        };
        worker.execute();  // Start the worker thread
    }
    /**
     * Resets all input fields and selections to their default state.
     */
    public void reset() {
        // Reset spinners to 0
        jSpinner1.setValue(0);
        jSpinner9.setValue(0);
        jSpinner3.setValue(0);
        jSpinner4.setValue(0);
        jSpinner5.setValue(0);
        jSpinner6.setValue(0);
        jSpinner7.setValue(0);
        jSpinner8.setValue(0);
        
        // Reset text fields to default values
        subTotalTextField.setText("0.0");
        itemTextField.setText("0.0");
        totalTextField.setText("0.0");
        
        // Uncheck all checkboxes
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
        jCheckBox5.setSelected(false);
        jCheckBox6.setSelected(false);
        jCheckBox7.setSelected(false);
        jCheckBox8.setSelected(false);
        
        // Clear the date field
        billDate.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadingPage = new javax.swing.JPanel();
        loadingPageImage1 = new javax.swing.JLabel();
        loading = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        loginDetails = new javax.swing.JPanel();
        loginText = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel53 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        cancleButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        dashboard = new javax.swing.JPanel();
        logopanel = new javax.swing.JPanel();
        dateTime = new javax.swing.JLabel();
        dateTime1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        menuField = new javax.swing.JPanel();
        food2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jSpinner9 = new javax.swing.JSpinner();
        food3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        food4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        food5 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        food1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        food6 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        food8 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jSpinner8 = new javax.swing.JSpinner();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        food7 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jSpinner7 = new javax.swing.JSpinner();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        menuText = new javax.swing.JLabel();
        footerPanel = new javax.swing.JPanel();
        billButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        FinalBill = new javax.swing.JPanel();
        itemTextField = new javax.swing.JTextField();
        totalTextField = new javax.swing.JTextField();
        Items = new javax.swing.JLabel();
        tax = new javax.swing.JLabel();
        SubTotal = new javax.swing.JLabel();
        totalPrice1 = new javax.swing.JLabel();
        sorting1 = new javax.swing.JButton();
        sorting2 = new javax.swing.JButton();
        sorting3 = new javax.swing.JButton();
        subTotalTextField = new javax.swing.JTextField();
        taxTextField = new javax.swing.JTextField();
        billDetails = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        billTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        billDate = new javax.swing.JTextArea();
        SearchTextField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();

        loadingPage.setBackground(new java.awt.Color(255, 255, 255));
        loadingPage.setMaximumSize(new java.awt.Dimension(1920, 1080));
        loadingPage.setMinimumSize(new java.awt.Dimension(1920, 1080));
        loadingPage.setPreferredSize(new java.awt.Dimension(1920, 1080));
        loadingPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loadingPageImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/logo.png"))); // NOI18N
        loadingPageImage1.setText("jLabel3");
        loadingPage.add(loadingPageImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 420, 360));
        loadingPage.add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 820, 1150, 20));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Welcome to FoodieGo!");
        loadingPage.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 560, 80));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 153, 0));
        jLabel19.setText("Hungry? ");
        loadingPage.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 200, 40));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 153, 0));
        jLabel26.setText("Tap to treat yourself today!");
        loadingPage.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 620, -1, -1));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 153, 0));
        jLabel33.setText(" We’ve got meals for every mood and every crew!");
        loadingPage.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 510, -1, -1));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 153, 0));
        jLabel41.setText("Quick, easy, and oh-so-delicious");
        loadingPage.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 570, -1, -1));

        login.setBackground(new java.awt.Color(228, 176, 32));
        login.setMaximumSize(new java.awt.Dimension(1920, 1080));
        login.setMinimumSize(new java.awt.Dimension(1920, 1080));
        login.setPreferredSize(new java.awt.Dimension(1920, 1080));

        loginDetails.setBackground(new java.awt.Color(62, 126, 166));
        loginDetails.setMaximumSize(new java.awt.Dimension(800, 700));
        loginDetails.setMinimumSize(new java.awt.Dimension(800, 700));
        loginDetails.setPreferredSize(new java.awt.Dimension(800, 700));

        loginText.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        loginText.setForeground(new java.awt.Color(255, 255, 255));
        loginText.setText("Login Account");

        userField.setToolTipText("Username");
        userField.setMaximumSize(new java.awt.Dimension(64, 22));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Username");

        passwordField.setMaximumSize(new java.awt.Dimension(64, 22));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Password");

        loginButton.setBackground(new java.awt.Color(0, 102, 102));
        loginButton.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        cancleButton.setBackground(new java.awt.Color(0, 102, 102));
        cancleButton.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        cancleButton.setForeground(new java.awt.Color(255, 255, 255));
        cancleButton.setText("Cancel");
        cancleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginDetailsLayout = new javax.swing.GroupLayout(loginDetails);
        loginDetails.setLayout(loginDetailsLayout);
        loginDetailsLayout.setHorizontalGroup(
            loginDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginDetailsLayout.createSequentialGroup()
                .addContainerGap(221, Short.MAX_VALUE)
                .addGroup(loginDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginText, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addGroup(loginDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cancleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(316, 316, 316))
        );
        loginDetailsLayout.setVerticalGroup(
            loginDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginDetailsLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(loginText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(cancleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/signIn.png"))); // NOI18N

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11))
            .addGroup(loginLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(loginDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1080));
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dashboard.setBackground(new java.awt.Color(250, 250, 250));
        dashboard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 2));
        dashboard.setMaximumSize(new java.awt.Dimension(1920, 1080));
        dashboard.setMinimumSize(new java.awt.Dimension(1920, 1080));
        dashboard.setPreferredSize(new java.awt.Dimension(1920, 1080));
        dashboard.setVerifyInputWhenFocusTarget(false);
        dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logopanel.setBackground(new java.awt.Color(228, 176, 32));
        logopanel.setMaximumSize(new java.awt.Dimension(1910, 85));
        logopanel.setMinimumSize(new java.awt.Dimension(1910, 85));
        logopanel.setPreferredSize(new java.awt.Dimension(1910, 85));

        dateTime.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        dateTime1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/logo3.png"))); // NOI18N

        javax.swing.GroupLayout logopanelLayout = new javax.swing.GroupLayout(logopanel);
        logopanel.setLayout(logopanelLayout);
        logopanelLayout.setHorizontalGroup(
            logopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1276, Short.MAX_VALUE)
                .addComponent(dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(dateTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        logopanelLayout.setVerticalGroup(
            logopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logopanelLayout.createSequentialGroup()
                .addGroup(logopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logopanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(logopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(logopanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dashboard.add(logopanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 2, 1910, 100));

        menu.setBackground(new java.awt.Color(228, 176, 32));
        menu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 2, true));
        menu.setMaximumSize(new java.awt.Dimension(970, 750));
        menu.setMinimumSize(new java.awt.Dimension(970, 750));
        menu.setPreferredSize(new java.awt.Dimension(970, 750));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuField.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        menu.add(menuField, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 56, -1, -1));

        food2.setBackground(new java.awt.Color(62, 126, 166));
        food2.setMaximumSize(new java.awt.Dimension(218, 328));
        food2.setMinimumSize(new java.awt.Dimension(218, 328));
        food2.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Name:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Price:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Quantity:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Purchase:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(242, 242, 242));
        jLabel24.setText("Pizza");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(242, 242, 242));
        jLabel25.setText("Rs 850.0");

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(242, 242, 242));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/pizza.png"))); // NOI18N

        jSpinner9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner9.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        javax.swing.GroupLayout food2Layout = new javax.swing.GroupLayout(food2);
        food2.setLayout(food2Layout);
        food2Layout.setHorizontalGroup(
            food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(food2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food2Layout.setVerticalGroup(
            food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addGap(21, 21, 21))
        );

        menu.add(food2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        food3.setBackground(new java.awt.Color(62, 126, 166));
        food3.setMaximumSize(new java.awt.Dimension(218, 328));
        food3.setMinimumSize(new java.awt.Dimension(218, 328));
        food3.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(242, 242, 242));
        jLabel27.setText("Name:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(242, 242, 242));
        jLabel28.setText("Price:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(242, 242, 242));
        jLabel29.setText("Quantity:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(242, 242, 242));
        jLabel30.setText("Purchase:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(242, 242, 242));
        jLabel31.setText("Mo:Mo");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(242, 242, 242));
        jLabel32.setText("Rs 250.0");

        jSpinner3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/momo.png"))); // NOI18N

        javax.swing.GroupLayout food3Layout = new javax.swing.GroupLayout(food3);
        food3.setLayout(food3Layout);
        food3Layout.setHorizontalGroup(
            food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGap(40, 40, 40)
                        .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox3)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(49, Short.MAX_VALUE))
                    .addGroup(food3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        food3Layout.setVerticalGroup(
            food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        menu.add(food3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, -1));

        food4.setBackground(new java.awt.Color(62, 126, 166));
        food4.setMaximumSize(new java.awt.Dimension(218, 328));
        food4.setMinimumSize(new java.awt.Dimension(218, 328));
        food4.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(242, 242, 242));
        jLabel34.setText("Name:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(242, 242, 242));
        jLabel35.setText("Price:");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(242, 242, 242));
        jLabel36.setText("Quantity:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(242, 242, 242));
        jLabel37.setText("Purchase:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(242, 242, 242));
        jLabel38.setText("Pasta");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(242, 242, 242));
        jLabel39.setText("Rs 900.0");

        jSpinner4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox4.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/Pasta.png"))); // NOI18N

        javax.swing.GroupLayout food4Layout = new javax.swing.GroupLayout(food4);
        food4.setLayout(food4Layout);
        food4Layout.setHorizontalGroup(
            food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food4Layout.createSequentialGroup()
                .addGroup(food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(40, 40, 40)
                        .addGroup(food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4)
                            .addComponent(jLabel39)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(food4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food4Layout.setVerticalGroup(
            food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(food4Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(food4Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        menu.add(food4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, 327));

        food5.setBackground(new java.awt.Color(62, 126, 166));
        food5.setMaximumSize(new java.awt.Dimension(218, 328));
        food5.setMinimumSize(new java.awt.Dimension(218, 328));
        food5.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Name:");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Price:");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(242, 242, 242));
        jLabel43.setText("Quantity:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(242, 242, 242));
        jLabel44.setText("Purchase:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Spaghetti");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Rs 750.0");

        jSpinner5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/Spaghetti.png"))); // NOI18N

        javax.swing.GroupLayout food5Layout = new javax.swing.GroupLayout(food5);
        food5.setLayout(food5Layout);
        food5Layout.setHorizontalGroup(
            food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jCheckBox5)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
            .addGroup(food5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food5Layout.setVerticalGroup(
            food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(14, 14, 14)
                .addGroup(food5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox5))
                .addGap(18, 18, 18))
        );

        menu.add(food5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        food1.setBackground(new java.awt.Color(62, 126, 166));
        food1.setMaximumSize(new java.awt.Dimension(218, 328));
        food1.setMinimumSize(new java.awt.Dimension(218, 328));
        food1.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(242, 242, 242));
        jLabel13.setText("Name:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(242, 242, 242));
        jLabel14.setText("Price:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(242, 242, 242));
        jLabel15.setText("Quantity:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(242, 242, 242));
        jLabel16.setText("Purchase:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(242, 242, 242));
        jLabel17.setText("Burger");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(242, 242, 242));
        jLabel18.setText("Rs 500.0");

        jSpinner1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/burger.png"))); // NOI18N

        javax.swing.GroupLayout food1Layout = new javax.swing.GroupLayout(food1);
        food1.setLayout(food1Layout);
        food1Layout.setHorizontalGroup(
            food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(food1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(27, 27, 27)))
                        .addGap(36, 36, 36))))
            .addGroup(food1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food1Layout.setVerticalGroup(
            food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(16, 16, 16))
        );

        menu.add(food1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        food6.setBackground(new java.awt.Color(62, 126, 166));
        food6.setMaximumSize(new java.awt.Dimension(218, 328));
        food6.setMinimumSize(new java.awt.Dimension(218, 328));
        food6.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(242, 242, 242));
        jLabel48.setText("Name:");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(242, 242, 242));
        jLabel49.setText("Price:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(242, 242, 242));
        jLabel50.setText("Quantity:");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(242, 242, 242));
        jLabel51.setText("Purchase:");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(242, 242, 242));
        jLabel70.setText("Sandwich");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(242, 242, 242));
        jLabel52.setText("Rs 350.0");

        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        jSpinner6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jLabel9.setForeground(new java.awt.Color(242, 242, 242));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/Sandwich.png"))); // NOI18N

        javax.swing.GroupLayout food6Layout = new javax.swing.GroupLayout(food6);
        food6.setLayout(food6Layout);
        food6Layout.setHorizontalGroup(
            food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food6Layout.createSequentialGroup()
                .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addGap(47, 47, 47)
                        .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox6)
                            .addComponent(jLabel52)
                            .addComponent(jLabel70)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(food6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        food6Layout.setVerticalGroup(
            food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox6))
                .addGap(16, 16, 16))
        );

        menu.add(food6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, -1, -1));

        food8.setBackground(new java.awt.Color(62, 126, 166));
        food8.setMaximumSize(new java.awt.Dimension(218, 328));
        food8.setMinimumSize(new java.awt.Dimension(218, 328));
        food8.setPreferredSize(new java.awt.Dimension(218, 328));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(242, 242, 242));
        jLabel62.setText("Name:");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(242, 242, 242));
        jLabel63.setText("Price:");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(242, 242, 242));
        jLabel64.setText("Quantity:");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(242, 242, 242));
        jLabel66.setText("Fried Chicken");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(242, 242, 242));
        jLabel65.setText("Purchase:");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(242, 242, 242));
        jLabel67.setText("Rs 1200.0");

        jSpinner8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox8ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/FriedChicken.png"))); // NOI18N

        javax.swing.GroupLayout food8Layout = new javax.swing.GroupLayout(food8);
        food8.setLayout(food8Layout);
        food8Layout.setHorizontalGroup(
            food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food8Layout.createSequentialGroup()
                .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food8Layout.createSequentialGroup()
                        .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(food8Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67)
                            .addComponent(jLabel66)
                            .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox8)))
                    .addGroup(food8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food8Layout.setVerticalGroup(
            food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(food8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox8))
                .addGap(18, 18, 18))
        );

        menu.add(food8, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 470, -1, -1));

        food7.setBackground(new java.awt.Color(62, 126, 166));
        food7.setMaximumSize(new java.awt.Dimension(218, 328));
        food7.setMinimumSize(new java.awt.Dimension(218, 328));
        food7.setPreferredSize(new java.awt.Dimension(218, 328));
        food7.setRequestFocusEnabled(false);

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(242, 242, 242));
        jLabel55.setText("Name:");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(242, 242, 242));
        jLabel56.setText("Price:");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(242, 242, 242));
        jLabel57.setText("Quantity:");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(242, 242, 242));
        jLabel58.setText("Purchase:");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(242, 242, 242));
        jLabel59.setText("Fried Rice");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(242, 242, 242));
        jLabel60.setText("Rs 400.0");

        jSpinner7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Foodie/resources/FriedRice.png"))); // NOI18N

        javax.swing.GroupLayout food7Layout = new javax.swing.GroupLayout(food7);
        food7.setLayout(food7Layout);
        food7Layout.setHorizontalGroup(
            food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food7Layout.createSequentialGroup()
                .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(food7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58))
                        .addGap(30, 30, 30)
                        .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox7)
                            .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel60)
                                .addComponent(jLabel59))))
                    .addGroup(food7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        food7Layout.setVerticalGroup(
            food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(food7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(food7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel60))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(food7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel57)
                            .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7)))
                .addContainerGap())
        );

        menu.add(food7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, -1, -1));

        menuText.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        menuText.setForeground(new java.awt.Color(255, 255, 255));
        menuText.setText("Menu Items");
        menu.add(menuText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        dashboard.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 103, 1080, 830));

        footerPanel.setBackground(new java.awt.Color(228, 176, 32));
        footerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 2));
        footerPanel.setMaximumSize(new java.awt.Dimension(887, 50));
        footerPanel.setMinimumSize(new java.awt.Dimension(887, 50));
        footerPanel.setPreferredSize(new java.awt.Dimension(887, 50));
        footerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        billButton.setBackground(new java.awt.Color(0, 102, 102));
        billButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billButton.setForeground(new java.awt.Color(255, 255, 255));
        billButton.setText("Bill");
        billButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billButtonActionPerformed(evt);
            }
        });
        footerPanel.add(billButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 147, 42));

        resetButton.setBackground(new java.awt.Color(0, 102, 102));
        resetButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        resetButton.setForeground(new java.awt.Color(255, 255, 255));
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        footerPanel.add(resetButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 147, 42));

        exitButton.setBackground(new java.awt.Color(0, 102, 102));
        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        footerPanel.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 147, 42));

        dashboard.add(footerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 930, 1080, 150));

        FinalBill.setBackground(new java.awt.Color(62, 126, 166));
        FinalBill.setMaximumSize(new java.awt.Dimension(760, 244));
        FinalBill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemTextField.setEditable(false);
        itemTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        itemTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemTextField.setText("0.0");
        FinalBill.add(itemTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 165, 31));

        totalTextField.setEditable(false);
        totalTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        totalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalTextField.setText("0.0");
        FinalBill.add(totalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 165, 31));

        Items.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Items.setForeground(new java.awt.Color(242, 242, 242));
        Items.setText("Items:");
        FinalBill.add(Items, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 93, -1));

        tax.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tax.setForeground(new java.awt.Color(242, 242, 242));
        tax.setText("Tax:");
        FinalBill.add(tax, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 93, 30));

        SubTotal.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SubTotal.setForeground(new java.awt.Color(242, 242, 242));
        SubTotal.setText("SubTotal:");
        FinalBill.add(SubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 93, 30));

        totalPrice1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        totalPrice1.setForeground(new java.awt.Color(242, 242, 242));
        totalPrice1.setText("Total:");
        FinalBill.add(totalPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 93, 30));

        sorting1.setBackground(new java.awt.Color(0, 102, 102));
        sorting1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sorting1.setForeground(new java.awt.Color(242, 242, 242));
        sorting1.setText("Sorting by Price");
        sorting1.setMaximumSize(new java.awt.Dimension(200, 44));
        sorting1.setMinimumSize(new java.awt.Dimension(200, 44));
        sorting1.setPreferredSize(new java.awt.Dimension(200, 44));
        sorting1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorting1ActionPerformed(evt);
            }
        });
        FinalBill.add(sorting1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        sorting2.setBackground(new java.awt.Color(0, 102, 102));
        sorting2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sorting2.setForeground(new java.awt.Color(242, 242, 242));
        sorting2.setText("Sorting By Name");
        sorting2.setMaximumSize(new java.awt.Dimension(200, 44));
        sorting2.setMinimumSize(new java.awt.Dimension(200, 44));
        sorting2.setPreferredSize(new java.awt.Dimension(200, 44));
        sorting2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorting2ActionPerformed(evt);
            }
        });
        FinalBill.add(sorting2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        sorting3.setBackground(new java.awt.Color(0, 102, 102));
        sorting3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        sorting3.setForeground(new java.awt.Color(242, 242, 242));
        sorting3.setText("Sorting By Calorie");
        sorting3.setMaximumSize(new java.awt.Dimension(200, 44));
        sorting3.setMinimumSize(new java.awt.Dimension(200, 44));
        sorting3.setPreferredSize(new java.awt.Dimension(200, 44));
        sorting3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sorting3ActionPerformed(evt);
            }
        });
        FinalBill.add(sorting3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, -1, -1));

        subTotalTextField.setEditable(false);
        subTotalTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        subTotalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subTotalTextField.setText("0.0");
        FinalBill.add(subTotalTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 165, 31));

        taxTextField.setEditable(false);
        taxTextField.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        taxTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        taxTextField.setText("0.0");
        FinalBill.add(taxTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 165, 31));

        dashboard.add(FinalBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 760, 840, 320));

        billDetails.setBackground(new java.awt.Color(62, 126, 166));
        billDetails.setMaximumSize(new java.awt.Dimension(840, 650));
        billDetails.setMinimumSize(new java.awt.Dimension(840, 650));

        billTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Quantity", "Ingredients", "Calorie", "IsVegetrain", "CustomAdd", "portionSize"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(billTable);

        billDate.setColumns(20);
        billDate.setRows(5);
        jScrollPane1.setViewportView(billDate);

        SearchButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billDetailsLayout = new javax.swing.GroupLayout(billDetails);
        billDetails.setLayout(billDetailsLayout);
        billDetailsLayout.setHorizontalGroup(
            billDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDetailsLayout.createSequentialGroup()
                .addContainerGap(439, Short.MAX_VALUE)
                .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addComponent(jScrollPane1)
            .addGroup(billDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(billDetailsLayout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        billDetailsLayout.setVerticalGroup(
            billDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDetailsLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(billDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(482, Short.MAX_VALUE))
            .addGroup(billDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, billDetailsLayout.createSequentialGroup()
                    .addContainerGap(170, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        dashboard.add(billDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 110, 840, 650));

        getContentPane().add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1930, 1040));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Action listener for the Reset button.
     * Resets the form to its default state.
     * 
     * @param evt the event generated when the button is clicked
     */
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reset(); // Call the reset method to reset the form
    }//GEN-LAST:event_resetButtonActionPerformed
    /**
     * Action listener for the Exit button.
     * Exits the application.
     *
     * @param evt the event generated when the button is clicked
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);// Terminate the application   
    }//GEN-LAST:event_exitButtonActionPerformed
    /**
     * Action listener for a checkbox (jCheckBox1).
     * Validates the quantity selected using a spinner.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int qty = Integer.parseInt(jSpinner1.getValue().toString());
        if (qtyIsZero(qty)) {
            foodie();// Perform foodie operation if quantity is zero
        } else {
            jCheckBox1.setSelected(false);// Deselect the checkbox if quantity is invalid
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    /**
     * Action listener for checkbox (jCheckBox5).
     * Similar functionality to jCheckBox1.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        int qty = Integer.parseInt(jSpinner5.getValue().toString());
        if (!qtyIsZero(qty)) {
            foodie();
        } else {
            System.out.println("Quantity is valid: " + qty);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed
     /**
     * Action listener for checkbox (jCheckBox5).
     * Similar functionality to jCheckBox1.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        int qty = Integer.parseInt(jSpinner5.getValue().toString());
        if (!qtyIsZero(qty)) {
            foodie();
        } else {
            System.out.println("Quantity is valid: " + qty);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    /**
     * Action listener for checkbox (jCheckBox5).
     * Similar functionality to jCheckBox1.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        int qty = Integer.parseInt(jSpinner5.getValue().toString());
        if (!qtyIsZero(qty)) {
            foodie();
        } else {
            System.out.println("Quantity is valid: " + qty);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed
   /**
     * Action listener for checkbox (jCheckBox5).
     * Similar functionality to jCheckBox1.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        int qty = Integer.parseInt(jSpinner5.getValue().toString());
        if (!qtyIsZero(qty)) {
            foodie();
        } else {
            System.out.println("Quantity is valid: " + qty);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed
    /**
     * Action listener for the jCheckBox6.
     * Validates the selected item and adds food details to the bill table.
     *
     * @param evt the event generated when the checkbox is clicked
     */
    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        if (jCheckBox8.isSelected()) { // Check if another checkbox is selected
            foodie(); 
            try {
                // Get the quantity from the spinner
                int quantity = Integer.parseInt(jSpinner8.getValue().toString());

                // Check if quantity is valid
                if (quantity > 0) {
                    // Example food details
                    String name = "Sandwich";
                    double price = 350.0;
                    String ingredients = "Chicken, Bread Crumps, Spices";
                    int calories = 800;
                    boolean isVegetarian = false;
                    String customAdd = "Extra Spicy";
                    String portionSize = "Large";

                    // Add details to the table
                    DefaultTableModel model = (DefaultTableModel) billTable.getModel();
                    model.addRow(new Object[]{name, price, quantity, ingredients, calories, isVegetarian, customAdd, portionSize});
                } else {
                    // Show a message box if quantity is zero
                    JOptionPane.showMessageDialog(null, "Please select a valid quantity.", "Invalid Quantity", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Show a message box if quantity is invalid
                JOptionPane.showMessageDialog(null, "Invalid quantity entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        if (jCheckBox7.isSelected()) {
            foodie();
            try {
                // Get the quantity from the spinner
                int quantity = Integer.parseInt(jSpinner7.getValue().toString());

                // Check if quantity is valid
                if (quantity > 0) {
                    // Example food details
                    String name = "Fried rice";
                    double price = 400.0;
                    String ingredients = "Chicken, Vegetables, Sause";
                    int calories = 600;
                    boolean isVegetarian = false;
                    String customAdd = "Less oil";
                    String portionSize = "medium";

                    // Add details to the table
                    DefaultTableModel model = (DefaultTableModel) billTable.getModel();
                    model.addRow(new Object[]{name, price, quantity, ingredients, calories, isVegetarian, customAdd, portionSize});
                } else {
                    // Show a message box if quantity is zero
                    JOptionPane.showMessageDialog(null, "Please select a valid quantity.", "Invalid Quantity", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Show a message box if quantity is invalid
                JOptionPane.showMessageDialog(null, "Invalid quantity entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void jCheckBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox8ActionPerformed
        if (jCheckBox8.isSelected()) {
            foodie();
            try {
                // Get the quantity from the spinner
                int quantity = Integer.parseInt(jSpinner8.getValue().toString());

                // Check if quantity is valid
                if (quantity > 0) {
                    // Example food details
                    String name = "Fried Chicken";
                    double price = 1200.0;
                    String ingredients = "Chicken, Bread Crumps, Spices";
                    int calories = 800;
                    boolean isVegetarian = false;
                    String customAdd = "Extra Spicy";
                    String portionSize = "Large";

                    food foodItem = new food(name, price, quantity, ingredients, calories, isVegetarian, customAdd, portionSize);

                    // Initialize calculateBills object
                    calculateBills controller = new calculateBills();

                    // Add details to the table
                    DefaultTableModel model = (DefaultTableModel) billTable.getModel();
                    controller.addFoodToTable(model, foodItem);

                    List<food> foodList = new ArrayList<>();  // Create a list to hold food items
                    foodList.add(foodItem);  // Add the item to the list
                    updateTotals(foodList, controller);
                } else {
                    // Show a message box if quantity is zero
                    JOptionPane.showMessageDialog(null, "Please select a valid quantity.", "Invalid Quantity", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                // Show a message box if quantity is invalid
                JOptionPane.showMessageDialog(null, "Invalid quantity entered. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jCheckBox8ActionPerformed
     /**
     * Updates the totals such as subtotal, tax, and final total based on the food list.
     *
     * @param foodList the list of food items
     * @param controller the calculateBills object used for calculations
     */
    private void updateTotals(List<food> foodList, calculateBills controller) {
        int totalQuantity = controller.calculateTotalQuantity(foodList);
        double subtotal = controller.calculateSubtotal(foodList);
        double tax = controller.calculateTax(subtotal);
        double total = controller.calculateFinalTotal(subtotal, tax);

        // Update the respective fields with the calculated totals
        itemTextField.setText(String.valueOf(totalQuantity));
        subTotalTextField.setText(String.format("%.2f", subtotal));
        taxTextField.setText(String.format("%.2f", tax));
        totalTextField.setText(String.format("%.2f", total));
    }
    /**
     * Action listener for the login button.
     * Validates user credentials and loads the main screen upon successful login.
     *
     * @param evt the event generated when the button is clicked
     */
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (userField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill out username");
        }// TODO add your handling code here:
        else if (passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill out password");
        } else if (userField.getText().contains("admin") && passwordField.getText().contains("admin")) {
            JOptionPane.showMessageDialog(null, "LOgin successfully!!");
            loadScreen("MainScreen");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong username or password!!!!!", "Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginButtonActionPerformed
    /**
     * Action performed when the "Cancel" button is clicked.
     * Disposes of the current frame.
     *
     * @param evt the event triggered by the button click
     */
    private void cancleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonActionPerformed

        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_cancleButtonActionPerformed
    /**
     * Action performed when the "Bill" button is clicked.
     * Currently commented out for future implementation.
     *
     * @param evt the event triggered by the button click
     */
    private void billButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billButtonActionPerformed
//    controller.calculateBills();
    }//GEN-LAST:event_billButtonActionPerformed
     /**
     * Action performed for sorting food items by price using Selection Sort.
     *
     * @param evt the event triggered by the button click
     */
    private void sorting1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorting1ActionPerformed
        List<food> sortedList = selectionSort.sortByPrice(billList, false);// Sort the list by price
        loadListToTable(sortedList);// Load sorted data into the table
        JOptionPane.showMessageDialog(this, "Sorting by Price completed successefully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_sorting1ActionPerformed
      /**
     * Action performed for sorting food items by name using Insertion Sort.
     *
     * @param evt the event triggered by the button click
     */
    private void sorting2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorting2ActionPerformed
        List<food> sortedList = InsertSort.sortByName(billList, false); // Sort the list by name
        loadListToTable(sortedList);// Load sorted data into the table
        JOptionPane.showMessageDialog(this, "Sorting by Name completed successefully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_sorting2ActionPerformed
        /**
     * Action performed for sorting food items by calorie using Merge Sort.
     *
     * @param evt the event triggered by the button click
     */
    private void sorting3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sorting3ActionPerformed
        List<food> sortedList = MergeSort.sortByCalorie(billList, false);// Sort the list by calorie
        loadListToTable(sortedList);// Load sorted data into the table
        JOptionPane.showMessageDialog(this, "Sorting by Calorie completed successefully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_sorting3ActionPerformed
     /**
     * Searches for a food item by its name using Binary Search.
     *
     * @param evt the event triggered by the button click
     */
    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        String SearchKey = SearchTextField.getText().trim();// Get the search key from the text field
        if (SearchKey.isEmpty()){
            // Show a warning if the search key is empty
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a class name to search.", "Input Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(billList == null || billList.isEmpty()){
            // Show an error if the list is empty
            javax.swing.JOptionPane.showMessageDialog(this, "The schedule list is empty.", "Data Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
// Sort the list by name before performing the search
        List<food> sortedList = InsertSort.sortByName(billList, false);
        BinarySearch search = new BinarySearch();
        food searchData = search.searchByName(SearchKey, sortedList,0, sortedList.size() -1);
        if (searchData != null){
            // If item is found, display it
            List<food> updateList = new ArrayList<>();
            updateList.add(searchData);
            loadListToTable(updateList);
        }else{
            // If item is not found, display a warning
            javax.swing.JOptionPane.showMessageDialog(this, "Sorry, food name not found.", "Search Result", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_SearchButtonActionPerformed
    /**
     * Generates a formatted string for the bill header.
     */
    public void foodie() {
        billDate.setText("********************************************************************************************************\n"
                + "Time: " + dateTime1.getText() + "             Date: " + dateTime.getText() + "\n"
                + "***********************************************************************************************************"
                + "");

    }
    /**
     * Continuously updates the time and date displayed in the application.
     * Runs in a separate thread to ensure real-time updates.
     */
    public void setTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Logger.getLogger(foodOrder.class.getName()).log(Level.SEVERE, null, e);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");

                    String time = tf.format(date);
                    String dateString = df.format(date);
                    // Update GUI components on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        dateTime1.setText(time); // Assuming time format is correct here
                        dateTime.setText(dateString);
                    });
                }
            }
        }).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(foodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(foodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(foodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(foodOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        foodOrder food = new foodOrder();
        java.awt.EventQueue.invokeLater(() -> {
            food.setVisible(true);

        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FinalBill;
    private javax.swing.JLabel Items;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField SearchTextField;
    private javax.swing.JLabel SubTotal;
    private javax.swing.JButton billButton;
    private javax.swing.JTextArea billDate;
    private javax.swing.JPanel billDetails;
    private javax.swing.JTable billTable;
    private javax.swing.JButton cancleButton;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel dateTime;
    private javax.swing.JLabel dateTime1;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel food1;
    private javax.swing.JPanel food2;
    private javax.swing.JPanel food3;
    private javax.swing.JPanel food4;
    private javax.swing.JPanel food5;
    private javax.swing.JPanel food6;
    private javax.swing.JPanel food7;
    private javax.swing.JPanel food8;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JTextField itemTextField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JProgressBar loading;
    private javax.swing.JPanel loadingPage;
    private javax.swing.JLabel loadingPageImage1;
    private javax.swing.JPanel login;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginDetails;
    private javax.swing.JLabel loginText;
    private javax.swing.JPanel logopanel;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuField;
    private javax.swing.JLabel menuText;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton sorting1;
    private javax.swing.JButton sorting2;
    private javax.swing.JButton sorting3;
    private javax.swing.JTextField subTotalTextField;
    private javax.swing.JLabel tax;
    private javax.swing.JTextField taxTextField;
    private javax.swing.JLabel totalPrice1;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables

    private void loadScreen(String loadingScreen) {
        cardLayout.show(getContentPane(), loadingScreen);
    }

}
