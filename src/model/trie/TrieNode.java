package model.trie;

import java.util.*;

class TrieNode {

	private static final int numOfChar = 26;
	private static final Map<Integer, Integer> charMap = new HashMap<Integer, Integer>();
	private static final char[] charList = new char[]{
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
		'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
		's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
	};
	private TrieNodeStatus status;
	private TrieNode[] childs;

	private enum TrieNodeStatus {
		PASS, ENDOFWORD
	}

	public TrieNode() {
		this.status = TrieNodeStatus.PASS;
		this.childs = new TrieNode[this.numOfChar];
		for (int i = 0; i < this.numOfChar; ++ i) {
			this.childs[i] = null;
			this.charMap.put((int)('a' + i), i);
		}
	}

	public TrieNodeIterator createIterator() {
		return new TrieNodeIterator(this.childs);
	}

	public static char getChar(int index) {
		return charList[index];
	}

	public static boolean validChar(char c) {
		return charMap.containsKey((int)c);
	}

	public TrieNode findChar(char c) {
		if (!validChar(c)) {
			return null;
		}
		else {
			return this.childs[this.charMap.get((int)c)];
		}
	}

	public boolean isEndOfWord() {
		return this.status == TrieNodeStatus.ENDOFWORD;
	}

	public void setEndOfWord() {
		this.status = TrieNodeStatus.ENDOFWORD;
	}

	public boolean haveChild() {
		for (int i = 0; i < this.numOfChar; ++ i) {
			if (this.childs[i] != null) {
				return true;
			}
		}
		return false;
	}

	public TrieNode addChild(char c) {
		int index = this.charMap.get((int)c);
		if (this.childs[index] == null) {
			this.childs[index] = new TrieNode();
		}
		return this.childs[index];
	}

}

class TrieNodeIterator {

	private TrieNode[] childs;
	private int position = 0;

	public TrieNodeIterator(TrieNode[] childs) {
		this.childs = childs;
	}

	public TrieNode next() {
		if (!hasNext()) {
			return null;
		}
		return this.childs[this.position ++];
	}

	public boolean hasNext() {
		for (int i = this.position; i < childs.length; ++ i) {
			if (this.childs[i] != null) {
				this.position = i;
				return true;
			}
		}
		return false;
	}

	public int getPosition() {
		return this.position;
	}

}
