package com.backend.event_service.services.user;

import com.backend.event_service.dto.*;
import com.backend.event_service.dto.user.UserDTO;
import com.backend.event_service.dto.user.UserEditDTO;
import com.backend.event_service.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> getRegistrationList();
    UserDTO getProfile();
    UserDTO editProfile(UserEditDTO userEditDTO);
    RequestResponse confirmUserAccount(Long id);
    RequestResponse rejectUserAccount(Long id);
    void save(User user);
    UserDetailsService userDetailsService();
}
