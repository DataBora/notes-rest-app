package com.bizanaliza.app.notes.Service.Implementation;

import com.bizanaliza.app.notes.Exception.User.UserExistsException;
import com.bizanaliza.app.notes.Exception.User.UserNameTooLongException;
import com.bizanaliza.app.notes.Exception.User.UserNicknameTooLongException;
import com.bizanaliza.app.notes.Exception.User.UserNotFoundException;
import com.bizanaliza.app.notes.Model.User;
import com.bizanaliza.app.notes.Repository.UserRepository;
import com.bizanaliza.app.notes.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //BUSINESS LOGIC
    @Override
    public void createUser(User user) {

        if(user.getNickname().length() > 20){
            throw new UserNicknameTooLongException("User nickname exceeds 20 characters.");
        }

        if(user.getUsername().length() > 20){
            throw new UserNameTooLongException("Username is longer than 20 characters.");
        }

        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserExistsException("User with that username already exists.");
        }

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(long id, User user) {

        // Check if the user with the given ID exists
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException("User with that ID does not exist.");
        }

        // Get the existing user
        User existingUser = existingUserOptional.get();

        // Update the existing user's properties with the new values
        existingUser.setNickname(user.getNickname());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        // Save the updated user
       userRepository.save(existingUser);

    }

    @Override
    public void deleteUser(long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User with that ID do not exists.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User with that ID doesn't exists.");
        }
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
