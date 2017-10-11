package Util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
//import retrofit.GsonConverterFactory;


public class EventManagerApp extends Application {

    private static Retrofit retrofit = null;
    private OkHttpClient client;
    private static EventManagerApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static EventManagerApp getInstance() {
        if (instance == null) {
            instance = new EventManagerApp();
        }
        return instance;
    }


    /**
     * @return the Retrofit Rest Api Service
     */
    public static APIService getApiService() {
        return retrofit.create(APIService.class);
    }


    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenet = connectivityManager.getActiveNetworkInfo();
        return activenet != null;
    }
}
