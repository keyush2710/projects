package com0.TrelloClone.board.service;

import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.board.repository.BoardRepository;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.workspace.model.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public boolean createBoard(Board board){
        if(!board.getBoardName().isEmpty())
        {
            boardRepository.save(board);
            return true;
        }
        return false;
    }

    public boolean updateBoard(Board board)
    {
        Board board1=boardRepository.findByBoardID(board.getBoardID());
        if(board.getBoardName().isEmpty())
        {
            return false;
        }
        board1.setBoardName(board.getBoardName());
        boardRepository.save(board1);
        return true;
    }

    public String deleteBoardByID(int boardID)
    {
        boardRepository.deleteById(boardID);
        return "board deleted successfully";
    }

    public List<String> getALLBoardNames(int workspaceId) {
        List<Board> allBoards = boardRepository.findAll();
        List<String> boardNames = new ArrayList<>();
        for (int i = 0; i < allBoards.size(); i++) {
            Board board=allBoards.get(i);
            if (board.getWorkspace().getWorkspaceID()==workspaceId)
            {
                boardNames.add(board.getBoardName());
            }
        }
        return boardNames;
    }
    public List<Integer> getALLBoardId(int workspaceId) {
        List<Board> allBoards = boardRepository.findAll();
        List<Integer> boardIds = new ArrayList<>();
        for (int i = 0; i < allBoards.size(); i++) {
            Board board=allBoards.get(i);
            if (board.getWorkspace().getWorkspaceID()==workspaceId)
            {
                boardIds.add(board.getBoardID());
            }
        }
        return boardIds;
    }
}
