package org.example;


import org.example.util.LiquibaseRunner;
import org.example.util.PropertiesUtil;
import org.example.view.ConsoleInput;



public class Main {
    public static void main(String[] args) {
//        new PropertiesUtil();
//        new LiquibaseRunner();
        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.inputFromUser();
    }
}