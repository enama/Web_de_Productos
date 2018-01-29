<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Registro de productos</h1>

<form name="form1" method="GET" action="ControladorProductos">
<input type="hidden" name="instruccion" value="insertar">
Código Artículo:  <input type="text" name="CArt">
<br>
Seccion: <input type="text" name="seccion">
<br>
Nombre Artículo: <input type="text" name="NArt">
<br>
Fecha: <input type="text" name="fecha">
<br>
Precio: <input type="text" name="precio">
<br>
Importado: <input type="text" name="importado">
<br>
País de origen: <input type="text" name="POrig">

<br>
<input type="submit" name="envio" value="Enviar">
<br>
<input type="submit" name="borrar" value="Reestablecer">

</form>
</body>
</html>