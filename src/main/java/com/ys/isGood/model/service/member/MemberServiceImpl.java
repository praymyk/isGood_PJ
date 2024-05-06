package com.ys.isGood.model.service.member;

import com.ys.isGood.model.dao.member.MemberDao;
import com.ys.isGood.model.vo.member.*;
import com.ys.isGood.model.vo.sns.KakaoProfile;
import com.ys.isGood.model.vo.sns.SnsProfile;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private SqlSessionTemplate sqlSession;

    // 회원가입 처리 메소드
    @Override
    public int memberEnrollEnd(Member member) {
        return memberDao.meberEnrollEnd(member, sqlSession);
    }

    // 로그인 처리 메소드
    @Override
    public LoginMember memberLogin(Member member) {
        return memberDao.memberLogin(member, sqlSession);
    }

    // 이메일 중복 검사용 메소드
    @Override
    public int memberEmailCheck(Member member) {
        return memberDao.memberEmailCheck(member, sqlSession);
    }

    // 구독 게임 리스트 조회용 메소드
    @Override
    public ArrayList<SubscribeList> memberSubList(String userNo) {
        return memberDao.memberSubList(userNo, sqlSession);
    }

    // 구독 게임 리스트 순번 저장용 메소드
    @Override
    public int memberSubListSave(Subscribe subscribe) {
        return memberDao.memberSubListSave(subscribe, sqlSession);
    }

    // 마이페이지 프로필 이미지 등록용 메소드
    @Override
    public int insertProfileImg(ProfileImg profileImg) {
        return memberDao.insertProfileImg(profileImg, sqlSession);
    }

    // 마이페이지 프로필 이미지 출력용 메소드
    @Override
    public ProfileImg displayProfileImg(String userNo) {
        return memberDao.displayProfileImg(userNo, sqlSession);
    }

    // 마이페이지 프로필 이미지 등록여부 확인용 메소드
    @Override
    public ProfileImg checkProfileImg(ProfileImg profileImg) {
        return memberDao.checkProfileImg(profileImg, sqlSession);
    }

    // 마이페이지 프로필 이미지 업데이트 메소드
    @Override
    public int updateProfileImg(ProfileImg profileImg) {
        return memberDao.updateProfileImg(profileImg, sqlSession);
    }

    // 마이페이지 프로필 정보 수정용 메소드
    @Override
    public int updateNickName(Member member) {
        return memberDao.updateNickName(member, sqlSession);
    }

    // 마이페이지 연락처 정보 수정용 메소드
    @Override
    public int updatePhone(Member member) {
        return memberDao.updatePhone(member, sqlSession);
    }

    // 마이페이지 프로필 정보 수정 후 업데이트 된 정보 조회용 메소드
    @Override
    public LoginMember updatedMember(String userNo) {
        return memberDao.updatedMember(userNo, sqlSession);
    }

    // 계정 정지용 메소드
    @Override
    public int stopUserId(Member member) {
        return memberDao.stopUserId(member, sqlSession);
    }

    // SNS 연동계정 확인용 메소드
    @Override
    public SnsProfile checkSnsProfile(String snsEmail) {
        return memberDao.checkSnsProfile(snsEmail, sqlSession);
    }

    // SNS 연동 추가용 메소드
    @Override
    public int snsEnroll(SnsProfile snsProfile) {
        return memberDao.snsEnroll(snsProfile, sqlSession);
    }

    // SNS 연동 로그인용 메소드
    @Override
    public LoginMember memberSnsLogin(SnsProfile loadSnsProfile) {
        return memberDao.memberSnsLogin(loadSnsProfile, sqlSession);
    }

}
