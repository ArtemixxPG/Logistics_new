package logistics.parsers;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

@Configuration
@ComponentScan("logistics")
public class ConsoleConfig {

    @Bean
    public DataSource dataSource() {

        PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
        pgDataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        pgDataSource.setUser("user");
        pgDataSource.setPassword("user");
        return pgDataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HashMap<String, String > hibernatePropertys = new HashMap<>();
        hibernatePropertys.put("hibernate.hbm2ddl.auto","update");
        hibernatePropertys.put("hibernate.connection.driver_class","org.postgresql.Driver");
        hibernatePropertys.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL10Dialect");
        hibernatePropertys.put("hibernate.show_sql","true");
        hibernatePropertys.put("hibernate.generate_statistics","true");
        hibernatePropertys.put("hibernate.connection.charSet","UTF-8");
        hibernatePropertys.put("hibernate.format_sql","true");
        hibernatePropertys.put("hibernate.use_sql_comments","true");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("logistics");
        factory.setDataSource(dataSource());
        factory.setJpaPropertyMap(hibernatePropertys);

        return factory;
    }
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactory);
//        manager.setDataSource(dataSource());
//        return manager;
//    }
}
//spring.datasource.driver-class-name=org.postgresql.Driver
//        spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
//        spring.datasource.username=user
//        spring.datasource.password=user
//
//        # Hibernate
//        spring.jpa.generate-ddl=true
//        spring.jpa.dialect=org.hibernate.dialect.PostgreSQL10Dialect
//        spring.jpa.show-sql=true
//        spring.jpa.hibernate.ddl-auto=update