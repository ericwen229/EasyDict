package main.model.dict;

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

    String getPhonetic() {
        return this.phonetic;
    }

    String getTranslation() {
        return this.translation;
    }

    String[] getExplains() {
        return this.explains;
    }

    String[][] getWebExplains() {
        return this.webExplains;
    }

}