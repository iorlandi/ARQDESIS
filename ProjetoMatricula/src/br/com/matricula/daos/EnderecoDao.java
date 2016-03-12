package br.com.matricula.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.matricula.to.EnderecoTO;

public class EnderecoDao
{
	private static String CONSULTA_ID_ENDERECO = "select idEndereco from Endereco where logradouro = ? and CEP = ? and bairro = ?";
	
	/**
	 * Metodo para Consulta do ID endereço para ser jogado dentro do Aluno como
	 * FK
	 * 
	 * @return Int
	 */
	public static int consultaIDEndereco(Connection conn, EnderecoTO enderecoTO) {
		ResultSet rs;
		PreparedStatement st = null;
		int retornoID = 0;
		try {
			
			st = conn.prepareStatement(CONSULTA_ID_ENDERECO);
			st.setString(1, enderecoTO.getLogradouro());
			st.setString(2, enderecoTO.getCep());
			st.setString(3, enderecoTO.getBairro());
			rs = st.executeQuery();
			if (rs.next()) { // so espero um resultado por isso uso o IF para
								// verificar
				retornoID = rs.getInt("idEndereco");
			}
			st.close(); // fecha consulta
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retornoID;
	}
}
