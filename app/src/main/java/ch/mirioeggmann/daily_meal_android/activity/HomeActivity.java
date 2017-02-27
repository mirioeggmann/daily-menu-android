package ch.mirioeggmann.daily_meal_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ch.mirioeggmann.daily_meal_android.R;
import ch.mirioeggmann.daily_meal_android.model.Menu;
import ch.mirioeggmann.daily_meal_android.model.MenuPlan;
import ch.mirioeggmann.daily_meal_android.model.Response;
import ch.mirioeggmann.daily_meal_android.model.Restaurant;
import ch.mirioeggmann.daily_meal_android.service.MenuPlanService;
import ch.mirioeggmann.daily_meal_android.service.RestaurantService;
import ch.mirioeggmann.daily_meal_android.view.MenuAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    private String mRestaurantShortcut = "restaurant-engehalde";
    private int mDay = 0;
    // make own api that mo - fr ersichtlich und nicht immer auf 0
    private List<Menu> mMenus;
    private RecyclerView mMenuRecyclerView;
    private RecyclerView.Adapter mMenuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("currentday",String.valueOf(new GregorianCalendar().get(Calendar.DAY_OF_WEEK)));

        mMenus = new ArrayList<>();
        mMenuAdapter = new MenuAdapter(R.layout.list_item, mMenus);
        mMenuRecyclerView = (RecyclerView)findViewById(R.id.menu_recycler_view);
        mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMenuRecyclerView.setHasFixedSize(false);
        mMenuRecyclerView.setAdapter(mMenuAdapter);

        final MenuPlanService menuPlanService = MenuPlanService.retrofit.create(MenuPlanService.class);
        menuPlanService.getMenuPlanByRestaurantAndDay(mRestaurantShortcut,mDay)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response<MenuPlan>, MenuPlan>() {
                    @Override
                    public MenuPlan call(Response<MenuPlan> menuPlanResponse) {
                        return menuPlanResponse.getData();
                    }
                })
                .subscribe(new Subscriber<MenuPlan>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Finished call", "Rx Call is finished");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "An error occured", e);
                    }

                    @Override
                    public void onNext(MenuPlan menuPlan) {
                        Log.i("Result", menuPlan.toString());
                        final TextView menuPlanDateTextView = (TextView) findViewById(R.id.menu_plan_date);
                        menuPlanDateTextView.setText(menuPlan.getDate());
                        final TextView restaurantNameTextView = (TextView) findViewById(R.id.restaurant_name);
                        restaurantNameTextView.setText(mRestaurantShortcut);
                        Log.i("MenuArray Size", String.valueOf(menuPlan.getMenus().size()));
                        mMenus.addAll(menuPlan.getMenus());
                        mMenuAdapter.notifyDataSetChanged();
                        Log.i("ItemCount",String.valueOf(mMenuAdapter.getItemCount()));
                    }
                });
    }
}
