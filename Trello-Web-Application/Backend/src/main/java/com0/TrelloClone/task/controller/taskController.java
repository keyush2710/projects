package com0.TrelloClone.task.controller;

import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.board.repository.BoardRepository;
import com0.TrelloClone.task.model.TaskModel;
import com0.TrelloClone.task.service.TaskService;
import com0.TrelloClone.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/task")
public class taskController {

    @Autowired
    TaskService taskService;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping("/save")
    public ResponseEntity<?> createTask(@RequestBody TaskModel taskModel,@RequestParam int boardId)
    {

        Board board= boardRepository.findByBoardID(boardId);

        if(board == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No board for given ID exists");
        }
        taskModel.setBoard(board);

        if(taskService.createTask(taskModel))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(taskModel);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unsuccessful");
    }

    @PutMapping("/changeDueDate")
    public ResponseEntity<String> changeDueDate(@RequestParam @DateTimeFormat LocalDate newDueDate, @RequestParam int taskId)
    {
        if(taskService.changeDueDate(newDueDate, taskId))
        {
            return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsuccessful");
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam TaskModel.status status,@RequestParam int taskId)
    {
        if(taskService.changeStatus(status, taskId))
        {
            return ResponseEntity.status(HttpStatus.OK).body("Updated Successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsuccessful");
    }

    @PostMapping("/assignMember")
    public ResponseEntity<String> assignMember(@RequestParam int userId,@RequestParam int taskId) {

        if(taskService.assignMember(userId,taskId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Member successfully assigned");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or task does not exist");
        }
    }
    @GetMapping("/allTasks")
    public List<TaskModel> allTasks(@RequestParam int boardId ,@RequestParam int workspaceId)
    {
        return taskService.allTasks(boardId,workspaceId);
    }

}
