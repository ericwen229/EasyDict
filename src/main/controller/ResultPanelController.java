package main.controller;

// ================================
// Built-in modules

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// ================================
// User-defined modules

import main.model.wordinfo.WordInfo;
import main.view.ResultPanel;

// ================================
// Class ResultPanelController

/**
 * Update ResultPanel content and control tab behaviours
 *
 * @author ericwen229
 * @see main.model.wordinfo.WordInfo
 * @see main.controller.MainPanelController
 * @see main.view.ResultPanel
 */
public class ResultPanelController extends MouseAdapter {

	// ================================
	// Members

	/**
	 * ResultPanel reference
	 */
	private ResultPanel resultPanel;

	/**
	 * Maximum number of tabs
	 */
	private final int maxTabNum = 4;

	/**
	 * Current number of tabs
	 */
	private int currTabNum;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes ResultPanel reference
	 * and updates number of tabs
	 *
	 * @param resultPanel reference of ResultPanel
	 */
	public ResultPanelController(ResultPanel resultPanel) {
		this.resultPanel = resultPanel;
		this.currTabNum = this.resultPanel.getTabCount();
	}

	/**
	 * Tells if current tabs contain given word
	 *
	 * @param word word to be tested
	 * @return true if current tabs contain given word
	 */
	boolean haveWord(String word) {
		return this.resultPanel.indexOfTab(word) != -1;
	}

	/**
	 * Select tab that contains given word
	 *
	 * @param word word to be selected
	 */
	void selectWord(String word) {
		int index = this.resultPanel.indexOfTab(word);
		if (index != -1) {
			this.resultPanel.setSelectedIndex(index);
		}
	}

	/**
	 * Add a tab to ResultPanel with given word and information
	 * (not necessarily add when word is in panel or tab number
	 * reaches maximum)
	 *
	 * @param word word to be added
	 * @param info information of word to be added
	 */
	void addTab(String word, WordInfo info) {
		int index = this.resultPanel.indexOfTab(word);
		if (index != -1) { // Word already displayed
			this.resultPanel.setSelectedIndex(index);
			return;
		}
		JEditorPane infoPanel = new JEditorPane();
		infoPanel.setEditable(false);
		infoPanel.setContentType("text/html");
		infoPanel.setText(info.generateHTML(word));
		JScrollPane scrollInfoPanel = new JScrollPane(infoPanel);
		if (this.resultPanel.getWelcome()) { // Only welcome panel
			this.resultPanel.setTitleAt(0, word);
			this.resultPanel.setComponentAt(0, scrollInfoPanel);
			this.resultPanel.setWelcome(false);
		} else { // Add tab
			int addIndex = 0;
			if (this.currTabNum == this.maxTabNum) { // Reach max num, then set last tab
				addIndex = this.resultPanel.getSelectedIndex();
				this.resultPanel.setTitleAt(addIndex, word);
				this.resultPanel.setComponentAt(addIndex, scrollInfoPanel);
			} else { // Add tab to last
				this.resultPanel.addTab(word, scrollInfoPanel);
				++this.currTabNum;
				addIndex = this.currTabNum - 1;
			}
			this.resultPanel.setSelectedIndex(addIndex); // Select added tab (always at tail)
		}
	}

	/**
	 * Double click to close tab
	 *
	 * @param e mouse event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int index = this.resultPanel.getSelectedIndex();
			this.currTabNum = this.resultPanel.getTabCount();
			if (this.currTabNum == 1) { // One tab left then set welcome tab
				this.resultPanel.setWelcomeTab();
				this.resultPanel.setWelcome(true);
			} else {
				this.resultPanel.removeTabAt(index);
				--this.currTabNum;
			}
		}
	}

}
