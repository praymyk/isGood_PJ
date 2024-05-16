package com.ys.isGood.controller.board;

import com.google.gson.JsonObject;
import com.ys.isGood.model.service.board.BoardService;
import com.ys.isGood.model.service.board.BoardServiceImpl;
import com.ys.isGood.model.vo.board.Board;
import com.ys.isGood.model.vo.board.Game;
import com.ys.isGood.model.vo.board.ImgFile;
import com.ys.isGood.model.vo.member.Subscribe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.conn.util.PublicSuffixList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Controller
public class boardController {

    @Autowired
    BoardServiceImpl boardServiceImpl;
    // 게시판 임시 이미지 저장 경로
    @Value("${boardImg.path}")
    private String boardImgPath;
    // 게시판 이미지 저장 url
    @Value("${boardImgUrl.path}")
    private String boardImgUrl;

    /*
    게시판 페이지 이동용 메서드
    @param boardType : 게시판 타입 > 게시판 타입에 따라 게시판 리스트를 가져올 예정
    */
    @GetMapping("/b/{gameCode}")
    @ResponseBody
    public ModelAndView boardList(@PathVariable String gameCode,
                                    @RequestParam(value = "p", defaultValue = "1") int p,
                                    ModelAndView mv) {

        // 1. 게임 코드에 해당하는 게임 정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);

        // 2. 게시판 리스트 가져오기
        ArrayList<Board> boardList = boardServiceImpl.boardList(gameCode);

        mv.addObject("game", game);
        mv.addObject("boardList", boardList);
        mv.addObject("gameCode", gameCode);
        mv.setViewName("board/boardIndex");

        return mv;
    }

    // 게시글 상세보기용 메소드
    @GetMapping("/b/{gameCode}/{boardNo}")
    public ModelAndView boardDetail(@PathVariable String gameCode,
                              @PathVariable String boardNo,
                              @RequestParam(value = "p", defaultValue = "1") int p,
                              ModelAndView mv){

        // 1. 게임 코드에 해당하는 게임 정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);
        // 2. 게시글 조회수 증가 + 게시글 상세정보 가져오기
        int result = boardServiceImpl.boardCount(boardNo);
        if(result>0){
            log.info("조회수 증가 성공");
        } else {
            log.info("조회수 증가 실패");
        }
        Board board = boardServiceImpl.selectBoard(boardNo);
        // 3. 게시글 상세보기 페이지에서 보여줄 게시글 리스트 가져오기
        ArrayList<Board> boardList = boardServiceImpl.boardList(gameCode);

        // ModelAndView 객체에 데이터 담고 상세보기 페이지로 이동
        mv.addObject("game", game);
        mv.addObject("board", board);
        mv.addObject("boardList", boardList);
        mv.addObject("gameCode", gameCode);
        mv.setViewName("board/boardViewIndex");

        return mv;
    }

    // 게임 구독 메서드
    @PostMapping("/subscribe")
    @ResponseBody
    public int gameSubscribe(Subscribe subscribe) {

        // 1. 게임 구독 상태 확인
        int isSubscribe = boardServiceImpl.isSubscribe(subscribe);

        // 2. 게임 구독 상태에 따라 구독 또는 구독 취소
        if(isSubscribe == 1) {
            // 2-1. 구독 취소
            return boardServiceImpl.gameUnSubscribe(subscribe);

        } else {
            // 2-2. 구독
            return boardServiceImpl.gameSubscribe(subscribe);
        }
    }

    // 게시글 작성 페이지 이동용 메소드
    @GetMapping("/b/{gameCode}/write")
    public ModelAndView boardWritePage(@PathVariable String gameCode, ModelAndView mv) {
        // 1. 게임 코드에 해당하는 게임 정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);

        mv.addObject("game", game);
        mv.setViewName("board/boardWriteIndex");
        return mv;
    }

