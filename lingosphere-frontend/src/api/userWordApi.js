const API_BASE_URL = 'http://localhost:8080/api/users';

export function getUserWords(userData) {
    const { userId } = userData;

    return fetch(`${API_BASE_URL}/${userId}/words`, {
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
            console.error('Error fetching words for user:', error);
            throw error;
        });
}