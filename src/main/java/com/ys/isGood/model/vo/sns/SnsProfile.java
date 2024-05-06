package com.ys.isGood.model.vo.sns;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SnsProfile {

    private String snsId;
    private String userNo;
    private String type;
    private String email;
    private String nickName;

    public SnsProfile(String snsId, String email, String nickname) {
        this.snsId = snsId;
        this.email = email;
        this.nickName = nickname;
    }
}
