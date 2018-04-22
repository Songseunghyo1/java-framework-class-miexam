package kr.ac.jejunu;

import lombok.AllArgsConstructor;
import lombok.Cleanup;

import javax.sql.DataSource;
import java.sql.*;

@AllArgsConstructor
public class JdbcContext {
    final DataSource dataSource;


    Product jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Product product = null;

        @Cleanup
        Connection connection = dataSource.getConnection();
        @Cleanup
        PreparedStatement preparedStatement = statementStrategy.makeStatement(connection);
        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setPrice(resultSet.getInt("price"));
        }
        return product;
    }

    Long jdbcContextForInsert(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            id = resultSet.getLong(1);

        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

    void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
               return preparedStatement;
            };
        jdbcContextForUpdate(statementStrategy);
    }

    Long insert(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                return preparedStatement;
            };
        return jdbcContextForInsert(statementStrategy);
    }

   Product queryForObject(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                return preparedStatement;
            };
        return jdbcContextForGet(statementStrategy);
    }
}
