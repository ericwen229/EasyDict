package main;

// ================================
// Built-in modules

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

// ================================
// User-defined modules

import main.model.dict.Dict;
import main.model.wordinfo.WordInfo;

import main.view.MainWindow;

// ================================
// Class EasyDict

/**
 * Main app class
 *
 * @author ericwen229
 * @see main.view.MainWindow
 */
public class EasyDict {

	/**
	 * Entrance of app
	 *
	 * @param args arguments (not used)
	 */
	public static void main(String[] args) {
		String dictFilePath = "resources/dict.txt";
		String dotFilePath = "visualize/trie.dot";
		EasyDict.importDict(dictFilePath);
		EasyDict.visualize(dotFilePath);
		// MainWindow w = new MainWindow();
		// w.setTitle("EasyDict");
	}

	/**
	 * Import dictionary from file
	 *
	 * @param dictFilePath path of dictionary file
	 */
	private static void importDict(String dictFilePath) {
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
				for (int i = 0; i < numOfExplanation; ++i) {
					explanation[i] = dictScanner.nextLine();
				}
				int numOfWebExpla = Integer.parseInt(dictScanner.nextLine());
				String[][] webExpla = new String[numOfWebExpla][2];
				for (int i = 0; i < numOfWebExpla; ++i) {
					webExpla[i][0] = dictScanner.nextLine();
					webExpla[i][1] = dictScanner.nextLine();
				}
				dictScanner.nextLine();

				WordInfo info = new WordInfo(phonetic, translation, explanation, webExpla);
				d.insert(word, info);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error reading file! Aborting...");
			System.exit(-1);
		}
	}

	/**
	 * Visualize trie
	 *
	 * @param dotFilePath path of .dot file
	 */
	private static void visualize(String dotFilePath) {
		Dict d = Dict.createDict();
		try {
			FileWriter dotFile = new FileWriter(dotFilePath);
			dotFile.write("digraph trie {\n");
			d.visualize(dotFile);
			dotFile.write("}\n");
			dotFile.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error creating file! Aborting...");
			System.exit(-1);
		}
	}

}
