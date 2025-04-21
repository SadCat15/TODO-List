package myprojects.todolist.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @Null(message = "ID field should be null")
    private Long id;
    @NotBlank(message = "Name must be not emplty")
    private String name;
    @NotBlank(message = "Emial must be not emplty")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password must be not emplty")
    private String password;
    @NotBlank(message = "Repeted password must be not emplty")
    private String repetedPassword;
    @Null(message = "ROLE field should be null")
    private String role;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordsMatching(){
        return password.equals(repetedPassword) && password != null;
    }
}
