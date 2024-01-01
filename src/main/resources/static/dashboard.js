function loadWordsOfTheDay() {
    fetch('http://localhost:8080/api/users/{userId}/words-of-the-day')
        .then(response => response.json())
        .then(words => {
            const wordsDiv = document.getElementById('wotd');
            wordsDiv.innerHTML = '';

            // Iterate through each word and add it to the div
            words.forEach(word => {
                const wordElement = document.createElement('p');
                wordElement.textContent = `Word: ${word.text}, Language: ${word.language.name}`;
                wordsDiv.appendChild(wordElement);
            });
        })
        .catch(error => {
            console.error('Error fetching words of the day:', error);
        });
}

loadWordsOfTheDay();
