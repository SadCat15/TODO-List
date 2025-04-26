package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")

public class HomeController {

    @GetMapping("/tasks")
    protected String index() {
        return "tasks";
    }

    @GetMapping("/add-task")
    private String addTask() {
        return "add-task";
    }

    @GetMapping("/registration")
    private String registration() {
        return "registration";
    }

    @GetMapping("/login")
    private String login(){
        return "login";
    }
}
