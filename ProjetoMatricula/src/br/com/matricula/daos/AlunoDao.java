package br.com.matricula.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.matricula.to.AlunoTO;
import br.com.matricula.to.EnderecoTO;

public class AlunoDao {

	private Connection conn;
	private static PreparedStatement st;
	private String slogradouro;
	private int sCEP;
	private String sbairro;

	private static String QUERY_INCLUIR_ALUNO = "Insert Into Aluno (IdEndereco,Nome,Rg,Cpf,Telefone, Email) VALUES (?,?,?,?,?,?)";

	/**
	 * METODO PARA INCLUSÃO DE ALUNOS NO DB
	 * 
	 * @param idEndereco
	 * @param nome
	 * @param RG
	 * @param CPF
	 * @param Telefone
	 * @param Celular
	 * @return Boolean
	 */
	public static boolean incluirAluno(Connection conn, AlunoTO alunoTO, EnderecoTO enderecoTO) {
		System.out.println("ESTAMOS NA INCLUSAO");
		boolean sucesso = false;
		int idEndereco = EnderecoDao.consultaIDEndereco(conn, enderecoTO);
		try {

			st = conn.prepareStatement(QUERY_INCLUIR_ALUNO);
			st.setInt(1, idEndereco);
			st.setString(2, alunoTO.getNome());
			st.setString(3, alunoTO.getRg());
			st.setString(4, alunoTO.getCpf());
			st.setString(5, alunoTO.getTelefone());
			st.setString(6, alunoTO.getEmail());
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



	/**
	 * Metodo para Exclusão de Aluno
	 * 
	 * @param idAluno
	 * @return Boolean
	 */
	public static boolean deletarAluno(int idAluno, Connection conn) {
		System.out.println("METODO DELETA ALUNODAO");

		boolean sucesso = false;
		try {
			String sql = "Delete from Aluno where idAluno = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, idAluno);
			st.executeUpdate();
			sucesso = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucesso;
	}

	/**
	 * Metodo para Consulta de Alunos
	 * 
	 * @param idAluno
	 * @return
	 */
	public ArrayList<String> ConsultaAluno(Connection connection, int idAluno) {
		System.out.println("CONSULTA ALUNO");
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<String> array = new ArrayList<>();
		ArrayList<String> arrayEnd = new ArrayList<>();
		try {
			String sql = "select * from Aluno where idAluno = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			result = statement.executeQuery();
			if (result.next()) { // so espero um resultado por isso uso o IF
									// para
									// verificar
				array.add(result.getString("Nome"));
				array.add(result.getString("Email"));
				array.add(result.getString("Rg"));
				array.add(result.getString("Cpf"));
				array.add(result.getString("Telefone"));
				arrayEnd = ConsultaEndereco(connection, result.getInt("idEndereco"));
				for (String end : arrayEnd) {
					array.add(end.toString());
				}

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
		return array;
	}

	/**
	 * Metodo para COnsulta de Endereços
	 * 
	 * @param idEndereco
	 * @return
	 */
	public ArrayList<String> ConsultaEndereco(Connection connection, int idEndereco) {
		ResultSet rs;
		ArrayList<String> array = new ArrayList<>();
		try {
			String sql = "select * from Endereco where idEndereco = ?";
			st = connection.prepareStatement(sql);
			st.setInt(1, idEndereco);
			rs = st.executeQuery();
			if (rs.next()) { // so espero um resultado por isso uso o IF para
								// verificar
				array.add(rs.getString("Logradouro"));
				array.add(rs.getString("CEP"));
				array.add(rs.getString("Bairro"));

			}
			st.close(); // fecha consulta
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	/**
	 * Metodo para Alterar Aluno
	 * 
	 * @return
	 */
	public boolean alterarAluno(int idAluno, String nome, String RG, String CPF, String Telefone, Connection conn) {
		boolean sucesso = false;
		PreparedStatement statement = null;
		try {
			String sql = "Update Aluno set Nome = ?,Rg = ?,Cpf = ?,Telefone = ? WHERE IdAluno = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, RG);
			statement.setString(3, CPF);
			statement.setString(4, Telefone);
			statement.setInt(5, idAluno);
			statement.executeUpdate();
			sucesso = true;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sucesso; // retorna true se conseguiu deletar e false se nao
						// conseguiu
	}

	public boolean alterarEndereco(String logradouro, String CEP, String Bairro, Connection conn) {
		boolean sucesso = false;
		PreparedStatement statement = null;
		EnderecoTO enderecoTO = new EnderecoTO(0, logradouro, CEP, Bairro);
		try {
			String sql = "Update Endereco set Logradouro = ?, Cep = ?,Bairro = ? WHERE IdEndereco = ?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, logradouro);
			statement.setString(2, CEP);
			statement.setString(3, Bairro);
			statement.setInt(4, EnderecoDao.consultaIDEndereco(conn, enderecoTO));
			statement.executeUpdate();
			sucesso = true;
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sucesso; // retorna true se conseguiu deletar e false se nao
						// conseguiu
	}
}
