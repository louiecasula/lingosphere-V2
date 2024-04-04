import React, { useState } from 'react';
import { Grid, Button, Modal, Box, Typography } from '@mui/material';

const languageOptions = [
  { code: 'en', name: 'English' },
  { code: 'es', name: 'Spanish' },
  // ...other languages
];

const proficiencyDescriptions = {
  1: 'Beginner',
  2: 'Intermediate',
  3: 'Advanced',
};

export default function LanguageSelection() {
  const [selectedLanguage, setSelectedLanguage] = useState(null);
  const [proficiencyLevel, setProficiencyLevel] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);

  const handleLanguageSelect = (language) => {
    setSelectedLanguage(language);
    setModalOpen(true);
  };

  const handleProficiencySelect = (level) => {
    setProficiencyLevel(level);
    setModalOpen(false);
    // Here you would also save the selected language and level to your state
  };

  const renderLanguageButtons = () => {
    return languageOptions.map((language) => (
      <Grid item key={language.code}>
        <Button onClick={() => handleLanguageSelect(language)}>
          {language.name}
        </Button>
      </Grid>
    ));
  };

  return (
    <>
      <Grid container spacing={2}>
        {renderLanguageButtons()}
      </Grid>

      <Modal open={modalOpen} onClose={() => setModalOpen(false)}>
        <Box>
          <Typography>Select your proficiency level for {selectedLanguage?.name}:</Typography>
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
