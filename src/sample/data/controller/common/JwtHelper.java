package sample.data.controller.common;


public class JwtHelper {
    private static JwtHelper jwtTokenInstance = new JwtHelper();
    private String jwtToken = "";

    private JwtHelper() {
    }

    public static JwtHelper getInstance() {
        return jwtTokenInstance;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
