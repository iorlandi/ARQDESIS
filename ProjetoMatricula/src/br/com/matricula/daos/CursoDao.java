package br.com.matricula.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.matricula.to.CursoTO;

public class CursoDao
{
	private static String QUERY_CURSO_SELECIONADO_POR_NOME = "SELECT * FROM bd2msin.curso WHERE Nome = ?";
	private static String QUERY_TODOS_CURSOS = "SELECT * FROM bd2msin.curso";
	private static String QUERY_CURSO  = " SELECT * FROM CURSO where IdCurso = ?";
	private static String QUERY_CONSULTA_CURSO_INFORMATICA  = " Select Curso.IdCurso,Curso.Nome,"
			                                                + "	Curso.Horario, Curso.DataInicial,"
										                    + "Curso.DataFinal, Curso.QuantidadeVaga, Curso.Valor,"
										                    + "from Curso INNER JOIN Informatica,"
										                    + "ON Informatica.IdInformatica = IdInformatica group by Curso.IdCurso";
	
	private static String QUERY_CONSULTA_CURSO_ARTE  = " Select Curso.IdCurso,Curso.Nome, Curso.Horario, Curso.DataInicial,"
											         + " Curso.DataFinal, Curso.QuantidadeVaga, Curso.Valor,"
											         + " from Curso INNER JOIN Arte,"
											         + " ON Arte.IdArte = IdArte group by Arte.IdArte";
	private static String QUERY_ATUALIZA_CURSO_INFORMATICA  = "Update Curso set Nome = ?,Horario = ?,DataInicial = ?, DataFinal = ?,"
															  +"QuantidadeVaga = ?, Valor = ?"
						 								      +"WHERE IdAluno = ?"
															  +"Update Informatica set  Software = ?,"
															  + " Laboratorio = ?,IdCurso = ?";
			
    private static String QUERY_ATUALIZA_CURSO_ARTE  = "Update Curso set Nome = ?,Horario = ?,DataInicial = ?, DataFinal = ?,"
													  + "QuantidadeVaga = ?, Valor = ?,"
													  + "WHERE IdAluno = ?"
    												  + "Update Arte set  DescricaoMaterial = ?, Livro = ?,IdCurso = ?";
	
    private static String QUERY_DELETA_CURSO = "Delete from Curso where IdCurso = ?";
    private static String QUERY_DELETA_ITEMCURSO = "Delete from itemcadastro where IdCurso = ?";
	private static String QUERY_INCLUIR_CURSO = "Insert into Curso (Nome,Horario,DataInicial,DataFinal,QuantidadeVaga,Valor) VALUES (?,?,?,?,?,?)";
	private static String QUERY_INCLUIR_CURSO_INFO = "Insert into Informatica (Software,Laboratorio,IdCurso) VALUES (?,?,?)";
	private static String QUERY_INCLUIR_CURSO_ARTE = "Insert into Arte (DescricaoMaterial,Livro,IdCurso) VALUES (?,?,?)";
	
	
	/** Metodo que retorna um Arralist com todos os Cursos Cadastrados***/
	public static ArrayList<CursoTO> consultaTodosCurso(Connection connection)
	{
		ArrayList<CursoTO>cursoTOs = new ArrayList<CursoTO>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			statement = connection.prepareStatement(QUERY_TODOS_CURSOS);
			resultSet = statement.executeQuery();
			while (resultSet.next())
			{
				
				CursoTO bean = new CursoTO(resultSet.getString(2), resultSet.getTimestamp(3), resultSet.getDate(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getFloat(7));
				cursoTOs.add(bean);
			}
			return cursoTOs;
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				connection.rollback();
			} catch (SQLException el)
			{
				System.out.println(el.getStackTrace());
			}
		} finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				} catch (SQLException el2)
				{
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return cursoTOs;
		
	}
	

	/**retorna resultser de curso**/
	public static ResultSet  retornaResulSetCurso(Connection connection)
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try
		{
			statement = connection.prepareStatement(QUERY_TODOS_CURSOS);
			System.out.println(statement);
			return  statement.executeQuery();
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return resultSet;
	}


	/**Metodo que consulta produto pelo id**/
	public static CursoTO consultaID(Connection connection, int parseInt)
	{
		CursoTO curso = new CursoTO ();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try
		{
			statement = connection.prepareStatement(QUERY_CURSO);
			statement.setInt(1, parseInt);
			resultSet = statement.executeQuery();
			if (resultSet.next())
			{	
				System.out.println(resultSet.getDate(3));
				curso.setNome(resultSet.getString(2));
				curso.setHorario(resultSet.getTimestamp(3));
				curso.setDataInicial(resultSet.getDate(4));
				curso.setDataFinal(resultSet.getDate(5));
				curso.setQuantidadeVaga(resultSet.getInt(6));
				curso.setValor(resultSet.getInt(7));
			}
			return curso;
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				connection.rollback();
			} catch (SQLException el)
			{
				System.out.println(el.getStackTrace());
			}
		} finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				} catch (SQLException el2)
				{
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return null;
			
	}

	/**Metodo que deleta item da tabela itemCurso**/
	public static boolean deletarItemCurso(int parseInt, Connection connection)
	{
		PreparedStatement statement = null;
		try
		{
			statement = connection.prepareStatement(QUERY_DELETA_ITEMCURSO);
			statement.setInt(1, parseInt);
			statement.executeUpdate();

			
			if(deletarCurso(parseInt, connection))
			{
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				connection.rollback();
			} catch (SQLException el)
			{
				System.out.println(el.getStackTrace());
			}
		} finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				} catch (SQLException el2)
				{
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return false;
	}

	public static CursoTO ConsultaCurso(Connection conn, String nome) {
		System.out.println("CONSULTA CURSO");
		PreparedStatement statement = null;
		ResultSet result = null;
		CursoTO curso = new CursoTO();
		
		try {
			
			statement = conn.prepareStatement(QUERY_CURSO_SELECIONADO_POR_NOME);
			statement.setString(1, nome);
			result = statement.executeQuery();
			if (result.next()) { // so espero um resultado por isso uso o IF
									// para
									// verificar
				curso.setIdCurso(Integer.parseInt(result.getString("IdCurso")));
				curso.setNome(result.getString("Nome"));
				curso.setHorario(result.getTimestamp("Horario"));
				curso.setDataInicial(result.getDate("DataInicial"));
				curso.setDataFinal(result.getDate("DataFinal"));
				curso.setQuantidadeVaga(result.getInt("QuantidadeVaga"));
				curso.setValor(result.getFloat("Valor"));

						

			}
			statement.close(); // fecha consulta
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException el) {
				System.out.println(el.getStackTrace());
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException el2) {
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return curso;
	}
	
	private static boolean deletarCurso(int parseInt, Connection connection)
	{
		PreparedStatement statement = null;
		try
		{
			statement = connection.prepareStatement(QUERY_DELETA_CURSO);
			statement.setInt(1, parseInt);
			statement.executeUpdate();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				connection.rollback();
			} catch (SQLException el)
			{
				System.out.println(el.getStackTrace());
			}
		} finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				} catch (SQLException el2)
				{
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return false;
		
	}
	
	
	
}
