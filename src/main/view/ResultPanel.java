package main.view;

import main.controller.ResultPanelController;

import javax.swing.JTabbedPane;

public class ResultPanel extends JTabbedPane {

	private boolean isWelcome;

	ResultPanel() {
		super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		this.addTab("Welcome", null);
		this.isWelcome = true;
		this.addMouseListener(new ResultPanelController(this));
	}

	void adjust() {
	}

	public void setWelcomeTab() {
		this.setTitleAt(0, "Welcome");
		this.setComponentAt(0, null);
	}

	public boolean getWelcome() {
		return this.isWelcome;
	}

	public void setWelcome(boolean isWelcome) {
		this.isWelcome = isWelcome;
	}

}
