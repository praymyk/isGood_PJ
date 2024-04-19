package com.ys.isGood.model.service;

import com.ys.isGood.model.vo.*;

import java.util.ArrayList;

public interface MemberService {

    // 회원가입 처리 메소드
    int memberEnrollEnd(Member member);

    // 로그인 처리 메소드
    LoginMember memberLogin(Member member);

    // 아이디 중복 체크 메소드
    String memberIdCheck(String email);

    // 구독 게임 리스트 조회용 메소드
    ArrayList<SubscribeList> memberSubList(String userNo);

    // 구독 게임 리스트 순번 저장용 메소드
    int memberSubListSave(Subscribe subscribe);

    // 마이페이지 프로필 이미지 업데이트용 메소드
    int updateProfileImg(ProfileImg profileImg);
}
