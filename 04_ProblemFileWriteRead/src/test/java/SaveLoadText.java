import org.junit.Test;

import java.io.*;
import java.util.Scanner;

/**
 * Сохранение и загрузка текста
 */
public class SaveLoadText {

    @Test
    public void testSaveLoad() throws IOException {
        File file = new File("a.txt");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("Hello world!");
        }
        try (Scanner scanner = new Scanner(file)) {
            String s = scanner.nextLine();
            System.out.println("s = " + s);
        }
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(file, true)))) {
            writer.println("Hello world 2!");
            writer.flush();
        }
    }
}
