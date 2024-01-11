window.onload = function () {
    document.getElementById('login-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const userData = document.getElementById('login-form');
        const username = userData.username.value;
        const password = userData.password.value;
        console.log({username, password});

        const user = {
            username: username,
            password: password
        };

        fetch('http://localhost:8080/api/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                if (data.userId) {
                    sessionStorage.setItem('userId', data.userId);
                    console.log('Stored username')
                    // Redirect to dashboard
                    window.location.href = '/dashboard.html';
                }

            })
            .catch((error) => {
                console.error('Error:', error);
                // TODO: Send an error message to the user
            });
    });
};