package bitcoinrates.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBean {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Bean
    RestTemplate getRestTemplate() {
        return restTemplateBuilder.build();
    }
}