package ch.mirioeggmann.daily_meal_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuPlan {
    private String date;
    @SerializedName("offers")
    private List<Menu> menus = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "MenuPlan{" +
                "date='" + date + '\'' +
                ", menus=" + menus +
                '}';
    }
}
