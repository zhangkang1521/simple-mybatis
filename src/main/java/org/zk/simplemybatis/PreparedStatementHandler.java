package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PreparedStatementHandler implements StatementHandler {

    private Configuration configuration;
    private MappedStatement mappedStatement;
    private ResultSetHandler resultSetHandler;



    public PreparedStatementHandler(MappedStatement mappedStatement, Configuration configuration) {
        this.mappedStatement = mappedStatement;
        this.configuration = configuration;
        this.resultSetHandler = configuration.newResultSetHandler(mappedStatement);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        return connection.prepareStatement(mappedStatement.getSourceSql());
    }

    @Override
    public void parameterize(Statement statement) {

    }

    @Override
    public <E> List<E> query(Statement statement) throws SQLException {
        statement.execute(mappedStatement.getSourceSql());
        return resultSetHandler.handleResultSets(statement);
    }
}
