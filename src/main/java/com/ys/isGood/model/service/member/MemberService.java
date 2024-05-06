package com.ys.isGood.model.service.member;

import com.ys.isGood.model.vo.member.*;
import com.ys.isGood.model.vo.sns.KakaoProfile;
import com.ys.isGood.model.vo.sns.SnsProfile;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

public interface MemberService {

    // 회원가입 처리 메소드
    int memberEnrollEnd(Member member);

    // 로그인 처리 메소드
    LoginMember memberLogin(Member member);

    // 이메일 중복 체크 메소드
    int memberEmailCheck(Member member);

    // 구독 게임 리스트 조회용 메소드
    ArrayList<SubscribeList> memberSubList(String userNo);

    // 구독 게임 리스트 순번 저장용 메소드
    int memberSubListSave(Subscribe subscribe);

    // 마이페이지 프로필 이미지 등록용 메소드
    int insertProfileImg(ProfileImg profileImg);

    // 마이페이지 프로필 이미지 출력용 메소드
    ProfileImg displayProfileImg(String userNo);

    // 마이페이지 프로필 이미지 등록여부 확인용 메소드
    ProfileImg checkProfileImg(ProfileImg profileImg);

    // 마이페이지 프로필 이미지 업데이트 메소드
    int updateProfileImg(ProfileImg profileImg);

    // 마이페이지 프로필 정보 수정용 메소드
    int updateNickName(Member member);

    // 마이페이지 연락처 정보 수정용 메소드
    int updatePhone(Member member);

    // 마이페이지 프로필 정보 수정 후 업데이트 된 정보 조회용 메소드
    LoginMember updatedMember(String userNo);

    // 계정 정지용 메소드
    int stopUserId(Member member);

    // sns 연동게정 확인용 메소드
    SnsProfile checkSnsProfile(String snsEmail);

    // sns 연동 정보 추가용 메소드
    int snsEnroll(SnsProfile snsProfile);

    // sns 연동 로그인 용 메소드
    LoginMember memberSnsLogin(SnsProfile loadSnsProfile);
}
