async function get_current_user_id() {
    const response = await fetch('/api/get-current-user-id', {
        method: 'GET'
    });
    if ((await response).status === 200)
        return response.text();
    else {
        console.log(response.text());
        return null;
    }
}

const form = document.querySelector('.add-task-from');

form.addEventListener('submit', async function (event) {
    event.preventDefault();
    const topic = document.querySelector('.nameInput').value || "";
    const description = document.querySelector('.descriptionInput').value || "";
    const user_id = Number(await get_current_user_id())

    const taskDto = {
        id: null,
        title: topic,
        description: description,
        userId: user_id
    }
    fetch('/api/add-task', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(taskDto)
    })
        .then(response => {
            if (response.status === 201)
                window.location.href = window.location.origin + "/tasks";
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