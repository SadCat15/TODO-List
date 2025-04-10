package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.exception.TaskException;
import myprojects.todolist.model.Task;
import myprojects.todolist.service.TaskService;
import myprojects.todolist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    private final TaskService taskService;
    private final UserService userService;

    @DeleteMapping("/delete{id}")
    private ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(200).body("Task deleted");
    }

    @PostMapping("/add-task")
    private ResponseEntity<String> createTask(@RequestParam("name") String name, @RequestParam("description") String description) {
        try {
            taskService.saveTask(new Task(name, description));
            return ResponseEntity.status(201).body("Task created");
        } catch (TaskException e) {
            return ResponseEntity.status(400).body(e.getUserMessage());
        }
    }

    @PostMapping("/register-user")
    private ResponseEntity<String> saveUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            userService.registerUser(email, name, password);
            return ResponseEntity.status(200).body("User registered");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
