package com.backend.event_service.services.user;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.UserDTO;
import com.backend.event_service.dto.UserEditDTO;
import com.backend.event_service.entities.User;
import com.backend.event_service.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
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

    @Override
    public RequestResponse applyUserAccount() {
        return null;
    }

    @Override
    public RequestResponse rejectUserAccount() {
        return null;
    }
}
