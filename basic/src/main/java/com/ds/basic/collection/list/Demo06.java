package com.ds.basic.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author ds
 */
public class Demo06 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        arrayList.add(0, 1);
        linkedList.add(0, 1);
        linkedList.add(2);
        arrayList.remove(1);
    }
}
