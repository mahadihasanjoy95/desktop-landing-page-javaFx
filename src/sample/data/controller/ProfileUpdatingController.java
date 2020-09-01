package sample.data.controller;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.SignUpDto;
import sample.data.model.UserDetails;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.ProfileUpdatingApi;
import sample.interfaces.ProfileUpdatingListener;
import sample.utils.Messages;

public class ProfileUpdatingController extends BaseController implements Callback<UserDetails> {
    private ProfileUpdatingListener listener = null;

    public ProfileUpdatingController(ProfileUpdatingListener listener) {
        this.listener = listener;
    }

    public void start(SignUpDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        ProfileUpdatingApi profileUpdatingApi = retrofit.create(ProfileUpdatingApi.class);
        Call<UserDetails> call = profileUpdatingApi.fetchUserDetails(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
        if(response.isSuccessful()) {
            SuccessResponse<UserDetails> userDetailsSuccessResponse = createSuccessResponse(response);
            if (listener != null) listener.profileUpdatingCompleted(userDetailsSuccessResponse);
        } else {
            if (listener != null) listener.profileUpdatingFailed(Messages.PROFILE_UPDATING_FAILED);
        }
    }

    @Override
    public void onFailure(Call<UserDetails> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.profileUpdatingFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}
