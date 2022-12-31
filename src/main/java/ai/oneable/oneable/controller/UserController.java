package ai.oneable.oneable.controller;

import ai.oneable.oneable.beans.User;
import ai.oneable.oneable.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//  For getting all the users from the database
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//  For getting the user by id
    @GetMapping("/user/{userid}")
    public User getUserById(@PathVariable("userid") String userid) {
        return userService.getUserById(userid);
    }

//  For storing new user data into DB
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

//  For updating the existing user
    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

//  Delete user by id
    @DeleteMapping("/user/{userid}")
    public User deleteUserById(@PathVariable("userid") String userid) {
        return userService.deleteUserById(userid);
    }
}
