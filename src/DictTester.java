import java.util.*;
import java.io.*;
import model.dict.*;

public class DictTester {

	public static void main(String[] args) {
		Dict d = Dict.createDict();

		String filePath = "resources/dict.txt";
		File dictFile;
		try {
			dictFile = new File(filePath);
			Scanner fileIn = new Scanner(dictFile);
			while (fileIn.hasNext()) {
				d.insert(fileIn.next());
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
			ArrayList<String> results = d.search(userIn.next(), 1);
			for (int i = 0; i < results.size(); ++ i) {
				System.out.println(results.get(i));
			}
			System.out.print(">>> ");
		}
	}

}
