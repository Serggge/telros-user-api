package ru.serggge.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.serggge.util.JwtUtil;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // получили токен из заголовка
        String barerToken = request.getHeader(AUTHORIZATION);
        // проверили, что токен существует и тип Barer
        if (Objects.nonNull(barerToken) && barerToken.startsWith("Barer ")) {
            // получаем логин из токена
            String token = barerToken.substring(7);
            String login = jwtUtil.extractLogin(token);
            // проверили ещё раз на нулл, что логин совпадает и токен не истёк по времени
            if (Objects.nonNull(login) && jwtUtil.validateToken(token, login)) {
                // загрузили и аутентифицировали пользователя
                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                                     .setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}