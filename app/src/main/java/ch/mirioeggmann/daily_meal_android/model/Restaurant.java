package ch.mirioeggmann.daily_meal_android.model;

public class Restaurant {

    private String name;
    private String link;
    private String shortcut;
    private Boolean _public;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public Boolean getPublic() {
        return _public;
    }

    public void setPublic(Boolean _public) {
        this._public = _public;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", shortcut='" + shortcut + '\'' +
                ", public=" + _public +
                '}';
    }
}