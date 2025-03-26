package com0.TrelloClone;


import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.board.repository.BoardRepository;
import com0.TrelloClone.board.service.BoardService;
import com0.TrelloClone.workspace.model.Workspace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardTest {

    @Mock
    BoardRepository boardRepository;

    @InjectMocks
    BoardService boardService;

    @Test
    public void testCreateBoard(){
        Board board=new Board();
        board.setBoardID(1);
        board.setBoardName("Course3130");

        when(boardRepository.save(board)).thenReturn(board);
        boolean savedBord= boardService.createBoard(board);
        assertTrue(savedBord);
        verify(boardRepository).save(board);
    }

    @Test
    public void testCreateBoardWithoutName() {
        Board board = new Board();
        board.setBoardID(1);
        board.setBoardName("");

        boolean savedBord = boardService.createBoard(board);
        assertFalse(savedBord);
    }
    @Test
    public void testUpdateBoardWithNameChange() {
        Board board = new Board();
        board.setBoardID(1);
        board.setBoardName("Course3130");

        when(boardRepository.findByBoardID(1)).thenReturn(board);

        Board board2 = new Board();
        board2.setBoardID(1);
        board2.setBoardName("Course_3130");

        boolean updated = boardService.updateBoard(board2);
        assertTrue(updated);
        verify(boardRepository).findByBoardID(1);
        verify(boardRepository).save(board);
    }


    @Test
    public  void testUpdateBoard(){
        Board board=new Board();
        board.setBoardID(1);
        board.setBoardName("Course3130");

        when(boardRepository.findByBoardID(1)).thenReturn(board);
        Board newBoard=new Board();
        newBoard.setBoardID(1);
        newBoard.setBoardName("Course_3130");

        boolean updated=boardService.updateBoard(newBoard);
        assertTrue(updated);
        verify(boardRepository).findByBoardID(1);
    }

    @Test
    public  void testUpdateBoardWithoutName(){
        Board board=new Board();
        board.setBoardID(1);
        board.setBoardName("Course3130");

        when(boardRepository.findByBoardID(1)).thenReturn(board);
        Board newBoard=new Board();
        newBoard.setBoardID(1);
        newBoard.setBoardName("");

        boolean updated=boardService.updateBoard(newBoard);
        assertFalse(updated);
        verify(boardRepository).findByBoardID(1);
    }
    @Test
    public void testDeleteBoardByID() {
        Board board=new Board();
        board.setBoardID(1);
        board.setBoardName("Course3130");

        String result = boardService.deleteBoardByID(board.getBoardID());

        assertEquals("board deleted successfully", result);
        verify(boardRepository).deleteById(board.getBoardID());
    }
    @Test
    public void testGetAllBoardNames() {
        int workspaceId = 1;
        List<Board> boards = new ArrayList<>();
        Board board1 = new Board();
        board1.setBoardID(1);
        board1.setBoardName("Board1");
        Workspace workspace1 = new Workspace();
        workspace1.setWorkspaceID(workspaceId);
        board1.setWorkspace(workspace1);
        boards.add(board1);

        Board board2 = new Board();
        board2.setBoardID(2);
        board2.setBoardName("Board2");
        Workspace workspace2 = new Workspace();
        workspace2.setWorkspaceID(workspaceId + 1);
        board2.setWorkspace(workspace2);
        boards.add(board2);

        when(boardRepository.findAll()).thenReturn(boards);

        List<String> boardNames = boardService.getALLBoardNames(workspaceId);
        assertEquals(1, boardNames.size());
        assertTrue(boardNames.contains("Board1"));
    }
    @Test
    public void testGetAllBoardIds() {
        int workspaceId = 1;
        List<Board> boards = new ArrayList<>();
        Board board1 = new Board();
        board1.setBoardID(1);
        board1.setBoardName("Board1");
        Workspace workspace1 = new Workspace();
        workspace1.setWorkspaceID(workspaceId);
        board1.setWorkspace(workspace1);
        boards.add(board1);

        Board board2 = new Board();
        board2.setBoardID(2);
        board2.setBoardName("Board2");
        Workspace workspace2 = new Workspace();
        workspace2.setWorkspaceID(workspaceId + 1);
        board2.setWorkspace(workspace2);
        boards.add(board2);

        when(boardRepository.findAll()).thenReturn(boards);

        List<Integer> boardIds = boardService.getALLBoardId(workspaceId);
        assertEquals(1, boardIds.size());
        assertTrue(boardIds.contains(1));
    }



}
