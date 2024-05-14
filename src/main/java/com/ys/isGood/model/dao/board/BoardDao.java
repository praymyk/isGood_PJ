package com.ys.isGood.model.dao.board;

import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.member.Subscribe;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BoardDao {

    // 게시판 리스트 조회용 메소드
    public ArrayList<Board> boardList(String gameCode, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", gameCode);   }

    // 게시글 상세 보기용 메소드
    public Board selectBoard(String boardNo, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
    }

    // 게임 정보 조회용 메소드
    public Game selectGame(String gameCode, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.selectGame", gameCode);
    }

    // 게임 구독용 메소드
    public int gameSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("boardMapper.gameSubscribe", subscribe);
    }

    // 선택 게임 구독 상태 조회용 메소드
    public int isSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.isSubscribe", subscribe);
    }

    // 게임 구독 취소용 메소드
    public int gameUnSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.delete("boardMapper.gameUnSubscribe", subscribe);
    }
}
