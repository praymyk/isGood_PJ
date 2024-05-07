package com.ys.isGood.model.vo.member;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginMember {

    private String userNo;      //    USER_NO	NUMBER
    private String email;       //    EMAIL	VARCHAR2(30 BYTE)
    private String userPwd;     //    USER_PWD	VARCHAR2(30 BYTE)
    private String nickName;    //    NICKNAME	VARCHAR2(30 BYTE)
    private String birthday;    //    BIRTHDAY	VARCHAR2(8 BYTE)
    private String gender;      //    GENDER    CHAR(1 BYTE)
    private String phone;       //    PHONE	VARCHAR2(13 BYTE)
    private String enrollDate;  //    ENROLL_DATE	DATE
    private String modifyDate;  //    MODIFY_DATE	DATE
    private String status;      //    STATUS	CHAR(1 BYTE)

    // 포로필 이미지 정보 추가 필드
    private String pimgNo;     //    PIMG_NO	NUMBERp
    private String pimgPath;     //    PIMG_PATH	VARCHAR2(100 BYTE)
    private String originName;    //    ORIGIN_NAME	VARCHAR2(100 BYTE)
    private String changeName;   //    CHANGE_NAME	VARCHAR2(100 BYTE)
    private String imgType;      //    IMG_TYPE	VARCHAR2(4 BYTE)
    private int imgSize;          //    IMG_SIZE	NUMBER

    // sns 로그인 정보 추가 필드
    private String snsType;
    
}
