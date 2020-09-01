package sample.interfaces;


import okhttp3.ResponseBody;
import sample.helper.SuccessResponse;

public interface ProfilePicUploadingListener {
    void uploadingProfilePicCompleted(SuccessResponse<ResponseBody> successResponse);

    void uploadingProfilePicFailed(String message);
}
