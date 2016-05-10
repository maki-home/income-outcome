package am.ik.home;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.Map;

@Component
public class Member implements Serializable {
    @Autowired
    ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public Map<String, Object> details() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication instanceof OAuth2Authentication)) {
            return Collections.emptyMap();
        }
        OAuth2Authentication auth = OAuth2Authentication.class.cast(authentication);
        OAuth2AuthenticationDetails details = OAuth2AuthenticationDetails.class.cast(auth.getDetails());
        String payload = details.getTokenValue().split("\\.")[1];
        try {
            return objectMapper.readValue(Base64Utils.decodeFromUrlSafeString(payload), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String userId() {
        return details().getOrDefault("user_id", "00000000-0000-0000-0000-000000000000").toString();
    }
}
