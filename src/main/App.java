package main;

import java.io.*;
import java.util.*;

import main.model.dict.Dict;
import main.model.wordinfo.WordInfo;

import main.view.MainWindow;

public class App {

    public static void main(String[] args) {
        String dictFilePath = "resources/dict.txt";
        App.importDict(dictFilePath);
        MainWindow w = MainWindow.createMainWindow();
    }

    public static void importDict(String dictFilePath) {
        Dict d = Dict.createDict();
        try {
            File dictFile = new File(dictFilePath);
            Scanner dictScanner = new Scanner(dictFile);
            while (dictScanner.hasNext()) {
                String word = dictScanner.nextLine();
                String phonetic = dictScanner.nextLine();
                String translation = dictScanner.nextLine();
                int numOfExplanation = Integer.parseInt(dictScanner.nextLine());
                String[] explanation = new String[numOfExplanation];
                for (int i = 0; i < numOfExplanation; ++ i) {
                    explanation[i] = dictScanner.nextLine();
                }
                int numOfWebExpla = Integer.parseInt(dictScanner.nextLine());
                String[][] webExpla = new String[numOfWebExpla][2];
                for (int i = 0; i < numOfWebExpla; ++ i) {
                    webExpla[i][0] = dictScanner.nextLine();
                    webExpla[i][1] = dictScanner.nextLine();
                }
                dictScanner.nextLine();

                WordInfo info = new WordInfo(phonetic, translation, explanation, webExpla);
                d.insert(word, info);
            }
        } catch (Exception e) {
            System.exit(-1);
        }
    }

}
