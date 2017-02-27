package ch.mirioeggmann.daily_meal_android.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.mirioeggmann.daily_meal_android.R;
import ch.mirioeggmann.daily_meal_android.model.Menu;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<Menu> mMenuDataset;
    private int mListItemLayout;

    public MenuAdapter(int layoutId, List<Menu> mMenus) {
        this.mListItemLayout = layoutId;
        this.mMenuDataset = mMenus;
    }


    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mListItemLayout, parent, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        TextView item = holder.item;
        item.setText(mMenuDataset.get(position).getTitle()+System.getProperty("line.separator")
        +mMenuDataset.get(position).getDescription()+System.getProperty("line.separator")
        +mMenuDataset.get(position).getTrimmings()+System.getProperty("line.separator")
        +mMenuDataset.get(position).getSidedish()+System.getProperty("line.separator")
        +mMenuDataset.get(position).getProvenance()+System.getProperty("line.separator")
        +"Intern:"+mMenuDataset.get(position).getPrice().getInternalPrice()+System.getProperty("line.separator")
        +"Extern:"+mMenuDataset.get(position).getPrice().getExternalPrice());
    }

    @Override
    public int getItemCount() {
        return mMenuDataset == null ? 0 : mMenuDataset.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        //public TextView title;
        //public TextView description;
        public TextView item;

        public MenuViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.row_item);
        }
    }
}
