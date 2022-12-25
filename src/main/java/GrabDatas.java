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
        answerSet = new ArrayList<>();
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
        //iterate list of countTypeQ to store how many answers the question it has
        List<String> ansList = listAnswers(web);
        int j = 0;
        for (int i = 0; i < countTypeQ.size(); i++) {
            int count = countTypeQ.get(i);
            List<String> curList = new ArrayList<>();
            while (count > 0 && j < ansList.size()) {
                curList.add(ansList.get(j));
                j++;
                count--;
            }
            answerSet.add(curList);
        }
//        System.out.println(answerSet);
        System.out.println(questions);
    }

    public List<String> listAnswers(Web web) {
        List<String> answers = new ArrayList<>();
        doc = web.getDocument();
        Elements elmsAus = doc.getElementsByTag("li");
        for (Element elm : elmsAus) {
            Elements listElm = elm.getElementsByTag("strong");
            for (Element iterator : listElm) {
                answers.add(iterator.text());
            }
        }
        return answers;
    }
}

