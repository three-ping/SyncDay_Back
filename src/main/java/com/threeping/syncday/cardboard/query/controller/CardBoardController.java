package com.threeping.syncday.cardboard.query.controller;
import com.threeping.syncday.cardboard.query.service.CardBoardService;
import com.threeping.syncday.common.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cardboards")
public class CardBoardController {

    private final CardBoardService cardBoardService;

    public CardBoardController(CardBoardService cardBoardService) {
        this.cardBoardService = cardBoardService;
    }

    @GetMapping("/")
    public ResponseDTO<?> findAllCardBoards() {
        return ResponseDTO.ok(cardBoardService.getAllCardBoards());
    }

    @GetMapping("/workspaces/{workspaceId}")
    public ResponseDTO<?> findAllCardBoardsByWorkspaceId(@PathVariable Long workspaceId) {
        return ResponseDTO.ok(cardBoardService.getCardBoardsByWorkspaceId(workspaceId));
    }
}
