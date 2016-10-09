package model.dict;

import java.util.*;
import model.trie.*;
import model.bloomfilter.*;

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

	public void insert(String str) {
		trie.insert(str);
	}

	public ArrayList<String> searchWithEditDist(String str, int maxEditDist) {
		ArrayList<String> results = new ArrayList<String>();
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

}
