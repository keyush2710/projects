package com0.TrelloClone.user.service;

import com0.TrelloClone.board.model.Board;
import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    UserEntity logInUser;
    public UserEntity signup(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (!password.matches(".*[@$!%*?&].*")) {
            return false;
        }
        return true;
    }

    public UserEntity findUserByEmailID(String email)
    {
        UserEntity user=userRepository.findByEmailID(email);
        if(user!=null) {
            System.out.println("user Information Fetched Successfully");
        }
        return user;
    }

    public boolean toCheckSameUser(String email)
    {
        List<UserEntity> allUsers=userRepository.findAll();

        for (int i=0;i<=allUsers.size()-1;i++)
        {
            if(email.equals(allUsers.get(i).getEmailID()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean validateEmailAndPassword (String email, String password){
        //we got the original(REAL and TRUE) data from the database and now we are having it in object
        UserEntity userEntity = userRepository.findByEmailID(email);
        if (userEntity != null && password.equals(userEntity.getPassword())){
            logInUser=userEntity;
            return true;
        }
        return false;
    }

    public boolean toCheckUserIsPresent(){
        List<UserEntity>user=userRepository.findAll();
        if(user.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean forgotPassword(String email,String securityAnswer,String newPassword)
    {
        UserEntity user=userRepository.findByEmailID(email);
        if(user!=null)
        {
            if(user.getSecurityAnswer().equals(securityAnswer))
            {
                if(validatePassword(newPassword))
                {
                    user.setPassword(newPassword);
                    updateUserEntity(user);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean updateUserEntity(UserEntity userEntity)
    {
            UserEntity userEntity1=userRepository.findById(userEntity.getId());
            if(userEntity1!=null){

            userEntity1.setFirstName(userEntity.getFirstName());
            userEntity1.setLastName(userEntity.getLastName());
            userEntity1.setEmailID(userEntity.getEmailID());
            if(validatePassword(userEntity.getPassword())) {
                userEntity1.setPassword(userEntity.getPassword());
            }
            userEntity1.setSecurityAnswer(userEntity.getSecurityAnswer());
            userRepository.save(userEntity1);
            return true;
            }
            return false;
    }

    public UserEntity getLogInUser()
    {
        return logInUser;
    }

    public boolean userInformation(UserEntity userEntity)
    {
        if(userEntity.getFirstName()==null||userEntity.getEmailID()==null||
                userEntity.getPassword()==null){
            return false;
        }
        if(userEntity.getFirstName().isEmpty()||userEntity.getEmailID().isEmpty()||
                userEntity.getPassword().isEmpty()){
            return false;
        }
        return true;
    }
}
