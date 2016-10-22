package main.controller;

// ================================
// Built-in modules

import java.util.ArrayList;

// ================================
// User-defined modules

import main.view.ResultList;

// ================================
// Class ResultListController

/**
 * Update ResultList content
 */
class ResultListController {

	// ================================
	// Members

	/**
	 * ResultList reference
	 */
	private final ResultList resultList;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes ResultList reference
	 *
	 * @param resultList reference of ResultList
	 */
	ResultListController(ResultList resultList) {
		this.resultList = resultList;
	}

	/**
	 * Set precise result
	 *
	 * @param list precise result
	 */
	void setPreciseResult(ArrayList<String> list) {
		ResultController controller = new ResultController(resultList.getPreciseResult());
		controller.setList(list);
	}

	/**
	 * Set fuzzy result
	 *
	 * @param list fuzzy result
	 */
	void setFuzzyResult(ArrayList<String> list) {
		ResultController controller = new ResultController(resultList.getFuzzyResult());
		controller.setList(list);
	}

	/**
	 * Clear precise result
	 */
	void clearPreciseResult() {
		ResultController controller = new ResultController(resultList.getPreciseResult());
		controller.clear();
	}

	/**
	 * Clear fuzzy result
	 */
	void clearFuzzyResult() {
		ResultController controller = new ResultController(resultList.getFuzzyResult());
		controller.clear();
	}

}
