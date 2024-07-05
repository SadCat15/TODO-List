package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.service.TaskService;
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
}
