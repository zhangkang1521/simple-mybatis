package org.zk.simplemybatis;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;
}
