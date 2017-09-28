package seoul.emergency.bbibbo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016-07-06.
 */
public class ApplicationController extends Application {

    private static ApplicationController instance;

    public static ApplicationController getInstance() {
        return instance;
    }


    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
        Log.d("####","2222");
    }

    private NetworkService networkService;

    public NetworkService getNetworkService() {
        return networkService;
    }

    //private String baseUrl = "http://52.78.14.151:3000";

    /*public String getBaseUrl() {
        return baseUrl;
    }*/

   // private static Retrofit.Builder builder = new Retrofit.Builder();

    public void buildNetworkService(String baseUrl) {
        synchronized (ApplicationController.class) {
            if(networkService == null) {

                //baseUrl = String.format("http://%s:%d",ip,port);

                OkHttpClient client = new OkHttpClient();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();
                GsonConverterFactory factory = GsonConverterFactory.create();

                //Retrofit 설정
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .client(client)
                        .build();

                //인터페이스 구현
                networkService = retrofit.create(NetworkService.class);
                //Toast.makeText(getApplicationContext(), "동기화 성공", Toast.LENGTH_LONG).show();
            }
        }
    }
}
