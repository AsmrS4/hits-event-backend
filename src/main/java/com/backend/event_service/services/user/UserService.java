package com.backend.event_service.services.user;

import com.backend.event_service.dto.*;
import com.backend.event_service.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> getRegistrationList();
    User getProfile();
    User editProfile(UserEditDTO userEditDTO);
    RequestResponse applyUserAccount();
    RequestResponse rejectUserAccount();
    void save(User user);
    UserDetailsService userDetailsService();
}
