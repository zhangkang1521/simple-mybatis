package org.zk.simplemybatis;

import org.zk.simplemybatis.type.*;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 */
public class Configuration {

    private Map<Class, TypeHandler> typeHandlerMapping = new HashMap<>();
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public Configuration() {
        initTypeHandlerMapping();
    }

    private void initTypeHandlerMapping() {
        typeHandlerMapping.put(Integer.class, new IntegerTypeHandler());
        typeHandlerMapping.put(String.class, new StringTypeHandler());
        typeHandlerMapping.put(Boolean.class, new BooleanTypeHandler());
        typeHandlerMapping.put(java.util.Date.class, new DateTypeHandler());
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        this.mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }

    public TypeHandler getTypeHandler(Class cls) {
        return typeHandlerMapping.get(cls);
    }

    public MappedStatement getMappedStatement(String mappedStatementId) {
        return mappedStatements.get(mappedStatementId);
    }

    public Executor newExecutor(Connection connection) {
        return new SimpleExecutor(connection, this);
    }

    public StatementHandler newStatementHandler(MappedStatement mappedStatement) {
        return new PreparedStatementHandler(mappedStatement, this);
    }

    public ResultSetHandler newResultSetHandler(MappedStatement mappedStatement) {
        return new DefaultResultSetHandler(mappedStatement, this);
    }
}
