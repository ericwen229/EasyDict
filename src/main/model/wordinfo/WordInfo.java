package main.model.wordinfo;

// ================================
// Class WordInfo

/**
 * Stores read-only information about a word
 *
 * @author ericwen229
 * @see main.model.dict.TrieNode
 * @see main.controller.MainPanelController
 */
public class WordInfo {

	// ================================
	// Members

	/**
	 * Phonetic of word, null as default
	 */
	private final String phonetic;

	/**
	 * Translation of word, null as default
	 */
	private final String translation;

	/**
	 * Explanations of word, null as default
	 */
	private final String[] explanation;

	/**
	 * Explanations from other websites, null as default
	 */
	private final String[][] webExpla;

	// ================================
	// Member functions

	/**
	 * Class constructor that initialize every read-only member
	 *
	 * @param phonetic    phonetic of word
	 * @param translation translation of word
	 * @param explanation explanations of word
	 * @param webExpla    web explanations of word
	 */
	public WordInfo(String phonetic, String translation, String[] explanation, String[][] webExpla) {
		this.phonetic = (phonetic.equals("#")) ? null : phonetic;
		this.translation = (translation.equals("#")) ? null : translation;
		this.explanation = (explanation.length == 0) ? null : explanation;
		this.webExpla = (webExpla.length == 0) ? null : webExpla;
	}

	/**
	 * Generate HTML doc from word information for display
	 *
	 * @param word word corresponding to information
	 * @return HTML doc
	 */
	public String generateHTML(String word) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		buffer.append("<h1>");
		buffer.append(word);
		buffer.append("</h1>");
		if (this.phonetic != null) {
			buffer.append("<h3>");
			buffer.append(this.phonetic);
			buffer.append("</h3>");
		}
		if (this.translation != null) {
			buffer.append("<h3>");
			buffer.append(this.translation);
			buffer.append("</h3>");
		}
		if (this.explanation != null) {
			buffer.append("<br><br>");
			buffer.append("<h3>释义：</h3>");
			for (String expla : this.explanation) {
				buffer.append("<p><strong>");
				buffer.append(expla);
				buffer.append("</strong></p>");
			}
		}
		if (this.webExpla != null) {
			buffer.append("<br><br>");
			buffer.append("<h3>网络释义：</h3>");
			for (String[] expla : this.webExpla) {
				buffer.append("<p><strong>");
				buffer.append(expla[0]);
				buffer.append("</strong></p>");
				buffer.append("<p>");
				buffer.append(expla[1]);
				buffer.append("</p>");
			}
		}
		buffer.append("</html>");
		return buffer.toString();
	}

}