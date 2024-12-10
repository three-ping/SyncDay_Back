package com.threeping.syncday.teamcomment.query.controller;

import com.github.pagehelper.PageInfo;
import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.teamcomment.query.aggregate.dto.CommentSearchResponse;
import com.threeping.syncday.teamcomment.query.aggregate.dto.TeamCommentDTO;
import com.threeping.syncday.teamcomment.query.service.TeamCommentQueryService;
import com.threeping.syncday.teamcomment.query.service.TeamCommentSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teamcomment")
public class TeamCommentQueryController {

    private final TeamCommentQueryService teamCommentQueryService;
    private final TeamCommentSearchService teamCommentSearchService;

    @Autowired
    public TeamCommentQueryController(TeamCommentQueryService teamCommentQueryService, TeamCommentSearchService teamCommentSearchService) {
        this.teamCommentQueryService = teamCommentQueryService;
        this.teamCommentSearchService = teamCommentSearchService;
    }

    @GetMapping("/{teamPostId}")
    public ResponseDTO<?> findTeamCommentPage(@PathVariable Long teamPostId,
                                              @RequestParam(defaultValue = "1")int page,
                                              @RequestParam(defaultValue = "10") int pageSize){
        PageInfo<TeamCommentDTO> teamCommentPageInfo = teamCommentQueryService.findTeamCommentPage(teamPostId,page,pageSize);
        return ResponseDTO.ok(teamCommentPageInfo);
    }

    @Operation(summary = "팀 게시글 댓글 검색",
            description = "게시글 제목, 댓글 내용, 작성자를 키워드로 팀 게시글 댓글을 검색합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "댓글 검색 성공",
                            content = @Content(schema = @Schema(implementation = CommentSearchResponse.class))
                    )
            }
    )
    @GetMapping("/search")
    public ResponseDTO<?> searchTeamComments(@RequestParam String keyword){
        return ResponseDTO.ok(teamCommentSearchService.searchComments(keyword));
    }
}

