package org.zk.simplemybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    public static final Logger log = LoggerFactory.getLogger(DefaultSqlSessionFactory.class);

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis", "sa", "");
            log.info("获取数据库连接成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Executor executor = configuration.newExecutor(connection);
        return new DefaultSqlSession(configuration, executor);
    }
}
