package sample.data.controller;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.SignUpDto;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.SignUpApi;
import sample.interfaces.SignUpListener;

public class SignUpController extends BaseController implements Callback<ResponseBody> {
    private SignUpListener listener = null;

    public SignUpController(SignUpListener listener) {
        this.listener = listener;
    }

    public void start(SignUpDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        SignUpApi signUpApi = retrofit.create(SignUpApi.class);
        Call<ResponseBody> call = signUpApi.loadSignUp(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        SuccessResponse<ResponseBody> preSignUp = createSuccessResponse(response);
        if (listener != null) listener.signUpCompleted(preSignUp.getResponseMetadata().getResponseCode());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.signUpFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}