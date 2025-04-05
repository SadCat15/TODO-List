package myprojects.todolist.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myprojects.todolist.exception.TaskException;
import myprojects.todolist.model.Task;
import myprojects.todolist.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class TaskService {
    private final TasksRepository tasksRepository;

    public void saveTask(Task task) throws TaskException {
        validateTask(task);
        tasksRepository.save(task);
    }

    public List<Task> findAll() {
        return tasksRepository.findAll();
    }

    public boolean isDataBaseEmpty() {
        return tasksRepository.count() == 0;
    }

    public Task createStartTask() {
        return new Task(1L, "Make Your first task", "Click on ADD NEW TASK to create a new task");
    }

    public void deleteTaskById(Long id) {
        if (tasksRepository.findById(id).isPresent()) tasksRepository.deleteById(id);
    }

    public void validateTask(Task task) throws TaskException {
        if (task.getName().isBlank() && task.getDescription().isBlank())
            throw new TaskException("Task's title and description can't be blank", "Set title or description of your task");
        else if (task.getName().isBlank()) {
            String[] words = task.getDescription().split(" ");
            int descriptionLength = words.length;
            int splitPoint = Math.min(descriptionLength / 2, 7);
            task.setName(Arrays.stream(words, 0, splitPoint).collect(Collectors.joining("\\s+")).trim());
            task.setDescription(Arrays.stream(words, splitPoint, descriptionLength).collect(Collectors.joining("\\s+")).trim());
        }
        if (task.getName().length() > 255)
            throw new TaskException("Title too long", "Title is too long. Max length is 255 charakters.");
        if (task.getDescription().length() > 255)
            throw new TaskException("Description too long", "Desription is too long. Max length is 255 charakters.");
    }
}
