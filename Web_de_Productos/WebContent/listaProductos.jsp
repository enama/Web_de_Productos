<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<%--     <%@ page import="java.util.*,com.pildoras.productos.*" %>
 --%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%-- <%
//Obtiene los productos del controlador

List<Productos>losProductos=(List<Productos>)request.getAttribute("LISTAPRODUCTOS");

%> --%>
<body>
<%-- <%=losProductos %> --%>
<input type="submit" value="Registrar producto" onclick="window.location.href='inserta_producto.jsp'">
<table>
<tr> 
<td>Código Artículo</td>
<td>Sección</td>
<td>Nombre Artículo</td>
<td>Fecha</td>
<td>Precio</td>
<td>Importado</td>
<td>País de Origen</td>
<td>Acción</td>
</tr>


<c:forEach var="temProd" items="${LISTAPRODUCTOS }">
<c:url var="linkTemp" value="ControladorProductos">

<c:param name="instruccion" value="cargar"></c:param>

<c:param name="CArticulo" value="${temProd.cArt}"></c:param>

</c:url>

<c:url var="linkTempEliminar" value="ControladorProductos">

<c:param name="instruccion" value="eliminar"></c:param>

<c:param name="CArticulo" value="${temProd.cArt}"></c:param>

</c:url>
<tr>

<td> ${temProd.cArt}</td>
<td>${temProd.seccion}</td>
<td>${temProd.nArt}</td>
<td>${temProd.fecha}</td>
<td>${temProd.precio}</td>
<td>${temProd.importado}</td>
<td>${temProd.pOrig}</td>
<td><a href="${linkTemp}">Actualizar</a><a href="${linkTempEliminar}">Eliminar</a></td>
</tr>

</c:forEach>
</table>

</body>
</html>