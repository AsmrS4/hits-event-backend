package com.backend.event_service.services.auth;

public interface BlackListService {
    void save(String token);
    boolean isTokenBlacklisted(String token);
}
