package myprojects.todolist.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myprojects.todolist.dto.TaskDto;
import myprojects.todolist.dto.UserDto;
import myprojects.todolist.exception.TaskException;
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
    private ResponseEntity<String> createTask(@Valid @RequestBody TaskDto taskDto) {
        try {
            taskService.saveTaskDto(taskDto);
            return ResponseEntity.status(201).body("Task created");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @PostMapping("/register-user")
    private ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.status(200).body("User registered");
    }
}
