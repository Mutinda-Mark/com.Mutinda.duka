import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Update extends JFrame implements ActionListener {
    private JPanel Update;
    private JButton btnBack;
    private JLabel lblUpdate;
    private JTable tbl;
    private JButton btndelete;
    private JButton btnupdate;
    private Connection conn;
    private Statement stmt;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_mark_mutuku_150066";
    static final String USER = "root";
    static final String PASSWORD = "";

    public Update() {
        setContentPane(Update);

        try {
            // Establish a database connection
            Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            // Execute a query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_products");

            // Create a table model
            String[] columnNames = {"prod_ID", "prod_Name", "prod_Description","prod_Price"};
            Object[][] data = new Object[100][4];
            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getInt("prod_ID");
                data[row][1] = rs.getString("prod_Name");
                data[row][2] = rs.getString("prod_Description");
                data[row][3] = rs.getDouble("prod_Price");

                row++;
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames);

            // Create a JTable and populate it with the table model
            tbl = new JTable(model);

            // Enable row selection and editing
            tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbl.setCellSelectionEnabled(true);

            // Add the JTable to a scroll pane
            JScrollPane scrollPane = new JScrollPane(tbl);
            add(scrollPane);

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(true);


        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attendant aFrame = new Attendant();
                aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                aFrame.setSize(450,300);
                aFrame.setVisible(true);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String args[]){
        Update uFrame = new Update();
        uFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uFrame.setSize(450,300);
        uFrame.setVisible(true);
    }
}
