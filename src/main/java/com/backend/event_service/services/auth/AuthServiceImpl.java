package com.backend.event_service.services.auth;

import com.backend.event_service.dto.AccessToken;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.SignInRequest;
import com.backend.event_service.dto.SignUpRequest;
import com.backend.event_service.entities.User;
import com.backend.event_service.services.jwt.JwtService;
import com.backend.event_service.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserService userService;
    private final BlackListService blackListService;
    private final JwtService jwtService;
    @Override
    public AccessToken login(SignInRequest signInRequest) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signInRequest.getLogin());
        if(!userDetails.isEnabled()) {
            throw  new RuntimeException("Account not confirmed or banned");
        }
        if(userDetails.getUsername().isEmpty() || userDetails.getPassword().equals(signInRequest.getPassword())) {
            throw new RuntimeException("Login failed");
        }
        return jwtService.getAccessToken(userDetails);
    }

    //TODO: метод должен быть доступен только при обращении через бота. Подумать как это сделать
    @Override
    public RequestResponse registerUser(SignUpRequest signUpRequest) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(signUpRequest.getLogin());
        if(!userDetails.getUsername().isEmpty()) {
            throw new RuntimeException("User with this login already exists");
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
    public RequestResponse logout() {
        var token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if(blackListService.isTokenBlacklisted(token)) {
            throw new RuntimeException("Unauthorized");
        }
        blackListService.save(token);
        return new RequestResponse(true, 200, "Logout");
    }
}
