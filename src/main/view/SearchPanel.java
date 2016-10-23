package main.view;

// ================================
// Built-in modules

import java.awt.BorderLayout;
import javax.swing.JPanel;

// ================================
// User-defined modules

import main.controller.SearchPanelController;

// ================================
// Class SearchPanel

/**
 * Search panel including input box and result list
 *
 * @author ericwen229
 * @see main.view.SearchPanel
 * @see main.view.SearchInputBox
 * @see main.view.ResultList
 */
public class SearchPanel extends JPanel {

	// ================================
	// Members

	/**
	 * Input box
	 */
	private SearchInputBox inputBox;

	/**
	 * Result list
	 */
	private ResultList resultList;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components and
	 * add event listener
	 */
	SearchPanel() {
		super();

		this.inputBox = new SearchInputBox();
		this.resultList = new ResultList();

		this.setLayout(new BorderLayout());
		this.add(this.inputBox, BorderLayout.NORTH);
		this.add(this.resultList, BorderLayout.CENTER);

		this.inputBox.getDocument().addDocumentListener(new SearchPanelController(this));
	}

	/**
	 * Adjust appearance
	 */
	void adjust() {
		this.inputBox.adjust();
		this.resultList.adjust();
	}

	/**
	 * Get reference of input box
	 *
	 * @return reference of InputBox
	 */
	public SearchInputBox getInputBox() {
		return this.inputBox;
	}

	/**
	 * Get reference of result list
	 *
	 * @return reference of ResultList
	 */
	public ResultList getResultList() {
		return this.resultList;
	}

}
