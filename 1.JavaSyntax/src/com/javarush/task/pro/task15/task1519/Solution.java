package com.javarush.task.pro.task15.task1519;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Поверхностное копирование
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Path.of(scanner.nextLine());
        Path targetDirectory = Path.of(scanner.nextLine());

        DirectoryStream<Path> allFilesAndDirectories = Files.newDirectoryStream(sourceDirectory);

            for (Path allFilesAndDirectory : allFilesAndDirectories) {
                if (Files.isRegularFile(allFilesAndDirectory)) {
                    Files.copy(allFilesAndDirectory,targetDirectory.resolve(allFilesAndDirectory.getFileName()));
                }

            }



    }
}

