package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TaskService taskService;
    @GetMapping("/")
    private String index(){
        return "index";
    }
}
