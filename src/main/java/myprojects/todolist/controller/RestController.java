package myprojects.todolist.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import myprojects.todolist.dto.TaskDto;
import myprojects.todolist.dto.UserDto;
import myprojects.todolist.model.Task;
import myprojects.todolist.service.TaskService;
import myprojects.todolist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    private final TaskService taskService;
    private final UserService userService;
    private final static Logger logger = Logger.getLogger("users_logger");

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
        logger.info(userDto.toString());
        userService.registerUser(userDto);
        return ResponseEntity.status(200).body("User registered");
    }

    @GetMapping("/get-current-user-id")
    public ResponseEntity<String> currentUserId() {
        try {
            Long id = userService.getCurrentUserId();
            return ResponseEntity.status(200).body(String.valueOf(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@RequestParam Long user_id) {
        List<Task> tasks = taskService.getTasksByUserId(user_id);
        logger.info(String.valueOf(tasks.isEmpty()));
        if (tasks.isEmpty()) {
            taskService.createDefaultTask(user_id);
            tasks = taskService.getTasksByUserId(user_id);
        }
        List<TaskDto> tasksDto = new ArrayList<>();
        for (Task task : tasks) tasksDto.add(new TaskDto(task.getId(), task.getTitle(), task.getDescription(), null));
        return ResponseEntity.status(200).body(tasksDto);
    }
}
