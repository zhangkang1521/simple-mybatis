package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor implements Executor {

    private Configuration configuration;
    private Connection connection;

    public SimpleExecutor(Connection connection, Configuration configuration) {
        this.connection = connection;
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
        StatementHandler statementHandler = configuration.newStatementHandler(ms);
        Statement statement = statementHandler.prepare(connection);
        statementHandler.parameterize(statement);
        return statementHandler.query(statement);
    }
}
