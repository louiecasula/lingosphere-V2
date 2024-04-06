import React, { useState, useEffect } from 'react';
import { getWordsOfTheDay } from '../api/wordApi';

export default function Dashboard() {
    const [words, setWords] = useState([]);

    //// Word-of-the-day function ////
    // *Might have to double check that the service logic makes sense...
    // Check if words have already been assigned for the day in each language
    // Display the words

    /* useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        getWordsOfTheDay({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));    
    }, []); */

    return (
        <>
            <h1>Your Words of The Day</h1>
            <div>
            {words.map((word, index) => (
                <div key={index}>{word}</div>
            ))}
            </div>
        </>
    ); 
}; 