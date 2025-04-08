package myprojects.todolist.exception;

import lombok.Getter;

@Getter
public class TaskException extends Exception {
    private final String userMessage;

    public TaskException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }
}
