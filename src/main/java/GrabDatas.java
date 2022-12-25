import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

public class GrabDatas {
    private Document doc;
    private List<Integer> countTypeQ;
    private List<List<String>> answerSet;
    private List<String> questions;

    public void getDatas(Web web) {
        countTypeQ = new ArrayList<>();
        questions = new ArrayList<>();
        doc = web.getDocument();
        Elements elms = doc.getElementsByTag("strong");
        Elements elmsAus = doc.getElementsByTag("li");
        String regexQues = ".*[?].*?";
        String regexMulti2choices = ".*\\(Choose two.\\)";
        String regexMulti3choices = ".*\\(Choose three.\\)";
        Pattern pattern = Pattern.compile(regexQues);
        for (Element elm : elms) {
            //load all questions from web and match
            Matcher matcher = pattern.matcher(elm.text());
            //simple choice
            if (matcher.matches()) {
//                System.out.println(elm.text());
                countTypeQ.add(1);
                questions.add(elm.text());
            }
            //2choices
            if (Pattern.matches(regexMulti2choices, elm.text())) {
                countTypeQ.add(2);
                questions.add(elm.text());
            }
            //3choices
            if (Pattern.matches(regexMulti3choices, elm.text())) {
                countTypeQ.add(3);
                questions.add(elm.text());
            }
        }
        //start to iterate ans
        int i=0;
        answerSet=new ArrayList<>();
        for (Element elm : elmsAus) {
            int count=countTypeQ.get(i);
            Elements listElm = elm.getElementsByTag("strong");
            //store multiple choices ans
//            List<String>curAns=new ArrayList<>();
            for (Element iterator : listElm) {
                System.out.println(iterator.text());
//                curAns.add(iterator.text());
//                count--;
//                if(count==0){
//                    answerSet.add(curAns);
//                    i++;
//                }
            }
        }
    }

    public void listAnswers(Web web) {
        doc = web.getDocument();
        Elements elmsAus = doc.getElementsByTag("li");
        for (Element elm : elmsAus) {
            Elements listElm = elm.getElementsByTag("strong");
            for (Element iterator : listElm) {
                System.out.println(iterator.text());
            }
        }
    }
}

