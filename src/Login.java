import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Login extends JFrame implements ActionListener {
    private JButton btnBack_2;
    private JTextField textField1;
    private JButton btnLogin;
    private JPasswordField pwdPassword_1;
    private JPanel Login;
    private JLabel lblLogin, copyright;
    private JLabel lblRegistrationID;
    private JLabel txtPassword_1;
    private JButton btnRegistration_1;

    private Connection conn;
    private Statement stmt;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_mark_mutuku_150066";
    static final String USER = "root";
    static final String PASSWORD = "";
    private JLabel messageLabel;

    public Login() {
        setContentPane(Login);

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


        btnBack_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Identifier iFrame = new Identifier();
                iFrame.setVisible(true);
                iFrame.setSize(450, 300);
                iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        btnRegistration_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration rFrame = new Registration();
                rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                rFrame.setSize(450, 300);
                rFrame.setVisible(true);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {
                    try {
                        // Get input values
                        String regId = textField1.getText();
                        String password = new String(pwdPassword_1.getPassword());

                        // Query the database for matching registration ID and password
                        String query = "SELECT * FROM tbl_users WHERE user_RegistrationID = '" + regId + "' AND user_Password = '" + password + "'";
                        ResultSet rs = stmt.executeQuery(query);

                        // Check if there is a match
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null,"Login successful");
                            Login.setVisible(false);

                            Attendant aFrame = new Attendant();
                            aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            aFrame.setSize(450,300);
                            aFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null,"Login Failed");
                            Login.setVisible(false);

                            Login lFrame = new Login();
                            lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            lFrame.setSize(450,300);
                            lFrame.setVisible(true);
                        }
                        textField1.setText("");
                        pwdPassword_1.setText("");
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public static void main(String args[]){
        Login lFrame = new Login();
        lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lFrame.setSize(450,300);
        lFrame.setVisible(true);
    }
}