    // 게시글 작성용 메서드
    @PostMapping("/b/{gameCode}/write")
    public String boardWrite(Board board, Model model) {
        // 게시글 작성
        int result = boardServiceImpl.boardWrite(board);
        // 등록 게시글 번호 조회
        String board_num= "";

        if(result>0){
            log.info("게시글 작성 성공 : "+board);
            model.addAttribute("msg", "게시글 작성 성공");
            // 등록 게시글 번호 가져오기
            board_num= boardServiceImpl.selectBoardNum(board);
        } else {
            log.info("게시글 작성 실패 : "+board);
            model.addAttribute("msg", "게시글 작성 실패");
        }

        // 1. 게시글 작성 성공시 게시글 내용의 이미지 경로 변경 (임시 저장 경로 -> 게시글 번호 폴더)
        String content = board.getBoardContent();
        content = content.replace(boardImgUrl + "temp/" + board.getBoardUserNo(),boardImgUrl + board_num);

        // 2. 변경된 이미지 경로로 게시글 내용 수정 및 DB 등록
        board.setBoardNo(board_num);
        board.setBoardContent(content);
        int result2 = boardServiceImpl.boardModify(board);

        if(result2>0){
            log.info("게시글 이미지 경로 수정 성공 : "+board);

            // 임시저장 'boardImg/(temp)/userNO' 에서 파일들 복사 => 'boardImg/boardNo' 폴더로 이동
            String path_folder1 = boardImgPath + "temp/" + board.getBoardUserNo() + "/";
            String path_folder2 = boardImgPath + board_num + "/";
            fileUpload(path_folder1, path_folder2);

            // 3. 게시글 작성 성공시 임시 저장 이미지 폴더 삭제
            deleteFolder(path_folder1);
        } else {
            log.info("게시글 이미지 경로 수정 실패 : "+board);
        }

        return "redirect:/b/" + board.getGameCode();
    }

