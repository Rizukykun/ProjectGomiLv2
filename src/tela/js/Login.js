function logar() {

    var url = new URL("http://localhost:8080/GomiV2/Login")

    login = document.getElementById('nome_login').value;
    senha = document.getElementById('senha_login').value;

    formData = 'login=' + login + '&senha=' + senha;

    var myInit = {
        method: 'POST',
        headers: new Headers({ 'content-type': 'application/x-www-form-urlencoded' }),
        body: formData
    };

    fetch(url, myInit)
        .then(item => item.json())
        .then(item => direcionaUsuario(item))
        //.then(item => sessionStorage.setItem('sessao', item.sessao))
        //.then(() => window.location.href = 'indexPaginaPrincipal.html');

}

function direcionaUsuario(jayson) {
    console.log(jayson)
    sessionStorage.setItem('sessao', jayson.sessao)
    if (jayson.tipo === 'C') {
        window.location.href = 'indexPaginaPrincipal.html';
    } else {
        window.location.href = 'indexPaginaPrincipalFunc.html';
    }
}