package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

public class DaoFactory {
    @Bean
    public ProductDao productDao() {
        return new ProductDao(dataSource());
    }

    @Bean
    private DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            dataSource.setDriverClass((Class <? extends Driver>) Class.forName("com.mysql.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            new RuntimeException(e);
        }

        dataSource.setUrl("jdbc:mysql://localhost/portalservice?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("sslabflask");

        return dataSource;
    }
}
