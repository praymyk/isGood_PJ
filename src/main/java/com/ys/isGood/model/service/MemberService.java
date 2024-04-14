package com.ys.isGood.model.service;

import com.ys.isGood.model.vo.Member;
import com.ys.isGood.model.vo.Subscribe;

import java.util.ArrayList;

public interface MemberService {

    // 회원가입 처리 메소드
    int memberEnrollEnd(Member member);

    // 로그인 처리 메소드
    Member memberLogin(Member member);

    // 아이디 중복 체크 메소드
    String memberIdCheck(String email);

    // 구독 게임 리스트 조회용 메소드
    ArrayList<Subscribe> memberSubList(String userNo);
}
