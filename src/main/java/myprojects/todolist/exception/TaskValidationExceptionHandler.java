package myprojects.todolist.exception;

import myprojects.todolist.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskValidationExceptionHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> handleTaskException(TaskException ex){
        return ResponseEntity.status(400).body(ex.getUserMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleTaskDtoExcpetion(MethodArgumentNotValidException ex) throws TaskException {
        Object target = ex.getBindingResult().getTarget();
        if (!(target instanceof TaskDto)) {
            return;
        }
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String field = error.getField();
            String code = error.getCode();

            if ("valid".equals(code)) {
                throw new TaskException("Task's title and description can't be blank", "Set title or description of your task");
            }
            if ("title".equals(field)) {
                if ("Size".equals(code)) {
                    throw new TaskException("Title too long", "Title is too long. Max length is 255 charakters.");
                }
            }
            if ("description".equals(field)) {
                if ("Size".equals(code)) {
                    throw new TaskException("Description too long", "Description is too long. Max length is 255 charakters.");
                }
            }
        }
    }
}
