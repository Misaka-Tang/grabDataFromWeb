import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Web {
    private Document document;
    private String url;

    public Web(String url){
        this.url=url;
    }
    public Document getDocument() {
        try{
            document=null;
            document= Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
