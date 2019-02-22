public class Offer {
    private String offerName;
    private String offerTemp;

    public Offer(String offerName, String offerTemp) {
        this.offerName = offerName;
        this.offerTemp = offerTemp;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferTemp() {
        return offerTemp;
    }

    public void setOfferTemp(String offerTemp) {
        this.offerTemp = offerTemp;
    }
}
