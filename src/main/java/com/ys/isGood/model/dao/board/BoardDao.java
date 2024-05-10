package com.ys.isGood.model.dao.board;

import com.ys.isGood.model.vo.board.Board;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BoardDao {

    // 게시판 리스트 조회용 메소드
    public ArrayList<Board> boardList(String gameCode, SqlSessionTemplate sqlSession) {
        return (ArrayList)sqlSession.selectList("boardMapper.selectBoardList", gameCode);   }
}
