var myVar;

function loadFunction() {
    var url = new URL("http://localhost:8080/GomiV2/Solicitacao/Motorista?idSolicitacao=" + sessionStorage.getItem("idColeta"));

    var myInit = {
        method: 'GET',
        headers: new Headers()
    }

    fetch(url, myInit)
        .then(item => item.json())
        .then(item => verificaMotorista(item));

}

function showPage() {
    document.getElementById("loader").style.display = "none";
    document.getElementById("myDiv").style.display = "block";
}

function verificaMotorista(jayson) {

    console.log(jayson)
    if (jayson === null) {
        myVar = setTimeout(loadFunction, 2000);
    } else {
        var pegaMoto = document.getElementById('motorista');
        let infoMoto = document.createElement('p');
        infoMoto.innerText = 'Nome: ' + jayson.nome +
            '\n Veículo: ' + jayson.tipoVeiculo +
            '\n Telefone: (' + jayson.telefoneddd + ')' + jayson.telefone;
        pegaMoto.append(infoMoto);
        showPage();
    }

}

function confirma() {

    document.getElementById("loader").style.display = "block";
    document.getElementById("myDiv").style.display = "none";

    var url2 = new URL("http://localhost:8080/GomiV2/Solicitacao/Confirmar?idSolicitacao=" + sessionStorage.getItem("idColeta"));

    var myHead = new Headers();
    myHead.append('sessao', sessionStorage.getItem('sessao'));

    var myInit = {
        method: 'PUT',
        headers: myHead
    }

    fetch(url2, myInit).then(() => aguardaConfirmacao())

}

function aguardaConfirmacao() {

    var url3 = new URL('http://localhost:8080/GomiV2/Solicitacao/Confirmar?idSolicitacao=' + sessionStorage.getItem("idColeta"));

    var myInit = {
        method: 'GET',
        headers: new Headers()
    }

    fetch(url3, myInit)
        .then(item => item.json())
        .then(item => verificaConfirmacao(item))

}

function verificaConfirmacao(jayson) {

    if (jayson.status === 'aberto')
        myVar = setTimeout(aguardaConfirmacao, 2000);
    else {
        alert('Coleta Confirmada com sucesso')
        window.location.href = 'indexPaginaPrincipal.html';
    }

}