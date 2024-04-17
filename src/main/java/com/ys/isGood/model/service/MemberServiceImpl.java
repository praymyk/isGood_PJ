package com.ys.isGood.model.service;

import com.ys.isGood.model.dao.MemberDao;
import com.ys.isGood.model.vo.Member;
import com.ys.isGood.model.vo.Subscribe;
import com.ys.isGood.model.vo.SubscribeList;
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
    public Member memberLogin(Member member) {
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

    @Override
    public int memberSubListSave(Subscribe subscribe) {
        return memberDao.memberSubListSave(subscribe, sqlSession);
    }

}
