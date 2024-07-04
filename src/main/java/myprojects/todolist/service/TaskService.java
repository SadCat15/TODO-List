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

    public void saveTask(Task task){
        tasksRepository.save(task);
    }
    public List<Task> findAll(){
        return tasksRepository.findAll();
    }
    public boolean isDataBaseEmpty(){
        return tasksRepository.count() == 0;
    }
    public void deleteStartTask(){
        if (tasksRepository.findById(1L).isPresent()) {
            tasksRepository.deleteById(1L);
        }
    }
    public void createStartTask(){
        Task task = new Task("Make Your first task", "Click on ADD NEW TASK to create a new task");
        saveTask(task);
    }
}
