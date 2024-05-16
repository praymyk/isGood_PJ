package com.ys.isGood.controller.comments;

import com.ys.isGood.model.service.comments.CommentsService;
import com.ys.isGood.model.vo.comments.Comments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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

        log.info("comments : " + comments);
        return comments;
    }

}
