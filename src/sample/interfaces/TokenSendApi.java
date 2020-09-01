package sample.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.OTPValidationDto;

public interface TokenSendApi {

    @POST("rest/account/validate-otp")
    Call<ResponseBody> loadToken(@Body OTPValidationDto body);
}
