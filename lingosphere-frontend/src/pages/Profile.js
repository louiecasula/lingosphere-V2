import React, { useState, useEffect } from 'react';
import { getUserWords } from '../api/userWordApi';

export default function Profile() {
    const [words, setWords] = useState([]);

    useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        getUserWords({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));
    }, []);

    return (
        <div>
            {words.map((word, index) => (
                <div key={index}>{word}</div>
            ))}
        </div>
    );
};