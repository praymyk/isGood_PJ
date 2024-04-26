package com.ys.isGood.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 프로필 이미지 upload 경로 설정용 config
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //WebMvcConfigurer interface를 상속받아 addResourceHandlers method를 오버 라이딩하고 리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해 리소스의 위치와 리소스와 매칭 될 url을 설정합니다.
    //addResourceHandler : 리소스와 연결될 URL path를 지정합니다. (클라이언트가 파일에 접근하기 위해 요청하는 url)
    //addResourceLocations: 실제 리소스가 존재하는 외부 경로를 지정합니다.
    //경로의 마지막은 반드시 " / "로 끝나야 하고, 로컬 디스크 경로일 경우 file:/// 접두어를 꼭 붙여야 합니다.
    //이렇게 설정하면 클라이언트로부터 http://호스트 주소:포트/imagePath/testImage.jpg 와 같은 요청이 들어 왔을 때 /home/uploadedImage/testImage.jpg 파일로 연결됩니다.

    // connectPath, resourcePath는 application.properties에 설정한 값들을 가져옵니다.

    // DB 저장 경로
    @Value("${displayProfileImg.path}")
    private String resourcePath;

    // 클라이언트가 요청하는 주소
    @Value("${requestDisplayProfileImg.path}")
    private String connectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }
}
