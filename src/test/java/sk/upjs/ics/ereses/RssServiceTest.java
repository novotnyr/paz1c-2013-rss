/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.upjs.ics.ereses;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rn
 */
public class RssServiceTest {
    @Test
    public void testGetEntries() throws MalformedURLException {
        RssService rssService = new RssService();
        URL url = new URL("http://rss.sme.sk/rss/rss.asp?id=frontpage");
        List<SyndEntry> entries = rssService.getEntries(url);
        System.out.println(entries);
        Assert.assertFalse(entries.isEmpty());
    }

    
}
