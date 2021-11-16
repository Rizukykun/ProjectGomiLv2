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
    verificaSolicitacoes();

}

function verificaSolicitacoes() {

    var listaColetas = document.getElementById('coletas');
    listaColetas.innerHTML = '';

    var url = new URL("http://localhost:8080/GomiV2/Solicitacao?idSolicitacao=all");

    myInit = {
        method: 'GET',
        headers: new Headers()
    }

    fetch(url, myInit)
        .then(item => item.json())
        .then(item => populaColetas(item))

}

function populaColetas(jayson) {

    var listaColetas = document.getElementById('coletas');

    for (var element in jayson) {
        let itemColeta = document.createElement('a');
        itemColeta.classList.add('block');
        itemColeta.innerText = jayson[element].descricao;
        itemColeta.id = jayson[element].id;
        itemColeta.href = "indexConfirmacao.html?" + jayson[element].id;
        listaColetas.append(itemColeta);
    }

    var myVar = setTimeout(verificaSolicitacoes, 7000);

}