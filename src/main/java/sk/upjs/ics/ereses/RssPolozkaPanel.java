package sk.upjs.ics.ereses;

import com.sun.syndication.feed.synd.SyndEntry;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RssPolozkaPanel extends JPanel {
    private JTextArea txtDescription = new JTextArea();
    
    private JLabel lblDate = new JLabel(new Date().toString());
    
    private JLabel lblDateLabel = new JLabel("Dátum:");
    
    private JButton btnMore = new JButton("Čítať viac...");
    
    private JPanel panDates = new JPanel();
    
    private JLabel lblTitle = new JLabel();
    
    private SyndEntry polozka;
    
    public RssPolozkaPanel() {
        setLayout(new BorderLayout());
        
        add(txtDescription, BorderLayout.CENTER);
        add(btnMore, BorderLayout.SOUTH);
        btnMore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                btnMoreActionPerformed(ae);
            }
        });
        
        panDates.setLayout(new BoxLayout(panDates, BoxLayout.X_AXIS));
        panDates.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        lblTitle.setFont(lblTitle.getFont().deriveFont(14.0f));
        
        panDates.add(lblTitle);
        
        panDates.add(Box.createHorizontalGlue());
        panDates.add(Box.createHorizontalStrut(5));
        
        lblDateLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        lblDate.setFont(lblDate.getFont().deriveFont(Font.ITALIC));
        panDates.add(lblDate);
        
        
        add(panDates, BorderLayout.NORTH);
    }

    public void setPolozka(SyndEntry polozka) {
        this.polozka = polozka;
        
        txtDescription.setText(polozka.getDescription().getValue());
        
        Date datum = polozka.getPublishedDate();        
        lblDate.setText(format(datum));
        
        lblTitle.setText(polozka.getTitle());
    }

    private String format(Date datum) {
        return SimpleDateFormat.getDateTimeInstance().format(datum);
    }
    
    public void btnMoreActionPerformed(ActionEvent ae) {
        try {
            URI uri = new URI(polozka.getLink());
            Utils.openWebpage(uri);
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(this, 
                    "Chyba", 
                    "Nemozem otvorit prehliadac s odkazom", 
                    ERROR);
        } catch (BrowserException e) {
            JOptionPane.showMessageDialog(this, 
                    "Chyba", 
                    "Nemozem otvorit prehliadac s odkazom", 
                    ERROR);            
        }
    }
}
