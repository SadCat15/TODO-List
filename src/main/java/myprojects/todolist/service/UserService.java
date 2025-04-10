package myprojects.todolist.service;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.model.User;
import myprojects.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        saveUser(user);
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
