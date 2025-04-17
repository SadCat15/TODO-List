package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")

public class HomeController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    protected String index(Model model) {
        if (taskService.isDataBaseEmpty()) {
            taskService.createStartTask();
            model.addAttribute("tasks", taskService.createStartTask());
        } else {
            model.addAttribute("tasks", taskService.findAll());
        }
        return "index";
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
