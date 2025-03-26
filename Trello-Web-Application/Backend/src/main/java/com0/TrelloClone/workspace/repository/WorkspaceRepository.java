package com0.TrelloClone.workspace.repository;

import com0.TrelloClone.workspace.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Integer> {

    Workspace findByWorkspaceName(String workspaceName);
    Workspace findByWorkspaceID(int workspaceID);


}
