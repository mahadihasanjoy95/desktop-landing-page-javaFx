package sample.data.controller;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.SendOtpDto;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.ForgotPasswordApi;
import sample.interfaces.ForgotPasswordListener;
import sample.utils.Messages;

public class ForgotPasswordController extends BaseController implements Callback<ResponseBody> {
    private ForgotPasswordListener listener = null;

    public ForgotPasswordController(ForgotPasswordListener listener) {
        this.listener = listener;
    }

    public void start(SendOtpDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        ForgotPasswordApi forgotPasswordApi = retrofit.create(ForgotPasswordApi.class);
        Call<ResponseBody> call = forgotPasswordApi.loadForgotPassword(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        SuccessResponse<ResponseBody> successResponse = createSuccessResponse(response);
        if (listener != null) listener.forgotPasswordCompleted(successResponse);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.forgotPasswordFailed(Messages.FAILED_TO_SEND_EMAIL);
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}