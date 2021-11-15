function load() {

    var url = new URL("http://localhost:8080/GomiV2/Cliente")

    var myInit = {
        method: 'POST',
        headers: new Headers({ 'content-type': 'application/x-www-form-urlencoded' })
    }

    fetch(url, myInit)
        .then(item => item.text())
        .then(item => console.log(item));

}