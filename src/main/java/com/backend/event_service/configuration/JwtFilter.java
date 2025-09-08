package com.backend.event_service.configuration;

import com.backend.event_service.services.auth.BlackListService;
import com.backend.event_service.services.jwt.JwtService;
import com.backend.event_service.services.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private static final String BEARER_PREFIX = "Bearer ";
    private static  final String HEADER_NAME = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;
    private final BlackListService blackListService;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var authHeader = request.getHeader(HEADER_NAME);

        if(!authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
        }
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        var userLogin = jwtService.getUserLogin(jwt);

        if(userLogin!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            if(blackListService.isTokenBlacklisted(jwt)) {
                filterChain.doFilter(request, response);
            }
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userLogin);
            if(jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
