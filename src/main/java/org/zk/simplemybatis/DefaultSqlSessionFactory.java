package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    @Override
    public SqlSession openSession() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zk", "root", "123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Executor executor = new SimpleExecutor(connection);
        return new DefaultSqlSession(executor);
    }
}
