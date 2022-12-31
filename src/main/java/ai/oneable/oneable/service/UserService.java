package ai.oneable.oneable.service;

import ai.oneable.oneable.beans.User;
import ai.oneable.oneable.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUserById(String userid) {
        User user = userRepository.findById(userid).get();
        if(user != null) {
            userRepository.deleteById(userid);
            return user;
        }
        return null;
    }

    public User getUserById(String userid) {
        return userRepository.findById(userid).get();
    }
}
