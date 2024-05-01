package com.ys.isGood.controller.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.isGood.model.service.member.MemberServiceImpl;
import com.ys.isGood.model.vo.member.*;
import com.ys.isGood.model.vo.sns.KakaoProfile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j

// 프로퍼티 파일을 여러개 사용하기 위한 어노테이션
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-API-KEY.properties")
})
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;
    @Value("${requestDisplayProfileImg.path}")
    private String profileImgPath;
    // 비밀번호 암호화를 위한 객체
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 카카오 로그인 기능 구현을 위한 변수
    @Value("${KAKAO-REDIRECT-URI}")
    private String kakaoRedirectUri;
    @Value("${KAKAO-RESTAPI-KEY}")
    private String kakaoRestApiKey;

    // 회원가입 페이지로 이동
    @RequestMapping("/enrollForm.me")
    public String memberEnroll(){
        return "member/signup";
    }

    // 회원가입 처리 메소드
    // @param member : 회원가입 정보를 담은 객체
    @PostMapping("/enroll.me" )
    public String memberEnrollEnd(Member member, RedirectAttributes redirectAttributes){

        // 비밀번호 암호화
        String encPwd = passwordEncoder.encode(member.getUserPwd());
        log.info("암호화된 비밀번호 : " + encPwd);

        member.setUserPwd(encPwd);
        log.info("암호화된 회원정보 : " + member);

        int result = memberService.memberEnrollEnd(member);


        if(result > 0) {
            redirectAttributes.addFlashAttribute("msg", "회원가입 성공");
        } else {
            redirectAttributes.addFlashAttribute("msg", "회원가입 실패");
        }

        return "redirect:/";
    }

    // 회원가입 이메일 중복 체크 메소드
    @PostMapping("/emailCheck.me")
    @ResponseBody
    public HashMap<String, String> memberEmailCheck(Member member){
        HashMap<String, String> isDuplicate = new HashMap<>();

        log.info("이메일 중복 체크 : " + member);
        int result = memberService.memberEmailCheck(member);
        log.info("중복체크 결과 : " + result);
        if(result == 0){
            isDuplicate.put("check", "YYYY");
            return isDuplicate;
        } else {
            isDuplicate.put("check", "NNNN");
            return isDuplicate;
        }
    }

    // 로그인 페이지로 이동
    @RequestMapping("/loginForm.me")
    public String memberLogin(){
        return "member/login";
    }

    // 로그인 처리 메소드
    // @param member : 로그인 정보를 담은 객체
    @PostMapping("/login.me")
    public String memberLogin(Member member,
                              @RequestParam(value = "rememberId", required = false) String rememberId,
                              HttpSession session,
                              Model model){

        LoginMember loginUser = memberService.memberLogin(member);
        // 1. 로그인 이메일 주소가 존재하지 않는 경우
        if(loginUser == null){
            model.addAttribute("msg", "이메일 혹은 비밀번호를 다시 확인해주세요.");
            return "member/login";
        }
        // 2. 비밀번호 일치 확인
        if(passwordEncoder.matches(member.getUserPwd(),loginUser.getUserPwd())){
            session.setAttribute("loginUser", loginUser);
            return "redirect:/";
        }
        // 3. 비밀번호 불일치
        model.addAttribute("msg", "이메일 혹은 비밀번호를 다시 확인해주세요.");
        return "member/login";

//        //테스트용 맴버 생성
//        Member testMember = new Member("2",
//                "test@naver.com",
//                "1234",
//                "testNickName",
//                "testBirthday",
//                "testGender",
//                "testPhone",
//                "testEnrollDate",
//                "testModifyDate",
//                "testStatus");
//
//
//        /*
//        *  로그인 기능 정상 구현시 매개변수 testMember 를 member로 변경!!
//        */
//        LoginMember loginUser = memberService.memberLogin(testMember);
//        log.info("로그인 정보(테스트용 맴버를 로그인 메서드에서 생성중): " + loginUser);
//        session.setAttribute("loginUser", loginUser);
//
//        return "redirect:/";
    }

    // 카카오 로그인 처리 메소드 ( 토큰 요청 )
    // API key 값을 감추기 위해 javaScript에서 처리하지 않고 java에서 처리
    @GetMapping("/kakaoLogin.me")
    public String kakaoCallbackToken(@RequestParam("code") String code) throws JsonProcessingException {

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

        return null;
    }

    // 카카오 로그인 처리 메소드 ( 토큰 정보를 이용하여 사용자 정보 요청 )
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
        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();

        return jsonNode;
    }

    // 로그아웃 처리 메소드
    @GetMapping("/logout.me")
    public String memberLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    // 마이페이지 이동
    @GetMapping("/mypage.me")
    public String memberMyPage(){
        return "member/myPage";
    }

    // 마이페이지 프로필 이미지 불러오기
    @GetMapping("mypage.me/{userNo}/displyProfileImg")
    @ResponseBody
    public String displayProfileImg(@PathVariable String userNo) throws IOException {

        // 1. 프로필 이미지정보를 DB에서 불러오기
        ProfileImg profileImg = memberService.displayProfileImg(userNo);

        // 2. VO 클래스로 부터 경로, 파일명을 받기
        // @pimgPath : 파일 경로
        // @changeName : 파일명.확장자
        profileImgPath = profileImgPath.replace("*", "");
        profileImgPath = profileImgPath.replace("/", File.separator);

        // 등록된 이미지가 없을 경우 기본 프로필 이미지 출력
        String pimgPath = profileImgPath + "default" + File.separator;
        String changeName = "default.jpg";

        // 등록된 이미지가 있을 경우 DB 이미지 정보로 변경
        if(profileImg != null) {
            pimgPath =  profileImgPath + userNo + File.separator;
            changeName = profileImg.getChangeName();

        }

        // 3. 프론트로 전달할 파일 객체 생성
        ProfileImg displayProfileImg = new ProfileImg();
        displayProfileImg.setPimgPath(pimgPath);
        displayProfileImg.setChangeName(changeName);

        // 4. json 형태로 변환하여 전달
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(displayProfileImg);

        return jsonStr;
    }

    // 마이페이지 프로필 정보(닉네임) 수정용 메소드
    @PostMapping("/updateNickName")
    @ResponseBody
    public HashMap<String, String> updateNickName(Member member, HttpSession session){

            HashMap<String, String> msg = new HashMap<>();
            log.info("프로필 수정 정보 : " + member);

            int result = memberService.updateNickName(member);

            if(result > 0){
                msg.put("msg", "사용자 닉네임 수정 완료");
                // 회원정보 수정 후 업데이트 된 맴버 정보를 DB로부터 다시 받아 세션에 저장
                LoginMember updatedMember = memberService.updatedMember(member.getUserNo());
                session.setAttribute("loginUser", updatedMember);

            } else {
                msg.put("msg", "사용자 닉네임 수정 실패");
            }
            return msg;
    }

    // 마이페이지 연락처 정보 수정용 메소드
    @PostMapping("/updatePhone")
    @ResponseBody
    public HashMap<String, String> updatePhone(Member member, HttpSession session){

        HashMap<String, String> msg = new HashMap<>();

        int result = memberService.updatePhone(member);

        log.info("연락처 수정 정보 : " + member);

        if(result > 0){
            msg.put("msg", "사용자 연락처 수정 완료");
            // 회원정보 수정 후 업데이트 된 맴버 정보를 DB로부터 다시 받아 세션에 저장
            LoginMember updatedMember = memberService.updatedMember(member.getUserNo());
            session.setAttribute("loginUser", updatedMember);

        } else {
            msg.put("msg", "사용자 닉네임 수정 실패");
        }
        return msg;
    }

    // 계정 정지용 메소드
    @PostMapping("/stopUserId")
    @ResponseBody
    public HashMap<String, String> stopUserId(Member member, HttpSession session){

        HashMap<String, String> msg = new HashMap<>();

        int result = memberService.stopUserId(member);

        if(result > 0){
            msg.put("msg", "사용자 계정 정지 완료");
            // 로그아웃 처리
            session.invalidate();

        } else {
            msg.put("msg", "사용자 계정 정지 실패");
        }
        return msg;
    }

    // 마이페이지 - 구독 게임 리스트 조회용 메소드
    @GetMapping("/subList.me/{userNo}")
    @ResponseBody
    public ArrayList<SubscribeList> memberSubList(@PathVariable String userNo){

        //log.info("구독 리스트(ajax) 출력 : " + test);
        return memberService.memberSubList(userNo);
    }

    // 마이페이지 - 구독 게임 저장용 메소드
    @PostMapping("updateSublist")
    @ResponseBody
    public String memberSubListSave(@RequestParam(value="userNo[]") String[] userNo,
                                    @RequestParam(value="subNoUp[]")int[] subNoUp,
                                    @RequestParam(value="subNo[]")int[] subNo,
                                    @RequestParam(value="gameNo[]")String[] gameNo
                                    ){

        // 변경된 순서 정보를 받아 순차적으로 업데이트
        for(int i = 0; i < userNo.length; i++){

            Subscribe subscribe = new Subscribe(userNo[i], gameNo[i], subNo[i], subNoUp[i]);

            int result = memberService.memberSubListSave(subscribe);
        }

        return "순서변경 성공";
    }

}
