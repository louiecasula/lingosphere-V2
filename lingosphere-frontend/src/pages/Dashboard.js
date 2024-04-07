import React, { useState, useEffect } from 'react';
import './Dashboard.css';
import { generateWordsOfTheDay } from '../api/wordApi';

export default function Dashboard() {
    const [words, setWords] = useState([]);

    // TODO: Make a component that greets the user in one of the languages they've signed up for

    //// Word-of-the-day function ////
    // Iterate user's languages,
        // If language doesn't have a word assigned for the day, assign one
        // Display the language's word for the day

    useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        generateWordsOfTheDay({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));    
    }, {});

    return (
        <>
            <h1>Your Words of The Day</h1>
            <div>
                {Object.entries(words).map(([lang, word]) => (
                    <div key={lang}>
                        <strong>{lang}:</strong> {word.text}
                    </div>
                ))}
            </div>
        </>
    ); 
}; 