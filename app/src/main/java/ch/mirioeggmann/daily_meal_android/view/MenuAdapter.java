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
        TextView description = holder.description;
        TextView title = holder.title;
        TextView trimmings = holder.trimmings;
        TextView provenance = holder.provenance;
        TextView price = holder.price;

        String trimmingsString = "";
        for (String trimming : mMenuDataset.get(position).getTrimmings()) {
            trimmingsString += trimming + " ";
        }

        description.setText(mMenuDataset.get(position).getDescription());
        title.setText(mMenuDataset.get(position).getTitle());
        trimmings.setText(trimmingsString);
        provenance.setText(mMenuDataset.get(position).getProvenance());
        price.setText( "Intern: " + mMenuDataset.get(position).getPrice().getInternalPrice() + " Extern: " + mMenuDataset.get(position).getPrice().getExternalPrice());
    }

    @Override
    public int getItemCount() {
        return mMenuDataset == null ? 0 : mMenuDataset.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView title;
        public TextView trimmings;
        //public TextView sidedish;
        public TextView provenance;
        public TextView price;

        public MenuViewHolder(View itemView) {
            super(itemView);

            this.description = (TextView) itemView.findViewById(R.id.menu_description);
            this.title = (TextView) itemView.findViewById(R.id.menu_title);
            this.trimmings = (TextView) itemView.findViewById(R.id.menu_trimmings);
            this.provenance = (TextView) itemView.findViewById(R.id.menu_provenance);
            this.price = (TextView) itemView.findViewById(R.id.menu_price);
        }
    }
}
