package com.ys.isGood.model.vo.comments;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comments {
    private String commentNo;               //    CM_NO	NUMBER
    private String boardNo;              //    POST_NO	NUMBER
    private String commentContent;      //    CM_CONTETN	VARCHAR2(4000 BYTE)
    private String commentWriter;       //    CM_WRITER	VARCHAR2(100 BYTE)
    private String commentEnrollDate;   //    CM_ENROLL	DATE
    private String commentModifyDate;   //    CM_MODIFY	DATE
    private String commentStatus;       //    CM_STATUS	CHAR(1 BYTE)

    private String nickName;
}
