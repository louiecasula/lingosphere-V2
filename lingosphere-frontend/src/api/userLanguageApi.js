const API_BASE_URL = 'http://localhost:8080/api/users';

export function addUserLanguage(userData) {
    const { userId, languageId, proficiencyLevel } = userData;

    return fetch(`${API_BASE_URL}/${userId}/languages`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId, languageId, proficiencyLevel })
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
            console.error('Error adding language for user:', error);
            throw error;
        });
}