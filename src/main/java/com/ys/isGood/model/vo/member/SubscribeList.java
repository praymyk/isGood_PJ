package com.ys.isGood.model.vo.member;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubscribeList {

    private String userNo;      //    USER_NO NUMBER,
    private String gameNo;      //    GAME_NO NUMBER,
    private int subNo;          //    SUB_NO NUMBER

    // Games 테이블에서 가져온 값
    private String gameTitle;        //    GAME_TITLE	VARCHAR2(100 BYTE)
    private String gameContent;        //    GAME_CONTENT	VARCHAR2(4000 BYTE)
    private int gamePrice;        //    GAME_PRICE	NUMBER
    private int gameSale;        //    GAME_SALE	NUMBER
    private String gameTag;        //    GAME_TAG	VARCHAR2(100 BYTE)
    private String gameEnrollDate;     //    GAME_ENROLL_DATE	DATE
    private String gameModifyDate;        //    GAME_MODIFY_DATE	DATE
    private String gameStatus;        //    GAME_STATUS	CHAR(1 BYTE)
}
