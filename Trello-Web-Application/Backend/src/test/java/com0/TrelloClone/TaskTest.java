package com0.TrelloClone;


import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.task.model.TaskModel;
import com0.TrelloClone.task.repository.TaskRepository;
import com0.TrelloClone.task.service.TaskService;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import com0.TrelloClone.workspace.model.Workspace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    UserRepository userRepository;

    @Test
    public void testChangeStatus()
    {
        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        when(taskRepository.findBytaskId(1)).thenReturn(taskModel);
        TaskModel taskModel1=new TaskModel();
        taskModel1.setTaskId(1);
        taskModel1.setTaskStatus(TaskModel.status.DOING);
        boolean updated=taskService.changeStatus(taskModel1.getTaskStatus(),1);

        assertEquals(TaskModel.status.DOING,taskModel.getTaskStatus());
        verify(taskRepository).findBytaskId(1);
    }

    @Test
    public void testChangeStatusOfNonExistingTask()
    {
        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        TaskModel taskModel1=new TaskModel();
        taskModel1.setTaskId(1);
        taskModel1.setTaskStatus(TaskModel.status.DOING);
        boolean updated=taskService.changeStatus(taskModel1.getTaskStatus(),2);
        assertFalse(updated);
    }
    @Test
    public void testChangeDueDate()
    {
        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        when(taskRepository.findBytaskId(1)).thenReturn(taskModel);
        TaskModel taskModel1=new TaskModel();
        taskModel1.setTaskId(1);
        taskModel1.setDueDate(LocalDate.of(2024,10,7));
        boolean updated=taskService.changeDueDate(taskModel1.getDueDate(),1);

        assertEquals(LocalDate.of(2024,10,7),taskModel.getDueDate());
        verify(taskRepository).findBytaskId(1);
    }

    @Test
    public void testChangeDueDateToBeforeToday()
    {
        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        when(taskRepository.findBytaskId(1)).thenReturn(taskModel);
        TaskModel taskModel1=new TaskModel();
        taskModel1.setTaskId(1);
        taskModel1.setDueDate(LocalDate.of(2021,10,7));
        boolean updated=taskService.changeDueDate(taskModel1.getDueDate(),1);
        assertFalse(updated);
        verify(taskRepository).findBytaskId(1);
    }

    @Test
    public void testAssignMember(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        when(taskRepository.findBytaskId(1)).thenReturn(taskModel);
        when(userRepository.findById(1)).thenReturn(user);
        boolean check=taskService.assignMember(1,1);
        assertTrue(check);
        verify(taskRepository).findBytaskId(1);
        verify(userRepository).findById(1);
    }

    @Test
    public void testAssignMemberToNonExistingTask(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.findById(1)).thenReturn(user);
        boolean check=taskService.assignMember(1,1);
        assertFalse(check);
        verify(userRepository).findById(1);
    }

    @Test
    public void testCreateTask()
    {
        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("hi");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);

        when(taskRepository.save(taskModel)).thenReturn(taskModel);
        boolean savedTask=taskService.createTask(taskModel);
        assertTrue(savedTask);
        verify(taskRepository).save(taskModel);
    }

        @Test
        public void testCreateBoardWithoutName() {
            TaskModel taskModel=new TaskModel();
            taskModel.setTaskId(1);
            taskModel.setDueDate(LocalDate.of(2023, 12, 1));
            taskModel.setTaskStatus(TaskModel.status.TODO);
            boolean savedTask=taskService.createTask(taskModel);
            assertFalse(savedTask);
        }

        @Test
    public void testallTask()
        {
            List<UserEntity> userEntities=new ArrayList<>();
            Workspace workspace=new Workspace(1,"Keyush","HI Good Morning",userEntities);
            Board board=new Board(1,"KeyushBoard");
            board.setWorkspace(workspace);
            TaskModel taskModel=new TaskModel();
            taskModel.setTaskId(1);
            taskModel.setTaskName("keyushTask");
            taskModel.setDueDate(LocalDate.of(2023, 12, 1));
            taskModel.setTaskStatus(TaskModel.status.TODO);
            taskModel.setBoard(board);

            TaskModel taskModel1=new TaskModel();
            taskModel1.setTaskId(1);
            taskModel1.setTaskName("keyushTask2");
            taskModel1.setDueDate(LocalDate.of(2023, 12, 1));
            taskModel1.setTaskStatus(TaskModel.status.TODO);
            taskModel1.setBoard(board);

            List<TaskModel> taskModelList=new ArrayList<>();
            taskModelList.add(taskModel);
            taskModelList.add(taskModel1);
            when(taskRepository.findAll()).thenReturn(taskModelList);
            List<TaskModel> getAllTasks=taskService.allTasks(1,1);

            assertEquals(taskModelList.size(),getAllTasks.size());
            verify(taskRepository).findAll();
        }


    @Test
    public void testallTaskForDifferentWorkspace()
    {
        List<UserEntity> userEntities=new ArrayList<>();
        Workspace workspace=new Workspace(1,"Keyush","HI Good Morning",userEntities);
        Board board=new Board(1,"KeyushBoard");
        board.setWorkspace(workspace);
        Workspace workspace1=new Workspace(2,"KeyushWorkspace","HI Good Morning",userEntities);
        Board board2=new Board(2,"KeyushBoard");
        board.setWorkspace(workspace);

        TaskModel taskModel=new TaskModel();
        taskModel.setTaskId(1);
        taskModel.setTaskName("keyushTask");
        taskModel.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel.setTaskStatus(TaskModel.status.TODO);
        taskModel.setBoard(board);

        TaskModel taskModel1=new TaskModel();
        taskModel1.setTaskId(1);
        taskModel1.setTaskName("keyushTask2");
        taskModel1.setDueDate(LocalDate.of(2023, 12, 1));
        taskModel1.setTaskStatus(TaskModel.status.TODO);
        taskModel1.setBoard(board2);

        List<TaskModel> taskModelList=new ArrayList<>();
        taskModelList.add(taskModel);
        when(taskRepository.findAll()).thenReturn(taskModelList);
        List<TaskModel> getAllTasks=taskService.allTasks(1,1);

        assertEquals(taskModelList.get(0).getTaskId(),getAllTasks.get(0).getTaskId());
        verify(taskRepository).findAll();
    }
}
