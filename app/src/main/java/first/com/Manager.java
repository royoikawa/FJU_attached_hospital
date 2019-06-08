package first.com;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Manager {
    // 以Singleton模式建立
    private static Manager mInstance = new Manager();

    private MyAPIServiceres myAPIServiceres;

    private Manager() {

        // 設置baseUrl即要連的網站，addConverterFactory用Gson作為資料處理Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.airtable.com/v0/appgPqAWrw2xTWKdx/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPIServiceres = retrofit.create(MyAPIServiceres.class);
    }

    public static Manager getInstance() {
        return mInstance;
    }

    public MyAPIServiceres getAPI() {
        return myAPIServiceres;
    }
}
