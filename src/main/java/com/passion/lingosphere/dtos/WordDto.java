package com.passion.lingosphere.dtos;

import com.passion.lingosphere.models.Language;

public class WordDto {

    private String text;
    private String partOfSpeech;
    private String definition;
    private Integer level;
    private Language language;

    public WordDto() {
    }

    public WordDto(String text, String partOfSpeech, String definition, Integer level, Language language) {
        this.text = text;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.level = level;
        this.language = language;
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
