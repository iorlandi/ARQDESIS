package br.com.matricula.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.matricula.to.MatriculaTO;
import br.com.matricula.views.Matricula;

public class MatriculaDao {

	private static PreparedStatement st;

	private static String CONSULTA_TODAS_MATRICULA = "SELECT "
			+ "m.IdMatricula, "
			+ "a.Nome, "
			+ "m.ValorMatricula, "
			+ "m.DataMatricula,  "
			+ "m.FlgPagamento "
			+ "FROM "
			+ "matricula as m inner join aluno as a on m.IdAluno = a.IdAluno inner join itemcadastro as ic on m.IdMatricula = ic.IdMatricula inner join curso as c on c.IdCurso = ic.IdCurso where m.DataMatricula between ? and ?";

	private static String QUERY_RELATORIO_MATRICULA = "select  c.Nome as Curso, c.DataInicial, c.DataFinal,  "
			+ "count(m.IdMatricula) as 'QtdMatricula', case m.FlgMatricula when 0 then 'Cancelado' when 1 then 'Matriculado' "
			+ "end  as 'Cancelamentos' from matricula as m inner join "
			+ "itemcadastro as ic on ic.IdMatricula = m.IdMatricula left outer join curso as c "
			+ "on c.IdCurso = ic.IdCurso "
			+ "where c.DataInicial >= ? or c.DataFinal >=? "
			+ "group by c.Nome, m.FlgMatricula order by c.Nome ";

	private static String QUERY_RELATORIO_MATRICULA_ALUNO = "select al.Nome as 'Aluno', c.Nome as 'Curso', "
			+ "if (c.DataInicial<=now() and c.DataFinal>=now(),if(m.FlgMatricula= 0 ,'Cancelado' ,'Cursando'),'Cursado') as 'StatusCurso', "
			+ "c.Horario from matricula as m inner join itemcadastro as ic on ic.IdMatricula = m.IdMatricula "
			+ "left outer join curso as c on c.IdCurso = ic.IdCurso "
			+ "inner join aluno as al on al.IdAluno = m.IdAluno "
			+ "WHERE al.Nome = ? group by c.Nome order by c.Nome ";
	private static String QUERY_CADASTRA_MATRICULA = "Insert Into Matricula (IdAluno,ValorMatricula,DataMatricula,FlgPagamento,FlgMatricula) VALUES    (?,?,?,?,?);";
	private static String QUERY_CONSULTA_MATRICULA = "select * from Matricula where idMatricula = ?";
	private static String QUERY_CANCELAR_MATRICULA = "Delete from Matricula where idMatricula = ?";
	private static String QUERY_BUSCAR_IDMATRICULA = "select idMatricula from Matricula where IdAluno = ? and ValorMatricula = ?";
	
	public static ArrayList<MatriculaTO> consultaMatriculaPorPeriodo(
			Connection conn, Date p_DataInicial, Date p_DataFinal) {
		ResultSet rs;
		PreparedStatement st = null;
		ArrayList<MatriculaTO> listMatriculaBeans = new ArrayList<>();
		try {

			st = conn.prepareStatement(CONSULTA_TODAS_MATRICULA);
			st.setDate(1, (java.sql.Date) p_DataInicial);
			st.setDate(2, (java.sql.Date) p_DataFinal);
			rs = st.executeQuery();
			while (rs.next()) {
				MatriculaTO bean = new MatriculaTO();
				bean.setIdMatricula(rs.getInt(1));
				bean.setNomeAluno(rs.getString(2));
				bean.setValorMatricula(rs.getFloat(3));
				bean.setDataMatricula(rs.getDate(4));
				bean.setFlgPagamento(rs.getInt(5));
				listMatriculaBeans.add(bean);
			}
			return listMatriculaBeans;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException el) {
				System.out.println(el.getStackTrace());
			}
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException el2) {
					System.err.println(el2.getStackTrace());
				}
			}
		}
		return listMatriculaBeans;
	}

	public static ResultSet retornaRelatorio(Connection conn,
			String p_stringDate, String p_string2Date) {
		ResultSet rs = null;
		PreparedStatement st = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.sql.Date dataInicial = null;
		java.sql.Date datafinal = null;
		try {
			System.out.println(p_stringDate);
			dataInicial = new java.sql.Date(format.parse(p_stringDate)
					.getTime());
			datafinal = new java.sql.Date(format.parse(p_string2Date).getTime());
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		try {

			st = conn.prepareStatement(QUERY_RELATORIO_MATRICULA);
			st.setDate(1, dataInicial);
			st.setDate(2, datafinal);

			return st.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;

	}

	public static ResultSet retornaRelatorioPorAluno(Connection conn,
			String p_Aluno) {
		ResultSet rs = null;
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(QUERY_RELATORIO_MATRICULA_ALUNO);
			st.setString(1, p_Aluno);

			return st.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;

	}

	public static boolean cadastraMatricula(Connection conn, int IdAluno,
			float ValorMatricula, String DataMatricula,
			int flgPagamento, int flgMatricula) {
		boolean sucesso = false;
		// (IdAluno,ValorMatricula,DataMatricula,FlgPagamento,FlgMatricula)
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
			java.sql.Date data = new java.sql.Date(format.parse(DataMatricula).getTime()); 
			st = conn.prepareStatement(QUERY_CADASTRA_MATRICULA);
			st.setInt(1, IdAluno);
			st.setFloat(2, ValorMatricula);
			st.setDate(3, data);
			st.setInt(4, flgPagamento);
			st.setInt(5, flgMatricula);
			st.executeUpdate();
			sucesso = true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException el) {
				System.out.println(el.getStackTrace());
			}
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException el2) {
					System.err.println(el2.getStackTrace());
				}
			}
		}

		return sucesso;

	}
	
	public static MatriculaTO ConsultaMatricula(Connection connection, int idMatricula) {
		System.out.println("CONSULTA ALUNO");
		PreparedStatement statement = null;
		ResultSet result = null;
		MatriculaTO matricula = new MatriculaTO();
		
		try {

			statement = connection.prepareStatement(QUERY_CONSULTA_MATRICULA);
			statement.setInt(1, idMatricula);
			result = statement.executeQuery();
			if (result.next()) { // so espero um resultado por isso uso o IF
									// para
									// verificar
				matricula.setValorMatricula(result.getFloat("ValorMatricula"));
				matricula.setDataMatricula(result.getDate("DataMatricula"));
				matricula.setIdAluno(Integer.parseInt(result.getString("IdAluno")));
				matricula.setIdMatricula(idMatricula);
				matricula.setFlgPagamento(result.getInt("FlgPagamento"));
				matricula.setFlgMatricula(result.getBoolean("FlgMatricula"));
				

			}
			statement.close(); // fecha consulta
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
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
		return matricula;
	}
	
	public static boolean deletarMatricula(int IdMatricula, Connection conn) {
		System.out.println("METODO DELETA MATRICULA");

		boolean sucesso = false;
		try {
			st = conn.prepareStatement(QUERY_CANCELAR_MATRICULA);
			st.setInt(1, IdMatricula);
			st.executeUpdate();
			sucesso = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucesso;
	}
	public static int ConsultaIdMatricula(Connection connection, int idAluno, float valorMatricula) {

		int IdMatricula = 0;
		PreparedStatement statement = null;
		ResultSet result = null;

		
		try {

			statement = connection.prepareStatement(QUERY_CONSULTA_MATRICULA);
			statement.setInt(1, idAluno);
			result = statement.executeQuery();
			IdMatricula = result.getInt("IdMatricula");
			statement.close(); // fecha consulta
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
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
		return IdMatricula;
	}
	
 
}
