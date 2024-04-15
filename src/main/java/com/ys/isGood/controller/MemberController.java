package com.ys.isGood.controller;

import com.ys.isGood.model.service.MemberService;
import com.ys.isGood.model.service.MemberServiceImpl;
import com.ys.isGood.model.vo.Member;
import com.ys.isGood.model.vo.Subscribe;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

        log.info("로그인 정보 입력: " + member);
        Member loginUser = memberService.memberLogin(member);
        log.info("로그인 정보 출력: " + loginUser);

        //session.setAttribute("loginUser", loginUser);

        //테스트용 맴버 생성
        Member testMember = new Member("2",
                "testProfileImg",
                "testEmail",
                "testPwd",
                "testNickName",
                "testBirthday",
                "testGender",
                "testPhone", "testEnrollDate", "testModifyDate", "testStatus");

        session.setAttribute("loginUser", testMember);

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

    // 마이페이지 - 구독 게임 리스트 조회용 메소드
    @GetMapping("/subList.me/{userNo}")
    @ResponseBody
    public ArrayList<Subscribe> memberSubList(@PathVariable String userNo){

        log.info("구독 리스트(ajax) 받은 userNo : " + userNo);
        ArrayList<Subscribe> test = memberService.memberSubList(userNo);
        log.info("구독 리스트(ajax) 출력 : " + test);
        return memberService.memberSubList(userNo);
    }

}
