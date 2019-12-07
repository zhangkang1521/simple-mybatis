package org.zk.simplemybatis;


import org.junit.Before;
import org.junit.Test;
import org.zk.simplemybatis.io.Resources;

import java.io.InputStream;
import java.util.List;

public class SqlSessionTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testSelectList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List list = sqlSession.selectList("org.zk.test.dao.UserDao.findAll", null);
    }
}
