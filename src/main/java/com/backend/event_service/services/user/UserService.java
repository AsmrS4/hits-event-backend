package com.backend.event_service.services.user;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.UserDTO;
import com.backend.event_service.dto.UserEditDTO;
import com.backend.event_service.entities.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getRegistrationList();
    User getProfile();
    User editProfile(UserEditDTO userEditDTO);
    RequestResponse applyUserAccount();
    RequestResponse rejectUserAccount();
}
