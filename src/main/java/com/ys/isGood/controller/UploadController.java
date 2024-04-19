package com.ys.isGood.controller;

import com.ys.isGood.model.service.MemberServiceImpl;
import com.ys.isGood.model.vo.ProfileImg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Controller
public class UploadController {

    @Autowired
    MemberServiceImpl memberService;
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    // 마이페이지 프로필 이미지 저장용 메소드 (작성중)
    @PostMapping("/profileImgUpdate")
    public String updateProfileImg(@RequestParam("uploadFile") MultipartFile uploadFile,
                                   ProfileImg profileImg,
                                   Model model){

        log.info("프로필 이미지 정보 : " + profileImg);

        // 1. 파일 확장자 체크(이미지 파일 여부 확인)
        if(uploadFile.getContentType().startsWith("image") == false){
            log.info("이미지 파일이 아닙니다.");

            model.addAttribute("msg", "이미지 파일만 업로드 가능합니다.");
            return "redirect:/mypage.me";
        }

        // @param originalName : 업로드한 파일의 원래 이름
        // @param fileName : 서버에 저장된 파일의 이름
        String originalName = uploadFile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        log.info("originalName : " +originalName);
        log.info("fileName : " +fileName);

        // 2. 폴더 구분
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();

        // 3. 서버에 저장할 파일경로+이름 작성 ( 업로드 경로 + 폴더 경로[년/월/일/] + UUID + 파일명 )
        // 4. uploadFile.transferTo(savePath); 파일 저장
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
        log.info("saveName : " + saveName);
        Path savePath = Paths.get(saveName);
        try {
            uploadFile.transferTo(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. DB에 업로드할 파일 정보 VO에 담기
        profileImg.setPimgPath(uploadPath + "/" + folderPath + "/");
        profileImg.setOriginName(originalName);
        profileImg.setChangeName(uuid + "_" + fileName);
        profileImg.setImgType(uploadFile.getContentType());
        profileImg.setImgSize((int)uploadFile.getSize());
        log.info("DB 저장 프로필 이미지 정보 : " + profileImg);

        // 6. DB에 저장
        int result = memberService.updateProfileImg(profileImg);

        if(result > 0){
            model.addAttribute("msg", "프로필 이미지 업데이트 성공");
        } else {
            model.addAttribute("msg", "프로필 이미지 업데이트 실패");
        }

        log.info("프로필 이미지 업데이트 결과 : " + result);

        return "redirect:/mypage.me";
    }

    // 이미지 저장 시 폴더 생성용(년/월/일 형식) 메소드
    private String makeFolder(){
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("//", File.separator);

        File uploadPathFolder = new File(uploadPath, folderPath);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }


}
