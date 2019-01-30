package com.westbank.config;

import com.westbank.PortalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
})
@EnableJpaRepositories(basePackages = {"com.westbank.repository", "com.westbank.service"})
@ComponentScan(basePackages = {"com.westbank.repository", "com.westbank.service"})
@EntityScan(value = "com.westbank.entity")
public class DataConfig {

    @Autowired
    private Environment env;

    /*
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(env.getProperty("spring.datasource.url"))
                .username(env.getProperty("spring.datasource.username"))
                .password(env.getProperty("spring.datasource.password"))
                .build();
    }
    */

    /*
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource) {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("com.westbank.entity");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setDataSource(dataSource);

        final Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("spring.datasource.platform"));
        // https://stackoverflow.com/a/36452366/339302
        // https://stackoverflow.com/a/25293929/339302
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();
        return factory;
    }
    */

    /*
    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(factory);
        return txManager;
    }
    */
}
