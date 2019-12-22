package org.zk.simplemybatis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zk.simplemybatis.type.TypeHandler;
import org.zk.simplemybatis.utils.BeanUtils;

import javax.management.ReflectionException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler {

    public static final Logger log = LoggerFactory.getLogger(DefaultResultSetHandler.class);

    private MappedStatement mappedStatement;
    private Configuration configuration;

    public DefaultResultSetHandler(MappedStatement mappedStatement, Configuration configuration) {
        this.mappedStatement = mappedStatement;
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        log.info("开始处理结果集");
        List list = new ArrayList();
        ResultSet rs = stmt.getResultSet();
        Class resultType = mappedStatement.getResultType();
        log.info("需要映射到类型：{}", resultType);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Object rowObj = BeanUtils.newInstance(resultType);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i).toLowerCase();
                Class returnType = BeanUtils.getFieldClass(resultType, columnName); // 数据库和Bean必须一致
                TypeHandler typeHandler = configuration.getTypeHandler(returnType);
                Object value = null;
                if (typeHandler != null) {
                    value = typeHandler.getResult(rs, columnName);
                }
                if (value != null) {
                    log.debug("设置属性：[{}]，值：[{}]", columnName, value);
                    BeanUtils.setProperties(rowObj, columnName, value);
                }
            }
            list.add(rowObj);
        }
        return list;
    }


}
