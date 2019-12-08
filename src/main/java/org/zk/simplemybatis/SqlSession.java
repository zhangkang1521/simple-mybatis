package org.zk.simplemybatis;

import java.util.List;

public interface SqlSession {

    <E> List<E> selectList(String statement, Object parameter);

    <T> T getMapper(Class<T> mapperInterface);

    void close();
}
