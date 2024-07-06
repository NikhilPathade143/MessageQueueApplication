package com.test.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIterationPro {
    public static void main(String[] args) {
    // Create a list and add some elements
    List<String> list = new ArrayList<>();
    list.add("One");
    list.add("Two");
    list.add("Three");
        list.add("w");
        list.add("ee");
        list.add("rr");

    // Get an iterator for the list
    Iterator<String> iterator = list.iterator();

    // Try to iterate through the list
    while (iterator.hasNext()) {
        String element = iterator.next();
        System.out.println(element);

        // Modify the list while iterating
        if (element.equals("w")) {
            list.remove(element); // This will cause a ConcurrentModificationException
        }
    }
}
}
