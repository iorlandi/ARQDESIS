package br.com.matricula.util;

/**Esta classe verifica erros quando do acesso � base de dados, analizando abertuta/fechamento***/
public class ExcRepositorio extends Exception
{
	public ExcRepositorio(String mensagem)
	{
		super(mensagem);
	}
}
