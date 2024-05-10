package com.ys.isGood.model.vo.board;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Board {

    private String boardNo;        //    BBS_NO	NUMBER
    private String gameCode;    //    Game_code
    private String boardTitle;    //    BBS_TITLE	VARCHAR2(100 BYTE)
    private String boardContent;    //    BBS_CONTENT	VARCHAR2(4000 BYTE)
    private String boardUserNo;    //    BBS_USER_NO	NUMBER
    private String boardEnrollDate;    //    BBS_ENROLL_DATE	DATE
    private String boardModifyDate;    //    BBS_MODIFY_DATE	DATE
    private int boardCount;    //    BBS_COUNT	NUMBER
    private String boardStatus;    //    BBS_STATUS	CHAR(1 BYTE)

    private String nickName;    //    USER_NICKNAME	VARCHAR2(20 BYTE)
}
