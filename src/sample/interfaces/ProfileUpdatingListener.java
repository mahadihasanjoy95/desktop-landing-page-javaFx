package sample.interfaces;


import sample.data.model.UserDetails;
import sample.helper.SuccessResponse;

public interface ProfileUpdatingListener {
    void profileUpdatingCompleted(SuccessResponse<UserDetails> result);
    void profileUpdatingFailed(String message);
}
