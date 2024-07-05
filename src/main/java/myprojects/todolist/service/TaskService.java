package myprojects.todolist.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myprojects.todolist.model.Task;
import myprojects.todolist.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class TaskService {
    private final TasksRepository tasksRepository;

    public void saveTask(Task task) {
        tasksRepository.save(task);
    }

    public List<Task> findAll() {
        return tasksRepository.findAll();
    }

    public boolean isDataBaseEmpty() {
        return tasksRepository.count() == 0;
    }

    public Task createStartTask() {
        Task task = new Task(1L, "Make Your first task", "Click on ADD NEW TASK to create a new task");
        return task;
    }

    public void deleteTaskById(Long id) {
        if (tasksRepository.findById(id).isPresent())
            tasksRepository.deleteById(id);
    }
}
