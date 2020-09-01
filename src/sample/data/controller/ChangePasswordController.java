package sample.data.controller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.ChangePasswordDto;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.ChangePasswordApi;
import sample.interfaces.ChangePasswordListener;
import sample.utils.Messages;

public class ChangePasswordController extends BaseController implements Callback<ResponseBody> {
    private ChangePasswordListener listener = null;

    public ChangePasswordController(ChangePasswordListener listener) {
        this.listener = listener;
    }

    public void start(ChangePasswordDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        ChangePasswordApi changePasswordApi = retrofit.create(ChangePasswordApi.class);
        Call<ResponseBody> call = changePasswordApi.loadChangePassword(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        SuccessResponse<ResponseBody> preSignUp = createSuccessResponse(response);
        if (listener != null) listener.changePasswordCompleted(preSignUp);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.changePasswordFailed(Messages.FAILED_TO_CHANGE_PASSWORD);
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}