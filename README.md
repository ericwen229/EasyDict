# EasyDict

Just another offline dictionary application implemented in Java.

###Features

* Completely **offline** available.
* **Real-time** display of search results.
* Fuzzy search with **edit distance**.

###For developers

* Words are stored in Trie with the advantage of supporting multiple search strategies in O(1) time.
* To deal with repetition issue of searching with edit distance, bloomfilter is introduced, which takes O(1) time to mark a string as appeared or check if a string has HIGHLY POSSIBLY already appeared.

###About `dev` branch

* For now, the program isn't able to respond to any input from user until search within dictionary is complete. I'm considering using SwingWorker to make this asynchronous. This requires huge refactor since DFS need to know whether the user want it cancelled.

