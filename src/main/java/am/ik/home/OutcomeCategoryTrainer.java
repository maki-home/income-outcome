package am.ik.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;

@Component
@Slf4j
public class OutcomeCategoryTrainer {
    @Autowired
    AsyncRestTemplate restTemplate;
    @Value("${predict.uri}")
    String predictUri;

    public void train(String outcomeName, Integer categoryId) {
        try {
            restTemplate.postForEntity(UriComponentsBuilder.fromUriString(predictUri)
                            .pathSegment("train")
                            .queryParam("outcomeName", UriUtils.encodeQueryParam(outcomeName, "UTF-8"))
                            .queryParam("categoryId", categoryId).build(true).toUri(),
                    HttpEntity.EMPTY, String.class)
                    .addCallback(s -> log.info("success : {}", s), e -> log.error("error", e));
        } catch (UnsupportedEncodingException e) {
            log.error("error", e);
        }
    }
}
