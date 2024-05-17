package com.ys.isGood.model.dao.comments;

import com.ys.isGood.model.vo.comments.Comments;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CommentsDao {

    // 댓글 리스트 조회용 메서드
    public ArrayList<Comments> commentList(String boardNo, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("commentsMapper.selectCommentList", boardNo);
    }

    // 댓글 등록용 메소드
    public int commentWrite(Comments comments, SqlSessionTemplate sqlSession) {
        return sqlSession.insert("commentsMapper.commentWrite", comments);
    }

    // 댓글 삭제용 메소드
    public int deletePost(String commentNo, SqlSessionTemplate sqlSession) {
        return sqlSession.update("commentsMapper.deletePost", commentNo);
    }
}
