package first.com;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MyAPIService {
    @GET("User?api_key=keyUwcLvTO51TNEHV")
    Call<records> getRecords();

    @POST("User?api_key=keyUwcLvTO51TNEHV")
        // 用@Body表示要傳送Body資料
    Call<records> postRecords(@Body userPost fields);

    @POST("doc?api_key=keyUwcLvTO51TNEHV")
        // 用@Body表示要傳送Body資料
    Call<records> postRecords1(@Body userPost fields);

    @DELETE("User?api_key=keyUwcLvTO51TNEHV")

    Call<records> deleteRecords();

    @PATCH("User?api_key=keyUwcLvTO51TNEHV")
    Call<records> changeRecords(@Body userPost fields);



}

