document.getElementById('language-selection-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var languagePreferences = {
        language: document.getElementById('language1').value,
        proficiency: document.getElementById('proficiency1').value,
        // Capture other language selections if applicable
    };

    // Retrieve userId from sessionStorage or other storage
    var userId = sessionStorage.getItem('userId');

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
