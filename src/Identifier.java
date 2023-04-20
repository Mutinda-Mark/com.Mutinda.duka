import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Identifier extends JFrame implements ActionListener {
    private JButton btnBack_1;
    private JButton btnBuyer;
    private JButton btnAttendant;
    private JLabel lblIdentifier,copyright;
    private JPanel Identify;

    public Identifier() {
        setContentPane(Identify);

        btnBack_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Startup sFrame = new Startup();
                sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                sFrame.setSize(450,300);
                sFrame.setVisible(true);
            }
        });
        btnBuyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buyer bFrame = new Buyer();
                bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bFrame.setSize(450,300);
                bFrame.setVisible(true);
            }
        });
        btnAttendant.addActionListener(new ActionListener() {
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
        Identifier iFrame = new Identifier();
        iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iFrame.setSize(450,300);
        iFrame.setVisible(true);
    }
}
