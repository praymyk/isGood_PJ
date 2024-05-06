package com.ys.isGood.model.vo.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 토큰 발급 요청 응답 객체
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NaverTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private String expiresIn;
    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
}