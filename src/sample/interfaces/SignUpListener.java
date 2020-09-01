package sample.interfaces;

public interface SignUpListener {
    void signUpCompleted(int code);
    void signUpFailed(String message);
}
