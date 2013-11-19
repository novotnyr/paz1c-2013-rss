package sk.upjs.ics.ereses;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.rometools.fetcher.FetcherException;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

public class RssService {
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
}
