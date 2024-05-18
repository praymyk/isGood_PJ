package com.ys.isGood.model.service.board;

import com.ys.isGood.common.model.vo.PageInfo;
import com.ys.isGood.model.dao.board.BoardDao;
import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.member.Subscribe;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    SqlSessionTemplate sqlSession;

    // 게임 정보 조회용 메서드
    @Override
    public Game selectGame(String gameCode) {
        return boardDao.selectGame(gameCode, sqlSession);
    }

    // 게시글 조회수 증가용 메서드
    @Override
    public int boardCount(String boardNo) {
        return boardDao.boardCount(boardNo, sqlSession);
    }

    // 게시글 상세보기 용 메소드
    @Override
    public Board selectBoard(String boardNo) {
        return boardDao.selectBoard(boardNo, sqlSession);
    }

    // 게임 구독용 메서드
    @Override
    public int gameSubscribe(Subscribe subscribe) {
        return boardDao.gameSubscribe(subscribe, sqlSession);
    }

    // 게임 구독 취소용 메서드
    @Override
    public int gameUnSubscribe(Subscribe subscribe) {
        return boardDao.gameUnSubscribe(subscribe, sqlSession);
    }

    // 게시글 작성용 메서드
    @Override
    public int boardWrite(Board board) {
        return boardDao.boardWrite(board, sqlSession);
    }
    // 등록 게시글 번호 조회용 메서드
    @Override
    public String selectBoardNum(Board board) {
        return boardDao.selectBoardNum(board, sqlSession);
    }

    // 게시글 수정용 메서드
    @Override
    public int boardModify(Board board) {
        return boardDao.boardModify(board, sqlSession);
    }

    // 게시글 삭제용 메소드
    @Override
    public int boardDelete(String boardNo) {
        return boardDao.boardDelete(boardNo, sqlSession);
    }

    // 선택 게임 구독상태 조회용 메서드
    @Override
    public int isSubscribe(Subscribe subscribe) {
        return boardDao.isSubscribe(subscribe, sqlSession);
    }

    // 게시글 리스트 조회용(페이징)
    @Override
    public ArrayList<Board> selectBoardList(PageInfo pi, String gameCode) {
        return boardDao.selectBoardList(pi, gameCode, sqlSession);
    }

    // 게시글 페이징 처리를 위한 게시글 수 조회용 메서드
    public int selectboardListCount(String gameCode) {
        return boardDao.selectBoardListCount(gameCode, sqlSession);
    }
}
