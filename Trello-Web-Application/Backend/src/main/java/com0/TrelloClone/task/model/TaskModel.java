package com0.TrelloClone.task.model;

import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.workspace.model.Workspace;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String taskName;

    public enum status{
        TODO,
        DOING,
        DONE
    }

    public status taskStatus;
    @DateTimeFormat
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "boardId")
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskModel(int taskId, String taskName, status taskStatus, LocalDate dueDate, UserEntity user, Board board) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.dueDate = dueDate;
        this.user = user;
        this.board = board;
    }

    public TaskModel() {
    }
}
