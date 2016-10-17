package main.model.dict;

import main.model.wordinfo.WordInfo;

import java.util.*;

class TrieNode {

	private static final int numOfChar = 28;
	private static final Map<Integer, Integer> charMap = new HashMap<>();
	private static final char[] charList = new char[]{
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
		'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
		'-',
	};

	private boolean isEndOfWord;
	private WordInfo info;
	private TrieNode[] childs;

	public TrieNode() {
		this.isEndOfWord = false;
		this.info = null;
		this.childs = new TrieNode[TrieNode.numOfChar];
		for (int i = 0; i < TrieNode.numOfChar; ++ i) {
			this.childs[i] = null;
			TrieNode.charMap.put('a' + i, i);
		}
		TrieNode.charMap.put((int)' ', 26);
		TrieNode.charMap.put((int)'-', 27);
	}

	TrieNodeIterator createIterator() {
		return new TrieNodeIterator(this.childs);
	}

	static char getChar(int index) {
		return TrieNode.charList[index];
	}

	static boolean validChar(char c) {
		return TrieNode.charMap.containsKey((int)c);
	}

	TrieNode findChar(char c) {
		if (!TrieNode.validChar(c)) {
			return null;
		}
		else {
			return this.childs[TrieNode.charMap.get((int)c)];
		}
	}

	boolean isEndOfWord() {
		return this.isEndOfWord;
	}

	void setEndOfWord() {
		this.isEndOfWord = true;
	}

	void addInfo(WordInfo info) {
		this.info = info;
	}

	boolean haveChild() {
		return this.createIterator().hasNext();
	}

	TrieNode addChild(char c) {
		int index = TrieNode.charMap.get((int)c);
		if (this.childs[index] == null) {
			this.childs[index] = new TrieNode();
		}
		return this.childs[index];
	}

	WordInfo getInfo() {
		return this.info;
	}

}

class TrieNodeIterator {

	private TrieNode[] childs;
	private int position = 0;

	TrieNodeIterator(TrieNode[] childs) {
		this.childs = childs;
	}

	TrieNode next() {
		if (!hasNext()) {
			return null;
		}
		return this.childs[this.position ++];
	}

	boolean hasNext() {
		for (int i = this.position; i < childs.length; ++ i) {
			if (this.childs[i] != null) {
				this.position = i;
				return true;
			}
		}
		return false;
	}

	int getPosition() {
		return this.position;
	}

}
