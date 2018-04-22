package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnectionMaker implements ConnectionMaker {
    @Value("{db.classname}")
    String className = "com.mysql.jdbc.Driver";
    @Value("{db.url}")
    String url = "jdbc:mysql://localhost/portalservice?characterEncoding=utf-8";
    @Value("{db.username}")
    String username = "root";
    @Value("{db.password}")
    String password = "sslabflask";

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(
                url, username, password);
    }
}
