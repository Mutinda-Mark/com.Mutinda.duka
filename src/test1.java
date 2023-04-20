import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class test1 extends JFrame {

    private JComboBox<String> comboBox;
    private DefaultTableModel tableModel;

    private JTable table;

    public test1() {
        super("Item Table");

        // Create a panel for the combobox and buttons, and add it to the JFrame
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(new JLabel("Search:"));
        comboBox = new JComboBox<>();
        controlPanel.add(comboBox);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new UpdateButtonActionListener());
        controlPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonActionListener());
        controlPanel.add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonActionListener());
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.NORTH);

        // Create a table and add it to the JFrame
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create a table model and set it to the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");
        table.setModel(tableModel);

        // Connect to the database and populate the combobox and table
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_mark_mutuku_150066", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_products");

            // Populate the combobox with the item names
            comboBox.addItem("All");
            while (rs.next()) {
                comboBox.addItem(rs.getString("prod_Name"));
            }

            // Populate the table with all items
            rs = stmt.executeQuery("SELECT * FROM tbl_products");
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("prod_ID"));
                row.add(rs.getString("prod_Name"));
                row.add(rs.getString("prod_Description"));
                row.add(rs.getString("prod_Price"));
                tableModel.addRow(row);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Add a listener to the combobox to filter the table
        comboBox.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            TableRowSorter<DefaultTableModel> sorter =
                    new TableRowSorter<>(tableModel);
            table.setRowSorter(sorter);
            if (selectedItem.equals("All")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(selectedItem));
            }
        });

        // Set JFrame properties
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setVisible(true);
    }

    private class UpdateButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/db_mark_mutuku_150066", "root", "");

                // Update each row in the table model in the database
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String id = (String) tableModel.getValueAt(i, 0);
                    String name = (String) tableModel.getValueAt(i, 1);
                    String description = (String) tableModel.getValueAt(i, 2);
                    String price = (String) tableModel.getValueAt(i, 3);

                    PreparedStatement stmt = con.prepareStatement(
                            "UPDATE tbl_products SET prod_Name = ?, prod_Price = ?,prod_Description = ? WHERE prod_ID = ?");
                    stmt.setString(1, name);
                    stmt.setString(2, price);
                    stmt.setString(3, description);
                    stmt.setString(4, id);
                    stmt.executeUpdate();
                }

                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void deleteItems() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_mark_mutuku_150066", "root", "");

            for (int rowIndex : table.getSelectedRows()) {
                String id = (String) table.getValueAt(rowIndex, 0);

                String query = "DELETE FROM tbl_products WHERE prod_ID = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, id);
                stmt.executeUpdate();

                tableModel.removeRow(rowIndex);
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void goback(){
        Attendant aFrame = new Attendant();
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setSize(450,300);
        aFrame.setVisible(true);
    }

    private class DeleteButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            deleteItems();
        }
    }
    private class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goback();
        }
    }


    public static void main(String[] args) {
        new test1();
    }
}





