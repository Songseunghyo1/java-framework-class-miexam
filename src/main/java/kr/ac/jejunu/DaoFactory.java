package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;

public class DaoFactory {
    @Bean
    public ProductDao productDao() {
        return new ProductDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
