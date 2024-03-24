package Task_3;

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

    private static boolean isInFile(String fileName, String search) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        byte[] searchSequence = search.getBytes();
        int ch;
        int i = 0;                         // geekbrains
        while ((ch = fis.read()) != -1) {
            if (ch == searchSequence[i])
                i++;
            else {
                i = 0;
                if (ch == searchSequence[i]) i++;
            }
            if (i == searchSequence.length) {
                fis.close();
                return true;
            }
        }
        fis.close();
        return false;
    }

    public static void main(String[] args) {
        String[] fileNames = new String[FILES_AMOUNT];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
        }
//        try {
//            for (int i = 0; i < fileNames.length; i++) {
//                if (i < 2)
//                    writeFileContents(fileNames[i], 100);
//                else
//                    writeFileContents(fileNames[i], WORDS_AMOUNT);
//            }
//            System.out.println("First task results are in file_0 and file_1.");
//            System.out.println();
//        }
//        catch (Exception ex) {throw new RuntimeException(ex);
//        }
        try {
            System.out.println("Check result in file_2 for third task is: " + isInFile(fileNames[2], WORD_TO_SEARCH));
        }
        catch (Exception ex) {throw  new RuntimeException(ex);}
    }

}
