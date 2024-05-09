package com.ys.isGood.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.isGood.model.vo.member.LoginMember;
import com.ys.isGood.model.vo.member.Member;
import com.ys.isGood.model.vo.sns.SnsProfile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;


@Controller
@Slf4j
public class GoogleLoginController {

    @Value("${GOOGLE-CLIENT-ID}")
    private String clientId;
    @Value("${GOOGLE-CLIENT-SECRET}")
    private String clientSecret;
    @Value("${GOOGLE-REDIRECT-URI}")
    private String redirectUri;

    @Autowired
    NaverLoginController naverLoginController;

    /*
    구글 로그인 처리 전체 흐름
    1. 구글 로그인 페이지로 이동
    2. 구글 로그인 페이지에서 로그인 후 code를 받음
    3. code를 이용하여 토큰을 받음
    4. 토큰을 이용하여 사용자 정보를 받음
     */

    /*
    step 1. 구글 로그인 페이지로 이동
    API key 정보를 숨기기 위해 Controller에서 직접 처리
    */
    @RequestMapping(value = "/googleLogin.me", method = RequestMethod.GET)
    public String googleLoginStart() {
        log.info("잘 들어오나?");
        // 구글로 부터 받아올 사용자 정보(scope)를 설정 (이메일, 프로필) 정보
        String email = "https://www.googleapis.com/auth/userinfo.email";
        String profile = "https://www.googleapis.com/auth/userinfo.profile";

        // Google 로그인 페이지로 이동 ( 요청에 필요한 정보 쿼리스트리응로 담기 )
        String url = "https://accounts.google.com/o/oauth2/auth?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=" + email + " " + profile;

        return "redirect:" + url;
    }

    /*
    step 2. 구글 로그인 페이지에서 로그인 후 code를 받음
    step 3. code를 이용하여 토큰을 받음
    */
    @GetMapping("/googleLogin")
    public String googleLogin(@RequestParam("code") String code,
                              HttpSession session,
                              Model model) throws JsonProcessingException {
        log.info("code: " + code);

        // 1. code를 이용하여 토큰을 받기위한 객체 생성
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String requestTokenUrl = "https://accounts.google.com/o/oauth2/token";

        // 2. POST 방식으로 토큰을 요청하기 위한 body 설정
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("grant_type", "authorization_code");
        body.add("redirect_uri", redirectUri);

        // 3. HTTP 요청 보내기(토큰 요청 보내기) - HttpEntity 객체로 포장
        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(body, headers);

        // 4. 토큰 요청 보낸뒤 ResponseEntity 객체로 받기
        ResponseEntity<String> response = rt.exchange(requestTokenUrl,
                                                        HttpMethod.POST,
                                                        googleTokenRequest,
                                                        String.class);

        log.info("response: " + response.getBody());

        // 5. HTTPBODY에 String 형태도로 받은 내용을 JsonNode로 변환 > key값으로 access_token을 받음
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String token = jsonNode.get("access_token").asText();
        log.info("token: " + token);

        // 6. 토큰을 이용하여 사용자 정보를 받아옴
        SnsProfile snsProfile = getGoogleProfile(token);
        LoginMember loginMember = naverLoginController.checkSnsProfile(snsProfile);

        // 7. SNS 연동계정 존재 여부 확인 (네이버 로그인 컨트롤러로 메소드 재활용)
        // 연동계정 정보가 존재하지 않을 경우 null 반환 + sns계정 정보 session 저장, 존재할 경우 연결된 계정 정보 반환
        if( naverLoginController.checkSnsProfile(getGoogleProfile(token)) == null ){
            // 연동계정 정보가 존재하지 않을 경우 회원가입 페이지로 이동
            session.setAttribute("snsProfile", snsProfile);
            model.addAttribute("msg",  "연동된 계정 정보가 없습니다. 회원가입을 부탁드립니다.");
            return "member/signup";
        } else {
            // 연동 계정 정보가 존재할 경우 로그인 처리
            session.setAttribute("loginUser", loginMember);
            return "redirect:/";
        }

    }

    /*
    step 4. 토큰을 이용하여 구글로 부터 사용자 정보를 받음
    */
    public SnsProfile getGoogleProfile(String token) throws JsonProcessingException {
        // 구글 프로필 정보 요청 URL
        String requestProfileUrl = "https://www.googleapis.com/oauth2/v1/userinfo";

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        // 2. HTTPBODY 설정 ( 넘길 정보가 없으므로 생략 )

        // 3. HTTP 요청을 보내기 위해 header와 body를 HttpEntity 객체로 포장
        HttpEntity googleProfileRequest = new HttpEntity(headers);

        // 4. RestTemplate 객체를 이용 HTTP 요청 보내기
        RestTemplate rt = new RestTemplate();

        // JsonNode 객체로 응답 데이터 받기
        JsonNode response = rt.exchange(requestProfileUrl,
                HttpMethod.GET,
                googleProfileRequest,
                JsonNode.class).getBody();

        log.info("response: " + response);

        // 받은 응답 데이터를 SNS Profile VO 객체로 변환
        SnsProfile googleProfile = new SnsProfile();

        googleProfile.setSnsId(response.get("id").asText());
        googleProfile.setEmail(response.get("email").asText());
        googleProfile.setNickName(response.get("name").asText());
        googleProfile.setType("google");

        return googleProfile;
    }

}
