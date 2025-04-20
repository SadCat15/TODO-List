package myprojects.todolist.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import myprojects.todolist.model.User;
import myprojects.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final static Logger logger = Logger.getLogger("a");

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void registerUser(String email, String name, String password){
        User user = new User(
                null,
                email,
                name,
                password,
                "USER"
                );
        encodePassword(user);
        logger.info(user.toString());
        saveUser(user);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id = " + id));
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
