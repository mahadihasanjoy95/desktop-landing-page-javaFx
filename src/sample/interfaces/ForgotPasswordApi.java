package sample.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.SendOtpDto;

public interface ForgotPasswordApi {

    @POST("rest/user/forgetPassword")
    Call<ResponseBody> loadForgotPassword(@Body SendOtpDto body);
}
