import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Mutuku Mark Mutinda
ICS-D
150066
28-Feb-2023
 */

public class Startup extends JFrame implements ActionListener {
    private JLabel lblLogo, copyright;
    private JPanel Startup;
    private JLabel lblWelcome;
    private JButton btnProceed;

    public Startup() {
        setContentPane(Startup);
        btnProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Identifier spf = new Identifier();
                spf.setVisible(true);
                spf.setSize(450,300);
                spf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Identifier spf=new Identifier();
        spf.setVisible(true);
        spf.setSize(450,300);
        spf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String args[]){
        Startup sFrame = new Startup();
        sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sFrame.setSize(450,300);
        sFrame.setVisible(true);
    }
}
