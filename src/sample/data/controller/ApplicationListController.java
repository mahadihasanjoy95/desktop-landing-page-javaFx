package sample.data.controller;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sample.data.dto.ApplicationListDto;
import sample.data.model.ApplicationInfo;
import sample.helper.AppConfig;
import sample.helper.SuccessResponse;
import sample.interfaces.ApplicationListApi;
import sample.interfaces.ApplicationListListener;

import java.util.List;

public class ApplicationListController extends BaseController implements Callback<List<ApplicationInfo>> {
    private ApplicationListListener listener = null;

    public ApplicationListController(ApplicationListListener listener) {
        this.listener = listener;
    }

    public void start(ApplicationListDto body) {
        Retrofit retrofit = getRetrofit(AppConfig.BASE_SERVER_URL);

        ApplicationListApi applicationListApi = retrofit.create(ApplicationListApi.class);
        Call<List<ApplicationInfo>> call = applicationListApi.loadAppData(body);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<ApplicationInfo>> call, Response<List<ApplicationInfo>> response) {
        SuccessResponse<List<ApplicationInfo>> listSuccessResponse = createSuccessResponse(response);
        if (listener != null) listener.applicationListCompleted(listSuccessResponse.getResponseData());
    }

    @Override
    public void onFailure(Call<List<ApplicationInfo>> call, Throwable throwable) {
        if (listener != null && throwable != null && throwable.getMessage() != null) {
            listener.applicationListFailed(throwable.getMessage());
        }
    }

    public void removeListener() {
        this.listener = null;
    }
}
