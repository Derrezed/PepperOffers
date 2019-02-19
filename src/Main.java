import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
       /* Document doc = Jsoup.connect("https://www.pepper.pl/").get();
        Elements num = doc.select("span[class='cept-vote-temp vote-temp vote-temp--hot']");
        int y = 1;
        for(Element x: num){
            System.out.println(y + ". " + x.text());
            y++;*/
        OfferList pepperek = new OfferList("https://www.pepper.pl/");
        pepperek.showList();
    }
}
