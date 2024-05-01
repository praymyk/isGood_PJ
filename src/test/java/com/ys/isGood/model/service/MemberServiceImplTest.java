package com.ys.isGood.model.service;

import com.ys.isGood.model.service.member.MemberServiceImpl;
import com.ys.isGood.model.vo.member.Member;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired private MemberServiceImpl memberService;
    @Autowired private SqlSessionTemplate sqlSession;
    @Test
    void memberEnrollEnd() {
        Member member = new Member("userNo", "email.com", "1212","nickName", "1212", "M", "1111111", "DEFAULT","DEFAULT", "Y");      //    USER_NO	NUMBER
        //예상
        int expected = 0;

        //실제데이터
        int result = memberService.memberEnrollEnd(member);

        assertEquals(expected, result);
    }
}