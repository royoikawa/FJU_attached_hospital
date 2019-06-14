package first.com;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager2 {
    // 以Singleton模式建立
    private static RetrofitManager2 mInstance = new RetrofitManager2();

    private MyAPIService myAPIService;

    private RetrofitManager2() {

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.airtable.com/v0/appgPqAWrw2xTWKdx/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIService = retrofit.create(MyAPIService.class);
    }

    public static RetrofitManager2 getInstance() {
        return mInstance;
    }

    public MyAPIService getAPI() {
        return myAPIService;
    }
}


