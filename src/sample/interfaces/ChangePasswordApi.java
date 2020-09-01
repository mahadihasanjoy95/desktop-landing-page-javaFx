package sample.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.ChangePasswordDto;

public interface ChangePasswordApi {

    @POST("rest/user/change-password")
    Call<ResponseBody> loadChangePassword(@Body ChangePasswordDto changePasswordDto);
}
