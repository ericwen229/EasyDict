package main.view;

// ================================
// Built-in modules

import javax.swing.JSplitPane;

// ================================
// User-defined modules

import main.controller.MainPanelController;

// ================================
// Class MainPanel

/**
 * Main panel in main window as a split pane
 *
 * @author ericwen229
 * @see main.view.MainWindow
 * @see main.view.SearchPanel
 * @see main.view.ResultPanel
 */
public class MainPanel extends JSplitPane {

	// ================================
	// Members

	/**
	 * search panel
	 */
	private SearchPanel searchPanel;

	/**
	 * result panel
	 */
	private ResultPanel resultPanel;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and their behaviours
	 */
	MainPanel() {
		super(JSplitPane.HORIZONTAL_SPLIT, true);

		this.searchPanel = new SearchPanel();
		this.resultPanel = new ResultPanel();
		this.setLeftComponent(this.searchPanel);
		this.setRightComponent(this.resultPanel);

		this.setOneTouchExpandable(false);

		MainPanelController controller = new MainPanelController(this);
		this.searchPanel.getResultList().getPreciseResult().addListSelectionListener(controller);
		this.searchPanel.getResultList().getFuzzyResult().addListSelectionListener(controller);

	}

	/**
	 * Adjust appearance
	 */
	void adjust() {
		this.searchPanel.adjust();
		this.resultPanel.adjust();

		this.setDividerLocation(0.32);
	}

	/**
	 * Get reference of result panel
	 *
	 * @return ResultPanel reference
	 */
	public ResultPanel getResultPanel() {
		return this.resultPanel;
	}

}
