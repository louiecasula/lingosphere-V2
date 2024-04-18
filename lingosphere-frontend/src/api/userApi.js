const API_BASE_URL = 'http://localhost:8080/api';

export function registerUser(userData) {
    const { username, email, password } = userData;

    return fetch(`${API_BASE_URL}/users/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, email, password })
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
            console.error('Error registering user:', error);
            throw error;
        });
}

export function loginUser(email, password) {
    return fetch(`${API_BASE_URL}/users/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    })
        .then(response => {
            if (!response.ok) {
                // Convert non-2xx HTTP responses into errors
                return response.json().then(data => {
                    throw new Error(`Server error: ${data.message || response.status}`);
                });
            }
            return response.json();
        })
        .catch((error) => {
            console.error('Error logging in user:', error);
            throw error; // Re-throw to allow the caller to handle it
        });
}

export function checkUsernameExists(username) {
    return fetch(`http://localhost:8080/api/users/check-username?username=${encodeURIComponent(username)}`)
    .then(response => response.json())
    .catch(error => console.error('Error checking username:', error));
}

export function checkEmailExists(email) {
    return fetch(`http://localhost:8080/api/users/check-email?email=${encodeURIComponent(email)}`)
    .then(response => response.json())
    .catch(error => console.error('Error checking email:', error));
}