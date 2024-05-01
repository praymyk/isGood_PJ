package com.ys.isGood.model.vo.member;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Subscribe {
    private String userNo;      //    USER_NO NUMBER,
    private String gameNo;      //    GAME_NO NUMBER,
    private int subNo;          //    SUB_NO NUMBER

    private int subNoUp;        //   SUB_NO_UP NUMBER (순번 번 변경시 jsp단에서 받은 sibNo 업데이트 값)

}
