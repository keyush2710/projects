package com0.TrelloClone.workspace.controller;
import com0.TrelloClone.user.service.UserService;
import com0.TrelloClone.workspace.model.Workspace;
import com0.TrelloClone.workspace.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> createWorkspace(@RequestBody Workspace workspace) {
        if (userService.toCheckUserIsPresent()) {
             boolean check=workspaceService.createWorkspace(workspace);
             if(check) {
                 workspaceService.addMember(userService.getLogInUser(), workspace.getWorkspaceID());
                 return ResponseEntity.status(HttpStatus.CREATED).body(workspace.getWorkspaceID());
             }
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information Required is missing");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user created yet");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateWorkspace(@RequestBody Workspace workspace) {
        if(workspaceService.updateWorkspace(workspace)){
            return ResponseEntity.status(HttpStatus.OK).body("Workspace information updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information Require is missing");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorkspaceByID(@PathVariable("id") int id) {
        workspaceService.deleteWorkspaceByID(id);
        return ResponseEntity.status(HttpStatus.OK).body("workspace deleted successfully");
    }

    @PostMapping("/addMember")
    public ResponseEntity<String> addMember(@RequestParam int workspaceID, @RequestParam String email) {
        boolean check=workspaceService.addMember(userService.findUserByEmailID(email), workspaceID);
        if(check) {
            return ResponseEntity.status(HttpStatus.OK).body("Member successfully added");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist OR User already added to Workspace");
        }
    }

    @GetMapping("/getWorkspaces")
    public List<String> getAllWorkspaceName() {
        return workspaceService.getALLWorkspaceName();
    }

    @GetMapping("/getAllMembers")
    public List<String> getNameOfMembers(@RequestParam int id)
    {
        return workspaceService.getAllMembers(id);
    }
}
