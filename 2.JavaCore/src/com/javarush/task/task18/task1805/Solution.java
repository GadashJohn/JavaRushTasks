package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
            Set<Integer> set = new TreeSet<>();

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
              FileInputStream inputStream = new FileInputStream(reader.readLine()))
        {
            while(inputStream.available() > 0) {
                int data = inputStream.read();
                set.add(data);
            }
        } catch (IOException ignore) {
        }
        for (Integer integer : set) {
            System.out.print(integer + " ");
        }


    }
}
