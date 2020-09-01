package sample.interfaces;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.SignUpDto;
import sample.data.model.UserDetails;

public interface ProfileUpdatingApi {

    @POST("/rest/user/edit")
    Call<UserDetails> fetchUserDetails(@Body SignUpDto signUpDto);
}
