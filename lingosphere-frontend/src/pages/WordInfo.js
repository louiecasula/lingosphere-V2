import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getWordById } from '../api/wordApi';

export default function WordInfo() {
  const { wordId } = useParams();
  const [wordDetails, setWordDetails] = useState(null);

  useEffect(() => {
    getWordById(wordId).then(data => {
      setWordDetails(data);
    }).catch(error => {
      console.error('Error fetching word details:', error);
    });
  }, [wordId]);

  if (!wordDetails) {
    return <div className='load-status'>Loading...</div>;
  }

  return (
    <div className="wotd-container">
        <div className="wotd">
            <div className="lang">
                <strong>{wordDetails.language.name}:</strong>
            </div>
            <div className="word">
                <h1>{wordDetails.text}</h1>
                <hr/>
                <i>{wordDetails.partOfSpeech}pos</i><br/>
                {wordDetails.definition}this is the definition of the word<br/><br/>
                this is an example sentence<br/><br/>
                this is the etymology
            </div>
        </div>
    </div>
  );
}
