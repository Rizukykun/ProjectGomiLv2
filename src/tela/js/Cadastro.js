function ligaCliente() {

    document.getElementById("cadastroCliente").style.display = "block";
    document.getElementById("cadastroMotorista").style.display = "none";
    document.getElementById("registerbtn").style.display = "block";

}

function ligaMotorista() {

    document.getElementById("cadastroCliente").style.display = "none";
    document.getElementById("cadastroMotorista").style.display = "block";
    document.getElementById("registerbtn").style.display = "block";

}

function enviaDados() {

    if (document.getElementById("rbCliente").checked && validaInfo('c')) {

        var url = new URL("http://localhost:8080/GomiV2/Cliente");

        formData = 'email=' + document.getElementById('cemail').value +
            '&nome=' + document.getElementById('cname_com').value +
            '&telefone=' + document.getElementById('cphone').value +
            '&cpf=' + document.getElementById('cCPF').value +
            '&dataNascimento=' + document.getElementById('cbirth').value +
            '&senha=' + document.getElementById('cpsw').value +
            '&confirmacaoSenha=' + document.getElementById('cpsw-repeat').value +
            '&cep=' + document.getElementById('ccep').value +
            '&numero=' + document.getElementById('cnumero').value +
            '&rua=' + document.getElementById('crua').value +
            '&complemento=' + document.getElementById('ccomplement').value +
            '&bairro=' + document.getElementById('cbairro').value +
            '&cidade=' + document.getElementById('ccidade').value;

        myInit = {
            method: 'POST',
            headers: new Headers({ 'content-type': 'application/x-www-form-urlencoded' }),
            body: formData
        }

        fetch(url, myInit).then(() => retornaLogin());



    } else if (document.getElementById("rbMotorista").checked && validaInfo('m')) {
        var url = new URL("http://localhost:8080/GomiV2/Motorista");

        formData = 'email=' + document.getElementById('memail').value +
            '&nome=' + document.getElementById('mname_com').value +
            '&telefone=' + document.getElementById('mphone').value +
            '&cpf=' + document.getElementById('mCPF').value +
            '&dataNascimento=' + document.getElementById('mbirth').value +
            '&senha=' + document.getElementById('mpsw').value +
            '&confirmacaoSenha=' + document.getElementById('mpsw-repeat').value +
            '&tipoVeiculo=' + document.getElementById('mveiculo').value +
            '&cnh=' + document.getElementById('mnumeroCNH').value +
            '&dataExpiracao=' + document.getElementById('mexpire').value +
            '&cnhCategoria=' + document.getElementById('mcategory').value +
            '&cargaSuportada=' + document.getElementById('mcarga').value;

        myInit = {
            method: 'POST',
            headers: new Headers({ 'content-type': 'application/x-www-form-urlencoded' }),
            body: formData
        }

        fetch(url, myInit).then(() => retornaLogin());

    }

}

function validaInfo(tipo) {

    if (tipo === 'c') {
        if (document.getElementById('cemail').value === '') {
            alert('Preencha o campo E-mail!');
        } else if (document.getElementById('cname_com').value === '')
            alert('Preencha o campo Nome Completo!');
        else if (document.getElementById('cCPF').value === '')
            alert('Preencha o campo CPF!');
        else if (document.getElementById('cbirth').value === '')
            alert('Preencha o campo Data de Nascimento!');
        else if (document.getElementById('cphone').value === '')
            alert('Preencha o campo Telefone!');
        else if (document.getElementById('crua').value === '')
            alert('Preencha o campo Rua!');
        else if (document.getElementById('cnumero').value === '')
            alert('Preencha o campo Número');
        else if (document.getElementById('cbairro').value === '')
            alert('Preencha o campo Bairro!');
        else if (document.getElementById('ccidade').value === '')
            alert('Preencha o campo Cidade!');
        else if (document.getElementById('ccep').value === '')
            alert('Preencha o campo Cep!');
        else if (document.getElementById('cpsw').value === '')
            alert('Preencha o campo Senha!');
        else if (document.getElementById('cpsw-repeat').value === '')
            alert('Preencha o campo Confirma Senha!');
        else if (document.getElementById('cpsw').value !== document.getElementById('cpsw-repeat').value)
            alert('Senha e Confirmação não coincidem!')
        else
            return true;
        return false;

    } else {
        if (document.getElementById('memail').value === '')
            alert('Preencha o campo Email!');
        else if (document.getElementById('mname_com').value === '')
            alert('Preencha o campo Nome Completo!');
        else if (document.getElementById('mCPF').value === '')
            alert('Preencha o campo CPF!');
        else if (document.getElementById('mbirth').value === '')
            alert('Preencha o campo Data de Nascimento!');
        else if (document.getElementById('mphone').value === '')
            alert('Preencha o campo Telefone!');
        else if (document.getElementById('mveiculo').value === '')
            alert('Preencha o campo Veículo!');
        else if (document.getElementById('mnumeroCNH').value === '')
            alert('Preencha o campo CNH!');
        else if (document.getElementById('mexpire').value === '')
            alert('Preencha o campo Data de Expiração!');
        else if (document.getElementById('mcategory').value === '')
            alert('Preencha o campo Categoria CNH!');
        else if (document.getElementById('mcarga').value === '')
            alert('Preencha o campo Carga Suportada!');
        else if (document.getElementById('mpsw').value === '')
            alert('Preencha o campo Senha!');
        else if (document.getElementById('mpsw-repeat').value === '')
            alert('Preencha o campo Confirma Senha!');
        else if (document.getElementById('mpsw').value !== document.getElementById('mpsw-repeat').value)
            alert('Senha e Confirmação não coincidem!')
        else
            return true;
        return false;

    }

}

function retornaLogin() {
    alert('Usuário cadastrado!\nRetornando a tela de login');
    window.location.href = 'indexLogin.html';
}