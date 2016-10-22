package main.view;

import java.awt.*;
import javax.swing.*;

public class SearchInputBox extends JTextField {

	private final Color ghostColor = Color.LIGHT_GRAY;
	private final Color successColor = Color.BLACK;
	private final Color failColor = Color.RED;

	SearchInputBox() {
		super();
	}

	void adjust() {
	}

	public void setSuccessColor() {
		this.setForeground(this.successColor);
	}

	public void setFailColor() {
		this.setForeground(this.failColor);
	}

	public void setGhostColor() {
		this.setForeground(this.ghostColor);
	}

}
