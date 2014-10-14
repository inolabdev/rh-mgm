package mz.inolabdev.rh.dao;

import java.util.List;

import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.exception.DuplicateUserException;
import mz.inolabdev.rh.exception.UserNotFoundException;

public interface UserDao {

    public void addUser(User user) throws DuplicateUserException;

    public User getUser(Long userId) throws UserNotFoundException;
    
    public User getUser(String username) throws UserNotFoundException;

    public void updateUser(User user) throws UserNotFoundException;

    public void deleteUser(Long userId) throws UserNotFoundException;

    public List<User> getUsers();
}
