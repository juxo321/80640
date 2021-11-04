var btnModificar = document.getElementById("btnModificar");
btnModificar.addEventListener("click", () => {
    axios.post("http://localhost:4567/modificar", {
        antiguoEmail : document.getElementById("antiguoEmail").value,
        nuevoEmail: document.getElementById("nuevoEmail").value,
        nuevoPassword: document.getElementById("nuevoPassword").value
    })
        .then(function (res) {
            alert("Estado :" + res.data.status);
        })
        .catch(function (error) {
            console.log(error)
        })
});