package com.kurenkievtimur;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String action = scanner.nextLine();

        String text = scanner.nextLine();

        int shift = scanner.nextInt();

        String cipher = "";
        if (action.equals("enc")) {
            cipher = encode(text, shift);
        } else if (action.equals("dec")) {
            cipher = decode(text, shift);
        }

        System.out.println(cipher);
    }

    public static String encode(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();

        for (Character ch : charArray) {
            builder.append((char) (ch + shift));
        }

        return builder.toString();
    }

    public static String decode(String text, int shift) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();

        for (Character ch : charArray) {
            builder.append((char) (ch - shift));
        }

        return builder.toString();
    }
}
