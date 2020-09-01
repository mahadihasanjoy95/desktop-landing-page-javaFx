package sample.helper;

public class AppConfig {
    public static String BASE_SERVER_URL = getBaseServerUrl();
    public static Long TOKEN_REFRESH_TIME_MILLIS = 5000l;

    public static String getBaseServerUrl() {
//        return "http://182.163.112.215:8082/";
        return "http://localhost:5000/";
    }
}
