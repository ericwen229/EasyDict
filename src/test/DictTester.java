package test;

import java.util.*;
import java.io.*;

import main.model.dict.*;

public class DictTester {

	public static void main(String[] args) {
		Dict d = Dict.createDict();

		String filePath = "resources/dict.txt";
		File dictFile;
		try {
			dictFile = new File(filePath);
			Scanner fileIn = new Scanner(dictFile);
			while (fileIn.hasNext()) {
				String word = fileIn.nextLine();
				String phonetic = fileIn.nextLine();
				String translation = fileIn.nextLine();
				String[] explains = null;
				int explaNum = Integer.parseInt(fileIn.nextLine());
				if (explaNum > 0) {
					explains = new String[explaNum];
					for (int i = 0; i < explaNum; ++ i) {
						explains[i] = fileIn.nextLine();
					}
				}
				String[][] webExplains = null;
				int webExplaNum = Integer.parseInt(fileIn.nextLine());
				if (webExplaNum > 0) {
					webExplains = new String[webExplaNum][2];
					for (int i = 0; i < webExplaNum; ++ i) {
						webExplains[i][0] = fileIn.nextLine();
						webExplains[i][1] = fileIn.nextLine();
					}
				}
				WordInfo info = new WordInfo(phonetic, translation, explains, webExplains);
				d.insert(word.substring(0, word.length() - 1), info);
				fileIn.nextLine();
			}
			fileIn.close();
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("Error reading file! Aborting...");
			System.exit(0);
		}

		System.out.print(">>> ");
		Scanner userIn = new Scanner(System.in);
		while (userIn.hasNext()) {
			String str = userIn.next();
			ArrayList<String> results = d.searchWithCommonPrefix(str);
			if (results.size() == 0) {
				System.out.println("Are you trying to search:");
				results = d.searchWithEditDist(str, 1);
			}
			results.forEach(System.out::println);
			System.out.print(">>> ");
		}
	}

}
