package sk.upjs.ics.ereses;

public enum RssServiceFactory {
    INSTANCE;
    
    private RssService rssService = new RssService();
    
    public RssService getRssService() {
        return rssService;
    }
}
