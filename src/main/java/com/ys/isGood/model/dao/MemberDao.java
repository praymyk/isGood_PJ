package com.ys.isGood.model.dao;

import com.ys.isGood.model.vo.Member;
import com.ys.isGood.model.vo.Subscribe;
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
    public Member memberLogin(Member member, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("memberMapper.memberLogin", member);
    }

    public ArrayList<Subscribe> membersubList(String userNo, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("memberMapper.memberSubList", userNo);
    }
}
