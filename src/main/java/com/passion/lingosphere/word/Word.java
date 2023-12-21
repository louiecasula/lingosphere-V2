package com.passion.lingosphere.word;

public class Word {
    private Long id;
    private String language;
    private String wordText;
    private String partOfSpeech;
    private String pronunciation;
    private String audio;
    private String definition;
    private String exampleSentence;
    private String etymology;

    public Word() {
    }

    public Word(Long id, String language, String wordText, String partOfSpeech, String pronunciation, String audio, String definition, String exampleSentence, String etymology) {
        this.id = id;
        this.language = language;
        this.wordText = wordText;
        this.partOfSpeech = partOfSpeech;
        this.pronunciation = pronunciation;
        this.audio = audio;
        this.definition = definition;
        this.exampleSentence = exampleSentence;
        this.etymology = etymology;
    }

    public Word(String language, String wordText, String partOfSpeech, String pronunciation, String audio, String definition, String exampleSentence, String etymology) {
        this.language = language;
        this.wordText = wordText;
        this.partOfSpeech = partOfSpeech;
        this.pronunciation = pronunciation;
        this.audio = audio;
        this.definition = definition;
        this.exampleSentence = exampleSentence;
        this.etymology = etymology;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public String getEtymology() {
        return etymology;
    }

    public void setEtymology(String etymology) {
        this.etymology = etymology;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", wordText='" + wordText + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", audio='" + audio + '\'' +
                ", definition='" + definition + '\'' +
                ", exampleSentence='" + exampleSentence + '\'' +
                ", etymology='" + etymology + '\'' +
                '}';
    }
}
