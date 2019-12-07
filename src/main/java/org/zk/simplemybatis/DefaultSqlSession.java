package org.zk.simplemybatis;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        try {
            return executor.query(mappedStatement, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
