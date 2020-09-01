package sample.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sample.data.controller.BaseController;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMetadata {
    private Integer responseCode;
    private boolean isSuccessful;
    private String message;

    public boolean isDetailsNotFound() {
        return responseCode == 204;
    }

    public boolean isConflict() {
        return responseCode == 409;
    }

    public boolean isBadRequest() {
        return responseCode == 400;
    }

    public boolean isEmailPhoneVerifyError() {
        return responseCode == 412;
    }

    public boolean isCloudError() {
        return responseCode == 424;
    }

    public boolean isInternalServerError() {
        return responseCode == 500;
    }

    public boolean isNotFoundError() {
        return responseCode == 404;
    }

    public boolean isWrongLicenseNumber() {
        return responseCode == 412;
    }

    public boolean isTooManyWrongRequestError() {
        return responseCode == 429;
    }

    public boolean isConfigObjectPreparing() {
        return responseCode == 417;
    }

    public <T> T parseErrorBody(Class<T> errorClassType) {
        return BaseController.gson.fromJson(message, errorClassType);
    }

    public boolean isUnAuthorized() {
        return responseCode == 401;
    }
}
