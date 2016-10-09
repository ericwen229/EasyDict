package model.trie;

import java.util.*;

public class Trie {

	private TrieNode root;

	private static Trie trie = null;

	private Trie() {
		this.root = new TrieNode();
	}

	public static Trie createTrie() {
		if (Trie.trie == null) {
			Trie.trie = new Trie();
		}
		return Trie.trie;
	}

	public static boolean validString(String str) {
		if (str.length() == 0) {
			return false;
		}
		for (int i = 0; i < str.length(); ++ i) {
			if (!TrieNode.validChar(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean insert(String str) {
		if (!validString(str)) {
			return false;
		}
		TrieNode currentNode = root;
		for (int i = 0; i < str.length(); ++ i) {
			currentNode = currentNode.addChild(str.charAt(i));
		}
		currentNode.setEndOfWord();
		return true;
	}

	public void printTrie() {
		StringBuffer strBuf = new StringBuffer();
		printTrieNode(this.root, strBuf);
	}

	public static void printTrieNode(TrieNode node, StringBuffer strBuf) {
		if (node.isEndOfWord()) {
			System.out.println(strBuf.toString());
		}
		if (!node.haveChild()) {
			return;
		}
		TrieNodeIterator nodeIterator = node.createIterator();
		while (nodeIterator.hasNext()) {
			int pos = nodeIterator.getPosition();
			char ch = TrieNode.getChar(pos);
			strBuf.append(ch);
			TrieNode nextNode = (TrieNode)nodeIterator.next();
			printTrieNode(nextNode, strBuf);
			strBuf.deleteCharAt(strBuf.length() - 1);
		}
	}

	public ArrayList<String> searchWithEditDist(String target, int editDist) {
		ArrayList<String> results = new ArrayList<String>();
		StringBuffer strBuf = new StringBuffer();
		dfsWithEditDist(this.root, target, 0, results, strBuf, editDist);
		return results;
	}

	public static void dfsWithEditDist(TrieNode node, String target, int currPos, ArrayList<String> results, StringBuffer strBuf, int editDist) {
		// end of recursion
		if (currPos == target.length() && editDist == 0) { // add to result list if end of target and end of word
			if (node.isEndOfWord()) {
				results.add(strBuf.toString());
			}
			return;
		}

		if (!node.haveChild() || currPos > target.length()) { // return if no childs to search or target's been traversed or editDist's been used
			return;
		}

		// then let's deal with edit distance!
		if (editDist < 0) {
			return;
		}
		else if (editDist > 0) {
			// first case: delete one character, ++ currPos, strBuf unchanged, -- editDist
			dfsWithEditDist(node, target, currPos + 1, results, strBuf, editDist - 1);

			TrieNodeIterator nodeIterator = node.createIterator();
			while (nodeIterator.hasNext()) {
				int pos = nodeIterator.getPosition();
				char ch = TrieNode.getChar(pos);
				TrieNode nextNode = (TrieNode)nodeIterator.next();

				// second case: insert one character, currPos unchanged, append to strBuf, -- editDist
				strBuf.append(ch);
				dfsWithEditDist(nextNode, target, currPos, results, strBuf, editDist - 1);
				strBuf.deleteCharAt(strBuf.length() - 1);

				if (currPos < target.length()) {
					// third case: modify one character, ++ currPos, append to strBuf, -- editDist
					if (ch != target.charAt(currPos)) {
						strBuf.append(ch);
						dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, editDist - 1);
						strBuf.deleteCharAt(strBuf.length() - 1);
					}

					// yield edit distance to next search
					if (ch == target.charAt(currPos)) {
						strBuf.append(ch);
						dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, editDist);
						strBuf.deleteCharAt(strBuf.length() - 1);
					}
				}
			}
		}
		else {
			TrieNode nextNode = node.findChar(target.charAt(currPos));
			if (nextNode != null) {
				strBuf.append(target.charAt(currPos));
				dfsWithEditDist(nextNode, target, currPos + 1, results, strBuf, 0);
				strBuf.deleteCharAt(strBuf.length() - 1);
			}
		}
	}

}
