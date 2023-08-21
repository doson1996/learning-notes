package com.ds.mybatisx.starter;

import com.ds.mybatisx.io.Resources;
import com.ds.mybatisx.session.SqlSession;
import com.ds.mybatisx.session.SqlSessionFactory;
import com.ds.mybatisx.session.SqlSessionFactoryBuilder;
import lombok.Getter;

import java.io.InputStream;

/**
 * @author ds
 * @date 2023/8/21
 * @description
 */
public class MyBatsixStarter {

    @Getter
    private static final MyBatsixStarter myBatsixStarter = new MyBatsixStarter();

    private MyBatsixStarter() {
    }

    public <T> T getMapper(Class<T> type, String configLocation) {
        InputStream resourceAsStream = Resources.getResourceAsStream(configLocation);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.getMapper(type);
    }

}
