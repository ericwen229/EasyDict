package model.bloomfilter;

import java.util.*;

public class BloomFilter {

	private boolean[] hashTable;
	private int tableSize;
	private final Hash[] hashFuncList = new Hash[]{
		new Hash1(), new Hash2(), new Hash3(),
		new Hash4(), new Hash5(),
	};

	public BloomFilter(int size) {
		this.tableSize = size;
		this.hashTable = new boolean[this.tableSize];
		for (int i = 0; i < this.tableSize; ++ i) {
			this.hashTable[i] = false;
		}
	}

	public void add(String str) {
		for (int i = 0; i < hashFuncList.length; ++ i) {
			this.hashTable[(this.hashFuncList[i].hash(str) % this.tableSize + this.tableSize) % this.tableSize] = true;
		}
	}

	public boolean contains(String str) {
		for (int i = 0; i < hashFuncList.length; ++ i) {
			if (!this.hashTable[(this.hashFuncList[i].hash(str) % this.tableSize + this.tableSize) % this.tableSize]) {
				return false;
			}
		}
		return true;
	}

}

interface Hash {
	int hash(String str);
}

class Hash1 implements Hash {
	public int hash(String str) {
		return str.hashCode();
	}
}

class Hash2 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 13;
	}
}

class Hash3 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 23;
	}
}

class Hash4 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 37;
	}
}

class Hash5 implements Hash {
	public int hash(String str) {
		return str.hashCode() * 47;
	}
}
