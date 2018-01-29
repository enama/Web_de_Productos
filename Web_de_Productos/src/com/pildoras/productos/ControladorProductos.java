package com.pildoras.productos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModeloProductos modeloProductos;
	@Resource(name="jdbc/Productos")
	private DataSource miPool;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
		modeloProductos=new ModeloProductos(miPool);
		
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	//Leer el parametro q llega desde el  formulario
		String parametro=request.getParameter("instruccion");
		//Sino se envia el parametro, listar productos
		if(parametro==null) parametro="listar";
		
		
		//Redirigir el flujo de ejecucion al metodo adecuado
		
		switch(parametro) {
		case"listar":
			obtenerProductos(request,response);
			System.out.println("LISTAR");
			break;
		case"insertar":
			
			agregarProductos(request,response);
			System.out.println("INSERTAR");
			break;
		case"cargar":
			try {
				cargaProductos(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		case"actualizar":
			
			try {
				actualizaProductos(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case"eliminar":
			
			try {
				eliminarProducto(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default:	
			System.out.println("mal");
		}
		
		
		
	}


	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		
		//Capturar el codigo articulo
		
		String CodArticulo=request.getParameter("CArticulo");
		
		
		//Borrar producto de la base de datos
		modeloProductos.eliminarProducto(CodArticulo);
		
		
		//volver al listado de productos
		
		obtenerProductos(request,response);
	}


	private void actualizaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		//Leer los datos que vienen del formulario de actualizar

		String CodArticulo=request.getParameter("CArt");
		String Seccion=request.getParameter("seccion");
		String NombreArticulo=request.getParameter("NArt");
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date Fecha=null;
		
		try {
			Fecha=formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double Precio=Double.parseDouble(request.getParameter("precio"));
		String Importado=request.getParameter("importado");
		String PaisOrigen=request.getParameter("POrig");
		
		
		
		
		
		//Crear un objeto producto con la informacion
		Productos ProductoActualizado=new Productos(CodArticulo,Seccion,NombreArticulo,Precio,Fecha,Importado,PaisOrigen);
		
		
		//Actializar la BD con la informacion
		modeloProductos.actualizarProducto(ProductoActualizado);
		//Mostrar el listado con informacion actualizada
		
		obtenerProductos(request,response);
	}


	private void cargaProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// TODO Auto-generated method stub
		//Leer el codigo articulo q viene del listado
		String codigoArticulo= request.getParameter("CArticulo");
		
		//Enviar el codigo articulo al modelo
		
		Productos elProducto=modeloProductos.getProducto(codigoArticulo);
		
		//Colocar atributo correspondiente al codigo articulo
		request.setAttribute("ProductoActualizar", elProducto);
		
		//Enviar producto al formulario de actualizar
		RequestDispatcher dispatcher=request.getRequestDispatcher("/actualizarProducto.jsp");
		dispatcher.forward(request, response);
	}


	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		//Leer la informacion del producto que viene del formulario
		
		String CodArticulo=request.getParameter("CArt");
		String Seccion=request.getParameter("seccion");
		String NombreArticulo=request.getParameter("NArt");
		SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		Date Fecha=null;
		
		try {
			Fecha=formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double Precio=Double.parseDouble(request.getParameter("precio"));
		String Importado=request.getParameter("importado");
		String PaisOrigen=request.getParameter("POrig");
		//crear un objeto de tipo producto
		
		
		Productos nuevoProducto=new Productos(CodArticulo,Seccion,NombreArticulo,Precio,Fecha,Importado,PaisOrigen);
		//Enviar el objeto al modelo e insertar el objeto producto en la bd
		modeloProductos.agregaProducto(nuevoProducto);
		
		//Volver a listar los productos
		
		
		obtenerProductos(request,response);
	}


	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		//Obtener lista de productos desde el modelo
		List<Productos> productos;
		try {
		productos=modeloProductos.getProductos();
		
	//Agregar la lista del producto al request
		request.setAttribute("LISTAPRODUCTOS", productos);
		
		
	//Enviar el request a la pagina jsp
		
		RequestDispatcher miDispatcher=request.getRequestDispatcher("/listaProductos.jsp");
		miDispatcher.forward(request, response);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}

}
