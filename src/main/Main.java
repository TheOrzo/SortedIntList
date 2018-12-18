package main;

import test.ConsoleTest;

public class Main {

    public static void main(String[] args) {
        ConsoleTest test = new ConsoleTest("C:\\Users\\Tobih\\Desktop\\test1in.txt", "C:\\Users\\Tobih\\Desktop\\test1out.txt");

        SortedIntList list = new SortedIntList();       // create list to interact with

        new UserInterface(list);                        // create UserInterface

        boolean result = test.finish();
        System.out.println(result);
    }
}
