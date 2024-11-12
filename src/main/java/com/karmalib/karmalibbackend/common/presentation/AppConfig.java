package com.karmalib.karmalibbackend.common.presentation;

import jakarta.persistence.EntityManagerFactory;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.boot.context.properties.ConfigurationProperties;
        import org.springframework.boot.jdbc.DataSourceBuilder;
        import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.transaction.PlatformTransactionManager;

        import javax.sql.DataSource;

        @Configuration
        @EnableJpaRepositories(
        basePackages = {
                "com.karmalib.karmalibbackend.user.infrastructure.repositories",
                "com.karmalib.karmalibbackend.library.infrastructure.repositories",
                "com.karmalib.karmalibbackend.file.infrastructure.repositories",
                "com.karmalib.karmalibbackend.admin.infrastructure.repositories",
                "com.karmalib.karmalibbackend.forum.infrastructure.repositories"
        },
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager"
)
public class AppConfig {

    // Основной DataSource и EntityManagerFactory для всех доменов (включая Admin Context)
    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/karmalib")
                .username("piko")
                .password("password")
                .build();
    }

    @Bean(name = "appEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("appDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.karmalib.karmalibbackend.user.domain.entities",
                        "com.karmalib.karmalibbackend.library.domain.entities",
                        "com.karmalib.karmalibbackend.file.domain.entities",
                        "com.karmalib.karmalibbackend.admin.domain.entities",
                        "com.karmalib.karmalibbackend.forum.domain.entities"
                )
                .persistenceUnit("appPU")
                .build();
    }

    @Bean(name = "appTransactionManager")
    public PlatformTransactionManager appTransactionManager(
            @Qualifier("appEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
