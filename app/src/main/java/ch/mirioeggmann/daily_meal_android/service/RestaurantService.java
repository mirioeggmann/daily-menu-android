package ch.mirioeggmann.daily_meal_android.service;

import java.util.List;

import ch.mirioeggmann.daily_meal_android.model.Response;
import ch.mirioeggmann.daily_meal_android.model.Restaurant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mirioeggmann on 25.02.17.
 */

public interface RestaurantService {

    @GET("api/restaurant")
    Observable<Response<List<Restaurant>>> getRestaurants();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://svmeal-api.jmnw.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
}
