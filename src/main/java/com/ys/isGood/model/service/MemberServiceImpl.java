package com.ys.isGood.model.service;

import com.ys.isGood.model.dao.MemberDao;
import com.ys.isGood.model.vo.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
