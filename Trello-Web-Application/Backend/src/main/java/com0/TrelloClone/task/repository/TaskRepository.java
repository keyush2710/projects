package com0.TrelloClone.task.repository;

import com0.TrelloClone.task.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel,Integer>{
    TaskModel findBytaskId(int taskId);
}
