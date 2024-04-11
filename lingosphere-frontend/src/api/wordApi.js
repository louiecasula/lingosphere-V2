const API_BASE_URL = 'http://localhost:8080/api/words';

export function generateWordsOfTheDay(userData) {
    const { userId } = userData;

    return fetch(`${API_BASE_URL}/${userId}/words-of-the-day`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(`Server error: ${data.message || response.status}`);
                });
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Error fetching word(s)-of-the-day for user:', error);
            throw error;
        });
}

export function getWordById(wordId) {

    return fetch(`${API_BASE_URL}/${wordId}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(`Server error: ${data.message || response.status}`);
                });
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Error fetching word details:', error);
            throw error;
        });
}