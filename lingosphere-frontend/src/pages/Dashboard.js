import React, { useState, useEffect } from 'react';
import './Dashboard.css';
import { generateWordsOfTheDay } from '../api/wordApi';

export default function Dashboard() {
    const [words, setWords] = useState([]);

    useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        generateWordsOfTheDay({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));    
    }, {});

    // TODO: Make a component that greets the user in one of the languages they've signed up for

    return (
        <>
            <h1>Your Words of The Day</h1>
            <div className="wotd-container">
                {Object.entries(words).map(([lang, word]) => (
                    <div key={lang}>
                        <strong>{lang}:</strong> {word.text}
                    </div>
                ))}
            </div>
        </>
    ); 
}; 