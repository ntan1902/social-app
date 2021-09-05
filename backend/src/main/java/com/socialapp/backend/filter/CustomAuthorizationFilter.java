package com.socialapp.backend.filter;

import com.socialapp.backend.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Inside doFilterInternal of CustomAuthorizationFilter: {}", request.getServletPath());
        String path = request.getServletPath();
        if (path.contains("/api/v1/users")
                && !path.contains("/api/v1/users/login")
                && !path.contains("/api/v1/users/register")
                && !path.contains("/api/v1/users/refreshToken")) {
            try {
                String jwt = getJwtFromRequest(request);
                handleAuthorizationUser(request, jwt);
            } catch (Exception e) {
                log.error("Failed on set user authorization", e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleAuthorizationUser(HttpServletRequest request, String jwt) {
        if (StringUtils.hasText(jwt)
                && jwtUtil.validateToken(jwt)) {

            Long userId = jwtUtil.getUserIdFromJwt(jwt);
            List<String> roles = jwtUtil.getRolesFromJwt(jwt);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null,
                            authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
