var nombreP = document.getElementById("nombre").value;
var constrasenaP = document.getElementById("contrasena").value;

var btnRegistrarse = document.getElementById("btnRegistrarse");
btnRegistrarse.addEventListener("click", click);

function click(){
    var params = new URLSearchParams();
    var nombre = document.getElementById("nombre").value;
    var contrasena = document.getElementById("contrasena").value;
    params.append("nombre", nombre);
    params.append("contrasena", contrasena);
    axios.get("http://localhost:4567/registrarUsuario?" +  params )
        .then(function (rs) {
            alert(rs.data.nombre)
            //Aqui recibo el valor del servidor pero no se porque el valor de las variables no coambia y no lo puedo guardar en el metodo guardar() para agregarlo a la tabla
            nombreP = rs.data.nombre;
            constrasenaP = rs.data.contrasena;
            alert(nombreP);
        })
        .catch(function (error) {
        });
}

function guardar(){
    var fila="<tr><td>"+nombreP+"</td><td>"+constrasenaP+"</td></tr>";
    var btn = document.createElement("TR");
   	btn.innerHTML=fila;
    document.getElementById("tablita").appendChild(btn);
}