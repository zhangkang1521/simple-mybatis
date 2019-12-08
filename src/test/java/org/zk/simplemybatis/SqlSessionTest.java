package org.zk.simplemybatis;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zk.simplemybatis.io.Resources;
import org.zk.test.dao.UserDao;
import org.zk.test.domain.User;

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

    @Test
    public void testDao() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.toString());
//        List list = userDao.findAll();
    }


}
