# EasyDict

Just another offline dictionary application implemented in Java.

###Features

* Completely **offline** available.
* **Real-time** display of search results.
* Fuzzy search with **edit distance**.

###About visualization
The visualiztion version generate a .dot file with trie.
The diagram(or part of the diagram) can be generated with the .dot file using dot.

###For developers

* Words are stored in Trie with the advantage of supporting multiple search strategies in O(1) time.
* To deal with repetition issue of searching with edit distance, bloomfilter is introduced, which takes O(1) time to mark a string as appeared or check if a string has HIGHLY POSSIBLY already appeared.
