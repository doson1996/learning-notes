package com.ds.concurrent.chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * @author ds
 * @date 2025/8/15
 * @description
 */
public class SubtitleProcessor {
    // 线程池：根据CPU核心数动态调整，避免线程过多导致开销
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        List<Track> tracks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tracks.add(new Track(i + "", "轨道" + i));
        }
        long  startTime =  System.currentTimeMillis();
        new SubtitleProcessor().processTracksAsync(tracks);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 并行处理所有字幕轨道
     *
     * @param tracks 字幕轨道列表
     * @return 所有任务完成后的CompletableFuture
     */

    public CompletableFuture<Void> processTracksAsync(List<Track> tracks) {
        // 为每个轨道创建并行任务
        List<CompletableFuture<Void>> futures = tracks.stream()
                .map(track -> CompletableFuture.runAsync(() -> processSingleTrack(track), executor))
                .collect(Collectors.toList());
        // 等待所有任务完成
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    /*
     * 处理单个轨道：转换为SRT + 上传COS
     * @param track 单个字幕轨道
     */
    private void processSingleTrack(Track track) {
        try {
            // 1. 生成字幕文件名
            String filename = getSrtFilename(track);
            // 2. 转换轨道为SRT格式
            String srtContent = convertTrackToSrt(track);
            // 3. 上传到COS
            upload(filename, srtContent);
        } catch (Exception e) {
            // 处理异常（如重试、记录日志等）
            throw new RuntimeException("处理轨道失败: " + track.getId(), e);
        }
    }

    /**
     * 上传到COS
     * @param filename
     * @param srtContent
     */
    private void upload(String filename, String srtContent) throws InterruptedException {
        System.out.println("filename = " + filename);
        Thread.sleep(200);
    }

    // 辅助方法：生成SRT文件名
    private String getSrtFilename(Track track) {
        return "subtitle_" + track.getId() + ".srt";
    }

    // 辅助方法：轨道转SRT
    private String convertTrackToSrt(Track track) throws InterruptedException {
        // 实际转换逻辑（省略）
        Thread.sleep(300);
        return "SRT内容：" + track.getContent();
    }
}
