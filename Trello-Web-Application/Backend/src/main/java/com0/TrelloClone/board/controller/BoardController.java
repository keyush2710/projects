package com0.TrelloClone.board.controller;

import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.board.service.BoardService;
import com0.TrelloClone.workspace.model.Workspace;
import com0.TrelloClone.workspace.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    WorkspaceRepository workspaceRepository;


    @PostMapping("/save")
    public ResponseEntity<?> createBoard(@RequestBody Board board, @RequestParam int workspaceId){

        Workspace workspace=workspaceRepository.findByWorkspaceID(workspaceId);

        if(workspace == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No workspace for given ID exists");
        }
        board.setWorkspace(workspace);
        if(boardService.createBoard(board))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(board.getBoardID());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information Required is missing");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBoard(@RequestBody Board board) {
        if(boardService.updateBoard(board))
        {
            return ResponseEntity.status(HttpStatus.OK).body("Board information updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information Required is missing");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBoardByID(@RequestParam int boardID) {
         boardService.deleteBoardByID(boardID);
        return ResponseEntity.status(HttpStatus.OK).body("Board deleted successfully");
    }

    @GetMapping("/getBoards")
    public List<String> getAllBoardNames(@RequestParam int workspaceId) {
        return boardService.getALLBoardNames(workspaceId);
    }

    @GetMapping("/getBoardIds")
    public List<Integer> getAllBoardIds(@RequestParam int workspaceId) {
        return boardService.getALLBoardId(workspaceId);
    }
}
