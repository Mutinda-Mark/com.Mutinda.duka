import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Upload extends JFrame implements ActionListener {
    private JPanel Upload;
    private JButton btnBack;
    private JTextField txtProductID;
    private JTextField txtProductName;
    private JTextField txtProductDescription;
    private JTextField txtPrice;
    private JLabel lblPrice;
    private JLabel lblProductDescription;
    private JLabel lblProductName;
    private JLabel lblProductID;
    private JButton btnUpload;
    private JLabel lblUpload, copyright;

    private Connection conn;
    private Statement stmt;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_mark_mutuku_150066";
    static final String USER = "root";
    static final String PASSWORD = "";
    public Upload() {

        setContentPane(Upload);

        try {
            Class.forName(DRIVER);
            String url = DATABASE_URL;
            String user = USER;
            String password = PASSWORD;
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnUpload) {
                    String ProdIDs = txtProductID.getText();
                    String ProdName = txtProductName.getText();
                    String ProdDescription = txtProductDescription.getText();
                    String Prices = txtPrice.getText();

                    int ProdID = 0;
                     double Price = 0.0;
                     try {
                        ProdID = Integer.parseInt(ProdIDs);
                         Price = Double.parseDouble(Prices);
                     } catch (NumberFormatException ex) {
                         JOptionPane.showMessageDialog(null, "Invalid input type! \nProduct ID must be an integer and Price must be a decimal.");
                         return;
                     }


                    try {
                        JOptionPane.showMessageDialog(null, "Product Uploaded!");
                        Upload.setVisible(false);
                        Upload uFrame = new Upload();
                        uFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        uFrame.setSize(450, 300);
                        uFrame.setVisible(true);


                            String query = "INSERT INTO tbl_products (prod_ID, prod_Name, prod_Description, Prod_Price) " +
                                    "VALUES ('" + ProdID + "', '" + ProdName + "', '" + ProdDescription + "', '" + Price + "')";
                            stmt.executeUpdate(query);

                            txtProductID.setText("");
                            txtProductName.setText("");
                            txtProductDescription.setText("");
                            txtPrice.setText("");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Upload Failed!");
                        ex.printStackTrace();

                        Upload.setVisible(false);
                        Upload uFrame = new Upload();
                        uFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        uFrame.setSize(450, 300);
                        uFrame.setVisible(true);

                    }
                }

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
        Upload uFrame = new Upload();
        uFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uFrame.setSize(450, 300);
        uFrame.setVisible(true);

    }
}
