package sample.data.controller;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.model.UserDetails;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.UserDetailsApi;
import sample.interfaces.UserDetailsListener;

public class UserDetailsController extends BaseController implements Callback<UserDetails> {
    private UserDetailsListener listener = null;

    public UserDetailsController(UserDetailsListener listener) {
        this.listener = listener;
    }

    public void start() {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);
        UserDetailsApi userDetailsApi = retrofit.create(UserDetailsApi.class);
        Call<UserDetails> call = userDetailsApi.fetchUserDetails();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
        if (response.isSuccessful()) {
            SuccessResponse<UserDetails> successResponse = createSuccessResponse(response);
            if (listener != null) listener.userDetailsCompleted(successResponse.getResponseData());
        } else {
            if (listener != null) listener.userDetailsFailed(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<UserDetails> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.userDetailsFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}
