function myMap() {
    var directionsService = new google.maps.DirectionsService();
    var directionsRenderer = new google.maps.DirectionsRenderer();


    var start = 'Rua General Lecor';
    var end = 'Rua Mario Pasin';
    var request = {
        origin: start,
        destination: end,
        travelMode: 'DRIVING'
    };
    directionsService.route(request, function(result, status) {
        if (status == 'OK') {
            directionsRenderer.setDirections(result);
        }
    });



    var mapProp = {
        center: new google.maps.LatLng(-23.7306651, -46.5837065),
        zoom: 15,
    };
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
    directionsRenderer.setMap(map);
}


function calcRoute() {
    var start = 'Rua General Lecor';
    var end = 'Rua Mario Pasin';
    var request = {
        origin: start,
        destination: end,
        travelMode: 'DRIVING'
    };
    directionsService.route(request, function(result, status) {
        if (status == 'OK') {
            directionsRenderer.setDirections(result);
        }
    });
}


function load() {

    document.getElementById("loader").style.display = "none";
    document.getElementById("myDiv").style.display = "block";

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
        window.location.href = 'indexPaginaPrincipalFunc.html';
    }

}