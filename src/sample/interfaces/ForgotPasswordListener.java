package sample.interfaces;


import okhttp3.ResponseBody;
import sample.helper.SuccessResponse;

public interface ForgotPasswordListener {
    void forgotPasswordCompleted(SuccessResponse<ResponseBody> successResponse);

    void forgotPasswordFailed(String message);
}
