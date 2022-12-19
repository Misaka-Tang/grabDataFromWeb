import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

public class GrabDatas {
    private Document doc;
    private HashMap<String, List<String>> map=new HashMap<>();

    public void getDatas(Web web){
        doc=web.getDocument();
        Elements elms=doc.getElementsByTag("strong");
        String regex=".*[?]\t*?";
        Pattern pattern=Pattern.compile(regex);
        for (Element elm: elms){
            Matcher matcher=pattern.matcher(elm.text());
            if (matcher.matches()) System.out.println(elm.text());
        }
    }
}
