const form = document.querySelector('.add-task-from');

form.addEventListener('submit', function (event) {
    event.preventDefault();
    const topic = document.querySelector('.nameInput').value;
    const description = document.querySelector('.descriptionInput').value;

    const params = new URLSearchParams();
    params.append('name', topic);
    params.append('description', description);
    fetch('/api/add-task', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: params.toString()
    })
        .then(response => {
            if (response.status === 201)
                window.location.href = window.location.origin;
            else // error
                response.text().then(err => showErrorMessage(err));

        })
        .catch(err => console.error(err));
});

function showErrorMessage(message){
    const errorDiv = document.querySelector('.error-message');
    errorDiv.innerText = message;
    errorDiv.style.display = 'block';

    setTimeout(() => {
        errorDiv.style.display = 'none';
        errorDiv.innerText = "";
    }, 2000)
}