package main.model.dict;

// ================================
// Built-in modules

import java.util.*;

// ================================
// User-defined modules

import main.model.wordinfo.WordInfo;

// ================================
// Class TrieNode

/**
 * Node of Trie. Positions of childs indicate possible characters
 * at this level. There is a static map to map from characters to
 * positions and a static array to map from positions to characters
 * for convenience.
 *
 * @author ericwen229
 * @see            main.model.wordinfo.WordInfo
 * @see main.model.dict.Trie
 */
class TrieNode {

	// ================================
	// Static members

	/**
	 * Number of character supported
	 */
	private static final int numOfChar = 28;

	/**
	 * Map characters to child positions
	 */
	private static final Map<Integer, Integer> charMap = new HashMap<>();

	/**
	 * Map child positions to characters
	 */
	private static final char[] charList = new char[]{
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
			'-',
	};

	// ================================
	// Static member functions

	/**
	 * Get expected character at given position
	 *
	 * @param pos character's position
	 * @return character at the position
	 */
	static char getChar(int pos) {
		return TrieNode.charList[pos];
	}

	/**
	 * Tells if a character is supported
	 *
	 * @param c character to be checked
	 * @return whether the character is supported
	 */
	static boolean validChar(char c) {
		return TrieNode.charMap.containsKey((int) c);
	}

	// ================================
	// Members

	/**
	 * Indicates whether current node CAN be end of word
	 */
	private boolean isEndOfWord = false;

	/**
	 * Information of word if current node can be end of node
	 */
	private WordInfo info = null;

	/**
	 * Childs of current node
	 */
	private TrieNode[] childs;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initialize the child and
	 * character map (static member)
	 */
	TrieNode() {
		this.childs = new TrieNode[TrieNode.numOfChar];
		for (int i = 0; i < TrieNode.numOfChar; ++i) {
			this.childs[i] = null;
			TrieNode.charMap.put('a' + i, i);
		}
		TrieNode.charMap.put((int) ' ', 26);
		TrieNode.charMap.put((int) '-', 27);
	}

	/**
	 * Create iterator for traversing through childs
	 *
	 * @return reference of newly created iterator
	 */
	TrieNodeIterator createIterator() {
		return new TrieNodeIterator();
	}

	/**
	 * Find the child corresponding to given character
	 *
	 * @param ch specified character
	 * @return corresponding child, null if character not valid
	 */
	TrieNode findChar(char ch) {
		if (!TrieNode.validChar(ch)) {
			return null;
		} else {
			return this.childs[TrieNode.charMap.get((int) ch)];
		}
	}

	/**
	 * Tell if current node can be end of word
	 *
	 * @return true if current node can be end of word
	 */
	boolean isEndOfWord() {
		return this.isEndOfWord;
	}

	/**
	 * Set current node as end of word
	 */
	void setEndOfWord() {
		this.isEndOfWord = true;
	}

	/**
	 * Add information to current node (i.e. to the word that ends here)
	 *
	 * @param        info information to be added
	 */
	void addInfo(WordInfo info) {
		this.info = info;
	}

	/**
	 * Tells if current node have any child
	 *
	 * @return true if current node have any child
	 */
	boolean haveChild() {
		return this.createIterator().hasNext();
	}

	/**
	 * Add child to position corresponding to given character
	 *
	 * @param ch character that specifies position
	 * @return reference of child (newly created if was null)
	 */
	TrieNode addChild(char ch) {
		int index = TrieNode.charMap.get((int) ch);
		if (this.childs[index] == null) {
			this.childs[index] = new TrieNode();
		}
		return this.childs[index];
	}

	/**
	 * Get word information stored in current node
	 *
	 * @return word information
	 */
	WordInfo getInfo() {
		return this.info;
	}

	// ================================
	// Nested classes

	// ================================
	// Class TrieNode Iterator

	/**
	 * Iterator for iterating through childs
	 *
	 * @author ericwen229
	 * @see main.model.dict.TrieNode
	 */
	class TrieNodeIterator {

		// ================================
		// Members

		/**
		 * Current position. When hasNext() is called, it is set
		 * to the position of child to be returned. Before next()
		 * is called, hasNext() is called first.
		 */
		private int position = 0;

		// ================================
		// Member functions

		/**
		 * Get next child
		 *
		 * @return reference of next child (null if !hasNext())
		 */
		TrieNode next() {
			if (!hasNext()) {
				return null;
			}
			return TrieNode.this.childs[this.position++];
		}

		/**
		 * Has next child to be visited
		 *
		 * @return true if there're childs unvisited left
		 */
		boolean hasNext() {
			for (int i = this.position; i < childs.length; ++i) {
				if (TrieNode.this.childs[i] != null) {
					this.position = i;
					return true;
				}
			}
			return false;
		}

		/**
		 * Get corresponding character of next child to be visited
		 *
		 * @return corresponding character
		 */
		char getNextChar() {
			return TrieNode.getChar(this.position);
		}

	}

}
