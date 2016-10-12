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
				String word = fileIn.next();
				d.insert(fileIn.next(), null);
			}
			fileIn.close();
		}
		catch (Exception e) {
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
