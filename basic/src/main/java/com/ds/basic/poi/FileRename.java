package com.ds.basic.poi;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.util.FileCopyUtils;

/**
 * @author ds
 * @date 2025/4/15
 * @description 文件重命名
 */
public class FileRename {

    static String inputDir = "D://docx//input//";
    static String outputDir = "D://docx//input//a//";

    public static void main(String[] args) throws Exception {
        rename(inputDir);
    }

    public static void rename(String inputDir) throws Exception {
        Path path = Paths.get(inputDir);
        File file = path.toFile();
        if (file.exists() && file.isDirectory()) {
            for (File listFile : Objects.requireNonNull(file.listFiles())) {
                if (listFile.isFile() && listFile.getName().endsWith(".docx")) {
                    String name = listFile.getName();
                    name = name.substring(0, name.lastIndexOf("（")).trim();
                    name = name + ".docx";
                    FileCopyUtils.copy(listFile, new File(outputDir + name));
                }
            }
        }

    }
}
