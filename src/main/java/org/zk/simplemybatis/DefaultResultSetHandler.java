package org.zk.simplemybatis;

import org.apache.commons.lang3.StringUtils;

import javax.management.ReflectionException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler {

    private MappedStatement mappedStatement;

    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        List list = new ArrayList();
        ResultSet rs = stmt.getResultSet();
        Class cls = mappedStatement.getResultType();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Object rowObj = null;
            try {
                rowObj = cls.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                try {
                    Method method = cls.getMethod("get" + StringUtils.capitalize(columnName));
                    Class returnType = method.getReturnType();
                    Object value = null;
                    // 使用TypeHandler
                    if (String.class.isAssignableFrom(returnType)) {
                        value = rs.getString(columnName);
                    } else if (int.class.isAssignableFrom(returnType)) {
                        value = rs.getInt(columnName);
                    }
                    if (value != null) {
                        Method setMethod = cls.getMethod("set" + StringUtils.capitalize(columnName), returnType);
                        setMethod.invoke(rowObj, value);
                    }
                } catch (NoSuchMethodException e) {
                    // ignore
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
            list.add(rowObj);
        }
        return list;
    }


}
