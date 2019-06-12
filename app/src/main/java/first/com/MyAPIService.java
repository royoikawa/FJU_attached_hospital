package first.com;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface MyAPIService {
    @GET("reservation?api_key=keygkXy0a4GuCXh7p")
    Call<records> getRecords();

    @POST("reservation?api_key=keygkXy0a4GuCXh7p")
        // 用@Body表示要傳送Body資料
    Call<records> postRecords(@Body userPost fields);

    @DELETE("reservation?api_key=keygkXy0a4GuCXh7p")

    Call<records> deleteRecords();

    @PATCH("reservation?api_key=keygkXy0a4GuCXh7p")
    Call<records> changeRecords(@Body userPost fields);



}
