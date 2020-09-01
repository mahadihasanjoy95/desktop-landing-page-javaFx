package sample.interfaces;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sample.data.dto.ApplicationListDto;
import sample.data.model.ApplicationInfo;

public interface ApplicationListApi {

    @POST("rest/application/listByType")
    Call<List<ApplicationInfo>> loadAppData(@Body ApplicationListDto applicationListDto);
}
