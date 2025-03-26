package com0.TrelloClone.workspace.model;

import com0.TrelloClone.user.entity.UserEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workspaceID;
    private String workspaceName;

    private String workspaceDesc;

    public String getWorkspaceDesc() {
        return workspaceDesc;
    }

    public void setWorkspaceDesc(String workspaceDesc) {
        this.workspaceDesc = workspaceDesc;
    }

    public int getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(int workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getWorkspaceName() {
        return workspaceName;
    }
    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    @ManyToMany
    @JoinColumn(referencedColumnName = "id")
    private List<UserEntity> user=new ArrayList<>();

    public List<UserEntity> getUser() {
        return user;
    }

    public void setUser(List<UserEntity> user) {
        this.user = user;
    }


    public Workspace(int workspaceID, String workspaceName, String workspaceDesc, List<UserEntity> user) {
        this.workspaceID = workspaceID;
        this.workspaceName = workspaceName;
        this.workspaceDesc = workspaceDesc;
        this.user = user;
    }

    public Workspace() {
    }
}
