package main.model.dict;

// ================================
// Built-in modules

import java.io.FileWriter;
import java.util.ArrayList;

// ================================
// User-defined modules

import main.model.wordinfo.WordInfo;

// ================================
// Class Trie

/**
 * Trie stores strings in a tree structure. Each level represents
 * a position in a string and each branch represents an available
 * character in current position. A string can be seen as the
 * pattern of a search from root to its descendants (not necessarily
 * a leaf, since one word can have another word as prefix).
 *
 * @author ericwen229
 * @see main.model.dict.TrieNode
 * @see main.model.wordinfo.WordInfo
 */
class Trie {

	// ================================
	// Static member functions

	/**
	 * Tells if given string is supported by Trie
	 * (can be stored in trie)
	 *
	 * @param str string to be tested
	 * @return whether string is supported
	 */
	private static boolean validString(String str) {
		if (str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); ++i) {
			if (!TrieNode.validChar(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// ================================
	// Members

	/**
	 * Root of Trie
	 */
	private TrieNode root;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initialize root node
	 */
	Trie() {
		this.root = new TrieNode();
	}

	/**
	 * Insert word and information into Trie
	 *
	 * @param str  word to be inserted
	 * @param info information of word to be inserted
	 * @return true if insertion is successful
	 */
	boolean insert(String str, WordInfo info) {
		if (!validString(str)) {
			return false;
		}
		TrieNode currentNode = root;
		for (int i = 0; i < str.length(); ++i) {
			currentNode = currentNode.addChild(str.charAt(i));
		}
		currentNode.setEndOfWord();
		currentNode.addInfo(info);
		return true;
	}

	/*void printTrie() {
		StringBuffer strBuf = new StringBuffer();
		dfsPrintAll(this.root, strBuf);
	}

	private void dfsPrintAll(TrieNode node, StringBuffer strBuf) {
		if (node.isEndOfWord()) {
			System.out.println(strBuf.toString());
		}
		if (!node.haveChild()) {
			return;
		}
		TrieNode.TrieNodeIterator nodeIterator = node.createIterator();
		while (nodeIterator.hasNext()) {
			char ch = nodeIterator.getNextChar();
			strBuf.append(ch);
			TrieNode nextNode = nodeIterator.next();
			this.dfsPrintAll(nextNode, strBuf);
			strBuf.deleteCharAt(strBuf.length() - 1);
		}
	}*/

	/**
	 * Retrieve info of a word from Trie
	 *
	 * @param word word inquired
	 * @return information of given word (null if word not found)
	 */
	WordInfo retrieveInfo(String word) {
		TrieNode currentNode = this.root;
		int currPos = 0;
		while (currPos < word.length()) {
			TrieNode nextNode = currentNode.findChar(word.charAt(currPos));
			if (nextNode == null) {
				return null;
			}
			++currPos;
			currentNode = nextNode;
		}
		if (currentNode.isEndOfWord()) {
			return currentNode.getInfo();
		} else {
			return null;
		}
	}

	/**
	 * Search for words that have common prefix with given
	 * word (including the given word)
	 *
	 * @param str    word to be searched
	 * @param maxNum maximum number of results
	 * @return search result list
	 */
	ArrayList<String> searchWithCommonPrefix(String str, int maxNum) {
		ArrayList<String> results = new ArrayList<>();
		StringBuffer strBuf = new StringBuffer();
		this.dfsWithCommonPrefix(this.root, str, 0, results, strBuf, maxNum);
		return results;
	}

	/**
	 * Perform DFS upon Trie to retrieve all words with common prefix.
	 * This is done by first match the given word then traverse
	 * through every word with given word as prefix.
	 *
	 * @param node    current node to be searched
	 * @param target  target word
	 * @param currPos position to be matched in target word
	 * @param results search result list
	 * @param strBuf  string buffer storing DFS path (i.e. part of word)
	 * @param maxNum  maximum number of results
	 */
	private void dfsWithCommonPrefix(TrieNode node, String target, int currPos, ArrayList<String> results, StringBuffer strBuf, int maxNum) {
		while (currPos < target.length()) { // First match target word
			TrieNode next = node.findChar(target.charAt(currPos));
			if (next == null) { // Fail to match
				return;
			}
			strBuf.append(target.charAt(currPos));
			++currPos; // Go forward along target word
			node = next; // Go down along one branch
		}
		if (node.isEndOfWord()) { // Add to result
			if (results.size() == maxNum) {
				return;
			}
			results.add(strBuf.toString());
		}
		if (!node.haveChild()) { // Reached leaf
			return;
		}
		TrieNode.TrieNodeIterator nodeIterator = node.createIterator();
		while (nodeIterator.hasNext()) { // Perform DFS and add every word to result
			char ch = nodeIterator.getNextChar();
			TrieNode nextNode = nodeIterator.next();
			strBuf.append(ch);
			this.dfsWithCommonPrefix(nextNode, target, currPos, results, strBuf, maxNum);
			strBuf.deleteCharAt(strBuf.length() - 1);
		}
	}

	/**
	 * Search for words with given edit distance. Notice than search
	 * can cause repetition since multiple edit behaviours can be
	 * reciprocal.
	 *
	 * @param str      word to be searched
	 * @param editDist specified edit distance
	 * @return search result list
	 */
	ArrayList<String> searchWithEditDist(String str, int editDist) {
		ArrayList<String> results = new ArrayList<>();
		StringBuffer strBuf = new StringBuffer();
		this.dfsWithEditDist(this.root, str, 0, results, strBuf, editDist);
		return results;
	}

	/**
	 * Perform DFS upon Trie to retrieve all words with given edit
	 * distance. In every step, one edit distance can be consumed by
	 * modification (search along another branch), addition (go down
	 * in Trie and stay in target), deletion (go forward in target and
	 * remain in Trie)
	 *
	 * @param node     current node to be searched
	 * @param target   target word
	 * @param currPos  position to be matched in target word
	 * @param results  search result list
	 * @param strBuf   string buffer storing DFS path (i.e. part of word)
	 * @param editDist edit distance left
	 */
	private void dfsWithEditDist(TrieNode node, String target, int currPos, ArrayList<String> results, StringBuffer strBuf, int editDist) {
		boolean targetComplete = currPos == target.length() && editDist == 0;
		boolean wordFoundInTrie = node.isEndOfWord();
		//boolean fitRestOfTarget = editDist > 0 && target.length() - strBuf.length() == editDist;
		boolean endOfTrie = !node.haveChild();
		boolean endOfTarget = currPos > target.length();

		if (wordFoundInTrie
				&& (targetComplete/* || fitRestOfTarget*/)) {
			results.add(strBuf.toString());
		}

		if (targetComplete || endOfTrie || endOfTarget) {
			return;
		}

		// then let's deal with edit distance!
		if (editDist < 0) {
			System.exit(-1);
		} else if (editDist > 0) {

			// FIRST CASE
			// Delete one character, ++ currPos, strBuf unchanged, -- editDist
			dfsWithEditDist(node, target, currPos + 1, results, strBuf, editDist - 1);

			TrieNode.TrieNodeIterator nodeIterator = node.createIterator();
			while (nodeIterator.hasNext()) {
				char ch = nodeIterator.getNextChar();
				TrieNode nextNode = nodeIterator.next();

				// SECOND CASE
				// Insert or append one character, currPos unchanged, append to strBuf, -- editDist
				strBuf.append(ch);
				dfsWithEditDist(nextNode, target, currPos, results, strBuf, editDist - 1);
				strBuf.deleteCharAt(strBuf.length() - 1);

				if (currPos < target.length()) {
					// THIRD CASE
					// Modify one character, ++ currPos, append to strBuf, -- editDist
					if (ch != target.charAt(currPos)) {
						strBuf.append(ch);
						dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, editDist - 1);
						strBuf.deleteCharAt(strBuf.length() - 1);
					}

					// Yield edit distance to next search
					if (ch == target.charAt(currPos)) {
						strBuf.append(ch);
						dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, editDist);
						strBuf.deleteCharAt(strBuf.length() - 1);
					}
				}
			}
		} else {
			// Precise match with editDist = 0
			TrieNode nextNode = node.findChar(target.charAt(currPos));
			if (nextNode != null) {
				strBuf.append(target.charAt(currPos));
				dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, 0);
				strBuf.deleteCharAt(strBuf.length() - 1);
			}
		}

	}

	void visualize(FileWriter dotFile) throws Exception {
		dfsVisualize(this.root, new StringBuffer("t_"), dotFile);
	}

	private void dfsVisualize(TrieNode node, StringBuffer curr, FileWriter dotFile) throws Exception {
		TrieNode.TrieNodeIterator nodeIterator = node.createIterator();
		while (nodeIterator.hasNext()) {
			char ch = nodeIterator.getNextChar();
			TrieNode nextNode = nodeIterator.next();
			String currStr = curr.toString();
			if (ch == ' ') {
				ch = 'B';
			}
			else if (ch == '-') {
				ch = 'H';
			}
			curr.append(ch);
			String nextStr = curr.toString();
			StringBuffer lineBuf = new StringBuffer("\t");
			lineBuf.append(currStr);
			lineBuf.append(" -> ");
			lineBuf.append(nextStr);
			lineBuf.append(";\n");
			dotFile.write(lineBuf.toString());
			dfsVisualize(nextNode, curr, dotFile);
			curr.deleteCharAt(curr.length() - 1);
		}
	}

}
