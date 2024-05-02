package com.ys.isGood.model.vo.member;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
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

    public Member(String userNo) {
        this.userNo = userNo;
    }
}
