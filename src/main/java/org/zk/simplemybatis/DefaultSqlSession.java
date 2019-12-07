package org.zk.simplemybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    public static final Logger log = LoggerFactory.getLogger(DefaultSqlSession.class);

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        log.info("根据{}获取MappedStatement", statement);
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        try {
            return executor.query(mappedStatement, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        executor.close();
    }
}
