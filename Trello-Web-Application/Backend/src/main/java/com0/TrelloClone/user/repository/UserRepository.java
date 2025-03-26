package com0.TrelloClone.user.repository;

import com0.TrelloClone.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findById(int id);
    UserEntity findByEmailID(String emailID);

}
