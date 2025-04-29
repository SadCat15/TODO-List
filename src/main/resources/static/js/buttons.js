const buttons = document.querySelectorAll('button')
buttons.forEach(button => {
    button.addEventListener("click", function () {
        const button_id = button.id;
        window.location.href = window.location.origin + button_id;
    })
})