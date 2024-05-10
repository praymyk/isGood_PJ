package com.ys.isGood.model.service.board;

import com.ys.isGood.model.vo.board.Board;

import java.util.ArrayList;


public interface BoardService {

    // 게시판 리스트 조회용 메소드
    ArrayList<Board> boardList(String gameCode);

}
