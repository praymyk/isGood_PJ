package com.ys.isGood.model.vo.board;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game {
    private String gameCode;    //    GAME_CODE	VARCHAR2(100 BYTE)
    private String gameTitle;   //    GAME_TITLE	VARCHAR2(100 BYTE)
    private String gameContent; //    GAME_CONTENT	VARCHAR2(4000 BYTE)
    private int gamePrice;      //    GAME_PRICE	NUMBER
    private int gameSale;       //    GAME_SALE	NUMBER
    private String gameTag;     //    GAME_TAG	VARCHAR2(100 BYTE)
    private String  EnrollDate;  //    GAME_ENROLL_DATE	DATE
    private String  ModifyDate;  //    GAME_MODIFY_DATE	DATE
    private String  Status;     //    GAME_STATUS	CHAR(1 BYTE)

    private String enrollCount; //   구독자수;
}
