package com.ys.isGood.controller;

import com.ys.isGood.model.service.MemberServiceImpl;
import com.ys.isGood.model.vo.*;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    // 회원가입 페이지로 이동
    @RequestMapping("/enrollForm.me")
    public String memberEnroll(){
        return "member/signup";
    }

    // 회원가입 처리 메소드
    // @param member : 회원가입 정보를 담은 객체
    @PostMapping("/enroll.me" )
    public String memberEnrollEnd(Member member, Model model){

        log.info("회원가입 정보 : " + member);

        int result = memberService.memberEnrollEnd(member);

        log.info("회원 가입 결과 : " + result);

        if(result > 0) {
            model.addAttribute("msg", "회원가입 성공");
        } else {
            model.addAttribute("msg", "회원가입 실패");
        }

        return "redirect:/";
    }

    // 로그인 페이지로 이동
    @RequestMapping("/loginForm.me")
    public String memberLogin(){
        return "member/login";
    }

    // 로그인 처리 메소드
    // @param member : 로그인 정보를 담은 객체
    @PostMapping("/login.me")
    public String memberLogin(Member member, HttpSession session){


        //테스트용 맴버 생성

        Member testMember = new Member("2",
                "test@naver.com",
                "1234",
                "testNickName",
                "testBirthday",
                "testGender",
                "testPhone",
                "testEnrollDate",
                "testModifyDate",
                "testStatus");

        // 매개변수 수정 필요
        LoginMember loginUser = memberService.memberLogin(testMember);
        log.info("로그인 정보(테스트용 맴버를 로그인 메서드에서 생성중): " + loginUser);
        session.setAttribute("loginUser", loginUser);

        return "redirect:/";
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
    public ProfileImg displayProfileImg(@PathVariable String userNo){

        // 프로필 이미지 불러오기 단계에서 작업 중단
        ProfileImg profileImg = memberService.displayProfileImg(userNo);

        log.info("프로필 이미지 정보 : " + profileImg);

        return profileImg;
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

        return "Ajax 테스트";
    }

}
