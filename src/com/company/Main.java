package com.company;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Simple Test for the class
        print("Enter an integer value to convert");
        Scanner sc = new Scanner(System.in);
        long inputInteger = sc.nextLong();

        NumberToWord numberToWord = new NumberToWord();
        String word = numberToWord.ConvertToWord(inputInteger);
        print(word);
    }
    private static void print(String st) {
        System.out.println(st);
    }

}