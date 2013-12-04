package sk.upjs.ics.ereses;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class Utils {
    public static void openWebpage(URI uri) {
        if(!Desktop.isDesktopSupported()) {
            throw new BrowserException("Plocha nemá podporu v systéme");
        }
        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new BrowserException("Nemožno otvoriť externý webový prehliadač");
        }
        try {
            desktop.browse(uri);
        } catch (IOException ex) {
            throw new BrowserException("Nemožno otvoriť adresu " + uri, ex);
        }
    }    
}

