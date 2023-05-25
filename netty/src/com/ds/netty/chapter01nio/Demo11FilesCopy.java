package com.ds.netty.chapter01nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ds
 * @date 2023/5/5
 * @description 文件拷贝
 */
@Slf4j
public class Demo11FilesCopy {
    public static void main(String[] args) throws Exception {
        // 源文件夹
        String source = "/Users/ds/IdeaProjects/learning-notes/netty/chapter02";
        // 目标文件夹
        String target = "/Users/ds/IdeaProjects/learning-notes/netty/chapter02copy";
        Files.walk(Paths.get(source)).forEach(path -> {
            // 目标路径（文件夹、文件）
            Path targetPath = Paths.get(path.toString().replace(source, target));
            log.info("source: {} -> target: {}", path, targetPath);
            // 如果是文件夹
            if (Files.isDirectory(path)) {
                try {
                    Files.createDirectory(targetPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // 如果是普通文件
            else {
                try {
                    Files.copy(path, targetPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
