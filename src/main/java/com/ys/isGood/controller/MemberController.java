package com.ys.isGood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.jconsole.JConsoleContext;
import com.ys.isGood.config.AuthConfig;
import com.ys.isGood.model.service.MemberServiceImpl;
import com.ys.isGood.model.vo.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;
    @Value("${requestDisplayProfileImg.path}")
    private String profileImgPath;
    @Autowired
    private UploadController uploadController;

    // 비밀번호 암호화를 위한 객체
    @Autowired
    private PasswordEncoder passwordEncoder;

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
            log.info("업데이트 된 맴버 정보 : " + updatedMember);

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
