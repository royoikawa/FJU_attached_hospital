package first.com;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface MyAPIService2 {
    @GET("reservation?api_key=keygkXy0a4GuCXh7p")
    Call<records2> getRecords();

    @POST("reservation?api_key=keygkXy0a4GuCXh7p")
        // 用@Body表示要傳送Body資料
    Call<records2> postRecords(@Body userPost2 fields);

    @DELETE("reservation?api_key=keygkXy0a4GuCXh7p")

    Call<records2> deleteRecords();

    @PATCH("reservation?api_key=keygkXy0a4GuCXh7p")
    Call<records2> changeRecords(@Body userPost2 fields);



}