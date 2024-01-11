document.getElementById('signup-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const userData = new FormData(event.currentTarget);
    const username = userData.get('username');
    const email = userData.get('email');
    const password = userData.get('password');
    const confirmpassword = userData.get('confirm-password');
    console.log({username, email, password, confirmpassword});

    if (password !== confirmpassword) {
        console.log("Passwords don't match");
        return;
    }

    const user = {
        username: username,
        email: email,
        password: password
    };

    fetch('http://localhost:8080/api/users/register', {
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
                // Redirect to dashboard
                window.location.href = '/';
            }

        })
        .catch((error) => {
            console.error('Error:', error);
            // TODO: Send an error message to the user
        })
})