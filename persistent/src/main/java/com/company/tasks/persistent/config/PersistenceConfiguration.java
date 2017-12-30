package com.company.tasks.persistent.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.company.tasks.persistent.repository")
@EntityScan("com.company.tasks.persistent.entity")
public class PersistenceConfiguration {

}
