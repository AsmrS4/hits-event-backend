package com.backend.event_service.services.auth;

import com.backend.event_service.entities.Token;
import com.backend.event_service.repositories.auth.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService{
    private final BlackListRepository blackListRepository;
    private final Logger logger = LoggerFactory.getLogger(BlackListServiceImpl.class);
    @Override
    public void save(String token) {
        if(!blackListRepository.existsByToken(token)) {
            logger.info("Token saved in blacklist");
            logger.info("token length: " + token.toString().length());
            Token blacklistedToken = new Token();
            blacklistedToken.setToken(token);
            blackListRepository.save(blacklistedToken);
        }
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        logger.info("Token is blacklisted: " + blackListRepository.existsByToken(token));
        return blackListRepository.existsByToken(token);
    }
}
