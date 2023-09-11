package com.bizanaliza.app.notes.Controler;

import com.bizanaliza.app.notes.Exception.User.UserExistsException;
import com.bizanaliza.app.notes.Exception.User.UserNameTooLongException;
import com.bizanaliza.app.notes.Exception.User.UserNicknameTooLongException;
import com.bizanaliza.app.notes.Exception.User.UserNotFoundException;
import com.bizanaliza.app.notes.Model.User;
import com.bizanaliza.app.notes.Response.UserResponseHandler;
import com.bizanaliza.app.notes.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get for retrieving all users
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    //get for retrieving users by ID
    //TODO: implement response handler
    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") long id){
        return  userService.getUser(id);
    }

    //post gor creating the user
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User successfully created!");
        }catch (UserNicknameTooLongException e){
            return UserResponseHandler.responseBuilder("User nickname is longer than 20 characters", HttpStatus.FORBIDDEN, null);
        }catch (UserNameTooLongException e){
            return UserResponseHandler.responseBuilder("Username exceeds 20 characters",HttpStatus.FORBIDDEN, null);
        } catch (UserExistsException e) {
            return UserResponseHandler.responseBuilder("User with that username already exists!", HttpStatus.CONFLICT, null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") long id, @RequestBody User user){
        try {
            userService.updateUser(id, user);
            return UserResponseHandler.responseBuilder("User updated successfully!", HttpStatus.OK, null);
        }catch (UserNotFoundException e) {
            return UserResponseHandler.responseBuilder("User with that ID does not exist.", HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "User successfully deleted!";
    }


}
