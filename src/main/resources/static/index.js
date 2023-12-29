const API_URL = `http://localhost:8080`

// uses FETCH web api
// https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
//

function fetchPostsData() {
    fetch(`${API_URL}`)
        .then((res) => {
            return res.json();
        })
        .then((data) => {
            showPostList(data)
        })
        .catch((error) => {
            console.log(`Error Fetching data : ${error}`)
            document.getElementById('words').innerHTML = 'Error Loading Words Data'
        })
}

// takes a UNIX integer date, and produces a prettier human string
function dateOf(date) {
    const milliseconds = date * 1000 // 1575909015000
    const dateObject = new Date(milliseconds)
    const humanDateFormat = dateObject.toLocaleString() //2019-12-9 10:30:15
    return humanDateFormat
}

function showPostList(data) {
    // the data parameter will be a JS array of JS objects
    // this uses a combination of "HTML building" DOM methods (the document createElements) and
    // simple string interpolation (see the 'a' tag on title)
    // both are valid ways of building the html.
    const posts = document.getElementById('words');
    const list = document.createDocumentFragment();

    data.map(function(word) {
        let div = document.createElement('div');
        let title = document.createElement('h3');
        if (word.blog != null) { // in case post is not assigned to a blog
            title.innerHTML = `<a href="/index.html?postid=${word.id}">${word.title}</a> <font size="-1">${word.blog.name}</font>`;
        } else {
            title.innerHTML = `<a href="/index.html?postid=${word.id}">${word.title}</a>`;
        }
        div.appendChild(title);
        list.appendChild(div);
    });

    posts.appendChild(list);
}


function handlePage() {
    console.log("load all posts")
    fetchPostsData()
}

handlePage()