ENTRY_POINT = DictTester

SOURCE_FILES = \
	model/bloomfilter/BloomFilter.java \
	model/trie/TrieNode.java \
	model/trie/Trie.java \
	model/dict/Dict.java \
	DictTester.java

JAVAC = javac
JFLAGS =

CLASSPATH = build/classes
SOURCEPATH = src

vpath %.class $(CLASSPATH)
vpath %.java $(SOURCEPATH)

.PHONY: clean run

Default:
	@echo "make new: new project, create src, bin, res dirs."
	@echo "make build: build project."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run your app."

build: $(SOURCE_FILES:.java=.class)

%.class: %.java
	$(JAVAC) -cp $(CLASSPATH) -d $(CLASSPATH) $(JFLAGS) $<

rebuild: clean build

clean:
	rm -frv $(CLASSPATH)/*

run:
	java -cp $(CLASSPATH) $(ENTRY_POINT)
