package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnectionMaker implements ConnectionMaker {
    @Value("{db.classname}")
    private String className = "com.mysql.jdbc.Driver";
    @Value("{db.url}")
    private String url = "jdbc:mysql://localhost/portalservice?characterEncoding=utf-8";
    @Value("{db.username}")
    private String username = "root";
    @Value("{db.password}")
    private String password = "sslabflask";

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(
                url, username, password);
    }
}
