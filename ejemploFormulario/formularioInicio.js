const nombre = document.getElementById("nombre");
const contrasena = document.getElementById("contrasena");


var btnIniciar = document.getElementById("btnIniciar");
btnIniciar.addEventListener("click", function () {
    var params = new URLSearchParams();
    var nombre = document.getElementById("nombre").value;
    var contrasena = document.getElementById("contrasena").value;
    params.append("nombre", nombre);
    params.append("contrasena", contrasena);
    axios.post("http://localhost:4567/iniciarSesion", { nombre: params.get("nombre"), contrasena: params.get("contrasena") } )
        .then(function (rs) {
            alert(rs.data)
            if(rs.data ==true){
                window.location.replace("http://127.0.0.1:5500/listaUsuarios.html");
            }else{
                alert("Usuario incorrecto");
            }
        })
        .catch(function (error) {
            
        });
}); 
