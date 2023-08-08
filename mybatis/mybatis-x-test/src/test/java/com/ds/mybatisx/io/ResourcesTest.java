package com.ds.mybatisx.io;

import com.ds.mybatisx.io.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class ResourcesTest {

    @Test
    public void test1() {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatisx.xml");
        Assert.assertNotNull(resourceAsStream);
    }

}
