package myprojects.todolist.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

    public void saveTask(Task task) {
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
        if (tasksRepository.findById(id).isPresent())
            tasksRepository.deleteById(id);
    }

    public void validateTask(Task task) {
        if (!task.getName().isBlank()) {
            return;
        } else if (task.getDescription().isBlank()) {
            task.setName("Set main goal of your task :)");
        } else {
            String[] words = task.getDescription().split(" ");
            int splitPoint = Math.min(words.length / 2, 7);
            task.setName(Arrays.stream(words, 0, splitPoint)
                    .collect(Collectors.joining(" "))
                    .trim());
            task.setDescription(Arrays.stream(words, splitPoint, words.length)
                    .collect(Collectors.joining(" "))
                    .trim());
        }
    }
}
