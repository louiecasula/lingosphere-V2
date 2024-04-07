import React, { useState, useEffect } from 'react';
import './Dashboard.css';
import { generateWordsOfTheDay } from '../api/wordApi';

export default function Dashboard() {
    const [words, setWords] = useState([]);
    const username = sessionStorage.getItem('username');

    useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        generateWordsOfTheDay({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));    
    }, {});

    // TODO: Make a component that greets the user in one of the languages they've signed up for
    const getOrdinalSuffix = (n) => {
        return n + (n > 0 ? ['th', 'st', 'nd', 'rd'][(n > 3 && n < 21) || n % 10 > 3 ? 0 : n % 10] : '');
    };

    const todaysDate = new Date();
    const dayWithSuffix = getOrdinalSuffix(todaysDate.getDate());
    const month = todaysDate.toLocaleString('default', { month: 'long' });
    const year = todaysDate.getFullYear();

    const headerTitle = `${username}'s words for ${month} ${dayWithSuffix}, ${year}:`;

    return (
        <>
            <h1>{headerTitle}</h1>
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