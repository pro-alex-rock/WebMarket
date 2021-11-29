package com.service;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class SecurityService {

    public String getPasswordEncode(String login, String password) {
        String sole = UUID.randomUUID().toString();
        return (login + (DigestUtils.md5Hex(password + sole)));
    }
}
