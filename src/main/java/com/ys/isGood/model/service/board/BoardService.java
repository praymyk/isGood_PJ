package com.ys.isGood.model.service.board;

import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.member.Subscribe;

import java.util.ArrayList;


public interface BoardService {

    // 게임 정보 조회용 메소드
    Game selectGame(String gameCode);

    // 게시판 리스트 조회용 메소드
    ArrayList<Board> boardList(String gameCode);

    // 게시판 상세보기용 메소드
    Board selectBoard(String boardNo);

    // 선택 게임 구독상태 조회용 메서드
    int isSubscribe(Subscribe subscribe);

    // 게임 구독용 메서드
    int gameSubscribe(Subscribe subscribe);

    // 게임 구독 취소용 메서드
    int gameUnSubscribe(Subscribe subscribe);
}
