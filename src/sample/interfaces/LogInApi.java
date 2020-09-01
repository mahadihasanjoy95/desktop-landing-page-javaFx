package sample.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.LogInDto;

public interface LogInApi {

    @POST("rest/user/signIn")
    Call<ResponseBody> loadLogin(@Body LogInDto logInDTO);
}
