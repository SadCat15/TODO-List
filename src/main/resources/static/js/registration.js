const form = document.querySelector('.registration-form');

form.addEventListener("submit", function (event) {
    event.preventDefault();
    const name = document.querySelector('#username').value;
    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;
    const repeatedPassword = document.querySelector('#repeat-password').value;

    const params = new URLSearchParams()
    params.append('name', name);
    params.append('email', email);
    params.append('password', password);
    params.append('repeatedPassword', repeatedPassword);

    fetch('/api/register-user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: params.toString()
    })
        .then(async response => {
            if (response.status === 200)
                window.location.href = window.location.origin;
            else // error
                throw new Error(await response.text())
        })
        .catch(err => console.error(err));
});

console.log("hello")
