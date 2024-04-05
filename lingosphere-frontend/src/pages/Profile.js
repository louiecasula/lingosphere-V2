import React, { useState, useEffect } from 'react';
import { getUserWords } from '../api/userWordApi';
import './Profile.css';

export default function Profile() {
    const [words, setWords] = useState([]);

    useEffect(() => {
        const userId = sessionStorage.getItem('userId');
        getUserWords({userId}).then(wordsFetched => {
            setWords(wordsFetched);
        }).catch(error => console.error(error));
    }, []);

    return (
        <table>
            <thead>
                <tr>
                    <th>Word</th>
                    <th>Date Received</th>
                </tr>
            </thead>
            <tbody>
                {words.map((word, index) => (
                    <tr key={index}>
                        <td>{word.userWordId}</td>
                        <td>{word.dateSent}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};