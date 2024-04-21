package com.turkcell.pair1.invoiceservice.core.configuration;

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
        basePackages = "com.turkcell.pair1.invoiceservice.repository",
        entityManagerFactoryRef = "invoiceEntityManagerFactory",
        transactionManagerRef = "invoiceTransactionManager"
)
public class InvoiceDataSourceConfiguration {
    @Bean(name = "invoiceDataSource")
    public DataSource invoiceDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/invoice_service")
                .username("postgres")
                .password("test")
                .build();
    }

    @Bean(name = "invoiceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean invoiceEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("invoiceDataSource") DataSource invoiceDataSource) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("jakarta.persistence.validation.mode", "auto");
        return builder
                .dataSource(invoiceDataSource)
                .packages("com.turkcell.pair1.invoiceservice.entity")
                .properties(properties)
                .persistenceUnit("invoice")
                .build();
    }

    @Bean(name = "invoiceTransactionManager")
    public PlatformTransactionManager invoiceTransactionManager(
            @Qualifier("invoiceEntityManagerFactory") LocalContainerEntityManagerFactoryBean invoiceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(invoiceEntityManagerFactory.getObject()));
    }
}
