package org.zk.simplemybatis;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Executor executor;

    public DefaultSqlSession(Executor executor) {
        this.executor = executor;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        // TODO 从配置中获取
        MappedStatement mappedStatement = new MappedStatement();
        mappedStatement.setSourceSql("select * from tb_user");
        mappedStatement.setResultType(User.class);
        try {
            return executor.query(mappedStatement, parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
