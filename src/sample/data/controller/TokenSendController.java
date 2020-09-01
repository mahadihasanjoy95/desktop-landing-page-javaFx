package sample.data.controller;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.OTPValidationDto;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.TokenSendApi;
import sample.interfaces.TokenSendListener;

public class TokenSendController extends BaseController implements Callback<ResponseBody> {
    private TokenSendListener listener = null;

    public TokenSendController(TokenSendListener listener) {
        this.listener = listener;
    }

    public void start(OTPValidationDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);
        TokenSendApi tokenApi = retrofit.create(TokenSendApi.class);
        Call<ResponseBody> call = tokenApi.loadToken(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            SuccessResponse<ResponseBody> successResponse = createSuccessResponse(response);
            if (listener != null) listener.sendTokenCompleted(successResponse);
        } else {
            if (listener != null) listener.sendTokenFailed(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.sendTokenFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}
