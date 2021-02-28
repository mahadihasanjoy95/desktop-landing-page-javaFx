package sample.utils;

import javafx.scene.paint.Color;

public class Constants {

    public static Page last_url;
    public static Page current_url;

    public static String CURRENT_URL = "";
    public static String CURRENT_APP_NAME = "SUPER APP";
    public static String EMAIL_FORGET_PASS = "";

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

    public class FxmlUrl {
        public static final String SIGNIN_URL = "/fxml/sign_in.fxml";
        public static final String SIGNIN_CSS = "/styles/signin.css";
        public static final String LOGIN_URL = "/fxml/sign_in.fxml";
        public static final String LOGIN_CSS = "/styles/signin.css";
        public static final String FORGET_PASSWORD_URL = "/fxml/forget_password.fxml";
        public static final String FORGET_PASSWORD_CSS = "/styles/signin.css";
        public static final String SIGNUP_URL = "/fxml/sign_up_form.fxml";
        public static final String SIGNUP_CSS = "/styles/signin.css";
        public static final String LANDING_PAGE_URL = "/fxml/landing_page.fxml";
        public static final String LANDING_PAGE_CSS = "/styles/landing_page.css";
        public static final String WEBVIEW_URL = "/fxml/webview_nav.fxml";
        public static final String WEBVIEW_CSS = "/styles/webview_page.css";
        public static final String PASSWORD_CHANGE_URL = "/fxml/password_change.fxml";
        public static final String PASSWORD_CHANGE_CSS = "/styles/signin.css";
        public static final String USER_PROFILE_URL = "/fxml/profile_update.fxml";
        public static final String USER_PROFILE_CSS = "/styles/signin.css";
        public static final String PASSWORD_RESET_URL = "/fxml/password_reset.fxml";
        public static final String PASSWORD_RESET_CSS = "/styles/signin.css";
        public static final String SPLASH_SCREEN_URL = "/fxml/splash_screen.fxml";
        public static final String SPLASH_SCREEN_CSS = "/styles/Styles.css";
    }

    public static class Colors {
        public static Color color1 = Color.web("#8F8D90", 1.0);
        public static Color color2 = Color.web("#020144", 1.0);
        public static Color color3 = Color.web("#84A3FD", 1.0);
        public static Color color4 = Color.web("#15369E", 1.0);
        public static Color color5 = Color.web("#ABABAB", 1.0);

    }

    public static class ImageUrl {
        public static final String WHITE_STAR = "/imgs/star1.png";
        public static final String YELLOW_STAR = "/imgs/star.png";
        public static final String CANCEL_BOOKMARKS = "/imgs/cancel.png";
        public static final String DEFAULT_ICON = "/imgs/default.png";
        public static final String LOGIN_BACKGROUND = "/imgs/background_login.png";
        public static final String MAIN_LOGO = "/imgs/main_logo.png";
        public static final String HOME_ICON = "/imgs/home.png";
        public static final String EYE_SLASH = "/imgs/eye_slash.png";
        public static final String EYE_OPEN = "/imgs/eye_open.png";
    }

    public static final double DEFAULT_HEIGHT = 31.0;
}
