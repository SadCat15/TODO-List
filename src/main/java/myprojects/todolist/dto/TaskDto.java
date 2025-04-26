package myprojects.todolist.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    @Null(message = "ID field should be null")
    private Long id;
    @Size(max = 255)
    private String title;
    @Size(max = 255)
    private String description;
    @NotNull(message = "User ID must be not emplty")
    private Long userId;

    @AssertTrue(message = "Task's title and description can't be blank")
    public boolean isValid() {
        return !((title == null || title.isBlank()) && (description == null || description.isBlank()));
    }
}
