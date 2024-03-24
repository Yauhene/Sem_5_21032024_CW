package Task_1;

import javax.swing.*;
import java.io.*;
import java.util.*;

/*
Задание:
Создать пару-тройку текстовых файлов. Для упрощения (чтобы не разбираться с кодировками) внутри
файлов следует писать текст только латинскими буквами.
 */
public class Main {
    private  static  final Random rnd = new Random();
    private  static  final int CHAR_BOUND_L = 65;
    private  static  final int CHAR_BOUND_H = 90;
    private  static  final int FILES_AMOUNT = 10;
    private  static  final int WORDS_AMOUNT = 5;
    private  static  final String WORD_TO_SEARCH = "geekbrains";



    private static String generateSymbols(int amount) {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sequence.append((char) (CHAR_BOUND_L + rnd.nextInt(CHAR_BOUND_H - CHAR_BOUND_L)));}
        return  sequence.toString();
    }

    private static void writeFileContents(String fileName, int length) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(generateSymbols(length).getBytes());
        fos.flush();
        fos.close();
    }

    public static void main(String[] args) {
        String[] fileNames = new String[FILES_AMOUNT];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
        }
        try {
            for (int i = 0; i < fileNames.length; i++) {
                if (i < 2)
                    writeFileContents(fileNames[i], 100);
                else
                    writeFileContents(fileNames[i], WORDS_AMOUNT);
            }
            System.out.println("First task results are in file_0 and file_1.");
        }
        catch (Exception ex) {throw new RuntimeException(ex);
        }
    }

}
