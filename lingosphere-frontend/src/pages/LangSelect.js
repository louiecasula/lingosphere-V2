import React, { useState, useEffect } from 'react';
import { Grid, Button, Modal, Box, Typography } from '@mui/material';
import { getAllLanguages } from '../api/languageApi';
import { getUserLanguages, addUserLanguage, updateUserLanguage } from '../api/userLanguageApi';
import './LangSelect.css';

const proficiencyDescriptions = {
  1: 'Beginner',
  2: 'Intermediate',
  3: 'Advanced',
};

export default function LanguageSelection() {
  const userId = sessionStorage.getItem('userId');
  const [selectedLanguage, setSelectedLanguage] = useState(null);
  const [proficiencyLevel, setProficiencyLevel] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [languageOptions, getLanguages] = useState([]);
  const [userLanguages, setLanguages] = useState([]);

  // Get all language options
  useEffect(() => {
    getAllLanguages().then(languagesFetched => {
        getLanguages(languagesFetched);
    }).catch(error => console.error(error));
  }, []);

  // Get all of current user's chosen languages
  useEffect(() => {
    getUserLanguages({userId}).then(languagesFetched => {
        setLanguages(languagesFetched);
    }).catch(error => console.error(error));
}, []);

  const handleLanguageSelect = (language) => {
    setSelectedLanguage(language);
    setModalOpen(true);
  };

  const handleProficiencySelect = (level) => {
    setProficiencyLevel(level);
    setModalOpen(false);

    // If user has already added the language,
    if (userLanguages.some(language => language.userLanguageId === selectedLanguage.id)) {
      updateUserLanguage({userId, languageId: selectedLanguage.id, proficiencyLevel: level});
    }
    // Else, add it
    else {
      addUserLanguage({userId, languageId: selectedLanguage.id, proficiencyLevel: level});
    }
  };
  const renderLanguageButtons = () => {
    return languageOptions.map((language) => (
      <Grid item key={language.code}>
        <Button className="lang-button" onClick={() => handleLanguageSelect(language)}>
            <img className="flag-icon" src={require(`../images/flags/${language.name.toLowerCase()}.svg`)} alt={language.name} />
            <h3>{language.name}</h3>
        </Button>
      </Grid>
    ));
  };

  return (
    <>
      <h1>Language Settings</h1>
      <Grid container className="lang-container" >
        {renderLanguageButtons()}
      </Grid>

      <Modal className="lang-modal" open={modalOpen} onClose={() => setModalOpen(false)}>
        <Box className="lang-prof-box">
          <Typography>Select your proficiency level for <strong>{selectedLanguage?.name}</strong>:</Typography>
          {Object.entries(proficiencyDescriptions).map(([level, description]) => (
            <Button key={level} onClick={() => handleProficiencySelect(level)}>
              {level} - {description}
            </Button>
          ))}
        </Box>
      </Modal>
    </>
  );
}
