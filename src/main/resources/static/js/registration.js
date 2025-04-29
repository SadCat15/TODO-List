const form = document.querySelector('.registration-form');

form.addEventListener("submit", function (event) {
    event.preventDefault();
    const name = document.querySelector('#username').value || "";
    const email = document.querySelector('#email').value || "";
    const password = document.querySelector('#password').value || "";
    const repeatedPassword = document.querySelector('#repeat-password').value || "";

    const userDto = {
        id: null,
        name: name,
        email: email,
        password: password,
        repeatedPassword: repeatedPassword,
        role: null
    };
    fetch('/api/register-user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userDto)
    })
        .then(async response => {
            if (response.status === 200)
                // window.location.href = window.location.origin;
                console.log("USER")
            else // error
                response.text().then(err => showErrorMessage(err));
        })
        .catch(err => console.error(err));
});

function showErrorMessage(message) {
    const errorDiv = document.querySelector('.error-message');
    errorDiv.innerText = message;
    errorDiv.style.display = 'block';

    setTimeout(() => {
        errorDiv.style.display = 'none';
        errorDiv.innerText = "";
    }, 2000)
}