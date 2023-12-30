package com.passion.lingosphere.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_languages")
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLanguageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "proficiency_level", nullable = false)
    private int proficiencyLevel;

    public UserLanguage() {
    }

    public UserLanguage(User user, Language language, int proficiencyLevel) {
        this.user = user;
        this.language = language;
        this.proficiencyLevel = proficiencyLevel;
    }

    public Long getUserLanguageId() {
        return userLanguageId;
    }

    public void setUserLanguageId(Long userLanguageId) {
        this.userLanguageId = userLanguageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }
}
