package main.controller;

// ================================
// Built-in modules

import java.util.ArrayList;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

// ================================
// User-defined modules

import main.model.dict.Dict;
import main.view.SearchInputBox;
import main.view.ResultList;
import main.view.SearchPanel;

// ================================
// Class SearchPanelController

/**
 * Display ghost text and perform search in real time
 * when user input changes
 *
 * @author ericwen229
 * @see main.view.SearchInputBox
 * @see main.view.SearchPanel
 * @see main.view.SearchInputBox
 * @see main.view.ResultList
 */
public class SearchPanelController implements FocusListener, DocumentListener, PropertyChangeListener {

	// ================================
	// Members

	/**
	 * true if input box is empty or contains ghost text
	 */
	private boolean isEmpty = true;

	/**
	 * true if input box contains ghost text
	 */
	private boolean isGhost = false;

	/**
	 * ghost text
	 */
	private final String ghostText = "Search";

	/**
	 * SearchInputBox reference
	 */
	private final SearchInputBox inputBox;

	/**
	 * ResultList reference
	 */
	private final ResultList resultList;

	/**
	 * Maximum result number searched with common prefix
	 */
	private final int maxNumWithCommonPrefix = 128;

	// ================================
	// Member functions

	/**
	 * Default class constructor to initialize references
	 * and update states
	 *
	 * @param searchPanel reference of SearchPanel
	 */
	public SearchPanelController(SearchPanel searchPanel) {
		this.inputBox = searchPanel.getInputBox();
		this.inputBox.addFocusListener(this);

		this.updateState();
		if (!this.inputBox.hasFocus()) {
			this.focusLost(null);
		}

		this.resultList = searchPanel.getResultList();
	}

	/**
	 * Register listeners to respond to events
	 */
	private void registerListeners() {
		this.inputBox.getDocument().addDocumentListener(this);
		this.inputBox.addPropertyChangeListener("foreground", this);
	}

	/**
	 * Unregister listeners temporarily
	 */
	private void unregisterListeners() {
		this.inputBox.getDocument().removeDocumentListener(this);
		this.inputBox.removePropertyChangeListener("foreground", this);
	}

	/**
	 * When focus is gained and input box is empty,
	 * clear ghost text and set text color
	 *
	 * @param e focus event
	 */
	@Override
	public void focusGained(FocusEvent e) {
		this.updateState();
		if (this.isEmpty) {
			this.unregisterListeners();
			try {
				this.isGhost = false;
				this.inputBox.setText("");
				this.inputBox.setSuccessColor();
			} finally {
				this.registerListeners();
			}
		}
	}

	/**
	 * When focus is lost and input ox is empty,
	 * set ghost text and set text color
	 *
	 * @param e focus event
	 */
	@Override
	public void focusLost(FocusEvent e) {
		this.updateState();
		if (this.isEmpty) {
			this.unregisterListeners();
			try {
				this.isGhost = true;
				this.inputBox.setText(this.ghostText);
				this.inputBox.setGhostColor();
			} finally {
				this.registerListeners();
			}
		}
	}

	/**
	 * Update states when property change is detected
	 *
	 * @param evt property change event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.updateState();
	}

	/**
	 * Update states when document property change is detected
	 *
	 * @param e document event
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		this.updateState();
	}

	/**
	 * Perform search when content of input box changes
	 *
	 * @param e document event
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.updateContent();
	}

	/**
	 * Perform search when content of input box changes
	 *
	 * @param e document event
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		this.updateContent();
	}

	/**
	 * Perform search using current content in input box
	 */
	private void updateContent() {
		this.updateState();
		boolean lastSuccess = true;
		if (!this.isEmpty) { // Not empty then search
			String word = this.inputBox.getText();
			Dict d = Dict.createDict();
			ResultListController resultListController = new ResultListController(this.resultList);
			//resultListController.clearPreciseResult();
			//resultListController.clearFuzzyResult();

			// TODO: use SwingWorker here
			ArrayList<String> preciseResult = d.searchWithCommonPrefix(word, this.maxNumWithCommonPrefix);
			ArrayList<String> fuzzyResult = d.searchWithEditDist(word, 2);

			resultListController.setPreciseResult(preciseResult);
			resultListController.setFuzzyResult(fuzzyResult);
			lastSuccess = (preciseResult.size() > 0); // Last search success
		} else { // Clear result list
			ResultListController resultListController = new ResultListController(this.resultList);
			resultListController.clearPreciseResult();
			resultListController.clearFuzzyResult();
		}
		if (lastSuccess) { // Set text color of input box
			this.inputBox.setSuccessColor();
		} else {
			this.inputBox.setFailColor();
		}
	}

	/**
	 * Update states
	 */
	private void updateState() {
		this.isEmpty = (this.inputBox.getText().length() == 0);
		if (this.isGhost) {
			this.isEmpty = true;
		}
	}

}
