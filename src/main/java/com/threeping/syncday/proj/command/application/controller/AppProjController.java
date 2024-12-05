package com.threeping.syncday.proj.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.proj.command.aggregate.vo.ProjVO;
import com.threeping.syncday.proj.command.aggregate.vo.RequestUpdateVcsInfoVO;
import com.threeping.syncday.proj.command.application.service.AppProjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/projs")
public class AppProjController {
    private final AppProjService appProjService;

    @Autowired
    public AppProjController(AppProjService appProjService){
        this.appProjService = appProjService;
    }

    @Operation(summary = "프로젝트 생성",
            description = "새로운 프로젝트 정보를 입력받아 프로젝트를 생성합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "프로젝트 생성 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjVO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "user_id": "1",
                                        "proj_name": "스웨거-신규 프로젝트 생성",
                                        "vcs_type": "GITHUB",
                                        "vcs_proj_url": "https://github.com/three-ping"
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "프로젝트 생성 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                        "success": true,
                                                        "data": {
                                                          "proj_id": 26,
                                                          "proj_name": "스웨거-신규 프로젝트 생성",
                                                          "start_time": null,
                                                          "end_time": null,
                                                          "created_at": "2024-11-26T07:51:36.842+00:00",
                                                          "progress_status": 0,
                                                          "vcs_type": "GITHUB",
                                                          "vcs_proj_url": "https://github.com/three-ping"
                                                        },
                                                        "error": null
                                                      }
                                """
                                    )
                            )
                    )
            }
    )
    @PostMapping("/")
    public ResponseDTO<?> createProj(@RequestBody ProjVO newProj){
        log.info("newProj: {}", newProj);
        return ResponseDTO.ok(appProjService.addProj(newProj));
    }

    @Operation(summary = "프로젝트 수정",
            description = "기존 프로젝트의 정보를 수정합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "프로젝트 수정 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProjVO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "proj_id": 10,
                                        "proj_name": "스웨거로 수정한 프로젝트"
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "프로젝트 수정 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                         "success": true,
                                                         "data": {
                                                           "proj_id": 10,
                                                           "proj_name": "스웨거로 수정한 프로젝트",
                                                           "start_time": null,
                                                           "end_time": null,
                                                           "created_at": "2024-11-26T07:51:36.000+00:00",
                                                           "progress_status": 0,
                                                           "vcs_type": null,
                                                           "vcs_proj_url": null
                                                         },
                                                         "error": null
                                                       }
                                """
                                    )
                            )
                    )
            }
    )
    @PutMapping("/{projId}")
    public ResponseDTO<?> updateProj(@PathVariable Long projId, @RequestBody ProjVO projVO){
        projVO.setProjId(projId);
        log.info("projVO: {}", projVO);
        return ResponseDTO.ok(appProjService.modifyProj(projVO));
    }

    @Operation(summary = "프로젝트 삭제",
            description = "프로젝트 ID를 입력받아 해당 프로젝트를 삭제합니다.",

            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "프로젝트 삭제 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                {
                                    "success": true,
                                    "data": "프로젝트가 삭제되었습니다.",
                                    "error": null
                                }
                                """
                                    )
                            )
                    )
            }
    )





    @DeleteMapping("/{projId}")
    public ResponseDTO<?> deleteProj(@PathVariable Long projId){
        return ResponseDTO.ok(appProjService.deleteProj(projId));
    }
}