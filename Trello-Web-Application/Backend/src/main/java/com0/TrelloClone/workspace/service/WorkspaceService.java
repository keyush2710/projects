package com0.TrelloClone.workspace.service;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import com0.TrelloClone.user.service.UserService;
import com0.TrelloClone.workspace.model.Workspace;
import com0.TrelloClone.workspace.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public boolean createWorkspace(Workspace workspace) {
        if(!missingInformation(workspace))
        {
            return false;
        }
        workspaceRepository.save(workspace);
        return true;
    }

    public boolean updateWorkspace(Workspace workspace) {
        Workspace workspace1 = workspaceRepository.findByWorkspaceID(workspace.getWorkspaceID());
        if(!missingInformation(workspace))
        {
            return false;
        }
        if(workspace.getWorkspaceName().equals(workspace1.getWorkspaceName())&&
                workspace.getWorkspaceDesc().equals(workspace1.getWorkspaceDesc())) {
            return false;
        }
        workspace1.setWorkspaceName(workspace.getWorkspaceName());
        workspace1.setWorkspaceDesc(workspace.getWorkspaceDesc());
        workspaceRepository.save(workspace1);
        return true;
    }

    public String deleteWorkspaceByID(int id) {
        workspaceRepository.deleteById(id);
        return "workspace deleted successfully";
    }

    public boolean addMember(UserEntity user,int workspaceID)
    {
        Workspace workspace=workspaceRepository.findByWorkspaceID(workspaceID);
        if (workspace==null || user==null)
        {
            return false;
        }
        List<UserEntity> allUsers=workspace.getUser();
        List<UserEntity> userList=userRepository.findAll();
        boolean userExists=false;
        for(int i=0;i<userList.size();i++)
        {
            if(userList.get(i).getId()==user.getId())
            {
                userExists=true;
            }
        }
        boolean present=allUsers.contains(user);
        if(!present && userExists){
        allUsers.add(user);
        workspace.setUser(allUsers);
        workspaceRepository.save(workspace);
        return true;}
        return false;
    }

    public List<String> getALLWorkspaceName() {
        UserEntity user = userService.getLogInUser();
        List<Workspace> allWorkspace = workspaceRepository.findAll();
        List<String> workspaceNames = new ArrayList<>();
        for (int i = 0; i < allWorkspace.size(); i++) {
            for(int j=0;j<allWorkspace.get(i).getUser().size();j++)
            if (allWorkspace.get(i).getUser().get(j).getId()==user.getId())
                workspaceNames.add(allWorkspace.get(i).getWorkspaceName());
        }
        return workspaceNames;
    }

    public List<String> getAllMembers(int workspaceID)
    {
        Workspace workspace=workspaceRepository.findByWorkspaceID(workspaceID);
        List<UserEntity> allMembers=workspace.getUser();
        List<String> allMemberNames=new ArrayList<>();
        for(int i=0;i<allMembers.size();i++)
        {
            allMemberNames.add(allMembers.get(i).getFirstName());
        }
        return allMemberNames;
    }

    public boolean missingInformation(Workspace workspace)
    {
       if(workspace.getWorkspaceName()==null||workspace.getWorkspaceDesc()==null) {
           return false;
       }
       if (workspace.getWorkspaceName().isEmpty() || workspace.getWorkspaceDesc().isEmpty()) {
           return false;
       }

        return true;
    }
}

