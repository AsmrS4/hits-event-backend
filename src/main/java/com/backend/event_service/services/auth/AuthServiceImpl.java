package com.backend.event_service.services.auth;

import com.backend.event_service.dto.AccessToken;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.SignInRequest;
import com.backend.event_service.dto.SignUpRequest;
import com.backend.event_service.entities.User;
import com.backend.event_service.errors.AuthException;
import com.backend.event_service.errors.BadRequestException;
import com.backend.event_service.services.jwt.JwtService;
import com.backend.event_service.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final BlackListService blackListService;
    private final JwtService jwtService;
    private final Logger LOGGER;
    @Override
    public AccessToken login(SignInRequest signInRequest) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signInRequest.getLogin());
        if(userDetails==null) {
            throw new UsernameNotFoundException("Login failed");
        }
        if(userDetails.getUsername().isEmpty() || !userDetails.getPassword().equals(signInRequest.getPassword())) {
            LOGGER.info("Login failed");
            throw new BadRequestException("Login failed");
        }
        if(!userDetails.isEnabled()) {
            LOGGER.info("Account not confirmed or banned");
            throw new AuthException("Account not confirmed or banned");
        }
        LOGGER.info("its Ok");
        return jwtService.getAccessToken(userDetails);
    }

    //TODO: метод должен быть доступен только при обращении через бота. Подумать как это сделать
    @Override
    public RequestResponse registerUser(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signUpRequest.getLogin());
        if(!userDetails.getUsername().isEmpty()) {
            throw new BadRequestException("User with this login already exists");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setLogin(signUpRequest.getLogin());
        user.setChatId(signUpRequest.getChatId());
        user.setPassword(null);
        userService.save(user);

        return new RequestResponse(
                true,
                200,
                "Your account was registered in system. Please, wait account confirmation"
        );
    }

    @Override
    public RequestResponse logout() throws AuthenticationException {
        var token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        LOGGER.info(token);
        if(blackListService.isTokenBlacklisted(token)) {
            throw new AuthException("Unauthorized");
        }
        blackListService.save(token);
        return new RequestResponse(true, 200, "Logout");
    }
}
