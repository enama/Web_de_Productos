package com.pildoras.productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProductos {
	
	private DataSource origenDatos;
	public ModeloProductos(DataSource origenDatos) {
		this.origenDatos=origenDatos;
	}

	public List<Productos> getProductos() throws Exception{
		List<Productos>productos=new ArrayList<>();
		
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultSet=null;
		
		//----Establecer la conexion
		
		miConexion=origenDatos.getConnection();
		
		
		//-----Crear la sentencia sql
		String consulta="SELECT * FROM PRODUCTOS";
		
		miStatement=miConexion.createStatement();
		//----Ejecutar la sentencia sql
		miResultSet=miStatement.executeQuery(consulta);
		
		//----Recorrer el ResultSet obtenido
		
		while(miResultSet.next()) {
			
			String c_art=miResultSet.getString(1);
			String seccion=miResultSet.getString(2);
			String n_art=miResultSet.getString(3);
			double precio=miResultSet.getDouble(4);
			Date fecha=miResultSet.getDate(5);
			String importado=miResultSet.getString(6);
			String p_orig=miResultSet.getString(7);
			
			Productos temProd= new Productos(c_art,seccion,n_art,precio,fecha,importado,p_orig);
			
			productos.add(temProd);
		}
		
		return productos;
	}

	public void agregaProducto(Productos nuevoProducto) {
		// TODO Auto-generated method stub
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		//Obtener la conexion con la bd
		
		try {
			
			miConexion=origenDatos.getConnection();
			
			
		
		
		//Crear instruccion para insertar
		
		String sql="INSERT INTO PRODUCTOS(CÓDIGOARTÍCULO,SECCIÓN,NOMBREARTÍCULO,PRECIO,FECHA,IMPORTADO,PAÍSDEORIGEN)VALUES(?,?,?,?,?,?,?)";
		miStatement=miConexion.prepareStatement(sql);
		
		//Establecer los parametros para el producto
		miStatement.setString(1, nuevoProducto.getcArt());
		miStatement.setString(2, nuevoProducto.getSeccion());
		miStatement.setString(3, nuevoProducto.getnArt());
		miStatement.setDouble(4, nuevoProducto.getPrecio());
		java.util.Date utilDate=nuevoProducto.getFecha();
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());
		miStatement.setDate(5, fechaConvertida);
		miStatement.setString(6, nuevoProducto.getImportado());
		miStatement.setString(7, nuevoProducto.getpOrig());
		
		
		//ejecutar la instruccion sql
		
		miStatement.execute();
		
		System.out.println("SE EJECUTA LA SENTENCIA");
}catch(Exception e) {
	System.out.println("nooooSE EJECUTA LA SENTENCIA");
	e.printStackTrace();
		}
		
	}

	public Productos getProducto(String codigoArticulo) {
		// TODO Auto-generated method stub
		Productos elProducto=null;
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		ResultSet miResultSet=null;
		String cArticulo=codigoArticulo;
		try {
		//Establecer la conexion con la db
		miConexion=origenDatos.getConnection();
		
		//crear la consulta sql
		String consulta="SELECT * FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";
		
		
		//consulta preparada
		
		miStatement= miConexion.prepareStatement(consulta);
		
		
		
		//establecer los parametros de esa consulta
		
		miStatement.setString(1, cArticulo);
		//ejecutar la consulta
		
		miResultSet=miStatement.executeQuery();
		//obtener los datos de respuesta
		
		if(miResultSet.next()) {
			
			String cart=miResultSet.getString(1);
			String seccion=miResultSet.getString(2);
			String n_art=miResultSet.getString(3);
			double precio=miResultSet.getDouble(4);
			Date fecha=miResultSet.getDate(5);
			String importado=miResultSet.getString(6);
			String p_orig=miResultSet.getString(7);
			
			elProducto= new Productos(cart,seccion,n_art,precio,fecha,importado,p_orig);
			
			
			
		}
		
		else {
			throw new Exception("No se ha encontrado el articulo");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return elProducto;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception{
		// TODO Auto-generated method stub
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		
		//Establecer la conexion con la BD
		
		miConexion=origenDatos.getConnection();
		
		//Crear sentencia SQL
		String sql="UPDATE PRODUCTOS SET SECCIÓN=?,NOMBREARTÍCULO=?,PRECIO=?,FECHA=?, IMPORTADO=?,PAÍSDEORIGEN=? WHERE CÓDIGOARTÍCULO=?";
		
		//Crear la consulta preparada
		
		miStatement=miConexion.prepareStatement(sql);
		
		//Establecer los parametros
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getnArt());
		miStatement.setDouble(3, productoActualizado.getPrecio());
		java.util.Date utilDate=productoActualizado.getFecha();
		java.sql.Date fechaConvertida=new java.sql.Date(utilDate.getTime());
		miStatement.setDate(4, fechaConvertida);
		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getpOrig());
		miStatement.setString(7, productoActualizado.getcArt());
		
		
		//Ejecutar la instruccion SQL
		miStatement.execute();
		
	}

	public void eliminarProducto(String codArticulo) throws Exception{
		// TODO Auto-generated method stub
		
		
		//Establecer la conexion
		Connection miConexion=null;
		PreparedStatement miStatement=null;
		
		
		//Establecer la conexion con la BD
		
		try {
		
		miConexion=origenDatos.getConnection();
		
		
		//crear instruccion sql de eliminacion
		
		String consulta="DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";
		
		//preparar la consulta
		
		miStatement= miConexion.prepareStatement(consulta);
		
		//establecer los parametros
		miStatement.setString(1, codArticulo);
		
		//ejecutar la sentencia sql
		
		miStatement.execute();
		}
		finally {
			miStatement.close();
			miConexion.close();
		}
	}
}
