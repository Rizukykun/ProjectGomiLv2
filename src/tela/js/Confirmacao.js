var idColeta = (window.location.search.replace('?', ''));

function load() {

    var url = new URL("http://localhost:8080/GomiV2/Solicitacao?idSolicitacao=" + idColeta);

    myInit = {
        method: 'GET',
        headers: new Headers()
    }

    fetch(url, myInit)
        .then(function(item) {
            item = item.text();
            return item;
        })
        .then(item => populaTexto(item));

}

function populaTexto(jayson) {

    var texto = document.getElementById('w3review');
    texto.append(jayson);
    sessionStorage.setItem('idColeta', idColeta);

}

function aceita() {
    var url = new URL("http://localhost:8080/GomiV2/Solicitacao/Motorista?idSolicitacao=" + idColeta);

    myHead = new Headers();
    myHead.append('sessao', sessionStorage.getItem('sessao'));

    myInit = {
        method: 'PUT',
        headers: myHead
    }

    fetch(url, myInit)
        .then(() => mapa());
}

function mapa() {

    window.location.href = 'indexMapa.html';

}

function voltar() {
    window.location.href = 'indexPaginaPrincipalFunc.html';
}