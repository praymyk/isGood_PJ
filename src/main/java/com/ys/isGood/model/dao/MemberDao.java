package com.ys.isGood.model.dao;

import com.ys.isGood.model.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    // 회원가입 처리 메소드
    public int meberEnrollEnd(Member member, SqlSession sqlSession) {
        return sqlSession.insert("memberMapper.memberEnrollEnd", member);
    }

}
