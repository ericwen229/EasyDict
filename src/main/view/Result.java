package main.view;

// ================================
// Built-in modules

import javax.swing.JList;
import javax.swing.ListSelectionModel;

// ================================
// Class Result

/**
 * Result list
 *
 * @author ericwen229
 * @see main.view.ResultList
 */
public class Result extends JList<String> {

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and their behaviours
	 */
	Result() {
		super();

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Adjust appearance (nothing to do)
	 */
	void adjust() {
	}

}
