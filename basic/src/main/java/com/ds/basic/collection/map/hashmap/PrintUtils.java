package com.ds.basic.collection.map.hashmap;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author ds
 * @date 2023/5/14
 * @description 打印map
 */
public final class PrintUtils {

    private PrintUtils() {
    }

    @SneakyThrows
    public static void hashMap(Map map) {
        Map.Entry[] table = (Map.Entry[]) get(map, "table");
        StringBuilder content = new StringBuilder("[");
        for (int i = 0; i < table.length; i++) {
            // 当前节点
            Map.Entry node = table[i];
            // 如果table当前元素为空
            if (node == null) {
                content.append("null");
            } else {  // 如果table当前元素不为空
                content.append(node.getValue());
                // 判断当前元素的链表是否还有下一个节点(hash冲突)
                Map.Entry nextNode = nextNode(node);
                while (nextNode != null) {
                    content.append("->").append(nextNode.getValue());
                    nextNode = nextNode(nextNode);
                }
            }

            if (i < (table.length - 1)) {
                content.append(",");
               // content.append("\r\n");
            }
        }
        content.append("]");
        System.out.println("content = " + content);
    }

    /**
     * 获取当前元素在链表中的下一个节点
     *
     * @param node
     * @return
     * @throws Exception
     */
    private static Map.Entry nextNode(Map.Entry node) throws Exception {
        if (node == null)
            return null;
        Field field = node.getClass().getDeclaredField("next");
        field.setAccessible(true);
        return (Map.Entry) field.get(node);
    }


    /**
     * 通过反射获取属性
     *
     * @param map
     * @param filedName
     * @return
     * @throws Exception
     */
    private static Object get(Map map, String filedName) throws Exception {
        Field field = map.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(map);
    }

    /**
     * hash冲突
     */
    public static void hashConflicts() {
        Map<Integer, List<String>> param = new HashMap<>();
        for (int i = 33; i < 1000; i++) {
            char ch = (char) i;
            String str = String.valueOf(ch);
            int index = 15 & hash(str);
            List<String> list = param.get(index);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(str);
            param.put(index, list);
        }

        param.forEach((k,v) -> System.out.println(k + " " + Arrays.toString(v.toArray())));

    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
