package sample.interfaces;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.PasswordResetDto;

public interface PasswordResetApi {

    @POST("rest/user/resetPassword")
    Call<ResponseBody> loadPasswordReset(@Body PasswordResetDto body);
}
