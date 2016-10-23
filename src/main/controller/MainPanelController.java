package main.controller;

// ================================
// Built-in modules

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

// ================================
// User-defined modules

import main.model.dict.Dict;
import main.model.wordinfo.WordInfo;

import main.view.MainPanel;
import main.view.Result;

// ================================
// Class MainPanelController

/**
 * Update ResultPanel when selection event of ResultList is detected
 *
 * @author ericwen229
 * @see main.model.dict.Dict
 * @see main.model.wordinfo.WordInfo
 * @see main.controller.ResultPanelController
 * @see main.view.MainPanel
 * @see main.view.Result
 */
public class MainPanelController implements ListSelectionListener {

	// ================================
	// Members

	/**
	 * MainPanel Reference
	 */
	private final MainPanel mainPanel;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes MainPanel reference
	 *
	 * @param        mainPanel reference of MainPanel
	 */
	public MainPanelController(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * Call back function when list selection event is detected
	 *
	 * @param        e list selection event
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		String word = ((Result) e.getSource()).getSelectedValue();
		if (word != null) {
			ResultPanelController controller = new ResultPanelController(this.mainPanel.getResultPanel());
			if (controller.haveWord(word)) {
				controller.selectWord(word);
				return;
			}
			Dict d = Dict.createDict();
			WordInfo info = d.retrieveInfo(word);
			controller.addTab(word, info);
		}
	}

}
