package sample.data.controller;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.ProfilePicUploadingApi;
import sample.interfaces.ProfilePicUploadingListener;

public class ProfilePicUploadingController extends BaseController implements Callback<ResponseBody> {
    private ProfilePicUploadingListener listener = null;

    public ProfilePicUploadingController(ProfilePicUploadingListener listener) {
        this.listener = listener;
    }

    public void start(RequestBody body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        ProfilePicUploadingApi profilePicUploadingApi = retrofit.create(ProfilePicUploadingApi.class);
        Call<ResponseBody> call = profilePicUploadingApi.loadProfilePic(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        SuccessResponse<ResponseBody> successResponse = createSuccessResponse(response);
        if (listener != null) listener.uploadingProfilePicCompleted(successResponse);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.uploadingProfilePicFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}