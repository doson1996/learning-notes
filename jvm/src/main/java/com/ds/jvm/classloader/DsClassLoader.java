package com.ds.jvm.classloader;

import java.io.*;

/**
 * @author ds
 */
public class DsClassLoader extends ClassLoader {

    private final String FILE_SUFFIX = ".class";

    private String byteCodePath;

    public DsClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        String fileName = byteCodePath + className + FILE_SUFFIX;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(fileName));
            baos = new ByteArrayOutputStream();

            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            byte[] byteCodes = baos.toByteArray();

            return defineClass(null, byteCodes, 0, byteCodes.length);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
