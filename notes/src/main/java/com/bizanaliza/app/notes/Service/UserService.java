package com.bizanaliza.app.notes.Service;

import com.bizanaliza.app.notes.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public void createUser(User user);
    public void updateUser(long id, User user);
    public void deleteUser(long id);
    public User getUser(long id);
    public List<User> getAllUsers();
}
