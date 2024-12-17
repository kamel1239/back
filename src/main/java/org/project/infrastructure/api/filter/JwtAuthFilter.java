/*
  2024
*/
package org.project.infrastructure.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.user.repository.UserRepository;
import org.project.domain.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Service
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        final var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // TODO create user without auth
            filterChain.doFilter(request, response);
            return;
        }

        //  Check if the username is valid and authentication status
        final var jwt = authorizationHeader.substring(7);
        try {
            final var tokenInfoModel = authService.extractTokenInfo(jwt);
            if (SecurityContextHolder.getContext().getAuthentication() == null
                && !tokenInfoModel.isExpired()) {
                var user = userRepository.findUserByToken(tokenInfoModel);
                //  Set the authentication in the context
                user.setCurrentSessionId(tokenInfoModel.id());
                var authentication = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities());
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.warn("Cannot set user authentication due to exception of type {} and message : {}",
                e.getClass(), e.getMessage());
        }
        filterChain.doFilter(request, response);
    }


}