    // 하위 폴더 복사 메서드
    private void fileUpload(String path_folder1, String path_folder2) {
        // 폴더1과 폴더2를 나타내는 File 객체 생성
        File folder1;
        File folder2;
        folder1 = new File(path_folder1);
        folder2 = new File(path_folder2);

        // 폴더1이 존재하지 않으면 생성
        if (!folder1.exists())
            folder1.mkdirs();

        // 폴더2가 존재하지 않으면 생성
        if (!folder2.exists())
            folder2.mkdirs();

        // 폴더1의 파일 목록을 가져옴
        File[] target_file = folder1.listFiles();

        // 폴더1의 파일을 폴더2로 복사
        for (File file : target_file) {
            File tmp = new File(folder2.getAbsolutePath() + File.separator + file.getName());

            // 폴더인 경우 폴더 생성
            if (file.isDirectory()) {
                tmp.mkdir();
            } else { // 파일인 경우 파일을 복사
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(tmp);

                    byte[] b = new byte[4096];
                    int cnt = 0;
                    while ((cnt = fis.read(b)) != -1) {
                        fos.write(b, 0, cnt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 게시글 작성중 summernote 첨부 이미지 삭제용 메소드
    @RequestMapping(value = "/deleteSummernoteImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public void deleteSummernoteImageFile(@RequestParam("file") String fileName,
                                          @RequestParam("userNo") String userNo) {
        // 폴더 위치
        String filePath = boardImgPath+"temp/"+userNo+"/";
        // 해당 파일 삭제
        deleteFile(filePath, fileName);
    }

    // 게시글 수정중 summernote 첨부 이미지 삭제용 메소드
    @RequestMapping(value = "/modifySummernoteImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public void deleteSummernoteImageFile(@RequestParam("file") String fileName,
                                          @RequestParam("userNo") String userNo,
                                          @RequestParam("boardNo") String boardNo) {
        // 폴더 위치 ( 수정 게시글은 임시저장 경로 + 작성후 경로 모두 삭제해줘야함 )
        String filePath = boardImgPath+"temp/"+userNo+"/";
        String filePath2 = boardImgPath+"/"+boardNo+"/";
        // 해당 파일 삭제
        deleteFile(filePath, fileName);
        deleteFile(filePath2, fileName);
    }

    // 파일 하나 삭제
    private void deleteFile(String filePath, String fileName) {
        Path path = Paths.get(filePath, fileName);
        try {
            // Files.delete() 메소드를 사용하여 파일 삭제
            Files.delete(path);
        } catch (NoSuchFileException e) {
            // 파일이 존재하지 않는 경우에 대한 예외 처리
            System.out.println("파일이 존재하지 않습니다.");
        } catch (IOException e) {
            // 파일 삭제 중에 발생한 예외 처리
            e.printStackTrace();
        }
    }
    // 하위 폴더 삭제
    private void deleteFolder(String path) {
        // 주어진 경로에 있는 폴더와 파일을 재귀적으로 삭제하는 함수입니다.
        File folder = new File(path);
        try {
            if (folder.exists()) {
                File[] folder_list = folder.listFiles();
                for (int i = 0; i < folder_list.length; i++) {
                    if (folder_list[i].isFile())
                        // 파일인 경우, 파일을 삭제합니다.
                        folder_list[i].delete();
                    else
                        // 폴더인 경우, 재귀적으로 폴더 내부의 파일 및 폴더를 삭제합니다.
                        deleteFolder(folder_list[i].getPath());
                    // 파일이나 폴더를 삭제합니다.
                    folder_list[i].delete();
                }
                // 폴더를 삭제합니다.
                folder.delete();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    // 게시글 첨부 이미지 저장용 메소드
    @RequestMapping(value="{userNo}/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
    @ResponseBody
    public String uploadSummernoteImageFile(@PathVariable String userNo, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
        JsonObject jsonObject = new JsonObject();
        /*
         * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
         */

        // 파일 업로드시 저장 경로 설정 (임시저장 폴더 temp > 게시글 등록 시 저장경로 이동 예정)
        String fileRoot = boardImgPath+"temp/"+userNo+"/";

        // 파일명, 확장자명, 저장될 파일명 설정
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            // 클라이언트가 볼수 있도록 파일을 불러올 URL 설정 (저장된 파일의 URL)
            jsonObject.addProperty("url", "resources/upfile/boardimg/temp/"+ userNo+ "/" + savedFileName); // contextroot + resources + 저장할 내부 폴더명
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }
        String a = jsonObject.toString();
        log.info("파일 업로드 결과 : " + a);
        return a;
    }

    // 게시글 수정 페이지 이동용 메소드
    @GetMapping("/b/{gameCode}/{boardNo}/modify")
    public ModelAndView boardModifyPage(@PathVariable String gameCode,
                                        @PathVariable String boardNo,
                                          ModelAndView mv) {
        // 1. 게시글 상세정보 가져오기
        Game game = boardServiceImpl.selectGame(gameCode);
        Board board = boardServiceImpl.selectBoard(boardNo);
        log.info("게시글 수정 페이지 정보: " + board);

        mv.addObject("game", game);
        mv.addObject("board", board);
        mv.setViewName("board/boardModifyIndex");
        return mv;
    }

    // 게시글 수정용 메소드
    @PostMapping("/b/{gameCode}/{boardNo}/modify")
    public String boardModify(@PathVariable String boardNo,
                              Board board,
                              Model model) {

        // 게시글 수정 내용 DB 등록
        int result = boardServiceImpl.boardModify(board);

        if(result>0){
            log.info("게시글 수정 성공 : "+board);
            model.addAttribute("msg", "게시글 수정 성공");
        } else {
            log.info("게시글 수정 실패 : "+board);
            model.addAttribute("msg", "게시글 수정 실패");
        }

        // 1. 게시글 수정 성공 시 게시글 내용의 이미지 경로 변경 (임시 저장 경로 -> 게시글 번호 폴더)
        String content = board.getBoardContent();
        content = content.replace(boardImgUrl + "temp/" + board.getBoardUserNo(),boardImgUrl + boardNo);

        // 2. 변경된 이미지 경로로 게시글 내용 수정 및 DB 등록
        board.setBoardContent(content);
        int result2 = boardServiceImpl.boardModify(board);

        if(result2>0){
            log.info("게시글 이미지 경로 수정 성공 : "+board);

            // 임시저장 'boardImg/(temp)/userNO' 에서 파일들 복사 => 'boardImg/boardNo' 폴더로 이동
            String path_folder1 = boardImgPath + "temp/" + board.getBoardUserNo() + "/";
            String path_folder2 = boardImgPath + boardNo + "/";
            fileUpload(path_folder1, path_folder2);

            // 3. 게시글 작성 성공시 임시 저장 이미지 폴더 삭제
            deleteFolder(path_folder1);
        } else {
            log.info("게시글 이미지 경로 수정 실패 : "+board);
        }

        return "redirect:/b/" + board.getGameCode() + "/" + board.getBoardNo();
    }

    // 게시글 삭제용 메소드
    @GetMapping("/b/{gameCode}/{boardNo}/delete")
    public String boardDelete(@PathVariable String gameCode,
                              @PathVariable String boardNo,
                              Model model) {
        // 게시글 삭제
        int result = boardServiceImpl.boardDelete(boardNo);

        if (result > 0) {
            log.info("게시글 삭제 성공 : " + boardNo);
            model.addAttribute("msg", "게시글 삭제 성공");
        } else {
            log.info("게시글 삭제 실패 : " + boardNo);
            model.addAttribute("msg", "게시글 삭제 실패");
        }

        return "redirect:/b/" + gameCode;
    }

}
