package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        print("Enter an integer value to convert");
        Scanner sc = new Scanner(System.in);
        long inputInteger = sc.nextLong();

        NumberToWord numberToWord = new NumberToWord();
        String word = numberToWord.ThousandValue(inputInteger);
        print(word);

    }






































    public static void print(String st){
        System.out.println(st);
    }

    public static void print(int st){
        System.out.println(st);
    }

    public static void print(boolean st){
        System.out.println(st);
    }

    public static void print(char st){
        System.out.print(st);
    }

    public static void print(double st){
        System.out.println(st);
    }
    public static void print(float st){
        System.out.println(st);
    }

    public static void print(Object st){
        System.out.println(st);
    }

    public static void print(char[] st){
        System.out.println();
    }


}
