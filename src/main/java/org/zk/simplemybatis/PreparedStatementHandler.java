package org.zk.simplemybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PreparedStatementHandler implements StatementHandler {

    public static final Logger log = LoggerFactory.getLogger(PreparedStatementHandler.class);

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
        // TODO 待实现
    }

    @Override
    public <E> List<E> query(Statement statement) throws SQLException {
        log.info("执行sql:{}", mappedStatement.getSourceSql());
        statement.execute(mappedStatement.getSourceSql());
        return resultSetHandler.handleResultSets(statement);
    }
}
