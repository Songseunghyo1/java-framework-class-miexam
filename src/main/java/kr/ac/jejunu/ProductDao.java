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

    public Product get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetProductStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public Long insert(Product product) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new InsertProductStatementStrategy(product);
        return jdbcContext.jdbcContextForInsert(statementStrategy);
    }

    public void update(Product product) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new UpdateProductStatemenStrategy(product);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete (Long id) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new DeleteProductStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
