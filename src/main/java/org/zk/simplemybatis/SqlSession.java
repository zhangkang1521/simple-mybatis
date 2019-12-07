package org.zk.simplemybatis;

import java.util.List;

public interface SqlSession {

    <E> List<E> selectList(String statement, Object parameter);

    void close();
}
