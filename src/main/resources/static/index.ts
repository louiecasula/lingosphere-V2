// Import stylesheets
// import './dist/style.css';
//
//
// const form: HTMLFormElement = document.querySelector('#defineform')!;
// const outputContainer: HTMLDivElement = document.querySelector('#output-container')!;
//
// outputContainer.innerHTML = `<h2>Enter a word above to learn more about it</h2>`;
//
// form.onsubmit = async (event) => {
//   event.preventDefault(); // prevent reload
//
//   const formData = new FormData(form);
//   const text = formData.get('defineword') as string;
//
//   outputContainer.innerHTML = `<h1>${text}</h1>`;
//
//   try {
//     const response = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${text}`)
//
//     const data: { word: string; origin?: string; phonetic: string; meanings: { partOfSpeech: string; definitions: { definition: string; example?: string; synonyms?: string[]; antonyms?: string[] }[] }[] }[] = await response.json();
//
//     let wordData = data[0];
//
//     let outputHTML = `<h1>${wordData.word}</h1><h5>${wordData.phonetic}</h5><hr>`;
//
//     for (let i = 0; i < data.length; i++){
//       wordData = data[i];
//
//       if (wordData.origin != undefined) {
//         outputHTML += `<>`
//       }
//
//       wordData.meanings.forEach((meaning) => {
//         outputHTML += `<p><strong>${meaning.partOfSpeech}</strong></p><ol>`;
//
//         meaning.definitions.forEach((definition) => {
//           outputHTML += `<li>${definition.definition}</li>`;
//
//           if (definition.example != undefined) {
//             outputHTML += `<ul><li><em>${definition.example}</em></li></ul>`;
//           }
//
//           if (definition.synonyms != undefined && definition.synonyms.length > 0) {
//               outputHTML += `<ul><li><em>Synonyms:</em> ${definition.synonyms.join(', ')}</li></ul>`;
//           }
//
//           if (definition.antonyms != undefined && definition.antonyms.length > 0) {
//             outputHTML += `<ul><li><em>Antonyms:</em> ${definition.antonyms.join(', ')}</li></ul>`;
//           }
//
//         });
//
//         outputHTML += `</ol>`;
//       });
//
//       // Turns out, there are multiple words stored in each word's data
//         // data is stored inconsistently too
//           // Find a way to get things like synonyms & antonyms when they aren't under the definition
//         // Don't forget to add the etymology
//         // See if you can add mp3s for the pronunciation
//         // Also get multiple IPA variations if applicable
//
//       // if (wordData.meanings.synonyms != undefined && wordData.meanings.synonyms.length > 0) {
//       //   outputHTML += `<ul><li><em>Synonyms:</em> ${wordData.meanings.synonyms.join(', ')}</li></ul>`;
//       // }
//
//       // if (wordData.meanings.antonyms != undefined && wordData.meanings.antonyms.length > 0) {
//       //   outputHTML += `<ul><li><em>Antonyms:</em> ${wordData.meanings.antonyms.join(', ')}</li></ul>`;
//       // }
//     }
//       outputContainer.innerHTML = outputHTML;
//
//     } catch(error) {
//       console.error('Error fetching data:', error);
//       outputContainer.innerHTML = `<h1>${text}</h1> <p>Can't find info on ${text}</p>`;
//     }
// };