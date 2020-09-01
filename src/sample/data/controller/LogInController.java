package sample.data.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.controller.common.JwtHelper;
import sample.data.dto.LogInDto;
import sample.data.model.LogInFailedResponse;
import sample.data.model.LogInResponse;
import sample.helper.AppConfig;
import sample.interfaces.LogInApi;
import sample.interfaces.LogInListener;

import java.io.IOException;

public class LogInController extends BaseController implements Callback<ResponseBody> {
    private LogInListener listener = null;

    public LogInController(LogInListener listener) {
        this.listener = listener;
    }

    public void start(LogInDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        LogInApi logInApi = retrofit.create(LogInApi.class);
        Call<ResponseBody> call = logInApi.loadLogin(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (response.isSuccessful()) {
            try {
                LogInResponse logInResponse = gson.fromJson(response.body().string(), LogInResponse.class);
                JwtHelper.getInstance().setJwtToken(logInResponse == null ? null : logInResponse.getTokenType().trim() + " " + logInResponse.getAccessToken().trim());
                if (listener != null) listener.logInCompleted(logInResponse);
            } catch (IOException e) {
                if (listener != null) listener.logInFailed("LogIn Failed");
                e.printStackTrace();
            }
        } else {
            try {
                LogInFailedResponse logInFailedResponse = gson.fromJson(response.errorBody().string(), LogInFailedResponse.class);
                if (listener != null) listener.logInCompleted(logInFailedResponse);
            } catch (IOException e) {
                if (listener != null) listener.logInFailed("LogIn Failed");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.logInFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}
