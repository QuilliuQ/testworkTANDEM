package ru.tandemservice.test.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Task1Impl task = (Task1Impl) Task1Impl.INSTANCE;
        List<String[]> array = new ArrayList<>();
        array.add(new String[]{"1", "wwerdas", "3"});
        array.add(new String[]{"1", null, "3"});
        array.add(new String[]{"4", "", "5"});
        array.add(new String[]{"1", "1233wwer1234das424", "3"});
        array.add(new String[]{"1", "ssas1233wwer1234das424", "3"});
        array.add(new String[]{"1", "324ewqs", "3"});
        array.add(new String[]{"6", null, "8"});
        array.add(new String[]{"7", "21", "9"});
        array.add(new String[]{"7", "", "9"});
        array.add(new String[]{"7", "1sdcasq", "9"});
        array.add(new String[]{"1", "1233wweqqqr1234das424", "3"});
        array.add(new String[]{"1", "124ewqs", "3"});
        array.add(new String[]{"1", "1233ewqs31134", "3"});
        System.out.println("before sort");
        for(String[] value : array) {
            System.out.println(Arrays.toString(value));
        }
        task.sort(array, 1);
        System.out.println("after sort");
        for(String[] value : array) {
            System.out.println(Arrays.toString(value));
        }
    }
    }


