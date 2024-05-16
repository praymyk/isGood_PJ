package com.ys.isGood.model.service.comments;

import com.ys.isGood.model.vo.comments.Comments;

import java.util.ArrayList;

public interface CommentsService {

    // 댓글 리스트 조회용 메서드
    ArrayList<Comments> commentList(String boardNo);
}
