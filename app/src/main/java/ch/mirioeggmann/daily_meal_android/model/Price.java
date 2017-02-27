package ch.mirioeggmann.daily_meal_android.model;

public class Price {
    private Double internalPrice;
    private Double externalPrice;

    public Double getInternalPrice() {
        return internalPrice;
    }

    public void setInternalPrice(Double internalPrice) {
        this.internalPrice = internalPrice;
    }

    public Double getExternalPrice() {
        return externalPrice;
    }

    public void setExternalPrice(Double externalPrice) {
        this.externalPrice = externalPrice;
    }

    @Override
    public String toString() {
        return "Price{" +
                "internalPrice=" + internalPrice +
                ", externalPrice=" + externalPrice +
                '}';
    }
}
