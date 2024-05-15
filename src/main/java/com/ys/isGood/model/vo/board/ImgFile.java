package com.ys.isGood.model.vo.board;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImgFile {
    private String imgNo;        //    IMG_NO	NUMBER
    private String gameCode;    //    GAME_NO	NUMBER
    private String imgPath;        //    IMG_PATH	VARCHAR2(100 BYTE)
    private String originName;   //    ORIGIN_NAME	VARCHAR2(100 BYTE)
    private String changeName;    //    CHANGE_NAME	VARCHAR2(100 BYTE)
    private String imgType;       //    IMG_TYPE	VARCHAR2(4 BYTE)
    private String imgSize;        //    IMG_SIZE	NUMBER
    private String createDate;    //    CREATE_DATE	DATE
    private String deleteDate;    //    DELETE_DATE	DATE
}
