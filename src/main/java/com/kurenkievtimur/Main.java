package com.kurenkievtimur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        int shift = 0;
        String mode = "enc";
        String data = "";
        String in = null;
        String out = null;
        String alg = "shift";

        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[++i];
                case "-key" -> shift = Integer.parseInt(args[++i]);
                case "-data" -> data = args[++i];
                case "-in" -> in = args[++i];
                case "-out" -> out = args[++i];
                case "-alg" -> alg = args[++i];
            }
        }

        if (data.equals("") && in != null)
            data = readFile(in);

        String cipher = "";
        if (mode.equals("enc")) {
            cipher = encode(data, shift, alg);
        } else if (mode.equals("dec")) {
            cipher = decode(data, shift, alg);
        }

        if (out == null)
            System.out.println(cipher);
        else
            writeFile(out, cipher);
    }

    public static String readFile(String path) {
        File file = new File(path);

        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

        return builder.toString();
    }

    public static void writeFile(String path, String text) {
        File file = new File(path);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static String encode(String text, int shift, String alg) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();

        if (alg.equals("unicode")) {
            for (Character ch : charArray) {
                builder.append((char) (ch + shift));
            }

            return builder.toString();
        }

        for (char c : charArray) {
            int oldIndex = alphabet.indexOf(c);

            if (oldIndex == -1) {
                builder.append(c);
            } else {
                int newIndex = (oldIndex + shift) % 26;
                builder.append(alphabet.charAt(Character.isUpperCase(c) ? newIndex + 26 : newIndex));
            }
        }

        return builder.toString();
    }

    public static String decode(String text, int shift, String alg) {
        StringBuilder builder = new StringBuilder();
        char[] charArray = text.toCharArray();

        if (alg.equals("unicode")) {
            for (Character ch : charArray) {
                builder.append((char) (ch - shift));
            }

            return builder.toString();
        }

        for (char c : charArray) {
            int oldIndex = alphabet.indexOf(c);

            if (oldIndex == -1) {
                builder.append(c);
            } else {
                int newIndex = (26 + oldIndex - shift) % 26;
                builder.append(alphabet.charAt(Character.isUpperCase(c) ? newIndex + 26 : newIndex));
            }
        }

        return builder.toString();
    }
}
