package ch.mirioeggmann.daily_meal_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ch.mirioeggmann.daily_meal_android.R;
import ch.mirioeggmann.daily_meal_android.model.Response;
import ch.mirioeggmann.daily_meal_android.model.Restaurant;
import ch.mirioeggmann.daily_meal_android.service.RestaurantService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        final RestaurantService restaurantService = RestaurantService.retrofit.create(RestaurantService.class);
        restaurantService.getRestaurants()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Response<List<Restaurant>>, List<Restaurant>>() {
                    @Override
                    public List<Restaurant> call(Response<List<Restaurant>> restaurantResponse) {
                        return restaurantResponse.getData();
                    }
                })
                .subscribe(new Subscriber<List<Restaurant>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Finished call", "Rx Call is finished");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "An error occured", e);
                    }

                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                        Log.i("Result", restaurants.toString());
                        final TextView textView = (TextView) findViewById(R.id.textView);
                        String test = "";
                        for (Restaurant restaurant : restaurants) {
                            test += restaurant.getName() + " \\n ";
                        }
                        textView.setText(test);
                    }
                });
    }
}
