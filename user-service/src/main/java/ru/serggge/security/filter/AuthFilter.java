package ru.serggge.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.serggge.security.authentication.AuthIdAuthenticationToken;
import ru.serggge.security.util.JwtUtil;
import java.io.IOException;
import java.util.Objects;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String barerToken = request.getHeader(AUTHORIZATION);
        if (Objects.nonNull(barerToken) && barerToken.startsWith("Barer ")) {
            String token = barerToken.substring(6);
            // извлекаем авторизационный айди пользователя
            String authId = jwtUtil.extractSubjectId(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(authId);
            // валидируем токен по айди пользователя и сроку действия токена
            if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                // аутентифицируем пользователя
                Authentication authentication = new AuthIdAuthenticationToken(userDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"invalid token\"}");
            }
        }
    }
}
