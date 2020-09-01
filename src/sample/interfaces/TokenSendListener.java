package sample.interfaces;



import okhttp3.ResponseBody;
import sample.helper.SuccessResponse;

public interface TokenSendListener {
    void sendTokenCompleted(SuccessResponse<ResponseBody> signUpValidation);
    void sendTokenFailed(String message);
}
