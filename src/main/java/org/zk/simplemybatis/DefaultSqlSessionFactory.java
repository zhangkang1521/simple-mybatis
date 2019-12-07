package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zk", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Executor executor = configuration.newExecutor(connection);
        return new DefaultSqlSession(configuration, executor);
    }
}
