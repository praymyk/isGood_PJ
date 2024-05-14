package com.ys.isGood.controller.board;

import com.ys.isGood.model.service.board.BoardService;
import com.ys.isGood.model.service.board.BoardServiceImpl;
import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.member.Subscribe;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Slf4j
@Controller
public class boardController {

    @Autowired
    BoardServiceImpl boardServiceImpl;

    /*
    게시판 페이지 이동용 메서드
    @param boardType : 게시판 타입 > 게시판 타입에 따라 게시판 리스트를 가져올 예정
    */
    @GetMapping("/b/{gameCode}")
    @ResponseBody
    public ModelAndView boardList(@PathVariable String gameCode,
                                    @RequestParam(value = "p", defaultValue = "1") int p,
                                    ModelAndView mv) {

        // 1. 게임 코드에 해당하는 게임 정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);

        // 2. 게시판 리스트 가져오기
        ArrayList<Board> boardList = boardServiceImpl.boardList(gameCode);

        mv.addObject("game", game);
        mv.addObject("boardList", boardList);
        mv.addObject("gameCode", gameCode);
        mv.setViewName("board/boardIndex");

        return mv;
    }

    // 게시글 상세보기용 메소드
    @GetMapping("/b/{gameCode}/{boardNo}")
    public ModelAndView boardDetail(@PathVariable String gameCode,
                              @PathVariable String boardNo,
                              @RequestParam(value = "p", defaultValue = "1") int p,
                              ModelAndView mv){

        // 1. 게임 코드에 해당하는 게임 정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);
        // 2. 게시글 상세정보 가져오기
        Board board = boardServiceImpl.selectBoard(boardNo);
        // 3. 게시글 상세보기 페이지에서 보여줄 게시글 리스트 가져오기
        ArrayList<Board> boardList = boardServiceImpl.boardList(gameCode);

        // ModelAndView 객체에 데이터 담고 상세보기 페이지로 이동
        mv.addObject("game", game);
        mv.addObject("board", board);
        mv.addObject("boardList", boardList);
        mv.addObject("gameCode", gameCode);
        mv.setViewName("board/boardViewIndex");

        return mv;
    }

    // 게임 구독 기능 메서드
    @PostMapping("game/subscribe")
    @ResponseBody
    public int gameSubscribe(Subscribe subscribe) {

        // 1. 게임 구독 상태 확인
        int isSubscribe = boardServiceImpl.isSubscribe(subscribe);

        // 2. 게임 구독 상태에 따라 구독 또는 구독 취소
        if(isSubscribe == 1) {
            // 2-1. 구독 취소
            boardServiceImpl.gameUnSubscribe(subscribe);

        } else {
            // 2-2. 구독
            boardServiceImpl.gameSubscribe(subscribe);
        }

        return 1;
    }
}
