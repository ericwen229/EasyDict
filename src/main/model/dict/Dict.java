package main.model.dict;

// ================================
// Built-in modules

import java.util.ArrayList;

// ================================
// User-defined modules

import main.model.wordinfo.WordInfo;

// ================================
// Class Dict

/**
 * Dictionary that supports inserting, searching and info
 * retrieving using data structure Trie. There only exists
 * one single dictionary when the program is running thus
 * Dict is designed using Singleton Pattern.
 *
 * @author ericwen229
 * @see main.model.dict.Trie
 * @see main.model.wordinfo.WordInfo
 */
public class Dict {

	// ================================
	// Static members

	/**
	 * One single reference of Dict object
	 */
	private static Dict dict = null;

	// ================================
	// Static member functions

	/**
	 * Singleton method that creates/retrieves the single
	 * reference of the Dict object
	 *
	 * @return reference of Dict object
	 */
	public static Dict createDict() {
		if (Dict.dict == null) {
			Dict.dict = new Dict();
		}
		return Dict.dict;
	}

	// ================================
	// Members

	/**
	 * Trie used for storing and searching
	 */
	private Trie trie;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initialize the Trie
	 */
	private Dict() {
		this.trie = new Trie();
	}

	/**
	 * Insert word and information
	 *
	 * @param str  word to be inserted
	 * @param info information of word to be inserted
	 */
	public void insert(String str, WordInfo info) {
		this.trie.insert(str, info);
	}

	/*public void printDict() {
		this.trie.printTrie();
	}*/

	/**
	 * Retrieve info of a word
	 *
	 * @param word word inquired
	 * @return information of given word (null if word not found)
	 */
	public WordInfo retrieveInfo(String word) {
		return this.trie.retrieveInfo(word);
	}

	/**
	 * Search for words with edit distance no more than given edit
	 * distance. This is done by looping through edit distances from 0
	 * to given one and searching in trie. Search can cause repetition
	 * since multiple edit behaviours can be reciprocal. So here
	 * {@link BloomFilter} is introduced to eliminate repetition.
	 * <p>Notice that BloomFilter may wrongly assume that a word has
	 * appeared while it hasn't, but there're DEFINITELY no multiple
	 * copies of one word in result list.
	 *
	 * @param str         word to be searched
	 * @param maxEditDist specified max edit distance
	 * @return search result list
	 */
	public ArrayList<String> searchWithEditDist(String str, int maxEditDist) {
		ArrayList<String> results = new ArrayList<>();
		BloomFilter filter = new BloomFilter(1000);
		for (int editDist = 0; editDist <= maxEditDist; ++editDist) {
			ArrayList<String> tempResults = this.trie.searchWithEditDist(str, editDist);
			for (String resultBuf : tempResults) {
				if (!filter.contains(resultBuf)) { // If one word has DEFINITELY not appeared
					results.add(resultBuf); // Then add it to result
					filter.add(resultBuf); // And mark it as appeared
				}
			}
		}
		return results;
	}

	/**
	 * Search for words that have common prefix with given
	 * word (including the given word)
	 *
	 * @param str    string to be searched
	 * @param maxNum maximum number of results
	 * @return search result list
	 */
	public ArrayList<String> searchWithCommonPrefix(String str, int maxNum) {
		return this.trie.searchWithCommonPrefix(str, maxNum);
	}

}
