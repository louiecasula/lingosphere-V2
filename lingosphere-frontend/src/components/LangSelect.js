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
        console.log("Fetched user languages:", languagesFetched);
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

    // Check if user has already added the language
    const isNewLanguage = !userLanguages.some(lang => lang.language.languageId === selectedLanguage.languageId);

    // Update local state of userLanguages
    const updatedUserLanguages = isNewLanguage ? 
        [...userLanguages, { language: selectedLanguage, proficiencyLevel: level }] :
        userLanguages.map(lang => 
            lang.language.languageId === selectedLanguage.languageId ? 
            { ...lang, proficiencyLevel: level } : 
            lang
        );
    setLanguages(updatedUserLanguages);

    // Update userLanguage table in database
    const updateData = { userId, languageId: selectedLanguage.languageId, proficiencyLevel: level };
    isNewLanguage ? addUserLanguage(updateData) : updateUserLanguage(updateData);
  };

  const getProficiencyLevel = (languageId) => {
    const language = userLanguages.find(lang => lang.language.languageId === languageId);
    return language ? proficiencyDescriptions[language.proficiencyLevel] : "Unselected";
  };

  const renderLanguageButtons = () => {
    return languageOptions.map((language) => (
      <Grid item key={language.code}>
        <Button className="lang-button" onClick={() => handleLanguageSelect(language)}>
            <img className="flag-icon" src={require(`../images/flags/${language.name.toLowerCase()}.svg`)} alt={language.name} />
            <h3 className='flag-lang-name'>{language.name}</h3>
            <h6 className='prof-level'>({getProficiencyLevel(language.languageId)})</h6>
        </Button>
      </Grid>
    ));
  };

  return (
    <>
      <h2 className='setting-header'>Language Preferences</h2>
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
