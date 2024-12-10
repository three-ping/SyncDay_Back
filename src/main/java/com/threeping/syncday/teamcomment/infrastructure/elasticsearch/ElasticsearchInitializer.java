//package com.threeping.syncday.teamcomment.infrastructure.elasticsearch;
//
//import com.threeping.syncday.proj.query.aggregate.dto.ProjectQueryDTO;
//import com.threeping.syncday.proj.query.repository.ProjMapper;
//import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.document.CommentSearchDocument;
//import com.threeping.syncday.teamcomment.infrastructure.elasticsearch.repository.ProjSearchRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component("proj_init")
//@RequiredArgsConstructor
//@Slf4j
//public class ElasticsearchInitializer {
//    private final ProjMapper projMapper;
//    private final ProjSearchRepository searchRepository;
//
//    @PostConstruct
//    public void init() {
//        log.info("프로젝트 ES 동기화 시작!");
//        synchronizeAll();
//    }
//
//    // 수동 동기화 메서드 추가
//    public void synchronizeAll() {
//        searchRepository.deleteAll();
////        log.info("ES db와 동기화를 위해 es db 모두 비우기");
//
//        List<ProjectQueryDTO> projs = projMapper.findAllProjs();
//                projs.forEach(projDto -> {
//                    CommentSearchDocument document = CommentSearchDocument.builder()
//                            .projectId(projDto.getProjectId())
//                            .projectName(projDto.getProjectName())
//                            .vcsType(projDto.getVcsType())
//                            .createdAt(convertToLocalDateTime(projDto.getCreatedAt()))
//                            .build();
//                    searchRepository.save(document);
//                });
//    }
//
//    private static LocalDateTime convertToLocalDateTime(Timestamp createdAt) {
//        if (createdAt == null) {
//            return null;
//        }
//        return createdAt.toLocalDateTime();
//    }
//}