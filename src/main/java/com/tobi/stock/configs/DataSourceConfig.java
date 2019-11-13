package com.teamapt.profectus.visa.settlementrecon.lib.config;

import com.teamapt.audit.lib.utils.AutowireUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.DataSourceHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TEAMAPT-AWAL on 17/02/2018.
 */
@Order(-100)
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.teamapt.profectus.settlementrecon.lib",
                "com.teamapt.profectus.visa"},
        entityManagerFactoryRef = "visaSettlementReconEntityManagerFactory",
        transactionManagerRef = "settlementReconTransactionManager")
public class DataSourceConfig {

    @Value("${datasource.primary.hibernate.dialect:org.hibernate.dialect.SQLServerDialect}")
    private String hibernateDialect;

    @Value("${datasource.primary.hbm2ddl.auto:update}")
    private String hibernateHbmDDL;
    @Value("${hibernate.id.new_generator_mappings:false}")
    private String isUsingIdGenerator;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean visaSettlementReconEntityManagerFactory() {


        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        Map<String, String> prop = new HashMap<>();
        prop.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        prop.put("hibernate.dialect", hibernateDialect);
        prop.put("hibernate.hbm2ddl.auto", hibernateHbmDDL);
        prop.put("hibernate.id.new_generator_mappings", isUsingIdGenerator);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(primaryDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setJpaPropertyMap(prop);

        factoryBean.setPackagesToScan("com.teamapt.profectus.settlementrecon", "com.teamapt.profectus.visa");
        return factoryBean;


    }


    @Bean(name = "settlementReconTransactionManager")
    @Primary
    PlatformTransactionManager settlementReconTransactionManager() {
        return new JpaTransactionManager(visaSettlementReconEntityManagerFactory().getObject());
    }


    @Bean(name = "inMemoryDs")
    public DataSource inMemoryDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .setName("NIBSSSettlementRecon")
                .addScript("classpath:schema-h2.sql")
                .build();
    }


    @Bean(name = "activitiInMemoryDatasource")
    public DataSource inMemoryDatasource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .setName("nibssActivitiInMemoryDb")
                .addScript("classpath:activiti-schema-h2.sql").build();
    }

    @Bean(name = "cbaDatasource")
    @ConfigurationProperties(prefix = "datasource.cbaDatasource")
    @ConditionalOnProperty(value = "datasource.cbaDatasource.url", matchIfMissing = false)
    public DataSource cbaDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public AutowireUtil autowireHelper() {
        return AutowireUtil.getInstance();
    }

    @Bean
    public DataSourceHealthIndicator primaryDataSourceHealthIndicator() {
        return new DataSourceHealthIndicator(primaryDataSource());
    }

}
