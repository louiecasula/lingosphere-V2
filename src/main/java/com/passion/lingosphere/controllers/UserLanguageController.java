package com.passion.lingosphere.controllers;

import com.passion.lingosphere.services.UserLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/language_preferences")
public class UserLanguageController {

    private final UserLanguageService userLanguageService;

    @Autowired
    public UserLanguageController(UserLanguageService userLanguageService) {
        this.userLanguageService = userLanguageService;
    }
}
