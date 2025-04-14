package com.ds.basic.util.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.util.CollectionUtils;

/**
 * @author ds
 * @date 2025/4/11
 * @description
 */
public class FileUtils {

    public static void main(String[] args) {
        String zipFilePath = "D:\\haizhi\\doc\\2025\\03.尽调-行业\\重庆银行2025.04.07.zip";
//        String destDirectory = "D:\\haizhi\\doc\\2025\\03.尽调-行业\\out\\";
//        try {
//            unzip(zipFilePath, destDirectory);
//            System.out.println("Unzipped successfully!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        File multipartFile = new File(zipFilePath);
        List<File> fileList = null;
        try {
            fileList = unzipInputStream(Files.newInputStream(multipartFile.toPath()));
            //fileList可能存在目录
            for (File fileTemp : fileList) {
                List<File> fileTempList = listAll(fileTemp);
                if (!CollectionUtils.isEmpty(fileTempList)) {
                    fileList.addAll(fileTempList);
                }
            }
            if (!CollectionUtils.isEmpty(fileList)) {
                // 省略业务逻辑
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //删除临时文件
            if (null != fileList) {
                for (File file1 : fileList) {
                    file1.delete();
                }
            }
        }
    }

    public static List<File> unzipInputStream(InputStream zipInputStream) {
        List<File> fileList = new ArrayList<>();
        try (ZipArchiveInputStream zip = new ZipArchiveInputStream(zipInputStream)) {
            ZipArchiveEntry zipEntry;
            while ((zipEntry = zip.getNextZipEntry()) != null) {
                String fileName_zip = zipEntry.getName();
                File file = new File(fileName_zip);
                if (fileName_zip.endsWith("/")) {
                    file.mkdir();
                    continue;
                } else {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] byte_s = new byte[1024];
                    int num;
                    while ((num = zip.read(byte_s, 0, byte_s.length)) > 0) {
                        outputStream.write(byte_s, 0, num);
                    }
                    outputStream.close();
                }
                fileList.add(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }


    // 单独列出方法获取目录下的图片
    public static List<File> listAll(File parent) {
        List<File> fileList = new ArrayList<>();
        MyFilter myFilter = new MyFilter();
        File[] children = parent.listFiles(myFilter);
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                // 如果子文件是个文件夹，则递归调用
                if (!children[i].isFile()) {
                    listAll(children[i]);
                } else {
                    fileList.add(children[i]);
                }
            }
        }
        return fileList;
    }

    //定义文件过滤器
    static class MyFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.contains(".png") || dir.isDirectory();
        }
    }


    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

}