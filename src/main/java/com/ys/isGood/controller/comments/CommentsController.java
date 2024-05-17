package com.ys.isGood.controller.comments;

import com.ys.isGood.model.service.comments.CommentsService;
import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.comments.Comments;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Slf4j
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    // 댓글 리스트 조회용 메서드
    @GetMapping("/b/{gameCode}/{boardNo}/commentList")
    @ResponseBody
    public ArrayList<Comments> commentList(@PathVariable String gameCode,
                                           @PathVariable String boardNo) {

        ArrayList<Comments> comments = commentsService.commentList(boardNo);

        return comments;
    }

    // 댓글 작성용 메서드
    @PostMapping("/b/{gameCode}/{boardNo}/commentWrite")
    public String commentWrite(@PathVariable String gameCode,
                               Comments comments){

        int result = commentsService.commentWrite(comments);

        if (result > 0) {
            log.info("댓글 등록 성공");
        } else {
            log.info("댓글 등록 실패");
        }

        return "redirect:/b/{gameCode}/{boardNo}";
    }

    // 댓글 삭제용 메소드
    @GetMapping("/b/loa/deletePost")
    @ResponseBody
    public String deletePost(@RequestParam String commentNo){

        int result = commentsService.deletePost(commentNo);

        if(result>0){
            return "댓글 삭제 완료";
        } else {
            return "댓글 삭제 실패";
        }

    }


}
