function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function createStar() {
    const star = document.createElement('span');
    star.classList.add('star');
    star.style.left = getRandomInt(20, window.innerWidth - 20) + 'px';
    star.style.top = getRandomInt(20, window.innerHeight - 20) + 'px';
    document.body.appendChild(star);
}

for (let i = 0; i < 50; i++)
    createStar()
