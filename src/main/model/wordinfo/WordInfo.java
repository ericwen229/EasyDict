package main.model.wordinfo;

public class WordInfo {

    private String phonetic;
    private String translation;
    private String[] explains;
    private String[][] webExplains;

    public WordInfo(String phonetic, String translation, String[] explains, String[][] webExplains) {
        this.phonetic = phonetic;
        this.translation = translation;
        this.explains = explains;
        this.webExplains = webExplains;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public String getTranslation() {
        return this.translation;
    }

    public String[] getExplains() {
        return this.explains;
    }

    public String[][] getWebExplains() {
        return this.webExplains;
    }

}