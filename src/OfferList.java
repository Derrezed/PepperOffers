import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class OfferList {
    private ArrayList<Offer> offerList;
    private Document page;
    private String site = "https://www.pepper.pl/";

    public OfferList() {
        /*if(downloadPage(site).equals(null)) {
            System.out.println("OfferList: method downloadPage("
                    + site + ") returns null");
        } else {
            this.page = downloadPage(site);
        }*/
        this.offerList = new ArrayList<>();
        //separeContent(page);
    }

    public void downloadPage(String site) {
        try {
            Document page = Jsoup.connect(site).get();
            separeContent(page);
        } catch (Exception e) {
            System.out.println("Error! " + e.toString());
        }
    }

    /*public boolean addElement(Offer element) {
        if(element.equals(null)) {
            System.out.println("OfferList: method addElement tries to" +
                    " add null object");
            return false;
        } else {
            offerList.add(element);
            return true;
        }
    }*/

    public boolean separeContent(Document page) {
        String temp;
        String name;
        if(!offerList.isEmpty()){
            offerList.removeAll(offerList);
        }

        Elements elem = page.select("div[class='gridLayout-item threadCardLayout--card']");
        try {
            for (Element x : elem) {
                if (x.select("span[class='cept-vote-temp vote-temp vote-temp--hot']").hasText()) {
                    temp = x.select("span[class='cept-vote-temp vote-temp vote-temp--hot']").text();
                } else if(x.select("span[class='space--h-2 text--b']").hasText()) {
                    temp = x.select("span[class='space--h-2 text--b']").text() + "  ZAKOńCZONA";
                } else {
                    temp = x.select("span[class='cept-vote-temp vote-temp vote-temp--burn']").text();
                }
                name = x.select("a[class='cept-tt thread-link linkPlain thread-title--card']").text();
                offerList.add(new Offer(name,temp));
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public void showList() {
        System.out.println("SizeOfOfferList: " + offerList.size());
        for(int i=0; i < offerList.size()-1; i++) {
            System.out.println(offerList.get(i).getOfferName() + "      " + offerList.get(i).getOfferTemp());
        }
    }

    public int listSize() {
        return this.offerList.size();
    }

    public ArrayList<Offer> getList(){
        return this.offerList;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
