//package com.cts.CityCare.CityCare.JwtFilter;
//
//
//
//
//import com.cts.CityCare.CityCare.entity.User;
//import com.cts.CityCare.CityCare.repository.UserRepository;
//import com.cts.CityCare.CityCare.security.UserDetailsServiceImpl;
//import com.cts.CityCare.CityCare.service.JWTService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    ApplicationContext context;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        String token = null;
//        String username = null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//            username = jwtService.extractUserName(token);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = context.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);
//            if (jwtService.validateToken(token, userDetails)) {
//               User user=userRepository.findByEmail(username).get();
//
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null, userDetails.getAuthorities());
//               // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}


package com.cts.CityCare.CityCare.JwtFilter;

import com.cts.CityCare.CityCare.entity.User;
import com.cts.CityCare.CityCare.repository.UserRepository;
import com.cts.CityCare.CityCare.security.UserDetailsServiceImpl;
import com.cts.CityCare.CityCare.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j // Useful for debugging token issues
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // 1. Extract Token and Username
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUserName(token);
            } catch (Exception e) {
                log.error("Could not extract username from token: {}", e.getMessage());
            }
        }

        // 2. Authenticate if username exists and no current authentication exists
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Fetch UserDetails using your Service
            UserDetails userDetails = context.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);

            // 3. Validate Token
            if (userDetails != null && jwtService.validateToken(token, userDetails)) {

                // 4. Safe Database Lookup
                // We use ifPresent to avoid the ".get()" NullPointerException
                userRepository.findByEmail(username).ifPresent(user -> {

                    // Create the Auth Token
                    // Standard practice: Pass userDetails as the Principal
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    // Link the request details (IP, Session ID, etc.)
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Finalize the Security Context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                });
            }
        }

        // 5. Continue the filter chain
        filterChain.doFilter(request, response);
    }
}