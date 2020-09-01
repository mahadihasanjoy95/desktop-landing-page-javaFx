package sample.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    ResponseMetadata responseMetadata;
    T responseData;

    public boolean isCompletelySuccessful() {
        return responseMetadata.isSuccessful() && responseData != null;
    }
}
