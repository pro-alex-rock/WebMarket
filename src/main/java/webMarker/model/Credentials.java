package webMarker.model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Credentials {

    public String getPasswordEncode(String login, String password) {
        String sole = UUID.randomUUID().toString();
        return (login + (DigestUtils.md5Hex(password + sole)));
    }
}
