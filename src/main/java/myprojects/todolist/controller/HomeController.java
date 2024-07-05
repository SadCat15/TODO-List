package myprojects.todolist.controller;

import lombok.RequiredArgsConstructor;
import myprojects.todolist.model.Task;
import myprojects.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
    private final TaskService taskService;

    @GetMapping("/")
    protected String index(Model model) {
        if (taskService.isDataBaseEmpty()) {
             model.addAttribute("tasks", taskService.createStartTask());
        } else {
            model.addAttribute("tasks", taskService.findAll());
        }
        return "index";
    }

    @GetMapping("/addTask")
    private String addTask() {
        return "addTask";
    }

    @PostMapping("/addTask")
    private String createTask(@RequestParam("name") String name, @RequestParam("description") String description) {
        taskService.saveTask(new Task(name, description));
        return "redirect:/";
    }
}
