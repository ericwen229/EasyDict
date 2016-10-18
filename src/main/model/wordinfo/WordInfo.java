package main.model.wordinfo;

public class WordInfo {

    private String phonetic;
    private String translation;
    private String[] explanation;
    private String[][] webExpla;

    public WordInfo(String phonetic, String translation, String[] explanation, String[][] webExpla) {
        this.phonetic = phonetic;
        this.translation = translation;
        this.explanation = explanation;
        this.webExpla = webExpla;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public String getTranslation() {
        return this.translation;
    }

    public String[] getExplanation() {
        return this.explanation;
    }

    public String[][] getWebExpla() {
        return this.webExpla;
    }

}