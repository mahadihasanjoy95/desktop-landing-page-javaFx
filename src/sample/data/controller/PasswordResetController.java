package sample.data.controller;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.PasswordResetDto;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.PasswordResetApi;
import sample.interfaces.PasswordResetListener;

public class PasswordResetController extends BaseController implements Callback<ResponseBody> {
    private PasswordResetListener listener = null;

    public PasswordResetController(PasswordResetListener listener) {
        this.listener = listener;
    }

    public void start(PasswordResetDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        PasswordResetApi passwordResetApi = retrofit.create(PasswordResetApi.class);
        Call<ResponseBody> call = passwordResetApi.loadPasswordReset(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        SuccessResponse<ResponseBody> successResponse = createSuccessResponse(response);
        if (listener != null) listener.passwordResetCompleted(successResponse);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.passwordResetFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}