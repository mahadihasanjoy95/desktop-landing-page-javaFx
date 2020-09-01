package sample.data.controller.common;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpHeaders;
import sample.utils.Constants;

public class JwtInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String jwtToken = JwtHelper.getInstance().getJwtToken();
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        if (jwtToken == null) return chain.proceed(builder.build());

        builder.addHeader(Constants.AUTHORIZATION_KEY, jwtToken);
        builder.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Request request = builder.build();
        Response response = chain.proceed(request);

        if (response.code() == Constants.StatusCodes.TOKEN_EXPIRED_CODE) {
//            SuperApplication.getInstance().forceLogout();
        }
        return response;
    }

}
