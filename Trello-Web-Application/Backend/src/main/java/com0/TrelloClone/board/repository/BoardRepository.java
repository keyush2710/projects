package com0.TrelloClone.board.repository;

import com0.TrelloClone.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Board findByBoardName(String boardName);
    Board findByBoardID(int boardID);

}
