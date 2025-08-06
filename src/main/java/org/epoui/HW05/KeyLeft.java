package org.epoui.HW05;

import java.util.Scanner;

public class KeyLeft {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Введите маленькую букву английского алфавита");
        String input=scanner.next();
        char letter= input.charAt(0);
        System.out.println("Вы ввели букву: "+letter);
        String keyboardRing="qwertyuiopasdfghjklzxcvbnm";
        System.out.println("Модель кольцевой клавиатуры (QWERTY): "+keyboardRing);
        int index=keyboardRing.indexOf(letter);
        System.out.println("Индекс введенной буквы: "+index);
        if(index==-1){
            System.out.println("Ошибка! Неподдерживаемый символ.");
        } else {
            int leftIndex=(index-1+keyboardRing.length()) % keyboardRing.length();
            System.out.println("Индекс левой буквы: "+leftIndex);
            char leftNeighbor=keyboardRing.charAt(leftIndex);
            System.out.println("Буква слева на клавиатуре: "+ leftNeighbor);
        }
    }
}
