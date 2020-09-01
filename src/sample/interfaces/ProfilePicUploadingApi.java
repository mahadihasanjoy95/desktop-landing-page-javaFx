package sample.interfaces;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfilePicUploadingApi {

    @POST("rest/user/profilePhotoUpload")
    Call<ResponseBody> loadProfilePic(@Body RequestBody body);
}
