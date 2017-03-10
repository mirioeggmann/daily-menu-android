package ch.mirioeggmann.daily_meal_android.activity;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.mirioeggmann.daily_meal_android.R;
import ch.mirioeggmann.daily_meal_android.model.MenuPlan;
import ch.mirioeggmann.daily_meal_android.model.Response;
import ch.mirioeggmann.daily_meal_android.service.MenuPlanService;
import ch.mirioeggmann.daily_meal_android.view.MenuAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MenuPlanActivity extends AppCompatActivity {

    /**
     * Ignore this, trash code atm...
     */
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_plan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private String mRestaurantShortcut = "restaurant-engehalde";
        private String mRestaurantName;
        private int mDay = 0;
        private int mWeekDay = 0;
        // make own api that mo - fr ersichtlich und nicht immer auf 0
        private List<ch.mirioeggmann.daily_meal_android.model.Menu> mMenus;
        private RecyclerView mMenuRecyclerView;
        private RecyclerView.Adapter mMenuAdapter;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_menu_plan, container, false);
            Log.i("current weekday: ", String.valueOf(mWeekDay));
            mWeekDay = Calendar.DAY_OF_WEEK;
            if ( mWeekDay >= 3) {
                mWeekDay -= 3;
            } else if ( mWeekDay == 2) {
                mWeekDay = 6 - 1;
            } else if ( mWeekDay == 1) {
                mWeekDay = 6 - 2;
            } else if ( mWeekDay == 0) {
                mWeekDay = 6 -3;
            }
            mMenus = new ArrayList<>();
            mMenuAdapter = new MenuAdapter(R.layout.list_item, mMenus);
            mMenuRecyclerView = (RecyclerView)rootView.findViewById(R.id.menu_recycler_view);
            mMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mMenuRecyclerView.setHasFixedSize(false);
            mMenuRecyclerView.setAdapter(mMenuAdapter);
            //mRestaurantName

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
                            final TextView menuPlanDateTextView = (TextView) rootView.findViewById(R.id.menu_plan_date);
                            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd.MM.yyyy");
                            menuPlanDateTextView.setText(simpleDate.format(menuPlan.getDate()));
                            final TextView restaurantNameTextView = (TextView) rootView.findViewById(R.id.restaurant_name);
                            restaurantNameTextView.setText(mRestaurantShortcut);
                            mMenus.addAll(menuPlan.getMenus());
                            mMenuAdapter.notifyDataSetChanged();
                        }
                    });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            int mWeekDay = Calendar.DAY_OF_WEEK;
            if ( mWeekDay >= 3) {
                mWeekDay -= 3;
            } else if ( mWeekDay == 2) {
                mWeekDay = 6 - 1;
            } else if ( mWeekDay == 1) {
                mWeekDay = 6 - 2;
            } else if ( mWeekDay == 0) {
                mWeekDay = 6 -3;
            }
            return 5 - mWeekDay;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int positiontrue;
            if (getCount() == 5) {
                positiontrue = position;
            } else if (getCount() == 4) {
                positiontrue = position + 1;
            } else if (getCount() == 3) {
                positiontrue = position + 2;
            } else if (getCount() == 2) {
                positiontrue = position + 3;
            } else if (getCount() == 1) {
                positiontrue = position + 4;
            } else {
                positiontrue = 0;
            }
            switch (positiontrue) {
                case 0:
                    return "MO";
                case 1:
                    return "DI";
                case 2:
                    return "MI";
                case 3:
                    return "DO";
                case 4:
                    return "FR";
            }
            return null;
        }
    }
}
