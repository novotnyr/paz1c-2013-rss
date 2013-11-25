package sk.upjs.ics.ereses;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RssKanalForm extends JDialog {
    private JLabel lblNazov = new JLabel("Názov:");
    private JTextField txtNazov = new JTextField("", 25);
    
    private JLabel lblUrl = new JLabel("URL:");
    private JTextField txtUrl = new JTextField("", 25);

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Storno");
    
    private RssService rssService = RssServiceFactory.INSTANCE.getRssService();

    public RssKanalForm(Frame vlastnik) {
        super(vlastnik, true);

        setLayout(new GridLayout(0, 2));
        
        add(lblNazov);
        add(txtNazov);
        add(lblUrl);
        add(txtUrl);
        
        add(btnOk);
        add(btnCancel);
        
        pack();
        
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });
        
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }

        });
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    
    }
    
    public void btnCancelActionPerformed(ActionEvent e) {
        setVisible(false);
    }
  
    public void btnOkActionPerformed(ActionEvent e) {
        try {
            String nazov = txtNazov.getText();
            String url = txtUrl.getText();
            
            RssKanal rssKanal = new RssKanal(nazov, url);
            
            rssService.pridaj(rssKanal);
            
            setVisible(false);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Kanál sa nepodarilo pridať",
                    "Chyba",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
