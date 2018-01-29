<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Actualizar Producto</h1>

<form name="form1" method="GET" action="ControladorProductos">
<input type="hidden" name="instruccion" value="actualizar">
<input type="hidden" name="CArt" value="${ProductoActualizar.cArt}">

Seccion: <input type="text" name="seccion" value="${ProductoActualizar.seccion}">
<br>
Nombre Artículo: <input type="text" name="NArt" value="${ProductoActualizar.nArt}">
<br>
Fecha: <input type="text" name="fecha" value="${ProductoActualizar.fecha}">
<br>
Precio: <input type="text" name="precio" value="${ProductoActualizar.precio}">
<br>
Importado: <input type="text" name="importado" value="${ProductoActualizar.importado}">
<br>
País de origen: <input type="text" name="POrig" value="${ProductoActualizar.pOrig}">

<br>
<input type="submit" name="envio" value="Actualizar">

</form>
</body>
</html>