function loadWordsOfTheDay() {
    // Retrieve userId from sessionStorage
    const userId = sessionStorage.getItem('userId');
    console.log(userId);

    fetch(`http://localhost:8080/api/words/${userId}/words-of-the-day`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch words of the day');
            }
            return response.json();
        })
        .then(words => {
            const wordsDiv = document.getElementById('wotd');
            wordsDiv.innerHTML = '';

            // Check if words is an array
            if (Array.isArray(words)) {
                words.forEach(word => {
                    const wordElement = document.createElement('p');
                    wordElement.textContent = `Word: ${word.text}, Language: ${word.language.name}`;
                    wordsDiv.appendChild(wordElement);
                });
            } else {
                console.error('Words data is not an array:', words);
            }
        })
        .catch(error => {
            console.error('Error fetching words of the day:', error);
        });
}

loadWordsOfTheDay();