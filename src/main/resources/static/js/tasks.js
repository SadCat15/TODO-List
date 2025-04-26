async function get_current_user_id() {
    const response = await fetch('/api/get-current-user-id', {
        method: 'GET'
    });
    if (response.status === 200)
        return response.text();
    else {
        console.log(response.text());
        return null;
    }
}


async function main() {
    const user_id = await get_current_user_id();

    console.log(user_id)

    fetch(`/api/tasks?user_id=${user_id}`, {
        method: 'GET'
    })
        .then(resposne => {
           return resposne.json()
        })
        .then(tasks => {
            console.log(tasks)
            const contentDiv = document.querySelector('#content');
            contentDiv.innerHTML = "";
            tasks.forEach(task => {
                console.log(task)
                contentDiv.innerHTML += `<div class="todo" data-set="${task.id}">
                <div class="checkbox" data-argument="${task.id}" onClick="deleteTask(this)"></div>
                <div class="tmp">
                    <div class="taskName">${task.title}</div>
                </div>
                <div class="descprition">${task.description}</div>
            </div>`;
            })
        })
        .catch(err => console.error(err));
}

main()