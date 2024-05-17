package com.ys.isGood.model.dao.board;

import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.member.Subscribe;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BoardDao {

    // 게시판 리스트 조회용 메소드
    public ArrayList<Board> boardList(String gameCode, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", gameCode);   }
    // 게시판 리스트 조회용(페이징) 메소드


    // 게시글 상세 보기용 메소드
    public Board selectBoard(String boardNo, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
    }

    // 게임 정보 조회용 메소드
    public Game selectGame(String gameCode, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.selectGame", gameCode);
    }

    // 선택 게임 구독 상태 조회용 메소드
    public int isSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.isSubscribe", subscribe);
    }
    // 게임 구독용 메소드
    public int gameSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("boardMapper.gameSubscribe", subscribe);
    }
    // 게임 구독 취소용 메소드
    public int gameUnSubscribe(Subscribe subscribe, SqlSessionTemplate sqlSession) {
        return sqlSession.delete("boardMapper.gameUnSubscribe", subscribe);
    }

    // 게시글 작성용 메소드
    public int boardWrite(Board board, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("boardMapper.boardWrite", board);
    }
    // 등록 게시글 번호 조회용 메소드
    public String selectBoardNum(Board board, SqlSessionTemplate sqlSession) {
        return sqlSession.selectOne("boardMapper.selectBoardNum", board);
    }

    // 게시글 수정용 메소드
    public int boardModify(Board board, SqlSessionTemplate sqlSession) {
        return sqlSession.update("boardMapper.boardModify", board);
    }

    // 게시글 조회수 증가용 메소드
    public int boardCount(String boardNo, SqlSessionTemplate sqlSession) {
        return sqlSession.update("boardMapper.updateBoardCount", boardNo);
    }

    // 게시글 삭제용 메서드
    public int boardDelete(String boardNo, SqlSessionTemplate sqlSession) {
        return sqlSession.update("boardMapper.boardDelete", boardNo);
    }

    public Page<Board> newBoardList(String gameCode, SqlSessionTemplate sqlSession) {
        return null;
    }
}
