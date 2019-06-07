package first.com;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface  MyAPIServiceres {

    @POST("reservation?api_key=keygkXy0a4GuCXh7")
        // 用@Body表示要傳送Body資料
    Call<resrecords> postRecords(@Body patientpost fields);



}
