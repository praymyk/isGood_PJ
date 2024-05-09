package com.ys.isGood.controller.board;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class boardController {

    /*
    게시판 페이지 이동용 메서드
    @param boardType : 게시판 타입 > 게시판 타입에 따라 게시판 리스트를 RestApi 통신으로 가져올 예정
     */
    @GetMapping("/b/{boardType}")
    public String boardList(@PathVariable String boardType, Model model) {

        model.addAttribute("boardType", boardType);
        log.info("boardType : " + boardType);

        return "index";
    }

}
