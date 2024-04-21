package com.ys.isGood.model.dao;

import com.ys.isGood.model.vo.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MemberDao {

    // 회원가입 처리 메소드
    public int meberEnrollEnd(Member member, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("memberMapper.memberEnrollEnd", member);
    }

    // 로그인 처리 메소드
    public LoginMember memberLogin(Member member, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("memberMapper.memberLogin", member);
    }

    // 마이페이지 구독게임 리스트 조회 메소드
    public ArrayList<SubscribeList> memberSubList(String userNo, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("memberMapper.memberSubList", userNo);
    }

    // 마이페이지 구독게임 리스트 순번 저장 메소드
    public int memberSubListSave(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.update("memberMapper.memberSubListUpdate", subscribe);
    }

    // 마이페이지 프로필이미지 등록용 메소드(insert)
    public int insertProfileImg(ProfileImg profileImg, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("memberMapper.insertProfileImg", profileImg);
    }

    // 마이페이지 프로필 이미지 출력용 메소드
    public ProfileImg displayProfileImg(String userNo, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("memberMapper.displayProfileImg", userNo);
    }

    // 마이페이지 프로필 이미지 등록여부 확인용 메소드
    public ProfileImg checkProfileImg(ProfileImg profileImg, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("memberMapper.checkProfileImg", profileImg);
    }

    // 마이페이지 프로필 이미지 업데이트 메소드
    public int updateProfileImg(ProfileImg profileImg, SqlSessionTemplate sqlSession) {
        return sqlSession.update("memberMapper.updateProfileImg", profileImg);
    }

    // 마이페이지 프로필 닉네임 수정용 메소드
    public int updateNickName(Member member, SqlSessionTemplate sqlSession) {
        return sqlSession.update("memberMapper.updateNickName", member);
    }

    // 마이페이지 프로필 정보 수정 후 업데이트 된 정보 조회용 메소드
    public LoginMember updatedMember(String userNo, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("memberMapper.updatedMember", userNo);
    }
}
