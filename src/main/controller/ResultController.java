package main.controller;

// ================================
// Built-in modules

import java.util.ArrayList;
import javax.swing.DefaultListModel;

// ================================
// User-Defined modules

import main.view.Result;

// ================================
// Class ResultController

/**
 * Update Result content
 *
 * @author ericwen229
 * @see main.controller.ResultListController
 * @see main.view.Result
 */
class ResultController {

	// ================================
	// Members

	/**
	 * List model
	 */
	private final DefaultListModel<String> model;

	// ================================
	// Member functions

	/**
	 * Default class constructor that sets result model
	 *
	 * @param        result reference of Result
	 */
	ResultController(Result result) {
		this.model = new DefaultListModel<>();
		result.setModel(this.model);
	}

	/**
	 * Set Result content with given list
	 *
	 * @param        list content to be set
	 */
	void setList(ArrayList<String> list) {
		this.clear();
		list.forEach(this.model::addElement);
	}

	/**
	 * Clear content of Result
	 */
	void clear() {
		this.model.clear();
	}

}
