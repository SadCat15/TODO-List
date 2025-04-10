package myprojects.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
