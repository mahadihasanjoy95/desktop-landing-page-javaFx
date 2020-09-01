package sample.interfaces;


import okhttp3.ResponseBody;
import sample.helper.SuccessResponse;

public interface PasswordResetListener {
    void passwordResetCompleted(SuccessResponse<ResponseBody> successResponse);

    void passwordResetFailed(String message);
}
