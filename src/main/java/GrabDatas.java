import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class GrabDatas {
    private Document doc;
    private HashMap<String,String> map=new HashMap<>();

    public void getDatas(Web web){
        doc=web.getDocument();
        Elements elms=doc.getElementsByTag("strong");
        for (Element elm: elms){
            System.out.println(elm.text());
        }
    }
}
