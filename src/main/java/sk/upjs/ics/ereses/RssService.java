package sk.upjs.ics.ereses;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import org.rometools.fetcher.FetcherException;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

public class RssService {
    private List<RssKanal> rssKanaly = new LinkedList<RssKanal>();

    public RssService() {
        try {
            RssKanal sme = new RssKanal("SME", "http://rss.sme.sk/rss/rss.asp?id=frontpage");
            RssKanal root = new RssKanal("Root", "http://www.root.cz/rss/clanky/");
            RssKanal cas = new RssKanal("Nový čas", "http://www.cas.sk/rss/");
            
            this.rssKanaly.add(sme);
            this.rssKanaly.add(root);
            this.rssKanaly.add(cas);
        } catch (MalformedURLException e) {
             throw new RssException("Nemozno nacitat RSS kanal", e);
        }
    }
    
    public List<RssKanal> dajKanaly() {
        return this.rssKanaly;
    }
    
    public List<SyndEntry> getEntries(URL adresa) {
        try {
            SyndFeed feed = new HttpURLFeedFetcher().retrieveFeed(adresa);
            return feed.getEntries();
            
        } catch (IOException e) {
            throw new RssException("Nemožno načítať RSS kanál kvôli I/O chybe. Je sieť dostupná?", e);
        } catch (FeedException e) {
            throw new RssException("Nesprávny formát RSS kanála. Syntaktická chyba?", e);
        } catch (FetcherException e) {
            throw new RssException("Chyba pri spracovani kanala. HTTP komunikácia zlyhala.", e);
        }
    }

    public void pridaj(RssKanal rssKanal) {
        this.rssKanaly.add(rssKanal);
    }
}
