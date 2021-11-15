function load() {

    var url = new URL("http://localhost:8080/GomiV2/Usuario")

    var myHeaders = new Headers();
    myHeaders.append("sessao", sessionStorage.getItem('sessao'));

    var myInit = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch(url, myInit)
        .then(item => item.json())
        .then(item => popula(item))

}

function popula(jayson) {

    var existente = document.getElementById('nomeMotorista');
    existente.append(jayson.nome);

}