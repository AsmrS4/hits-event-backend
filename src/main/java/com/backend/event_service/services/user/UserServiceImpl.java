package com.backend.event_service.services.user;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.user.UserDTO;
import com.backend.event_service.dto.user.UserEditDTO;
import com.backend.event_service.entities.User;
import com.backend.event_service.enums.AccountStatus;
import com.backend.event_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        List<User> users = userRepository.getUnverifiedAccounts();
        return users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getLogin());
            userDTO.setChatId(user.getChatId());
            userDTO.setRole(user.getRole());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setAccountStatus(user.getAccountStatus());
            userDTO.setCreateTime(user.getCreateTime());
            return userDTO;
        }).toList();
    }
    @Override
    public UserDTO getProfile() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if(login == null) {
            throw new RuntimeException("Unauthorized");
        }
        User user = userRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setChatId(user.getChatId());
        userDTO.setRole(user.getRole());
        userDTO.setAccountStatus(userDTO.getAccountStatus());
        userDTO.setCreateTime(user.getCreateTime());

        return userDTO;
    }

    @Override
    public UserDTO editProfile(UserEditDTO userEditDTO) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if(login == null) {
            throw new RuntimeException("Unauthorized");
        }
        User user = userRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        user.setFirstName(userEditDTO.getFirstName());
        user.setLastName(userEditDTO.getLastName());
        userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setChatId(user.getChatId());
        userDTO.setRole(user.getRole());
        userDTO.setAccountStatus(userDTO.getAccountStatus());
        userDTO.setCreateTime(user.getCreateTime());

        return userDTO;
    }

    @Override
    public RequestResponse confirmUserAccount(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if(user.getAccountStatus().equals(AccountStatus.CONFIRMED)) {
            throw new RuntimeException("User's account already confirmed");
        }
        user.setAccountStatus(AccountStatus.CONFIRMED);
        userRepository.save(user);
        //TODO:сделать рассылку ТГ боту + выдать пароль
        return new RequestResponse(true, 200, "User's account was confirmed");
    }

    @Override
    public RequestResponse rejectUserAccount(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        if(user.getAccountStatus().equals(AccountStatus.REJECTED)) {
            throw new RuntimeException("User's account already rejected");
        }
        user.setAccountStatus(AccountStatus.REJECTED);
        userRepository.save(user);
        //TODO:сделать рассылку ТГ боту
        return new RequestResponse(true, 200, "User's account was rejected");
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
