package com.ds.cache.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ds.cache.Cache;

/**
 * @author ds
 */
public class DiskCache implements Cache {

    @Override
    public Object get(String key) {
        File cacheFile = getCacheFile(key);
        try {
            if (cacheFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
                return reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void set(String key, Object value) {
        File cacheFile = getCacheFile(key);
        BufferedWriter writer = null;
        try {
            if (cacheFile.exists()) {
                cacheFile.delete();
            }
            writer = new BufferedWriter(new FileWriter(cacheFile));
            writer.write(String.valueOf(value));
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private File getCacheFile(String key) {
        String fileName = "cache-" + key + ".txt";
        Path path = Paths.get(fileName);
        return path.toFile();
    }

}