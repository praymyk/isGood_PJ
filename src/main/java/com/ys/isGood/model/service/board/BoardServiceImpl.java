package com.ys.isGood.model.service.board;

import com.ys.isGood.model.dao.board.BoardDao;
import com.ys.isGood.model.vo.board.Board;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public ArrayList<Board> boardList(String gameCode) {
        return boardDao.boardList(gameCode, sqlSession);
    }
}
