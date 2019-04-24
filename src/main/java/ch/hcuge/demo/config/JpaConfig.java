package ch.hcuge.demo.config;

import ch.hcuge.demo.tenant.TenantAwareRoutingSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    @Bean
    public DataSource dataSource() {
        AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

        Map<Object,Object> targetDataSources = new HashMap<>();

        targetDataSources.put("concerto", concerto());
        targetDataSources.put("greco", greco());

        dataSource.setTargetDataSources(targetDataSources);
        dataSource.afterPropertiesSet();

        return dataSource;
    }

    private DataSource concerto() {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.addDataSourceProperty("url", "jdbc:postgresql://localhost:5432/concerto");
        dataSource.addDataSourceProperty("user", "concerto");
        dataSource.addDataSourceProperty("password", "concerto");

        return dataSource;
    }

    private DataSource greco() {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setInitializationFailTimeout(0);
        dataSource.setMaximumPoolSize(5);
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.addDataSourceProperty("url", "jdbc:postgresql://localhost:5433/greco");
        dataSource.addDataSourceProperty("user", "greco");
        dataSource.addDataSourceProperty("password", "greco");

        return dataSource;
    }

//    private static Properties getDefaultProperties() {
//
//        Properties defaultProperties = new Properties();
//
//        // Set sane Spring Hibernate properties:
//        defaultProperties.put("spring.jpa.show-sql", "true");
//        defaultProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//        defaultProperties.put("spring.datasource.initialize", "false");
//
//        // Prevent JPA from trying to Auto Detect the Database:
//        defaultProperties.put("spring.jpa.database", "postgresql");
//
//        // Prevent Hibernate from Automatic Changes to the DDL Schema:
//        defaultProperties.put("spring.jpa.hibernate.ddl-auto", "none");
//
//        return defaultProperties;
//    }

}
