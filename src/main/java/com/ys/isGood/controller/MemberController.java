package com.ys.isGood.controller;

import com.ys.isGood.model.service.MemberService;
import com.ys.isGood.model.service.MemberServiceImpl;
import com.ys.isGood.model.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

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


}
