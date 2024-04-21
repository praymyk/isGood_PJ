package com.ys.isGood.model.service;

import com.ys.isGood.model.dao.MemberDao;
import com.ys.isGood.model.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 아이디 중복 체크 메소드
    @Override
    public String memberIdCheck(String email) {
        return "";
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

    @Override
    public LoginMember updatedMember(String userNo) {
        return memberDao.updatedMember(userNo, sqlSession);
    }


}
