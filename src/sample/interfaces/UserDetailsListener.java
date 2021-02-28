package sample.interfaces;

import sample.data.model.UserDetails;

public interface UserDetailsListener {
    void userDetailsCompleted(UserDetails userDetails);

    void userDetailsFailed(String message);
}
