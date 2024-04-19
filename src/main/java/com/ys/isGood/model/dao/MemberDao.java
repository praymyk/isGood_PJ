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
}
