package com.passion.lingosphere.controllers;

import com.passion.lingosphere.services.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/words")
public class UserWordController {

    private final UserWordService userWordService;

    @Autowired
    public UserWordController(UserWordService userWordService) {
        this.userWordService = userWordService;
    }
}
