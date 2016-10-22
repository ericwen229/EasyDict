package main.view;

// ================================
// Built-in modules

import java.awt.Color;
import javax.swing.JTextField;

// ================================
// Class SearchInputBox

/**
 * Search input box
 *
 * @author ericwen229
 * @see main.view.SearchPanel
 */
public class SearchInputBox extends JTextField {

	// ================================
	// Members

	/**
	 * Ghost color
	 */
	private final Color ghostColor = Color.LIGHT_GRAY;

	/**
	 * Success color
	 */
	private final Color successColor = Color.BLACK;

	/**
	 * Fail color
	 */
	private final Color failColor = Color.RED;

	// ================================
	// Member functions

	/**
	 * Default class initializer
	 */
	SearchInputBox() {
		super();
	}

	/**
	 * Adjust appearance (nothing to do)
	 */
	void adjust() {
	}

	/**
	 * Set current color to be success color
	 */
	public void setSuccessColor() {
		this.setForeground(this.successColor);
	}

	/**
	 * Set current color to be fail color
	 */
	public void setFailColor() {
		this.setForeground(this.failColor);
	}

	/**
	 * Set current color to be ghost color
	 */
	public void setGhostColor() {
		this.setForeground(this.ghostColor);
	}

}
