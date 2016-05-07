package am.ik.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

@Component
public class Member implements Serializable {
    @SuppressWarnings("unchecked")
    public Map<String, Object> details() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null || !(authentication instanceof OAuth2Authentication)) {
            return Collections.emptyMap();
        }
        OAuth2Authentication auth = OAuth2Authentication.class.cast(authentication);
        return (Map<String, Object>) auth.getUserAuthentication().getDetails();
    }

    public String userId() {
        return details().getOrDefault("id", "00000000-0000-0000-0000-000000000000").toString();
    }
}
