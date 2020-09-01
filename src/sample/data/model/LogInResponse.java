package sample.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import sample.helper.AppConfig;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogInResponse {
	@SerializedName("jwt")
	private String accessToken;
	@SerializedName("token_type")
	private String tokenType = "Bearer";
	private Long expiredAt = AppConfig.TOKEN_REFRESH_TIME_MILLIS;

	public String createJwtToken() {
		return tokenType + accessToken;
	}
}