import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Registration extends JFrame implements ActionListener {
    private JButton btnBack_1;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtRegistrationID;
    private JPasswordField pwdPassword;
    private JPasswordField pwdCpassword;
    private JButton btnRegister;
    private JButton btnLogin;
    private JLabel lblRegistration;
    private JPanel Registration;
    private JLabel lblFname;
    private JLabel lblLname;
    private JLabel lblRegistrationID;
    private JLabel lblPassword;
    private JLabel lblCpassword, copyright;

    private Connection conn;
    private Statement stmt;
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_mark_mutuku_150066";
    static final String USER = "root";
    static final String PASSWORD = "";

    public Registration(){
        setContentPane(Registration);

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

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == btnRegister) {

                    try {
                        String Fname = txtFname.getText();
                        String Lname = txtLname.getText();
                        String RegistrationID = txtRegistrationID.getText();
                        String Password = pwdPassword.getText();

                        String CPassword = new String(pwdCpassword.getPassword());

                        if (!Password.equals(CPassword)) {
                            JOptionPane.showMessageDialog(null, "Passwords do not match.");
                            Registration.setVisible(false);
                            Registration rFrame = new Registration();
                            rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            rFrame.setSize(450, 300);
                            rFrame.setVisible(true);


                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Registration successful");
                            Registration.setVisible(false);
                            Login lFrame = new Login();
                            lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            lFrame.setSize(450,300);
                            lFrame.setVisible(true);

                            String query = "INSERT INTO tbl_users (user_Fname, user_Lname, user_RegistrationID, user_Password) " +
                                    "VALUES ('" + Fname + "', '" + Lname + "', '" + RegistrationID + "', '" + Password + "')";
                            stmt.executeUpdate(query);

                            txtFname.setText("");
                            txtLname.setText("");
                            txtRegistrationID.setText("");
                            pwdPassword.setText("");

                        }

                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }

            }
        });
        btnBack_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login lFrame = new Login();
                lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lFrame.setSize(450,300);
                lFrame.setVisible(true);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login lFrame = new Login();
                lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lFrame.setSize(450,300);
                lFrame.setVisible(true);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String args[]){
        Registration rFrame = new Registration();
        rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rFrame.setSize(450, 300);
        rFrame.setVisible(true);

    }
}
