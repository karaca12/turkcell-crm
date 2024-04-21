package com.turkcell.pair1.customerservice.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.turkcell.pair1.customerservice.repository",
        entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager"
)
public class CustomerDataSourceConfiguration {
    @Bean(name = "customerDataSource")
    public DataSource customerDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/customer_service")
                .username("postgres")
                .password("test")
                .build();
    }

    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("customerDataSource") DataSource customerDataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("jakarta.persistence.validation.mode", "auto");
        return builder
                .dataSource(customerDataSource)
                .packages("com.turkcell.pair1.customerservice.entity")
                .properties(properties)
                .persistenceUnit("customer")
                .build();
    }

    @Bean(name = "customerTransactionManager")
    public PlatformTransactionManager customerTransactionManager(
            @Qualifier("customerEntityManagerFactory") LocalContainerEntityManagerFactoryBean customerEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(customerEntityManagerFactory.getObject()));
    }
}
