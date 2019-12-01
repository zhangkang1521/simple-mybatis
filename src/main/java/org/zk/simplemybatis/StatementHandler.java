package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    /**
     * 创建Statement
     * @param connection
     * @return
     */
    Statement prepare(Connection connection) throws SQLException;

    /**
     * 设置参数
     * @param statement
     */
    void parameterize(Statement statement);

    /**
     * 执行查询，并处理结果集
     * @param statement
     * @param <E>
     * @return
     */
    <E> List<E> query(Statement statement)  throws SQLException ;

}
