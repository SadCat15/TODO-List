package myprojects.todolist.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomFailureLoginHandler implements AuthenticationFailureHandler {

    private final static Logger logger = Logger.getLogger("Failure");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        logger.info("Błąd przy logowaniu: " + exception.getMessage());
        response.sendRedirect("/login?error");
    }
}
