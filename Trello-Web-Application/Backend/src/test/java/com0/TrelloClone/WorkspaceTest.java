package com0.TrelloClone;

import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import com0.TrelloClone.user.service.UserService;
import com0.TrelloClone.workspace.model.Workspace;
import com0.TrelloClone.workspace.repository.WorkspaceRepository;
import com0.TrelloClone.workspace.service.WorkspaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkspaceTest {

    @InjectMocks
    WorkspaceService workspaceService;
    @Mock
    WorkspaceRepository workspaceRepository;
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void test_createWorkspace()
    {
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        when(workspaceRepository.save(workspace)).thenReturn(workspace);

        boolean savedWorkspace=this.workspaceService.createWorkspace(workspace);
        assertTrue(savedWorkspace);
        verify(workspaceRepository).save(workspace);
    }
    @Test
    public void test_createWorkspace_missingInformation() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceName("Workspace 1");
        boolean result = workspaceService.createWorkspace(workspace);
        assertFalse(result);
    }

    @Test
    public void testDeleteWorkspaceByID() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        String result = this.workspaceService.deleteWorkspaceByID(1);
        assertEquals("workspace deleted successfully", result);

        verify(workspaceRepository).deleteById(1);
    }
    @Test
    public void testAddMemberToExistingWorkspaceAlreadyAdded() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(workspaceRepository.findByWorkspaceID(1)).thenReturn(workspace);
        workspace.setUser(Collections.singletonList(user));

        boolean check = this.workspaceService.addMember(user, 1);
        assertFalse(check);
    }
    @Test
    public void testMissingInformationWithNoName()
    {
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceDesc("hello1");

        boolean isMissing=workspaceService.missingInformation(workspace);
        assertFalse(isMissing);
    }

    @Test
    public void testMissingInformation(){
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        boolean isMissing=workspaceService.missingInformation(workspace);
        assertTrue(isMissing);
    }

    @Test
    public void testUpdateWorkspace()
    {
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");
        when(workspaceRepository.findByWorkspaceID(1)).thenReturn(workspace);

        Workspace newWorkspace=new Workspace();
        newWorkspace.setWorkspaceID(1);
        newWorkspace.setWorkspaceName("He");
        newWorkspace.setWorkspaceDesc("he1");

        this.workspaceService.updateWorkspace(newWorkspace);

        assertEquals(workspace.getWorkspaceName(),newWorkspace.getWorkspaceName());
        verify(workspaceRepository).findByWorkspaceID(1);
    }

    @Test
    public void testUpdateWorkspaceWithSameCredentials()
    {
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        when(workspaceRepository.findByWorkspaceID(1)).thenReturn(workspace);

        Workspace newWorkspace=new Workspace();
        newWorkspace.setWorkspaceID(1);
        newWorkspace.setWorkspaceName("Hello");
        newWorkspace.setWorkspaceDesc("hello1");

        boolean check=this.workspaceService.updateWorkspace(newWorkspace);

        assertFalse(check);
        verify(workspaceRepository).findByWorkspaceID(1);
    }

    @Test
    public void addMembertoNonExistingWorkspace(){
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");
        workspace.setUser(new ArrayList<UserEntity>());

        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        boolean check=this.workspaceService.addMember(user,2);
        assertFalse(check);

    }
    @Test
    public void addMemberIfUserAlreadyExists() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");

        Workspace workspace = new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setUser(Collections.singletonList(user));

        Mockito.when(workspaceRepository.findByWorkspaceID(Mockito.anyInt())).thenReturn(workspace);

        boolean result = workspaceService.addMember(user, 1);
        assertFalse(result);
    }

    @Test
    public void testGetAllMembersIsEmptyBeforeAddingMembers(){
        Workspace workspace=new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");

        when(workspaceRepository.save(workspace)).thenReturn(workspace);
        this.workspaceService.createWorkspace(workspace);
        when(workspaceRepository.findByWorkspaceID(1)).thenReturn(workspace);
        List<String> allMembers=workspaceService.getAllMembers(1);
        assertEquals(0,allMembers.size());
        verify(workspaceRepository).save(workspace);
        verify(workspaceRepository).findByWorkspaceID(1);
    }
    @Test
    public void testGetAllMembersWithNoMembers() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("hello1");
        workspace.setUser(new ArrayList<>());

        when(workspaceRepository.findByWorkspaceID(1)).thenReturn(workspace);

        List<String> allMembers = workspaceService.getAllMembers(1);
        assertTrue(allMembers.isEmpty());
    }
    @Test
    public void getAllMembersValidWorkspaceID() {
        UserEntity user1 = new UserEntity();
        user1.setFirstName("John");

        UserEntity user2 = new UserEntity();
        user2.setFirstName("Alice");

        Workspace workspace = new Workspace();
        workspace.setUser(Arrays.asList(user1, user2));

        Mockito.when(workspaceRepository.findByWorkspaceID(Mockito.anyInt())).thenReturn(workspace);

        List<String> result = workspaceService.getAllMembers(1);
        assertEquals(Arrays.asList("John", "Alice"), result);
    }
    @Test
    public void addMemberWorkspaceNotFound() {
        UserEntity user = new UserEntity();
        user.setId(1);
        user.setFirstName("John");

        Mockito.when(workspaceRepository.findByWorkspaceID(Mockito.eq(1))).thenReturn(null);

        boolean result = workspaceService.addMember(user, 1);
        assertFalse(result);
    }
    @Test
    public void testMissingInformationWithEmptyDescription() {
        Workspace workspace = new Workspace();
        workspace.setWorkspaceID(1);
        workspace.setWorkspaceName("Hello");
        workspace.setWorkspaceDesc("");

        boolean isMissing = workspaceService.missingInformation(workspace);
        assertFalse(isMissing);
    }


}
