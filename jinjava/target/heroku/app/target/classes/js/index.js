var modelo;
var dueno;

var btnregistrar = document.getElementById("btnregistrar");
btnregistrar.addEventListener("click", () => {
    axios.post("https://jinjavaapp.herokuapp.com//registrar", {
        modelo: document.getElementById("modelo").value,
        dueno: document.getElementById("dueno").value
    }).then(function (res) {
            alert("Automovil:" + res.data.status + " id:" + res.data.id);
            modelo = document.getElementById("modelo").value;
            dueno = res.data.id;
        })
        .catch(function (error) {
            console.log(error)
        })
});

var btnLista = document.getElementById("btnLista");
btnLista.addEventListener("click", () => {
    window.location.replace("https://jinjavaapp.herokuapp.com//jinjava");
});
