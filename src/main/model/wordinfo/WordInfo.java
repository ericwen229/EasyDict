package main.model.wordinfo;

public class WordInfo {

    private String phonetic;
    private String translation;
    private String[] explanation;
    private String[][] webExpla;

    public WordInfo(String phonetic, String translation, String[] explanation, String[][] webExpla) {
        this.phonetic = (phonetic == "#")? null: phonetic;
        this.translation = (translation == "#")? null: translation;
        this.explanation = (explanation.length == 0)? null: explanation;
        this.webExpla = (webExpla.length == 0)? null: webExpla;
    }

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
            for (String expla: this.explanation) {
                buffer.append("<p><strong>");
                buffer.append(expla);
                buffer.append("</strong></p>");
            }
        }
        if (this.webExpla != null) {
            buffer.append("<br><br>");
            buffer.append("<h3>网络释义：</h3>");
            for (String[] expla: this.webExpla) {
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