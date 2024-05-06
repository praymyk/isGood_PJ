package com.ys.isGood.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.isGood.model.service.member.MemberService;
import com.ys.isGood.model.vo.member.LoginMember;
import com.ys.isGood.model.vo.sns.KakaoProfile;
import com.ys.isGood.model.vo.sns.NaverTokenResponse;
import com.ys.isGood.model.vo.sns.SnsProfile;
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

@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-API-KEY.properties")
})

public class NaverLoginController {

    // 네이버 로그인 기능 구현을 위한 변수
    @Value("${NAVER-CLIENT-ID}")
    private String clientId;
    @Value("${NAVER-CLIENT-SECRET}")
    private String clientSecret;

    @Autowired
    MemberService memberService;


    /*
    네이버 로그인 처리 메소드 ( 토큰 요청 )
    REST API 방식이므로 공통화 시도
    */
    @GetMapping("/naverLogin.me")
    public String NaverLogin(@RequestParam("code") String code,
                             HttpSession session,
                             Model model) throws JsonProcessingException {

        // 1. code를 이용하여 토큰을 받아옴
        String token = getTokens(code);

        // 2. 토큰 값으로 sns 계정 불러오기
        SnsProfile snsProfile = getNaverProfile(token);

        // 3. sns 계정과 연동된 회원이 존재하는지 확인
        // 3-1. sns 계정으로 회원가입이 되어있는 경우 로그인 처리
        LoginMember loginMember = checkSnsProfile(snsProfile);

        if(loginMember == null){
            // 3-2. SNS 계정으로 회원가입이 되어있지 않은 경우( null 반환 됐을때) 회원가입 페이지로 이동
            //     SNS 계정으로 로그인 시도를 한 경우 SNS 계정 연동 가입이 됨 (snsProfile DB 데이터가 존재하면 연동 계정을 의미)

            // @paran snsProfile : 회원 가입 시 sns 계정아 연동되도록 sns계정 정보 세션에 저장
            session.setAttribute("snsProfile", snsProfile);

            model.addAttribute("msg", "연동된 계정 정보가 없습니다. 회원가입을 부탁드립니다.");
            return "member/signup";
        } else {
            // 5-3 userNo가 null이 아닌 경우 연동된 계정으로 로그인 진행
            //     카카오 계정 로그아웃이 가능하도록 accessToken을 세션에 저장
            session.setAttribute("loginUser", loginMember);
            session.setAttribute("snsAccessToken", token);

            return "redirect:/";
        }
    }

    /*
    토큰 요청 메소드
    */
    String getTokens(String code) {

        String requestTokenUrl = "https://nid.naver.com/oauth2.0/token";

        // sns로그인 API에 인증 코드 받고 토큰 요청
        // POST 방식으로 코드 전달 후 토큰을 받아옴

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        // api 요구 조건에 따라 content-type 설정
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 2. HTTPBODY 설정 (key=value 형태로 전달)
        // sns api 요구 조건에 따라 body 설정
        // Spring Framework의 클래스인 MultiValueMap을 사용하여 key=value 형태로 전달
        // 순서가 유지되는 특징의 Map
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);

        // 3. HTTP 요청 보내기
        // HTTP 요청을 보내기 위한 요청 바디(body)와 헤더(headers)를 설정하는 객체
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);

        // HTTP 요청을 보내고 응답을 받기 위한 객체 Spring에서 제공하는 HTTP 클라이언트
        RestTemplate rt = new RestTemplate();

        // ResponseEntity: HTTP 응답 내용을 담을 객체
        // exchange() 메서드를 사용하여 HTTP 요청을 보내기
        ResponseEntity<NaverTokenResponse> response = rt.exchange(
                requestTokenUrl,  // 요청할 URL
                HttpMethod.POST,  // HTTP 메서드: POST
                tokenRequest,  // 요청 바디와 헤더가 담긴 HttpEntity 객체
                NaverTokenResponse.class);  // 응답의 타입 > 원하는 형식 지정 가능

        // 4. 응답( NaverTokenResponse ) 데이터 받기
        // 받은 응답 데이터를 문자열로 저장
        NaverTokenResponse responseBody = response.getBody();
        log.info("responseBody: {}", responseBody);
        return responseBody.getAccessToken();
    }

    // 계정 정보 불러오기
    public SnsProfile getNaverProfile(String token) throws JsonProcessingException {
        String requestProfileUrl = "https://openapi.naver.com/v1/nid/me";

        // 1. HTTPHEADER 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 2. HTTPBODDY 설정 ( 넘길 정보가 없으므로 생략 )
        // 3. HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                requestProfileUrl,
                HttpMethod.POST,
                naverProfileRequest,
                String.class
        );

        // 4. 응답( JSON ) 데이터 받기
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        log.info("jsonNode: {}", jsonNode);

        // 받은 응답 데이터를 VO 객체로 변환
        String id = jsonNode.get("response").get("id").asText();
        String email = jsonNode.get("response").get("email").asText();
        String nickname = jsonNode.get("response").get("nickname").asText();

        SnsProfile naverProfile = new SnsProfile(id, email, nickname);
        log.info("naverProfile: {}", naverProfile);

        return naverProfile;
    }

    /*
    SNS 계정 연동 확인 및 로그인/회원가입 처리 메소드
    */
    public LoginMember checkSnsProfile(SnsProfile snsProfile) {

        // 1. 연동 계정 정보 확인
        // DB에서 카카오 계정 정보 확인 ( 이메일로 확인 )
        SnsProfile loadSnsProfile = memberService.checkSnsProfile(snsProfile.getEmail());

        // 2. 연동 계정 정보가 존재하지 않을 경우 null 반환
        if(loadSnsProfile == null){
            return null;
        } else { // 3. 연동된 계정 정보가 존재할 경우 연결된 계정 정보 불러오기

            LoginMember loginMember = memberService.memberSnsLogin(loadSnsProfile);
            return loginMember;
        }
    }

}