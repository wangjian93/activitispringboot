package com.ivo.dataSourceConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wj
 * @Date: 2019-08-19 00:47
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryThird",
        transactionManagerRef="transactionManagerThird",
        basePackages= {"com.ivo.modules.eifapi"}) // 设置Repository所在位置
public class ThirdDataSourceConfig {

    @Autowired
    @Qualifier("thirdDataSource")
    private DataSource thirdDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Autowired
    private JpaProperties jpaProperties;

    @Value("${spring.jpa.hibernate.third-dialect}")
    private String thirdDialect;

    @Bean(name = "entityManagerThird")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryThird(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryThird")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryThird (EntityManagerFactoryBuilder builder) {
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.dialect", thirdDialect);// 设置对应的数据库方言
        jpaProperties.setProperties(map);
        Map<String, Object> propertiesMap= hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(thirdDataSource)
                .properties(propertiesMap)
                .packages("com.ivo.modules.eifapi") // 设置实体类所在位置
                .persistenceUnit("thirdPersistenceUnit")
                .build();
    }

   /* // 此方法在springBoot2.1以上版本不适合了

    private Map<String, String> getVendorProperties() {
    	return jpaProperties.getHibernateProperties(new HibernateProperties());
    }*/

    @Bean(name = "transactionManagerThird")
    public PlatformTransactionManager transactionManagerThird(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryThird(builder).getObject());
    }
}
