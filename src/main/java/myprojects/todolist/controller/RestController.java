package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.model.Task;
import myprojects.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    private final TaskService taskService;

    @DeleteMapping("/delete{id}")
    private void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

    @PostMapping("/add-task")
        private ResponseEntity<String> createTask(@RequestParam("name") String name, @RequestParam("description") String description) {
        Task task = new Task(name, description);
        taskService.saveTask(task);
        return ResponseEntity.status(201).body("Task created");
    }
}
