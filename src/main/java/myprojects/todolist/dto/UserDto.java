package myprojects.todolist.dto;

import jakarta.validation.constraints.*;
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
    @NotNull(message = "Name must be not emplty")
    private String name;
    @NotBlank(message = "Emial must be not emplty")
    @NotNull(message = "Emial must be not emplty")
    @Email(message = "Email should be valid")
    @UniqueEmail
    private String email;
    @NotBlank(message = "Password must be not emplty")
    @NotNull(message = "Password must be not emplty")
    private String password;
    @NotBlank(message = "Repeated password must be not emplty")
    @NotNull(message = "Repeated password must be not emplty")
    private String repeatedPassword;
    @Null(message = "ROLE field should be null")
    private String role;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordsMatching() {
        return password.equals(repeatedPassword) && password != null;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
