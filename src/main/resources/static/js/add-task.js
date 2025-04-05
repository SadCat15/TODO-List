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
            else
                throw new Error(`Error: Status code ${response.status}`);
        })
        .catch(error => {
            console.error(error);
        });
});