package sample.interfaces;


import okhttp3.ResponseBody;
import sample.helper.SuccessResponse;

public interface ChangePasswordListener {
    void changePasswordCompleted(SuccessResponse<ResponseBody> responseInfo);

    void changePasswordFailed(String message);
}
