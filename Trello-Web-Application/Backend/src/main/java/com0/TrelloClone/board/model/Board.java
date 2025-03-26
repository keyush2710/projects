package com0.TrelloClone.board.model;

import com0.TrelloClone.workspace.model.Workspace;
import jakarta.persistence.*;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardID;
    private String boardName;


    @ManyToOne
    @JoinColumn(referencedColumnName = "workspaceID")
    private Workspace workspace;

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public int getBoardID() {
        return boardID;
    }

    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }
    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Board(int boardID, String boardName) {
        this.boardID = boardID;
        this.boardName = boardName;
    }

    public Board() {
    }
}