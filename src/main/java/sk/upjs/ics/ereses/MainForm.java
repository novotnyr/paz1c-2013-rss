package sk.upjs.ics.ereses;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.sun.syndication.feed.synd.SyndEntry;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainForm extends JFrame {

    private RssService rssService = RssServiceFactory.INSTANCE.getRssService();

    private JList lstPolozky;
    private JSplitPane oddelovac;
    private JTextArea txtDescription;
    private final JScrollPane lstPolozkyScrollPane;
    private final JScrollPane txtDescriptionScrollPane;
    
    private JList lstKanaly = new JList();
    
    private JComboBox cmbKanaly = new JComboBox();
    
    private JPanel panKanaly = new JPanel();
    
    private JButton btnPridatKanal = new JButton(" + ");

    public MainForm() throws MalformedURLException {
        lstKanaly.setListData(rssService.dajKanaly().toArray());
        lstKanaly.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                RssKanal vybranyKanal = (RssKanal) lstKanaly.getSelectedValue();
                zmenaVyberuKanala(vybranyKanal);
            }
        });
        lstKanaly.setVisible(false);
        add(lstKanaly, BorderLayout.WEST);
        
        panKanaly.setLayout(new BorderLayout());
        add(panKanaly, BorderLayout.NORTH);
        
        panKanaly.add(cmbKanaly);
        reinicializujCmbKanaly();
        cmbKanaly.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RssKanal vybranyKanal = (RssKanal) cmbKanaly.getSelectedItem();
                zmenaVyberuKanala(vybranyKanal);
            }
        });
        
        btnPridatKanal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPridatKanalActionPerformed(e);
            }
        });
        panKanaly.add(btnPridatKanal, BorderLayout.EAST);
  
        
        lstPolozky = new JList();
        
        

        URL url = new URL("http://rss.sme.sk/rss/rss.asp?id=frontpage");
        List<SyndEntry> entries = rssService.getEntries(url);
        lstPolozky.setListData(entries.toArray());
        lstPolozky.setCellRenderer(new SyndFeedListCellRender());
        lstPolozky.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    return;
                }
                SyndEntry polozka = (SyndEntry) lstPolozky.getSelectedValue();
                txtDescription.setText(polozka.getDescription().getValue());
            }
        });
        lstPolozkyScrollPane = new JScrollPane(lstPolozky);
        
        txtDescription = new JTextArea();
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setPreferredSize(new Dimension(200, 200));
        
        txtDescriptionScrollPane = new JScrollPane(txtDescription);

        
        
        oddelovac = new JSplitPane(JSplitPane.VERTICAL_SPLIT, lstPolozkyScrollPane, txtDescriptionScrollPane);

        add(oddelovac);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
    }

    private void reinicializujCmbKanaly() {
        cmbKanaly.removeAllItems();
        
        List<RssKanal> kanaly = rssService.dajKanaly();
        for (RssKanal rssKanal : kanaly) {
            cmbKanaly.addItem(rssKanal);
        }
    }
    
    private void zmenaVyberuKanala(RssKanal rssKanal) {
        if(rssKanal == null) {
            return;
        }
        
       List<SyndEntry> polozky = rssService.getEntries(rssKanal.getUrl());
       lstPolozky.setListData(polozky.toArray());
    }
    
    public void btnPridatKanalActionPerformed(ActionEvent e) {
        RssKanalForm rssKanalForm = new RssKanalForm(this);
        rssKanalForm.setVisible(true);
        
        reinicializujCmbKanaly();
    }
    
    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        new MainForm().setVisible(true);
    }    
}
