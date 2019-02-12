import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class OfferList {
    private ArrayList<Offer> offerList;
    private Document page;

    public OfferList(String site) {
        if(downloadPage(site).equals(null)) {
            System.out.println("OfferList: method downloadPage("
                    + site + ") returns null");
        } else {
            this.page = downloadPage(site);
        }
        this.offerList = new ArrayList<>();
        separeContent(page);
    }

    public Document downloadPage(String site) {
        try {
            Document page = Jsoup.connect(site).get();
            return page;
        } catch (Exception e) {
            System.out.println("Error! " + e.toString());
            return null;
        }
    }

    public boolean addElement(Offer element) {
        if(element.equals(null)) {
            System.out.println("OfferList: method addElement tries to" +
                    " add null object");
            return false;
        } else {
            offerList.add(element);
            return true;
        }
    }

    public boolean separeContent(Document page) {
        Elements temp;
        ArrayList<String> tempList = new ArrayList<>();
        Elements name;
        ArrayList<String> nameList = new ArrayList<>();
        if(!page.equals(null)) {
             temp = page.select("span[class='cept-vote-temp vote-temp vote-temp--hot']");
             name = page.select("a[class='cept-tt thread-link linkPlain thread-title--card']");
             if(!temp.equals(null) && !name.equals(null)) {
                 for(Element x : temp) {
                     tempList.add(x.text());
                 }
                 for(Element x : name) {
                     nameList.add(x.text());
                 }
                 for(int i=0; i < tempList.size()-1; i++){
                     addElement(new Offer(nameList.get(i),tempList.get(i)));
                 }
             } else {
                 System.out.println("OfferList: method separeContent second 'if' went to else");
                 return false;
             }
            return true;
        } else {
            System.out.println("OfferList: method separeContent first 'if' went to else");
            return false;
        }
    }

    public void showList() {
        for(int i=0; i < offerList.size()-1; i++) {
            System.out.println(offerList.get(i).getOfferName() + "      " + offerList.get(i).getOfferTemp());
        }
    }
}
