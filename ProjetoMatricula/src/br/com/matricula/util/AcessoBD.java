package br.com.matricula.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Classe responsável pela conexão com banco de dados sqlServer
*/

public class AcessoBD
{
	// -----------------------------------------------------------
	// Carrega driver JDBC	- executada automaticamente quando a classe é chamada (Mesma para todos os objetos da classe)
	//
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	//-----------------------------------------------------------
	//Obtém conexão com o banco de dados
	public Connection obtemConexao() throws SQLException
	{
		 Connection connection = null;
		//DriveManager -> Serviço básico para a administração de um conjunto de drivers (http://docs.oracle.com/javase/7/docs/api/java/sql/DriverManager.html)
		 connection = DriverManager.getConnection("jdbc:mysql://localhost/BD2MSIN","root","root");
		 
		 if (connection != null)
		 {
			 System.out.println("Conectado com sucesso");
		 }
		 else
		{
			System.out.println("N�o foi conectado");
		}
		 
		 return connection;
	}
}
