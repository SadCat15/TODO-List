function addTask() {
    window.location.href = "/add-task";
}

function back() {
    window.location.href = "/";
}

function deleteTask(element) {
    const id = element.getAttribute('data-argument');
    let setElement = document.querySelector(`.todo[data-set="${id}"]`);
    let nameElement = setElement.querySelector('.taskName');
    let checkboxElement = setElement.querySelector('.checkbox');
    let descriptionElement = setElement.querySelector('.descprition');

    nameElement.classList.toggle('strikethrough');
    checkboxElement.classList.toggle('strikethrough');
    descriptionElement.classList.toggle('strikethrough');

    setTimeout(function () {
        nameElement.style.display = 'none';
        checkboxElement.style.display = 'none';
        descriptionElement.style.display = 'none';
        deleteTaskFromDB(id)
    }, 2000);
}

function deleteTaskFromDB(id) {
    fetch(`/api/delete${id}`, {
        method: 'DELETE'
    });
}
