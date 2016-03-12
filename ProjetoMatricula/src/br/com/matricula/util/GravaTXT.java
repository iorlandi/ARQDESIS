package br.com.matricula.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class GravaTXT {

	private static FileWriter fileWriter;
	private static PrintWriter escreverArquivo;
	private static String[] camposSplits;

	public static void gravarTxt() {
		// criando e atribuindo valor as variavels nome e senha
		String nome = JOptionPane.showInputDialog("Informe seu nome");
		String senha = JOptionPane.showInputDialog("Informe sua senha");
		
		// criar arquivo neste diretório
		File arquivo = new File( "nome_e_senha.txt");

		try {
			fileWriter = new FileWriter(arquivo, true);

			escreverArquivo = new PrintWriter(fileWriter);

			// adcionando as variavies dentro do arquivo
			escreverArquivo.println(nome + "|" + senha);

			// o método flush libera a escrita no arquivo
			escreverArquivo.flush();

			// o método close fecha o arquivo
			escreverArquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Ler Compromisso
	public static String lerTC() {
		String campos = "";
		boolean flagSenha = false;
		boolean flagNome = false;

		File file = new File( "nome_e_senha.txt");
		try {
			// Indicamos o arquivo que será lido
			FileReader fileReader = new FileReader(file);

			// Criamos o bojeto bufferReader.
			// Nele consta o metodo de leitura readLine()
			BufferedReader reader = new BufferedReader(fileReader);
			String dados = null;
			while ((dados = reader.readLine()) != null) {
				/*camposSplits = dados.split("[\\W]");
				for (String string : camposSplits) {
					System.out.println(string + " aq");
				}*/

				campos += dados;
			}

			/*if (!flagNome && !flagSenha) {
				JOptionPane.showMessageDialog(null,
						"LOGIN E/OU SENHA ERRADO(S)", "ATENÇÃO",
						JOptionPane.CANCEL_OPTION);
			}*/

			fileReader.close();
			reader.close();

		} catch (IOException erro) {
			erro.printStackTrace();

		}
		return campos;
	}

	public void gravarTCI(String tci) {
		try {
			String aux = "";
			File file = new File( "texto_cifrado.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(tci);

			writer.flush(); // Cria o conteúdo do arquivo.
			writer.close(); // Fechando conexão e escrita do arquivo.
		} catch (IOException e) {

			e.printStackTrace(); // possibilita capturar qualquer erro que o
									// "io" venha ocasionar
			// printStackTrace ---> mostra a descrição do erro
		}
	}

	// Ler Compromisso
	public static String lerTCI() {
		String campos = "";

		try {
			File file = new File( "texto_cifrado.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String dados = null;
			while ((dados = reader.readLine()) != null)// cada volta do looping
														// ele armazena uma
														// linha
			{
				// Na busca ele traz os dados separados, no arquivo salva td
				// junto, agora ele vai salvar separado tbm.
				campos = dados;
			}

			fileReader.close();
			reader.close();
		} catch (IOException erro) {
			erro.printStackTrace();

		}
		return campos;
	}

	public void gravarNovoTxt(String p_Senha, String p_Usuario, Object selectedItem)
	{
		
				// criar arquivo neste diretório
				File arquivo = new File("nome_e_senha.txt");

				try {
					fileWriter = new FileWriter(arquivo, true);

					escreverArquivo = new PrintWriter(fileWriter);

					// adcionando as variavies dentro do arquivo
					escreverArquivo.println(p_Usuario + "|" + p_Senha+"|"+selectedItem+"|");

					// o método flush libera a escrita no arquivo
					escreverArquivo.flush();

					// o método close fecha o arquivo
					escreverArquivo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

}
