package main.model.dict;

import javafx.beans.value.WritableDoubleValue;
import main.model.wordinfo.WordInfo;

import java.util.*;

public class Dict {

	private Trie trie;

	private static Dict dict = null;

	private Dict() {
		this.trie = Trie.createTrie();
	}

	public static Dict createDict() {
		if (Dict.dict == null) {
			Dict.dict = new Dict();
		}
		return Dict.dict;
	}

	public void insert(String str, WordInfo info) {
		trie.insert(str, info);
	}

	public void printDict() {
		this.trie.printTrie();
	}

	public WordInfo retrieveInfo(String word) {
		return this.trie.retrieveInfo(word);
	}

	public ArrayList<String> searchWithEditDist(String str, int maxEditDist) {
		ArrayList<String> results = new ArrayList<>();
		BloomFilter filter = new BloomFilter(1000);
		for (int editDist = 0; editDist <= maxEditDist; ++ editDist) {
			ArrayList<String> tempResults = this.trie.searchWithEditDist(str, editDist);
			for (int i = 0; i < tempResults.size(); ++ i) {
				String resultBuf = tempResults.get(i);
				if (!filter.contains(resultBuf)) {
					results.add(resultBuf);
					filter.add(resultBuf);
				}
			}
		}
		return results;
	}

	public ArrayList<String> searchWithCommonPrefix(String str) {
		return this.trie.searchWithCommonPrefix(str);
	}

}
