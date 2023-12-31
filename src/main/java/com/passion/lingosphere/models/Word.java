package com.passion.lingosphere.models;

import jakarta.persistence.*;

@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "part_of_speech")
    private String partOfSpeech;

    @Column(name = "definition", length = 1000)
    private String definition;

    @Column(name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    public Word() {
    }

    public Word(String text, String partOfSpeech, String definition, Integer level, Language language) {
        this.text = text;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.level = level;
        this.language = language;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
