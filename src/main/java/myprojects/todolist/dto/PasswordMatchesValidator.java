package myprojects.todolist.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        if (userDto.getPassword() == null || userDto.getRepetedPassword() == null)
            return false;
        return userDto.getPassword().equals(userDto.getRepetedPassword());
    }
}
