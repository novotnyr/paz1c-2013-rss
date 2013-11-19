package sk.upjs.ics.ereses;

import com.sun.syndication.feed.synd.SyndEntry;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class SyndFeedListCellRender extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        SyndEntry entry = (SyndEntry) value; 
        String title = entry.getTitle();
        return super.getListCellRendererComponent(list, title, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
    }
    
}
