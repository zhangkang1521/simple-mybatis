package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor implements Executor {

    private Connection connection;

    public SimpleExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
        StatementHandler statementHandler = new PreparedStatementHandler(ms);
        Statement statement = statementHandler.prepare(connection);
        statementHandler.parameterize(statement);
        return statementHandler.query(statement);
    }
}
