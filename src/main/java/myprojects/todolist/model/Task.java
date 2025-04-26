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
    private String title;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Task(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public Task(Long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
