package org.epoui.HW05;

import java.util.Scanner;

public class ArrowCounter {
    public static void main(String[] args) {
        System.out.println("Введите строку содержащую стрелы '>>-->' и '<--<<'");
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        System.out.println("Введена строка: "+sequence);
        int l=sequence.length();
        System.out.println("Длина строки составляет : "+l+ " символов.");
        int count = 0;

        for (int i = 0; i <= sequence.length() - 5; i++) {
            String substring = sequence.substring(i, i + 5);
            if (substring.equals(">>-->") || substring.equals("<--<<")) {
                count++;
            }
        }

        System.out.println("Количество стрел в строке = "+count);
    }
}