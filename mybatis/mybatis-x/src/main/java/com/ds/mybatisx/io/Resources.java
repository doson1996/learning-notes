package com.ds.mybatisx.io;

import java.io.InputStream;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class Resources {

    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
