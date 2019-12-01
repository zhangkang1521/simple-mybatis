package org.zk.simplemybatis;


import org.junit.Test;

import java.util.List;

public class SqlSessionTest {

    @Test
    public void testSelectList() {
        SqlSession sqlSession = new DefaultSqlSessionFactory().openSession();
        List list = sqlSession.selectList("org.zk.test.dao.UserDao.findAll", null);
    }
}
