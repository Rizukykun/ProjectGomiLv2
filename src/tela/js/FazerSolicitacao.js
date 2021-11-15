function load() {
    if (sessionStorage.getItem('erro') != null)
        errou();
    else {
        var errorArea = document.getElementById('erro');
        errorArea.textContent = '';
    }
}

function errou() {
    var errorArea = document.getElementById('erro');
    errorArea.textContent = '';
    let errorMessage = document.createElement('h1')

    errorMessage = sessionStorage.getItem('erro');
    errorArea.append(errorMessage);

}

function solicita() {
    sessionStorage.removeItem('erro');
    var boxes = document.getElementsByClassName('cbx');
    var checked = [];
    var descricao = '';

    for (var i = 0; i < boxes.length; i++) {

        if (boxes[i].checked)
            checked.push(boxes[i].name);

    }

    if (checked.length === 0) {
        sessionStorage.setItem('erro', 'Nenhuma categoria selecionada');
        load();
        return;
    }

    for (var i = 0; i < checked.length; i++) {
        var texto = document.getElementById(checked[i]).value;

        if (texto === '') {

            sessionStorage.setItem('erro', checked[i] + ' não pode estar vazio');
            load();
            return;

        }

        descricao += (checked[i] + ': ' + texto + '\n');
    }

    load();

    var url = new URL("http://localhost:8080/GomiV2/Solicitacao")

    formData = 'descricao=' + descricao;

    myHead = new Headers({ 'content-type': 'application/x-www-form-urlencoded' });
    myHead.append("sessao", sessionStorage.getItem('sessao'))

    var myInit = {
        method: 'POST',
        headers: myHead,
        body: formData
    };

    fetch(url, myInit)
        .then(item => item.json())
        .then(item => trolha(item));

    function trolha(jayson) {
        sessionStorage.setItem('idColeta', jayson.id);
        //mandar para a próxima tela
    }


}