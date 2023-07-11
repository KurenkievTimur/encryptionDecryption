package com.kurenkievtimur;

import java.util.Scanner;

public class Main {
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        int shift = scanner.nextInt();

        String encode = encode(text, shift);
        System.out.println(encode);
    }

    public static String encode(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();

        for (Character ch : charArray) {
            int oldIndex = alphabet.indexOf(ch);

            if (oldIndex == -1) {
                builder.append(ch);
            } else {
                int newIndex = (oldIndex + shift) % 26;
                builder.append(alphabet.charAt(newIndex));
            }
        }

        return builder.toString();
    }
}
