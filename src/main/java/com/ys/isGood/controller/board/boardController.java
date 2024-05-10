package com.ys.isGood.controller.board;

import com.ys.isGood.model.service.board.BoardService;
import com.ys.isGood.model.service.board.BoardServiceImpl;
import com.ys.isGood.model.vo.board.Board;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Slf4j
@Controller
public class boardController {

    @Autowired
    BoardServiceImpl boardServiceImpl;

    /*
    게시판 페이지 이동용 메서드
    @param boardType : 게시판 타입 > 게시판 타입에 따라 게시판 리스트를 RestApi 통신으로 가져올 예정
     */
    @GetMapping("/b/{gameCode}")
    public String boardList(@PathVariable String gameCode, Model model) {

        model.addAttribute("gameCode", gameCode);

        return "board/boardIndex";
    }

    // 게시판 리스트 조회용 메서드
    @GetMapping("/b/boardList/{gameCode}")
    @ResponseBody
    public ArrayList<Board> boardDetail(@PathVariable String gameCode) {

        ArrayList<Board> boardList = boardServiceImpl.boardList(gameCode);

        log.info("boardList : " + boardList);


        return boardList;
    }

}
