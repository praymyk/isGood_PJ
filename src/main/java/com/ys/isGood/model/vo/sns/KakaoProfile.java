package com.ys.isGood.model.vo.sns;

import lombok.*;
import org.springframework.security.web.PortResolverImpl;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KakaoProfile {

    private Long snsId;
    private String userNo;
    private String type;
    private String email;
    private String nickName;

    public KakaoProfile(Long snsId, String email, String nickname) {
        this.snsId = snsId;
        this.email = email;
        this.nickName = nickname;
    }
}


