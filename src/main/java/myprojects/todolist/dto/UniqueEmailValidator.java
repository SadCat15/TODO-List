package myprojects.todolist.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import myprojects.todolist.repository.UserRepository;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;
    private final static Logger logger = Logger.getLogger("email");
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email.isBlank() || email == null)
            return true;
        logger.info(email);
        logger.info(String.valueOf(userRepository.existsByEmail(email)));
        return !userRepository.existsByEmail(email);
    }
}
