package com.ds.mybatisx.session;

import com.ds.mybatisx.io.Resources;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class SqlSessionFactoryBuilderTest {

    @Test
    public void testBuild() {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisx.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        System.out.println("build = " + build);
    }

}
