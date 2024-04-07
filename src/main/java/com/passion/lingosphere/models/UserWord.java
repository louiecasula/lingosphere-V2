package com.passion.lingosphere.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_words")
public class UserWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWordId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @Column(name = "date_sent")
    @Temporal(TemporalType.DATE)
    private LocalDate dateSent;

    public UserWord() {
    }

    public UserWord(User user, Word word, LocalDate dateSent) {
        this.user = user;
        this.word = word;
        this.dateSent = dateSent;
    }

    public Long getUserWordId() {
        return userWordId;
    }

    public void setUserWordId(Long userWordId) {
        this.userWordId = userWordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public LocalDate getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDate dateSent) {
        this.dateSent = dateSent;
    }
}
