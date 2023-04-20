import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attendant extends JFrame implements ActionListener {
    private JPanel Attendant;
    private JButton btnback;
    private JButton btnUpdate;
    private JButton btnUpload;
    private JButton btnManage;
    private JLabel lblUpdate,copyright;
    private JLabel lblUpload;
    private JLabel lblManage;
    private JLabel lblAttendantpg;

    public Attendant() {
        setContentPane(Attendant);

        btnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login lFrame = new Login();
                lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lFrame.setSize(450,300);
                lFrame.setVisible(true);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new test1();
            }
        });
        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Upload uFrame = new Upload();
                uFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                uFrame.setSize(450, 300);
                uFrame.setVisible(true);
            }
        });
        btnManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new test3();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String args[]){
        Attendant aFrame = new Attendant();
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setSize(450,300);
        aFrame.setVisible(true);
    }
}
