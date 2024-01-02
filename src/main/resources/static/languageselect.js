document.getElementById('language-selection-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // This is how the JSON payload looks
    const languagePreferences = [
        {
            language: document.getElementById('language1').value,
            proficiency: parseInt(document.getElementById('proficiency1').value) // Convert to integer if necessary
        }
    ];

    const userId = sessionStorage.getItem('userId');

    console.log("UserID:", userId);
    fetch(`http://localhost:8080/api/users/${userId}/languages`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(languagePreferences)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save language preferences');
            }
            return response.json();
        })
        .then(data => {
            console.log('Preferences saved:', data);
            window.location.href = '/dashboard.html';
        })
        .catch(error => {
            console.error('Error:', error);
            // TODO: Send an error message to the user
        });
});

