package myprojects.todolist.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import myprojects.todolist.dto.UserDto;
import myprojects.todolist.model.User;
import myprojects.todolist.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void registerUser(UserDto userDto) {
        saveUser(new User(
                null,
                userDto.getEmail(),
                userDto.getName(),
                encodePassword(userDto.getPassword()),
                "USER"
        ));
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id = " + id));
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated account");
        }
        String email = authentication.getName();
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User with email: " + email + "not found")).getId();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
