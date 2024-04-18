package com.ys.isGood.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileImg {
    private String pImgNo;     //    PIMG_NO	NUMBERp
    private String pImgPath;     //    PIMG_PATH	VARCHAR2(100 BYTE)p
    private String originName;    //    ORIGIN_NAME	VARCHAR2(100 BYTE)p
    private String changeName;   //    CHANGE_NAME	VARCHAR2(100 BYTE)
    private String userNo;         //    USER_NO	NUMBERp
    private String imgType;      //    IMG_TYPE	VARCHAR2(4 BYTE)p
    private int imgSize;          //    IMG_SIZE	NUMBER
}
