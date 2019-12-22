package org.zk.simplemybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    /**
     * 创建Statement
     * @param connection
     * @return
     */
    PreparedStatement prepare(Connection connection) throws SQLException;

    /**
     * 设置参数
     * @param statement
     */
    void parameterize(PreparedStatement statement);

    /**
     * 执行查询，并处理结果集
     * @param statement
     * @param <E>
     * @return
     */
    <E> List<E> query(PreparedStatement statement)  throws SQLException ;

}
