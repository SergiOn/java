package com.packt.sb5be.searchapp.datarest;

import com.packt.sb5be.searchapp.datarest.dao.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class CustomRestMvcConfiguration {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

//        return new RepositoryRestConfigurerAdapter() {
        return new RepositoryRestConfigurer() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Topic.class);
                config.setReturnBodyForPutAndPost(true);
            }
        };

    }

}
