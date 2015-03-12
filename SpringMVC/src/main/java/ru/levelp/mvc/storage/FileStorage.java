package ru.levelp.mvc.storage;

import java.io.*;
import java.util.Scanner;

/**
 *
 */
public class FileStorage {
    static String fileName = "C:\\resume.txt";

    public static void addNewResume(String name) throws IOException {
        PrintWriter writer = new PrintWriter(
                new FileWriter(new File(fileName), true)
        );
        // TODO: UTF-8
        writer.flush();
        writer.println(name);
        writer.close();
    }

    public static String search(String query) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext()) {
            String resume = scanner.nextLine();
            if (resume.contains(query)) {
                scanner.close();
                return resume;
            }
        }
        scanner.close();
        return "Ничего не найдено!";
    }
}
