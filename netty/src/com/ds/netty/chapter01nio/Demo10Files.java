package com.ds.netty.chapter01nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ds
 * @date 2023/4/26
 * @description 遍历文件夹
 */
public class Demo10Files {

    private static final AtomicInteger dirCount = new AtomicInteger();

    private static final AtomicInteger fileCount = new AtomicInteger();
    public static void main(String[] args) throws IOException {
       // 遍历文件夹
        //m1();
        // 递归删除文件夹
        delete();
    }

    /**
     * 递归删除文件夹
     * @throws IOException
     */
    private static void delete() throws IOException {
        Path path = Paths.get("/Users/ds/IdeaProjects/learning-notes/netty/dir");
        // 不能直接删除，文件夹非空
        //Files.delete(path);

        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("进入文件夹=====>" + dir);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("退出文件夹=====>" + dir);
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    /**
     *  遍历文件夹
     * @throws IOException
     */
    private static void m1() throws IOException {
        Path path = Paths.get("/Users/ds/IdeaProjects/learning-notes/netty");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            //访问文件夹之前
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dirCount.incrementAndGet();
                System.out.println("dir = " + dir);
                return super.preVisitDirectory(dir, attrs);
            }

            //访问文件时
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCount.incrementAndGet();
                System.out.println("file = " + file);
                return super.visitFile(file, attrs);
            }

            //访问文件失败时
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return super.visitFileFailed(file, exc);
            }

            //访问文件夹后
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return super.postVisitDirectory(dir, exc);
            }
        });

        System.out.println("dirCount = " + dirCount);
        System.out.println("fileCount = " + fileCount);
    }
}
