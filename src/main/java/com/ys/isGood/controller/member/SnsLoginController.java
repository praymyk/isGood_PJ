package com.ys.isGood.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.isGood.model.service.member.MemberService;
import com.ys.isGood.model.vo.member.LoginMember;
import com.ys.isGood.model.vo.member.Member;
import com.ys.isGood.model.vo.sns.KakaoProfile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j

// 프로퍼티 파일을 여러개 사용하기 위한 어노테이션
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-API-KEY.properties")
})
public class SnsLoginController {

    // 카카오 로그인 기능 구현을 위한 변수
    @Value("${KAKAO-REDIRECT-URI}")
    private String kakaoRedirectUri;
    @Value("${KAKAO-RESTAPI-KEY}")
    private String kakaoRestApiKey;

    @Autowired
    MemberService memberService;


    // 카카오 로그인 처리 메소드 ( 토큰 요청 )
    // API key 값을 감추기 위해 javaScript에서 처리하지 않고 java에서 처리
    @GetMapping("/kakaoLogin.me")
    public String kakaoCallbackToken(@RequestParam("code") String code,
                                     HttpSession session,
                                     Model model) throws JsonProcessingException {

        // 카카오로그인 API에 인증 코드 받고 토큰 요청
        // POST 방식으로 코드 전달 후 토큰을 받아옴

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        // 카카오 api 요구 조건에 따라 content-type 설정
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 2. HTTPBODY 설정 (key=value 형태로 전달)
        // 카카오 api 요구 조건에 따라 body 설정
        // grant_type(authorization_code로 고정), client_id(앱 REST API 키), redirect_uri(인가 코드가 리다이렉트된 URI), grant_type(code)
        // Spring Framework의 클래스인 MultiValueMap을 사용하여 key=value 형태로 전달
        // 순서가 유지되는 특징의 Map
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoRestApiKey);
        body.add("redirect_uri", kakaoRedirectUri);
        body.add("code", code);

        // 3. HTTP 요청 보내기
        // HTTP 요청을 보내기 위한 요청 바디(body)와 헤더(headers)를 설정하는 객체
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);

        // HTTP 요청을 보내고 응답을 받기 위한 객체 Spring에서 제공하는 HTTP 클라이언트
        RestTemplate rt = new RestTemplate();

        // ResponseEntity: HTTP 응답 내용을 담을 객체
        // exchange() 메서드를 사용하여 HTTP 요청을 보내기
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",  // 요청할 URL
                HttpMethod.POST,  // HTTP 메서드: POST
                kakaoTokenRequest,  // 요청 바디와 헤더가 담긴 HttpEntity 객체
                String.class);  // 응답의 타입: 문자열(String)

        // 4. 응답( JSON ) 데이터 받기
        // 받은 응답 데이터를 문자열로 저장
        String responseBody = response.getBody();

        // 받은 응답 데이터를 JSON 형태로 파싱하기 위해 ObjectMapper 객체를 생성
        // ObjectMapper는 JSON 데이터를 자바 객체로 변환하거나, 자바 객체를 JSON 형태로 변환하는 데 사용
        ObjectMapper objectMapper = new ObjectMapper();
        //readTree() 메서드를 사용하여 JSON 형식의 문자열을 JsonNode 객체로 변환
        //JsonNode는 JSON 데이터의 노드를 나타내며, 이를 통해 데이터에 접근할 수 있다.
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String token = jsonNode.get("access_token").asText();

        // 토큰 번호로 카카오계정 정보 불러오기
        KakaoProfile kakaoProfile = getKakaoProfile(token);
        kakaoProfile.setType("kakao");

        // 5. 카카오 계정으로 연동한 회원이 존재하는지 확인
        // 5-1. 카카오 계정으로 회원가입이 되어있는 경우 로그인 처리
        LoginMember result = checkSnsProfile(kakaoProfile);

        if(result.getUserNo() == null){
            // 5-2. 카카오 계정으로 회원가입이 되어있지 않은 경우(result에 회원 번호 정보가 없을 때) 회원가입 페이지로 이동
            //     SNS 계정으로 로그인 시도를 한 경우 SNS 계정 연동 가입이 됨 (kaoProfile DB 데이터가 존재하면 연동 계정)

            // @paran kaoProfile : sns 계정 정보
            session.setAttribute("kakaoProfile", kakaoProfile);

            model.addAttribute("msg", "연동된 계정 정보가 없습니다. 회원가입을 부탁드립니다.");
            return "member/signup";
        } else {
            // 5-3 userNo가 null이 아닌 경우 연동된 계정으로 로그인 진행
            //     카카오 계정 로그아웃이 가능하도록 accessToken을 세션에 저장
            session.setAttribute("loginUser", result);
            session.setAttribute("snsAccessToken", token);

            return "redirect:/";
        }
    }

    // 카카오 로그인 요청 메소드 ( 토큰 정보를 이용하여 사용자 정보 요청 )
    public KakaoProfile getKakaoProfile(String token) throws JsonProcessingException {

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 2. HTTPBODDY 설정 ( 넘길 정보가 없으므로 생략 )
        // 3. HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        // 4. 응답( JSON ) 데이터 받기
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        // 받은 응답 데이터를 VO 객체로 변환
        Long id = jsonNode.get("id").asLong();
        // 카카오 애플리케이션 설정에서 이메일 정보를 동의 받지 않았을 경우 null 반환 ( 카카오 애플리케이션 설정에서 동의 받도록 해야함 )
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

        KakaoProfile kakaoProfile = new KakaoProfile(id, email, nickname);

        return kakaoProfile;
    }

    // 카카오 연동 계정 확인 및 로그인/회원가입 처리 메소드
    public LoginMember checkSnsProfile(KakaoProfile kakaoProfile) {

        // 1. 카카오 계정 정보 확인
        // DB에서 카카오 계정 정보 확인 ( 이메일로 확인 )
        KakaoProfile loadKakaoProfile = memberService.checkSnsProfile(kakaoProfile.getEmail());

        // 2. 연동 계정 정보가 존재하지 않을 경우 회원 가입 유도 -> 회원가입 페이지에 넘길 회원 정보 가공
        if(loadKakaoProfile == null){
            LoginMember enrollMember = new LoginMember();
            enrollMember.setEmail(kakaoProfile.getEmail());
            enrollMember.setNickName(kakaoProfile.getNickName());

            // 회원가입 페이지에 넘길 회원 정보 반환
            return enrollMember;

            } else { // 3. 연동된 계정 정보가 존재할 경우 연결된 계정 정보 불러오기

            LoginMember loginMember = memberService.memberSnsLogin(loadKakaoProfile);

            return loginMember;
        }
    }

    // 카카오 로그아웃 처리 메소드
    public void SnsLogout(String accessToken) throws JsonProcessingException {

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        // 2. BODY 설정 ( 넘길 정보가 없으므로 생략 )

        // 3. HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoLogoutRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v1/user/logout",
                HttpMethod.POST,
                kakaoLogoutRequest,
                String.class
        );

        // responseBody에 있는 처리 결과를 확인
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
    }
}
