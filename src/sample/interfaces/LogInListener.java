package sample.interfaces;

public interface LogInListener {
    void logInCompleted(Object result);
    void logInFailed(String message);
}
