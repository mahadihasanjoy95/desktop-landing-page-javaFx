package sample.utils;

import javafx.scene.paint.Color;

public class Constants {

    public static String CURRENT_URL = "";
    public static String CURRENT_APP_NAME = "SUPER APP";
    public static String EMAIL_FORGET_PASS = "";

    //Added by Raman
    public static final int SUCCESS_RESPONSE_CODE_200 = 200;
    public static final int SUCCESS_RESPONSE_CODE_201 = 201;
    public static final int SUCCESS_RESPONSE_CODE_202 = 202;
    public static final int SUCCESS_RESPONSE_CODE_204 = 204;
    public static final int SUCCESS_RESPONSE_CODE_400 = 400;
    public static final int SUCCESS_RESPONSE_CODE_401 = 401;
    public static final int SUCCESS_RESPONSE_CODE_404 = 404;
    public static final int SUCCESS_RESPONSE_CODE_409 = 409;
    public static final int SUCCESS_RESPONSE_CODE_424 = 424;
    public static final int SUCCESS_RESPONSE_CODE_500 = 500;

    public static final int INPUT_FIELD_CHARACTER_COUNT = 255;
    public static final int PASSWORD_LENGTH = 8;
    public static final String USER_LOGIN_TAG = "USER_LOGIN_TAG";
    public static final String APPLICATION_LIST = "APPLICATION_LIST";
    public static final String TAG_PROFILE_UPDATE = "TAG_PROFILE_UPDATE";
    public static final String TAG_EMAIL = "TAG_EMAIL";

    public static long systemCurrentTime = 0;
    public static final String LOGIN_TAG = "LOGIN_TAG";
    public static final String JWT_TOKEN_TAG = "JWT_TOKEN_TAG";
    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final String TAG_SHOULD_PREVENT_BACK = "shud_prev_back";

    public class StatusCodes {
        public static final int LIMIT_EXCEEDED = 413;
        public static final int CONFLICT = 409;
        public static final int TOKEN_EXPIRED_CODE = 401;
        public static final int RESPONSE_CODE_NETWORK_FAILED = -1;
    }

    public class FxmlUrl{
        public static final String SIGNIN_URL = "/fxml/sign_in.fxml";
        public static final String SIGNIN_CSS = "/styles/signin.css";
        public static final String LOGIN_URL = "/fxml/sign_in.fxml";
        public static final String LOGIN_CSS = "/styles/signin.css";
        public static final String FORGET_PASSWORD_URL ="/fxml/forget_password.fxml";
        public static final String FORGET_PASSWORD_CSS ="/styles/signin.css";
        public static final String SIGNUP_URL ="/fxml/sign_up_form.fxml";
        public static final String SIGNUP_CSS ="/styles/signin.css";
        public static final String LANDING_PAGE_URL ="/fxml/landing_page.fxml";
        public static final String LANDING_PAGE_CSS ="/styles/landing_page.css";
        public static final String WEBVIEW_URL ="/fxml/webview_nav_new.fxml";
        public static final String WEBVIEW_CSS ="/styles/webview_page.css";
        public static final String PASSWORD_CHANGE_URL ="/fxml/password_change.fxml";
        public static final String PASSWORD_CHANGE_CSS ="/styles/signin.css";
        public static final String USER_PROFILE_URL ="/fxml/profile_update.fxml";
        public static final String USER_PROFILE_CSS ="/styles/signin.css";
        public static final String PASSWORD_RESET_URL ="/fxml/password_reset.fxml";
        public static final String PASSWORD_RESET_CSS ="/styles/signin.css";
        public static final String SPLASH_SCREEN_URL ="/fxml/splash_screen.fxml";
        public static final String SPLASH_SCREEN_CSS ="/styles/Styles.css";
    }

    public static class Colors {
        public static Color color1 = Color.web("#8F8D90", 1.0);
        public static Color color2 = Color.web("#020144",1.0);
        public static Color color3 = Color.web("#84A3FD",1.0);
        public static Color color4 = Color.web("#15369E",1.0);

    }

    public static final double DEFAULT_HEIGHT = 31.0 ;
}