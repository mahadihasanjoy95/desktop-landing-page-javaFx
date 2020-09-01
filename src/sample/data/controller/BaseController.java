package sample.data.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sample.data.controller.common.JwtInterceptor;
import sample.helper.ResponseMetadata;
import sample.helper.SuccessResponse;
import sample.utils.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseController {

    public static Gson gson;
    private static HttpLoggingInterceptor interceptor;
    private static OkHttpClient client;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();


    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new JwtInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    public Retrofit getRetrofit(String baseUrl) {
        if (interceptor == null) {
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        if (client == null)
            client = createHttpClient();


        if (gson == null)
            gson = new GsonBuilder().setLenient().create();
                    /*.setLenient()
                    .create();*/

        if (!retrofitHashMap.containsKey(baseUrl)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            retrofitHashMap.put(baseUrl, retrofit);
            return retrofit;
        }
        return retrofitHashMap.get(baseUrl);
    }

    public <T> ResponseMetadata createResponseMetadata(Response<T> response) {
        ResponseMetadata responseMetadata = new ResponseMetadata();
        try {
            responseMetadata.setMessage(response.isSuccessful() ? response.message() : response.errorBody().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        responseMetadata.setSuccessful(response.isSuccessful());
        responseMetadata.setResponseCode(response.code());

        return responseMetadata;
    }

    public <T> ResponseMetadata createResponseMetadata(Throwable throwable) {
        ResponseMetadata responseMetadata = new ResponseMetadata();
        responseMetadata.setMessage(throwable.getMessage());
        responseMetadata.setSuccessful(false);
        responseMetadata.setResponseCode(Constants.StatusCodes.RESPONSE_CODE_NETWORK_FAILED);

        return responseMetadata;
    }


    public <T> T processResponse(Map<String, Object> rawBody, Class<T> classType) {

        try {
            JsonElement jsonElement = rawBody.containsKey("statusCode") ?
                    gson.toJsonTree(rawBody.get("responseBody")) : gson.toJsonTree(rawBody);
            T result = gson.fromJson(jsonElement, classType);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> SuccessResponse<T> createSuccessResponse(Response<Map<String, Object>> response, Class<T> type) {
        SuccessResponse successResponse = new SuccessResponse(
                createResponseMetadata(response),
                processResponse(response.body(),
                        type));
        return successResponse;
    }

    public <T> SuccessResponse<T> createSuccessResponse(Response<T> response) {
        SuccessResponse successResponse = new SuccessResponse(createResponseMetadata(response), response.body());
        return successResponse;
    }

}