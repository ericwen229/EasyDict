package main.view;

// ================================
// Built-in modules

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

// ================================
// Class ResultList

/**
 * Result list consisting of precise result and
 * fuzzy result
 *
 * @author ericwen229
 * @see main.view.Result
 * @see main.view.SearchPanel
 */
public class ResultList extends JSplitPane {

	// ================================
	// Members

	/**
	 * Precise result
	 */
	private Result preciseResult;

	/**
	 * Fuzzy result
	 */
	private Result fuzzyResult;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and their behaviours
	 */
	ResultList() {
		super(JSplitPane.VERTICAL_SPLIT, true);

		this.preciseResult = new Result();
		this.fuzzyResult = new Result();
		JScrollPane precisePane = new JScrollPane(this.preciseResult);
		JScrollPane fuzzyPane = new JScrollPane(this.fuzzyResult);
		precisePane.setBorder(BorderFactory.createTitledBorder("Results"));
		fuzzyPane.setBorder(BorderFactory.createTitledBorder("Are you trying to search"));
		this.setTopComponent(precisePane);
		this.setBottomComponent(fuzzyPane);

		this.setOneTouchExpandable(true);
	}

	/**
	 * Adjust appearance
	 */
	void adjust() {
		this.preciseResult.adjust();
		this.fuzzyResult.adjust();

		this.setDividerLocation(0.5);
	}

	/**
	 * Get reference of precise result
	 *
	 * @return Result reference of precise result
	 */
	public Result getPreciseResult() {
		return this.preciseResult;
	}

	/**
	 * Get reference of fuzzy result
	 *
	 * @return Result reference of fuzzy result
	 */
	public Result getFuzzyResult() {
		return this.fuzzyResult;
	}

}
