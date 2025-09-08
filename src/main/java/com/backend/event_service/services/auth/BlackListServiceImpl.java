package com.backend.event_service.services.auth;

import com.backend.event_service.entities.Token;
import com.backend.event_service.repositories.auth.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService{
    private final BlackListRepository blackListRepository;
    @Override
    public void save(String token) {
        if(!blackListRepository.existsByToken(token)) {
            Token blacklistedToken = new Token();
            blacklistedToken.setPayload(token);
            blackListRepository.save(blacklistedToken);
        }
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return blackListRepository.existsByToken(token);
    }
}
