package ch.mirioeggmann.daily_meal_android.model;

import java.util.List;

// Rename to menu
public class Menu {
    private String description;
    private String title;
    private List<String> trimmings = null;
    private String sidedish;
    private Price price;
    private String provenance;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTrimmings() {
        return trimmings;
    }

    public void setTrimmings(List<String> trimmings) {
        this.trimmings = trimmings;
    }

    public String getSidedish() {
        return sidedish;
    }

    public void setSidedish(String sidedish) {
        this.sidedish = sidedish;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", trimmings=" + trimmings +
                ", sidedish='" + sidedish + '\'' +
                ", price=" + price +
                ", provenance='" + provenance + '\'' +
                '}';
    }
}
