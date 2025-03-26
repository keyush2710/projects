package com0.TrelloClone;

import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.repository.UserRepository;
import com0.TrelloClone.user.service.UserService;
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
public class UserTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testCreateUser()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        assertEquals(user,savedUser,"mismatch");
        verify(userRepository).save(user);
    }

    @Test
    public void testValidatePasswordWithIncorrectPassword()
    {
        boolean password=userService.validatePassword("PASSWORD@2023");
        assertFalse(password);
    }

    @Test
    public void testValidatePasswordWithCorrectPassword()
    {
        boolean password=userService.validatePassword("Password@2023");
        assertTrue(password);
    }


    @Test
    public void testFindUserByEmailWithValidEmail()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        UserEntity found=this.userService.findUserByEmailID("a@a.com");

        assertEquals(found,savedUser,"Mismatch");
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testFindUserByEmailWithInvalidEmail()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);
        UserEntity found=this.userService.findUserByEmailID("b@a.com");

        assertNotEquals(found,savedUser,"Mismatch");
        verify(userRepository).save(user);
    }

//    @Test
//    public void testToCheckSameUserExist(){
//        UserEntity user=new UserEntity();
//        user.setId(1);
//        user.setFirstName("keyush");
//        user.setLastName("patel");
//        user.setEmailID("a@a.com");
//        user.setPassword("Password@2023");
//        user.setSecurityAnswer("dog");
//
//        when(userRepository.save(user)).thenReturn(user);
//        //UserEntity savedUser=this.userService.signup(user);
//
//        boolean exist=this.userService.toCheckSameUser(user.getEmailID());
//        assertFalse(exist);
//        verify(userRepository).save(user);
//    }

    @Test
    public void testValidateEmailAndPasswordWithValidCredentials(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        boolean login=userService.validateEmailAndPassword("a@a.com","Password@2023");
        assertTrue(login);
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testValidateEmailAndPasswordWithInvalidCredentials(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        boolean login=userService.validateEmailAndPassword("a@a.com","password@2023");
        assertFalse(login);
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testForgotPasswordWithCorrectSecurityAnswer(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        boolean updated=userService.forgotPassword("a@a.com","dog","Password@1234");
        assertTrue(updated);
        assertNotEquals("Password@2023",user.getPassword(),"matched");
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testForgotPasswordWithIncorrectSecurityAnswer(){
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        boolean updated=userService.forgotPassword("a@a.com","cat","Password@1234");
        assertFalse(updated);
        assertEquals("Password@2023",user.getPassword(),"mismatched");
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testUpdateUserEntity()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.findById(1)).thenReturn(user);
        UserEntity newUser=new UserEntity();
        newUser.setId(1);
        newUser.setFirstName("keyush");
        newUser.setLastName("patel");
        newUser.setEmailID("ab@a.com");
        newUser.setPassword("Password@1234");
        newUser.setSecurityAnswer("dog");

        userService.updateUserEntity(newUser);
        assertEquals(newUser.getEmailID(),user.getEmailID(),"Mismatch");
        assertEquals(newUser.getPassword(),user.getPassword(),"Mismatch");
        verify(userRepository).save(user);
        verify(userRepository).findById(1);
    }

    @Test
    public void testUpdateUserEntityWithInvalidPassword()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.findById(1)).thenReturn(user);
        UserEntity newUser=new UserEntity();
        newUser.setId(1);
        newUser.setFirstName("keyush");
        newUser.setLastName("patel");
        newUser.setEmailID("ab@a.com");
        newUser.setPassword("Password");
        newUser.setSecurityAnswer("dog");

        userService.updateUserEntity(newUser);
        assertEquals(newUser.getEmailID(),user.getEmailID(),"Mismatch");
        assertNotEquals(newUser.getPassword(),user.getPassword(),"Matches");
        verify(userRepository).save(user);
        verify(userRepository).findById(1);
    }

    @Test
    public void testGetLoginUser()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        when(userRepository.save(user)).thenReturn(user);
        UserEntity savedUser=this.userService.signup(user);

        when(userRepository.findByEmailID(savedUser.getEmailID())).thenReturn(user);
        userService.validateEmailAndPassword("a@a.com","Password@2023");

        assertEquals(user,userService.getLogInUser());
        verify(userRepository).save(user);
        verify(userRepository).findByEmailID(savedUser.getEmailID());
    }

    @Test
    public void testUserInformation()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setEmailID("a@a.com");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        boolean check=userService.userInformation(user);
        assertTrue(check);
    }

    @Test
    public void testUserInformationWithoutEmail()
    {
        UserEntity user=new UserEntity();
        user.setId(1);
        user.setFirstName("keyush");
        user.setLastName("patel");
        user.setPassword("Password@2023");
        user.setSecurityAnswer("dog");

        boolean check=userService.userInformation(user);
        assertFalse(check);
    }
}
