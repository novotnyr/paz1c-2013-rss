package sk.upjs.ics.ereses;

import java.net.MalformedURLException;
import java.net.URL;

public class RssKanal {
    private String nazov;
    
    private URL url;

    public RssKanal(String nazov, String url) throws MalformedURLException {
        this(nazov, new URL(url));
    }
    
    public RssKanal(String nazov, URL url) {
        this.nazov = nazov;
        this.url = url;
    }

    public String getNazov() {
        return nazov;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return nazov;
    }
    
    
}
