package com.ys.isGood.model.vo.member;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfileImg {
    private String pimgNo;     //    PIMG_NO	NUMBERp
    private String pimgPath;     //    PIMG_PATH	VARCHAR2(100 BYTE)
    private String originName;    //    ORIGIN_NAME	VARCHAR2(100 BYTE)
    private String changeName;   //    CHANGE_NAME	VARCHAR2(100 BYTE)
    private String userNo;         //    USER_NO	NUMBERp
    private String imgType;      //    IMG_TYPE	VARCHAR2(4 BYTE)
    private int imgSize;          //    IMG_SIZE	NUMBER
}
