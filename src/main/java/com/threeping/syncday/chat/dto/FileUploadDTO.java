package com.threeping.syncday.chat.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileUploadDTO {

    private String fileNo;
    private MultipartFile file; // MultipartFile(문서로 전송)
    private String originFileName;  // 파일 원본 이름
    private String transaction; // UUID를 활용한 랜덤한 파일 위치
    private String chatRoomId;  // 파일 업로드 된 채팅방 id
    private String s3DataUrl;   // 파일 링크
    private String fileDir;     // s3 파일 경로
    private Status status;      // 파일 업로드 상태

    public enum Status {
        UPLOADED, FAIL
    }


}