package org.zk.simplemybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public PreparedStatement prepare(Connection connection) throws SQLException {
        log.info("prepare statement {}", mappedStatement.getSourceSql());
        return connection.prepareStatement(mappedStatement.getSourceSql());
    }

    @Override
    public void parameterize(PreparedStatement statement) {
        // TODO 待实现
    }

    @Override
    public <E> List<E> query(PreparedStatement statement) throws SQLException {
        statement.execute();
        return resultSetHandler.handleResultSets(statement);
    }
}
