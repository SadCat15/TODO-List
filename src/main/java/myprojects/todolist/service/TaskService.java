package myprojects.todolist.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myprojects.todolist.dto.TaskDto;
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
    private final UserService userService;

    public void saveTask(Task task) {
        tasksRepository.save(task);
    }

    public void saveTaskDto(TaskDto taskDto) {
        fillMissingNameIfNeeded(taskDto);
        saveTask(new Task(
                taskDto.getTitle(),
                taskDto.getDescription(),
                userService.findUserById(taskDto.getUserId())
        ));
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

    public void fillMissingNameIfNeeded(TaskDto task) {
        if (task.getTitle().isBlank()) {
            String[] words = task.getDescription().split("\\s+");
            int descriptionLength = words.length;
            int splitPoint = Math.min((descriptionLength / 2) + 1, 7);
            task.setTitle(Arrays.stream(words, 0, splitPoint).collect(Collectors.joining(" ")).trim());
            task.setDescription(Arrays.stream(words, splitPoint, descriptionLength).collect(Collectors.joining(" ")).trim());
        }
    }
}
