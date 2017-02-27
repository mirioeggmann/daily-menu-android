package ch.mirioeggmann.daily_meal_android.service;

import ch.mirioeggmann.daily_meal_android.model.MenuPlan;
import ch.mirioeggmann.daily_meal_android.model.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MenuPlanService {

    @GET("api/restaurant/{restaurant}/meal/{day}")
    Observable<Response<MenuPlan>> getMenuPlanByRestaurantAndDay(@Path("restaurant")String restaurant, @Path("day")int day);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://svmeal-api.jmnw.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
}
