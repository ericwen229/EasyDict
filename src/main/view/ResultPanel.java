package main.view;

// ================================
// Built-in modules

import javax.swing.JTabbedPane;

// ================================
// User-defined modules

import main.controller.ResultPanelController;

// ================================
// Class ResultPanel

/**
 * Result panel that contains detailed information
 * of multiple words
 *
 * @author ericwen229
 * @see main.view.MainPanel
 */
public class ResultPanel extends JTabbedPane {

	// ================================
	// Members

	/**
	 * true if only tab is welcome tab
	 */
	private boolean isWelcome;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and their behaviours, and adds mouse listener to tabs
	 */
	ResultPanel() {
		super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		this.addTab("Welcome", null);
		this.isWelcome = true;
		this.addMouseListener(new ResultPanelController(this));
	}

	/**
	 * Adjust appearance (nothing to do)
	 */
	void adjust() {
	}

	/**
	 * Set the first tab to welcome tab
	 */
	public void setWelcomeTab() { // TODO: add user guide here
		this.setTitleAt(0, "Welcome");
		this.setComponentAt(0, null);
	}

	/**
	 * Tells if current only tab is welcome tab
	 *
	 * @return true if current is welcome tab
	 */
	public boolean getWelcome() {
		return this.isWelcome;
	}

	/**
	 * Set isWelcome flag
	 *
	 * @param isWelcome true if current is welcome
	 */
	public void setWelcome(boolean isWelcome) {
		this.isWelcome = isWelcome;
	}

}
