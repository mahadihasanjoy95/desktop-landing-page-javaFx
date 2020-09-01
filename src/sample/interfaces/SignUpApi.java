package sample.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.SignUpDto;

public interface SignUpApi {

    @POST("rest/user/signUp")
    Call<ResponseBody> loadSignUp(@Body SignUpDto signUpDTO);
}
