package com.passion.lingosphere.dtos;

import com.passion.lingosphere.models.Language;

public class UserLanguageDto {

    private Long userId;
    private Long languageId;
    private Integer proficiencyLevel;

    public UserLanguageDto() {
    }

    public UserLanguageDto(Long userId, Long languageId, Integer proficiencyLevel) {
        this.userId = userId;
        this.languageId = languageId;
        this.proficiencyLevel = proficiencyLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Integer getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(Integer proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }
}
