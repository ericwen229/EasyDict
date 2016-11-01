package main.controller;

// ================================
// Built-in modules

import javax.swing.SwingWorker;

// ================================
// User-defined modules

import main.model.dict.Dict;

/**
 * Precisely search the dictionary for a single word
 * and return the result in real time.
 * <p>Can be cancelled when interrupted.
 *
 * @author ericwen229
 * @see main.model.dict.Dict
 */
public class PreciseDictSearcher extends SwingWorker<Object, String> {

	// ================================
	// Static members

	/**
	 * Singleton reference of the only copy
	 * of PreciseDictSearcher instance
	 */
	private static PreciseDictSearcher searcher;

	// ================================
	// Members

	/**
	 * Keyword to be searched
	 */
	private String key;

	/**
	 * Reference to dictionary
	 */
	private Dict d;

	/**
	 * Reference of resultList Controller
	 */
	private ResultListController controller;

	// ================================
	// Member functions

	private PreciseDictSearcher(String key, ResultListController controller) {
		this.key = key;
		this.d = Dict.createDict();
		this.controller = controller;
	}

	public static PreciseDictSearcher createPreciseDictSearcher(String key, ResultListController controller) {
		if (PreciseDictSearcher.searcher == null) {
			PreciseDictSearcher.searcher = new PreciseDictSearcher(key, controller);
		}
		else {
			PreciseDictSearcher.searcher.cancel(true);
			PreciseDictSearcher.searcher = new PreciseDictSearcher(key, controller);
		}
		return PreciseDictSearcher.searcher;
	}

	@Override
	protected Object doInBackground() {
		return null;
	}

}
