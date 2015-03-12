package ru.levelp.mvc;

import org.junit.Test;
import ru.levelp.mvc.storage.FileStorage;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ru.levelp.mvc.storage.FileStorage.NOT_FOUND;
import static ru.levelp.mvc.storage.FileStorage.search;

/**
 * Тестирование работы файлового хранилища
 */
public class FileStorageTest {

    @Test
    public void testFileStorage() throws IOException {
        assertEquals(NOT_FOUND, search("Нет такой строки"));
        FileStorage.addLine("Одна строка");
        assertEquals("Одна строка", search("строка"));
        assertEquals("Одна строка", search("ОДНА СТРОКА"));
    }
}
