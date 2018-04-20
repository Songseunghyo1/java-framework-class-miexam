package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

public class DaoFactory {
    @Value("{db.classname}")
    private String className = "com.mysql.jdbc.Driver";
    @Value("{db.url}")
    private String url = "jdbc:mysql://localhost/portalservice?characterEncoding=utf-8";
    @Value("{db.username}")
    private String username = "root";
    @Value("{db.password}")
    private String password = "sslabflask";

    @Bean
    public ProductDao productDao() {
        return new ProductDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            new RuntimeException(e);
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
