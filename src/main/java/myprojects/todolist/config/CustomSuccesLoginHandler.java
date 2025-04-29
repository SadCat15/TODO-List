package myprojects.todolist.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomSuccesLoginHandler implements AuthenticationSuccessHandler {

    private final static Logger logger = Logger.getLogger("Succees");
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        logger.info("✅ Zalogowano: " + authentication.getName());
        response.sendRedirect("/tasks");
    }
}
