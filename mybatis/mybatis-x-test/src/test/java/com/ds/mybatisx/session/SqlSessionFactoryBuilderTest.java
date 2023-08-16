package com.ds.mybatisx.session;

import com.ds.entity.User;
import com.ds.mybatisx.io.Resources;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class SqlSessionFactoryBuilderTest {

    @Test
    public void testBuild() {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisx.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("com.ds.mapper.UserMapper.selectList", new User(1, "user0"));
        System.out.println("list = " + list);
    }

}
