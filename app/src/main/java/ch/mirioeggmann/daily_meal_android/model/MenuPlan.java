package ch.mirioeggmann.daily_meal_android.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class MenuPlan {
    private Date date;
    @SerializedName("offers")
    private List<Menu> menus = null;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
