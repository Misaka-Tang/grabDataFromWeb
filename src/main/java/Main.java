public class Main {
    public static void main(String[] args) {
        Web web = new Web("https://itexamanswers.net/ccna-1-v7-modules-14-15-network-application-communications-" +
                "exam-answers.html");
        GrabDatas data = new GrabDatas();
        data.listAnswers(web);
//        data.getDatas(web);
    }
}
