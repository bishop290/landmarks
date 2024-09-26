package com.aston.landmarks.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

import static org.springframework.orm.jpa.vendor.Database.valueOf;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@ComponentScan("com.aston.landmarks")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.aston.landmarks.repository")
public class ApplicationConfig implements WebMvcConfigurer {
    private static final String ENTITY_PACKAGE = "com.aston.landmarks.model";

    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        driver.setUrl(environment.getProperty("spring.datasource.url"));
        driver.setUsername(environment.getProperty("spring.datasource.username"));
        driver.setPassword(environment.getProperty("spring.datasource.password"));
        return driver;
    }

    @Bean
    @DependsOn("liquibase")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(valueOf(environment.getProperty("spring.jpa.database")));
        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(environment.getProperty("spring.jpa.hibernate.ddl-auto")));
        vendorAdapter.setShowSql(Boolean.parseBoolean(environment.getProperty("spring.jpa.show-sql")));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(ENTITY_PACKAGE);
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(environment.getProperty("liquibase.change-log"));
        liquibase.setDropFirst(Boolean.parseBoolean(environment.getProperty("liquibase.drop-first")));
        liquibase.setDataSource(dataSource());
        liquibase.setShouldRun(Boolean.parseBoolean(environment.getProperty("liquibase.enabled")));
        return liquibase;
    }
}