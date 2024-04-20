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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @ResponseBody
    public String insertProfileImg(@RequestParam("uploadFile") MultipartFile uploadFile,
                                   ProfileImg profileImg,
                                   Model model){

        // 1. 파일 확장자 체크(이미지 파일 여부 확인)
        if(uploadFile.getContentType().startsWith("image") == false){
            log.info("이미지 파일이 아닙니다.");
            model.addAttribute("msg", "이미지 파일만 업로드 가능합니다.");
            return "member/myPage";
        }

        // @param originalName : 업로드한 파일의 원래 이름
        // @param fileName : 서버에 저장된 파일의 이름
        String originalName = uploadFile.getOriginalFilename();
        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        // 2. 폴더 구분
        String folderPath = makeFolder(profileImg.getUserNo());
        String uuid = UUID.randomUUID().toString();

        // 3. 서버에 저장할 파일경로+이름 작성 ( 업로드 경로 + 폴더 경로[년/월/일/] + UUID + 파일명 )
        // 4. uploadFile.transferTo(savePath); 파일 저장
        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
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

        // 6-1. DB 저장전 기존 프로필 이미지 정보 조회 및 삭제
        // @param exist : 기존 프로필 이미지 정보 조회 결과
        // 이미지가 존재할 경우 기존 DB Update + 로컬 경로 파일 삭제
        ProfileImg exist = memberService.checkProfileImg(profileImg);

        if(exist != null){ // 프로필 이미지 존재할경우 1. DB update 2. 서버에 저장된 기존 file 삭제

            // DB 업데이트 (기존 이미지 update 후 결과 메시지 반납)
            model.addAttribute("msg", updateProfileImg(profileImg));
            // 서버에 저장된 기존 file 삭제
            deleteProfileImg(exist);

        } else {    // 프로필 이미지가 존재하지 않을경우

            // 6-2. DB에 등록 프로필 정보 insert
            model.addAttribute("msg", insertProfileImg(profileImg));// 이미지가 존재하지 않을 경우 DB Insert
        }

        return "member/myPage";
    }

    // 이미지 저장 시 폴더 생성용(userNo명칭) 메소드
    private String makeFolder(String userNo){
        String folderPath = userNo;

        File uploadPathFolder = new File(uploadPath, folderPath);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    // 프로필 이미지 update용 메소드
    private String updateProfileImg(ProfileImg profileImg){
        String msg = "";

        int result = memberService.updateProfileImg(profileImg);

        if(result > 0){
            msg = "프로필 이미지 업데이트 성공";
        } else {
            msg = "프로필 이미지 업데이트 실패";
        }
        return msg;
    }

    // 프로필 이미지 insert용 메소드
    private String insertProfileImg(ProfileImg profileImg){
        String msg = "";

        int result = memberService.insertProfileImg(profileImg);

        if(result > 0){
            msg = "프로필 이미지 업데이트 성공";
        } else {
            msg = "프로필 이미지 업데이트 실패";
        }
        return msg;
    }

    // 기존 프로필 이미지 File 삭제용 메소드
    private Boolean deleteProfileImg(ProfileImg profileImg){
        String path = profileImg.getPimgPath() + profileImg.getChangeName();
        log.info("삭제할 파일 경로 : " + path);
        File existFile = new File(profileImg.getPimgPath() + profileImg.getChangeName());

        return existFile.delete();
    }

}
