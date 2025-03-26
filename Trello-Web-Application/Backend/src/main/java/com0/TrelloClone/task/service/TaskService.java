package com0.TrelloClone.task.service;

import com0.TrelloClone.task.model.TaskModel;
import com0.TrelloClone.task.repository.TaskRepository;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;
    public boolean createTask(TaskModel task)
    {
        if(task!=null)
        {
            if(task.getTaskName()!=null && !task.getTaskName().isEmpty()){// && task.getDueDate().isAfter(LocalDate.now())) {
                taskRepository.save(task);
                return true;
            }
        }
        return false;
    }

    public boolean changeDueDate(@DateTimeFormat LocalDate newDueDate, int taskId){
        TaskModel taskModel=taskRepository.findBytaskId(taskId);
        if(taskModel!=null)
        {
            if(newDueDate.isBefore(LocalDate.now()))
            {
                return false;
            }
            else{
                taskModel.setDueDate(newDueDate);
                taskRepository.save(taskModel);
                return true;
            }
        }
        return false;
    }

    public boolean assignMember(int userId,int taskId)
    {
        TaskModel taskModel=taskRepository.findBytaskId(taskId);
        UserEntity user=userRepository.findById(userId);
        if(taskModel!=null && user!=null)
        {

            taskModel.setUser(user);
            taskRepository.save(taskModel);
            return true;
        }
        return false;
    }

    public boolean changeStatus(TaskModel.status status,int taskId)
    {
        TaskModel taskModel1=taskRepository.findBytaskId(taskId);
        if(taskModel1==null)
        {
            return false;
        }
        taskModel1.setTaskStatus(status);
        taskRepository.save(taskModel1);
        return true;
    }

    public List<TaskModel> allTasks(int boardId, int workspaceId)
    {
        List<TaskModel> tasks=taskRepository.findAll();
        List<TaskModel> allTask=new ArrayList<>();
        for(int i=0;i<tasks.size();i++)
        {
            if (tasks.get(i).getBoard().getBoardID()==boardId) {
                if(tasks.get(i).getBoard().getWorkspace().getWorkspaceID()==workspaceId) {
                    allTask.add(tasks.get(i));
                }
            }
        }
        return allTask;
    }
}
