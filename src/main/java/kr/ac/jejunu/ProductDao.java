package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    private final JdbcContext jdbcContext;

    public ProductDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public Product get(Long id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            String sql = "select * from product where id = ?";
            Object[] params = new Object[]{id};
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws SQLException {
        String sql = "insert into product(title, price) values (?, ?)";
        Object[] params = new Object[]{product.getTitle(), product.getPrice()};
        return jdbcContext.insert(sql, params);
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        String sql = "update product set title = ?, price = ? where id = ?";
        Object[] params = new Object[]{product.getTitle(), product.getPrice(), product.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete (Long id) throws SQLException, ClassNotFoundException {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }
}
