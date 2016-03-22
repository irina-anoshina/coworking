package com.regr.web.config;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация функциональности доступа к БД
 * Created by maratische on 22.03.16.
 */
@Configuration
@PropertySource({"classpath:database.properties", "classpath:smtp.properties"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.regr.web.database.dao", entityManagerFactoryRef = "emf")
public class DatabaseConfig
{
    //Данные для поключения к БД. Берутся из системных переменных, которые устанавливаются OpenShift
    /** Класс драйвера JDBC */
    private static final String driverClassName = "org.postgresql.Driver";

    @Value("${db.name}")
    private String name;
    @Value("${db.host}")
    private String host;
    @Value("${db.port}")
    private String port;
    @Value("${db.username}")
    private String username;
    /** Пароль пользователя для входа в систему СУБД */
    @Value("${db.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /** Data source на нашу БД в PostgreSQL */
    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        if (host.equals("OPENSHIFT_POSTGRESQL_DB_HOST")) {
            host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
        }
        if (port.equals("OPENSHIFT_POSTGRESQL_DB_PORT")) {
            port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
        }
        if (name.equals("OPENSHIFT_APP_NAME")) {
            name = System.getenv("OPENSHIFT_APP_NAME");
        }

        String url = "jdbc:postgresql://"+host+":"+port+"/"+name;
        //Пока использую настройки для подключения к локальной БД,
        //при заливе на OpenShift использовать настройки выше
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    /** Объект для интеграции Liquibase со Spring */
    @Bean
    public SpringLiquibase springLiquibase()
    {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource());
        springLiquibase.setChangeLog("classpath:changelog.xml");
        return springLiquibase;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf()
    {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.regr.web.database.domain");
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    private Properties jpaProperties()
    {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager()
    {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }
}
