package com.threeping.syncday.workspace.command.application.controller;

import com.threeping.syncday.common.ResponseDTO;
import com.threeping.syncday.workspace.command.aggregate.vo.WorkspaceVO;
import com.threeping.syncday.workspace.command.application.service.AppWorkspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/workspaces")
public class AppWorkspaceController {

    private final AppWorkspaceService appWorkspaceService;

    @Autowired
    public AppWorkspaceController(AppWorkspaceService appWorkspaceService) {
        this.appWorkspaceService = appWorkspaceService;
    }


    @Operation(summary = "워크스페이스 생성",
            description = "새로운 워크스페이스 정보를 입력받아 워크스페이스를 생성합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "워크스페이스 생성 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WorkspaceVO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "workspace_name": "백엔드 개발",
                                        "proj_id": 1,
                                        "vcs_type": "GITHUB",
                                        "vcs_repo_url": "https://github.com/three-ping/backend"
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "워크스페이스 생성 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                            {
                                                "success": true,
                                                "data": {
                                                    "workspace_id": 1,
                                                    "workspace_name": "백엔드 개발",
                                                    "created_at": "2024-11-26T07:51:36.842+00:00",
                                                    "progress_status": 0,
                                                    "proj_id": 1,
                                                    "vcs_type": "GITHUB",
                                                    "vcs_repo_url": "https://github.com/three-ping/backend"
                                                },
                                                "error": null
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @PostMapping()
    public ResponseDTO<?> createWorkspace(@RequestBody WorkspaceVO newWorkspace) {
        return ResponseDTO.ok(appWorkspaceService.addWorkspace(newWorkspace));
    }


    @Operation(summary = "워크스페이스 수정",
            description = "기존 워크스페이스의 정보를 수정합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "워크스페이스 수정 정보",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WorkspaceVO.class),
                            examples = @ExampleObject(
                                    value = """
                                    {
                                        "workspace_id": 1,
                                        "workspace_name": "백엔드 개발 - 리팩토링"
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "워크스페이스 수정 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                            {
                                                "success": true,
                                                "data": {
                                                    "workspace_id": 1,
                                                    "workspace_name": "백엔드 개발 - 리팩토링",
                                                    "created_at": "2024-11-26T07:51:36.000+00:00",
                                                    "progress_status": 0,
                                                    "proj_id": 1,
                                                    "vcs_type": "GITHUB",
                                                    "vcs_repo_url": "https://github.com/three-ping/backend"
                                                },
                                                "error": null
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @PutMapping()
    public ResponseDTO<?> updateWorkspace(@RequestBody WorkspaceVO workspaceVO) {
        return ResponseDTO.ok(appWorkspaceService.modifyWorkspace(workspaceVO));
    }
    @Operation(summary = "워크스페이스 삭제",
            description = "워크스페이스 ID를 입력받아 해당 워크스페이스를 삭제합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "워크스페이스 삭제 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = """
                                            {
                                                "success": true,
                                                "data": "워크스페이스가 삭제되었습니다.",
                                                "error": null
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/{workspaceId}")
    public ResponseDTO<?> deleteWorkspace(@PathVariable("workspaceId") Long workspaceId){
        return ResponseDTO.ok(appWorkspaceService.deleteWorkspace(workspaceId));
    }
}
