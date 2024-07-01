package myprojects.todolist.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import myprojects.todolist.model.Task;
import myprojects.todolist.repository.TasksRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class TaskService {
    private final TasksRepository tasksRepository;

    public void saveTask(Task task){
        tasksRepository.save(task);
    }
}
