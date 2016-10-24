package main.model.dict;

// ================================
// Class BloomFilter

/**
 * BloomFilter tells if a string has already appeared
 * (i.e. has already been added to it). It consists
 * of a bit array and multiple hash functions. When
 * adding a word it calculate hash values for this word
 * and set the corresponding bits in bit array. When
 * checking a word it calculate hash values for this
 * word and check the corresponding bits. If all bits
 * are set, the word has HIGHLY POSSIBLY already appeared.
 * <p>Notice that it goes HIGHLY POSSIBLY here. A
 * word has already appeared will get a certain result
 * while a word hasn't MAY be regarded as APPEARED since
 * the corresponding bits may be set by other words
 * (collision).
 * <p>Basically two factors would affect accuracy:
 * size of bit array and number of hash function list
 * (given that those hash functions are well designed).
 * It would be more accurate to have more hash functions,
 * while at the same time more hash functions will
 * consume bit array faster and generate more collisions.
 *
 * @author ericwen229
 * @see main.model.dict.Dict
 */
class BloomFilter {

	// ================================
	// Members

	/**
	 * Size of bit array
	 */
	private final int arraySize;

	/**
	 * Hash functions using Strategy Pattern
	 */
	private final Hash[] hashFuncList = new Hash[]{
			new Hash1(), new Hash2(), new Hash3(),
			new Hash4(), new Hash5(),
	};

	/**
	 * Bit array
	 */
	private boolean[] bitArray;

	// ================================
	// Member functions

	/**
	 * Default class constructor that specifies the size of bit
	 * array and unset each bit
	 *
	 * @param size size of the bit array
	 */
	BloomFilter(int size) {
		this.arraySize = size;
		this.bitArray = new boolean[this.arraySize];
		for (int i = 0; i < this.arraySize; ++i) {
			this.bitArray[i] = false;
		}
	}

	/**
	 * Mark a string as appeared by setting the bits
	 * corresponding to hash values of the string
	 *
	 * @param str string to be marked
	 */
	void add(String str) {
		for (int i = 0; i < hashFuncList.length; ++i) {
			this.bitArray[(this.hashFuncList[i].hash(str) % this.arraySize + this.arraySize) % this.arraySize] = true;
		}
	}

	/**
	 * Tells if a string has already appeared by checking
	 * bits corresponding to hash values of the string.
	 * If yes then the string has HIGHLY POSSIBLY already
	 * appeared.
	 *
	 * @param str string to be tested
	 * @return true if string has already appeared
	 */
	boolean contains(String str) {
		for (int i = 0; i < hashFuncList.length; ++i) {
			if (!this.bitArray[(this.hashFuncList[i].hash(str) % this.arraySize + this.arraySize) % this.arraySize]) {
				return false;
			}
		}
		return true;
	}

}

// ================================
// Interface Hash

/**
 * Interface of hash function that contains only
 * one method used to compute hash value of a string.
 * This interface is created to use strategy pattern.
 */
interface Hash {
	/**
	 * Compute hash value of a string
	 *
	 * @param str string to be hashed
	 * @return hash value of the string
	 */
	int hash(String str);
}

// ================================
// Hash classes

/**
 * Built-in hash function implements Hash
 *
 * @author ericwen229
 * @see main.model.dict.Hash
 */
class Hash1 implements Hash {
	public int hash(String str) {
		return str.hashCode();
	}
}

/**
 * Built-in hash function with coefficient 13 implements Hash
 *
 * @author ericwen229
 * @see main.model.dict.Hash
 */
class Hash2 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 13;
	}
}

/**
 * Built-in hash function with coefficient 23 implements Hash
 *
 * @author ericwen229
 * @see main.model.dict.Hash
 */
class Hash3 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 23;
	}
}

/**
 * Built-in hash function with coefficient 37 implements Hash
 *
 * @author ericwen229
 * @see main.model.dict.Hash
 */
class Hash4 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 37;
	}
}

/**
 * Built-in hash function with coefficient 47 implements Hash
 *
 * @author ericwen229
 * @see main.model.dict.Hash
 */
class Hash5 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 47;
	}
}
