import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class test2 extends JFrame {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    private JLabel nameLabel, emailLabel, quantityLabel, totalCostLabel;
    private JTextField nameTextField, emailTextField, quantityTextField, totalCostTextField;
    private JButton orderButton, backButton;
    private JComboBox<String> searchComboBox;
    private JTable itemTable;
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_mark_mutuku_150066";
    String username = "root";
    String password = "";
    public test2() {
        try {
            // Connect to the MySQL database
            
            Class.forName(DRIVER);
            con = DriverManager.getConnection(url, username, password);
            stmt = con.createStatement();

            // Set up the UI components
           /* nameLabel = new JLabel("Name:");
            emailLabel = new JLabel("Email:");
            quantityLabel = new JLabel("Quantity:");
            totalCostLabel = new JLabel("Total Cost:");
            nameTextField = new JTextField(20);
            emailTextField = new JTextField(20);
            quantityTextField = new JTextField(10);
            totalCostTextField = new JTextField(10);*/
            orderButton = new JButton("Order");
            backButton = new JButton("Back");
            searchComboBox = new JComboBox<>();
            searchComboBox.addItem("All Items");

            // Get the list of items from the database and add them to the combo box
            rs = stmt.executeQuery("SELECT * FROM tbl_products");
            while (rs.next()) {
                String itemName = rs.getString("prod_Name");
                searchComboBox.addItem(itemName);
            }

            // Set up the table to display the items
            rs = stmt.executeQuery("SELECT * FROM tbl_products");
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Description");
            tableModel.addColumn("Price");
            while (rs.next()) {
                int id = rs.getInt("prod_ID");
                String itemName = rs.getString("prod_Name");
                String description = rs.getString("prod_Description");
                double price = rs.getDouble("prod_Price");
                Object[] row = {id, itemName, description, price};
                tableModel.addRow(row);
            }
            itemTable = new JTable(tableModel);

            // Add the components to the content pane
            JPanel inputPanel = new JPanel(new GridLayout(4, 2));
           /* inputPanel.add(nameLabel);
            inputPanel.add(nameTextField);
            inputPanel.add(emailLabel);
            inputPanel.add(emailTextField);
            inputPanel.add(quantityLabel);
            inputPanel.add(quantityTextField);
            inputPanel.add(totalCostLabel);
            inputPanel.add(totalCostTextField);*/
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(orderButton);
            buttonPanel.add(backButton);
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            searchPanel.add(searchComboBox);
            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(new JScrollPane(itemTable), BorderLayout.CENTER);

            Container contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());
           // contentPane.add(inputPanel, BorderLayout.NORTH);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);
            contentPane.add(searchPanel, BorderLayout.WEST);
            contentPane.add(tablePanel, BorderLayout.CENTER);

            // Set up the action listeners
            backButton.addActionListener(new BackButtonListener());
            orderButton.addActionListener(new OrderButtonListener());
            searchComboBox.addActionListener(new SearchComboBoxListener());

            // Set up the window
            setTitle("ItemManagement System");
            pack();
            //setLocationRelativeTo(null);
            setVisible(true);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Buyer bFrame = new Buyer();
            bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            bFrame.setSize(450,300);
            bFrame.setVisible(true);
        }
    }
    private class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Prompt the user for their name, email, and quantity of items to order
                String name = JOptionPane.showInputDialog(null, "Enter your name:");
                String email = JOptionPane.showInputDialog(null, "Enter your email:");
                int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the quantity to order:"));

            // Get the selected row from the table
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an item to order.");
                return;
            }

            // Get the item ID from the selected row
            int itemId = Integer.parseInt(itemTable.getValueAt(selectedRow, 0).toString());

            try {
                // Get the item information from the database
                rs = stmt.executeQuery("SELECT * FROM tbl_products WHERE prod_ID=" + itemId);
                if (rs.next()) {
                    String itemName = rs.getString("prod_Name");
                    double itemPrice = rs.getDouble("prod_Price");
                    double totalCost = itemPrice * quantity;

                    // Prompt the user to confirm the order and show the total cost
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to order " + quantity + " " +
                            itemName + "(s) for a total cost of $" + totalCost + "?", "Confirm Order", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Add the order to the orders table in the database
                        PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_orders (order_Name, order_Email, order_prod_ID, order_Quantity, order_Total) VALUES (?, ?, ?, ?, ?)");
                        stmt.setString(1, name);
                        stmt.setString(2, email);
                        stmt.setInt(3, itemId);
                        stmt.setInt(4, quantity);
                        stmt.setDouble(5, totalCost);
                        stmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Order placed successfully.");
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private class SearchComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String selectedOption = (String) searchComboBox.getSelectedItem();


            if (selectedOption.equals("All Items")) {
                loadItems();
            } else {

                try {
                    rs = stmt.executeQuery("SELECT * FROM tbl_products WHERE prod_Name='" + selectedOption + "'");
                    DefaultTableModel tableModel = (DefaultTableModel) itemTable.getModel();
                    tableModel.setRowCount(0);
                    while (rs.next()) {
                        int id = rs.getInt("prod_ID");
                        String name = rs.getString("prod_Name");
                        String description = rs.getString("prod_Description");
                        double price = rs.getDouble("prod_Price");
                        Object[] row = {id, name, description, price};
                        tableModel.addRow(row);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }

        private void loadItems() {
            test2 ims = new test2();
            ims.setSize(450,300);
            ims.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ims.setVisible(true);
        }
    }

    public static void main(String[] args) {
        test2 ims = new test2();
        ims.setSize(450,300);
        ims.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ims.setVisible(true);
    }
}
