package com.ys.isGood.model.service.comments;

import com.ys.isGood.model.dao.comments.CommentsDao;
import com.ys.isGood.model.vo.comments.Comments;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    CommentsDao commentsDao;
    @Autowired
    SqlSessionTemplate sqlSession;

    // 댓글 리스트 조회용 메서드
    @Override
    public ArrayList<Comments> commentList(String boardNo) {
        return commentsDao.commentList(boardNo, sqlSession);
    }
}
