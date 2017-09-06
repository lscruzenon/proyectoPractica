
var Txt_Nombre;
var Txt_Edad;
var Txt_Direccion;
var Txt_Empresa;

function nuevoAjax(){
	var xmlhttp=false;
	try{
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	}catch(e){
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}catch(E){
			xmlhttp = false;
		}
	}
        
	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}

	return xmlhttp;
}

function Nombre()
{
	Txt_Nombre=document.getElementById('idnom').value;
	Txt_Edad=document.getElementById('ided').value;
        Txt_Direccion=document.getElementById('iddir').value;
        Txt_Empresa=document.getElementById('idemp').value;
        if(Txt_Nombre == "" || Txt_Edad == "" || Txt_Direccion == "" || Txt_Empresa == ""){
            alert("campo vacio");
            return Nombre(exit);
        }else{
        //el .innerHTML abre el tag para ingresar datos 
        document.getElementById('resultado').innerHTML="";//accede atodos los  el elementos html y vacia el objeto  abre el tag
        vacio(Txt_Nombre,Txt_Edad,Txt_Direccion,Txt_Empresa);  
    }
}
function vacio(a,b,c,d)
{
        var resul = document.getElementById('resultado');
	var ajax = nuevoAjax();	
	ajax.open("POST", "server.jsp",true);	
	ajax.onreadystatechange=function()
        {
	
            if (ajax.readyState==4)
            {//escucador
                resul.innerHTML = resul.innerHTML+ajax.responseText;//concatena los datos en la tabla  
            }
	}
        ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        ajax.send("nombre="+a+"&edad="+b+"&direccion="+c+"&empresa="+d);//envia todos los datos
        
       
}

function reset()
{
	resul = document.getElementById('resultado');
	resul.innerHTML = '';
}