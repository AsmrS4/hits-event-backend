package com.backend.event_service.services.user;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.UserDTO;
import com.backend.event_service.dto.UserEditDTO;
import com.backend.event_service.entities.User;
import com.backend.event_service.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepository userRepository;
    //TODO:доступен только админу
    @Override
    public List<UserDTO> getRegistrationList() {
        return null;
    }

    @Override
    public User getProfile() {
        return null;
    }

    @Override
    public User editProfile(UserEditDTO userEditDTO) {
        return null;
    }
    //TODO:доступен только админу
    @Override
    public RequestResponse applyUserAccount() {
        return null;
    }
    //TODO:доступен только админу
    @Override
    public RequestResponse rejectUserAccount() {
        return null;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByLogin;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByLogin(username);
    }
}
