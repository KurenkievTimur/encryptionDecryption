package com.kurenkievtimur;

public class Main {

    public static void main(String[] args) {
        String mode = "enc";
        int shift = 0;
        String data = "";

        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[++i];
                case "-key" -> shift = Integer.parseInt(args[++i]);
                case "-data" -> data = args[++i];
            }
        }

        String cipher = "";
        if (mode.equals("enc")) {
            cipher = encode(data, shift);
        } else if (mode.equals("dec")) {
            cipher = decode(data, shift);
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
