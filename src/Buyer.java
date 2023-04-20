import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Buyer extends JFrame implements ActionListener{
    private JPanel Buyer;
    private JButton btnback_1;
    private JButton btnAttendant;
    private JButton btnOurproducts;
    private JLabel lblBpage, copyright;


    public Buyer() {
        setContentPane(Buyer);


        btnback_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Identifier iFrame = new Identifier();
                iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                iFrame.setSize(450,300);
                iFrame.setVisible(true);
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
        btnOurproducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test2 ims = new test2();
                ims.setSize(450,300);
                ims.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ims.setVisible(true);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String args[]){
        Buyer bFrame = new Buyer();
        bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bFrame.setSize(450,300);
        bFrame.setVisible(true);

    }
}
