package sample.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import sample.data.model.UserDetails;


public interface UserDetailsApi {
    @GET("/rest/user/user-details")
    Call<UserDetails> fetchUserDetails();
}
