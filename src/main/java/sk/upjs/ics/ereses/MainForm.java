package sk.upjs.ics.ereses;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.sun.syndication.feed.synd.SyndEntry;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainForm extends JFrame {

    private RssService rssService = new RssService();

    private JList lstPolozky;
    private JSplitPane oddelovac;
    private JTextArea txtDescription;
    private final JScrollPane lstPolozkyScrollPane;
    private final JScrollPane txtDescriptionScrollPane;

    public MainForm() throws MalformedURLException {

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

    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        new MainForm().setVisible(true);
    }
}
