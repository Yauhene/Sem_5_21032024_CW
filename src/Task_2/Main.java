package Task_2;

import java.io.*;
import java.util.*;

public class Main {
    /*
    Задание:
Написать метод, осуществляющий конкатенацию (соединение) переданных ей в качестве параметров
файлов (не особенно важно, в первый допишется второй или во второй первый, или файлы вовсе
объединятся в какой-то третий)
     */
    private  static  final Random rnd = new Random();
    private  static  final int CHAR_BOUND_L = 65;
    private  static  final int CHAR_BOUND_H = 90;
    private  static  final int FILES_AMOUNT = 10;
    private  static  final int WORDS_AMOUNT = 5;
    private  static  final String WORD_TO_SEARCH = "geekbrains";
    private static void concatenate(String file_in1, String file_in2, String file_out)
        throws IOException {
        FileOutputStream fos = new FileOutputStream(file_out);
        int ch;
        FileInputStream fis = new FileInputStream(file_in1);
        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }
        fis.close();
        fis = new FileInputStream(file_in2);
        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }
        fis.close();

        fos.flush();
        fos.close();
    }
    public static void main(String[] args) {
        String[] fileNames = new String[FILES_AMOUNT];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
        }
        try {
            concatenate(fileNames[0], fileNames[1], "FILE_OUT.txt");
            System.out.println("Second task result is in FILE_OUT.");
        } catch (Exception ex) {throw new RuntimeException(ex);}
    }
}
