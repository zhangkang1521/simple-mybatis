package org.zk.simplemybatis;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zk.simplemybatis.io.Resources;

import java.io.InputStream;
import java.util.List;

public class SqlSessionTest {

    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    @Before
    public void before() {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void testSelectList() {
        List list = sqlSession.selectList("org.zk.test.dao.UserDao.findAll", null);
    }


}
