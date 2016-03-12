package br.com.matricula.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import br.com.matricula.views.Curso;

public class MatriculaTO
{
	private int IdMatricula;
	private int IdAluno;
	private String NomeAluno;
	private float ValorMatricula;
	private Date DataMatricula;
	private boolean FlgPagamento;
	private boolean FlgMatricula;
	private ArrayList<Object> cursos;
	
	
	public MatriculaTO(){}
	
	public MatriculaTO(int idMatricula, String nomeAluno, float valorMatricula, Date dataMatricula,
			boolean flgPagamento, boolean flgMatricula)
	{

		NomeAluno = nomeAluno;
		ValorMatricula = valorMatricula;
		DataMatricula = dataMatricula;
		FlgPagamento = flgPagamento;
		FlgMatricula = flgMatricula;
	}
	public int getIdMatricula()
	{
		return IdMatricula;
	}
	public void setIdMatricula(int idMatricula)
	{
		IdMatricula = idMatricula;
	}
	public String getNomeAluno()
	{
		return NomeAluno;
	}
	public void setNomeAluno(String nomeAluno)
	{
		NomeAluno = nomeAluno;
	}
	public float getValorMatricula()
	{
		return ValorMatricula;
	}
	public void setValorMatricula(float valorMatricula)
	{
		ValorMatricula = valorMatricula;
	}
	public Date getDataMatricula()
	{
		return DataMatricula;
	}
	public void setDataMatricula(Date dataMatricula)
	{
		DataMatricula = dataMatricula;
	}
	public boolean isFlgPagamento()
	{
		return FlgPagamento;
	}
	public void setFlgPagamento(int flgPagamento)
	{
		FlgPagamento = (flgPagamento == 1 ? true : false);
	}
	public boolean isFlgMatricula()
	{
		return FlgMatricula;
	}
	public void setFlgMatricula(boolean flgMatricula)
	{
		FlgMatricula = flgMatricula;
	}
	public int getIdAluno() {
		return IdAluno;
	}

	public void setIdAluno(int idAluno) {
		IdAluno = idAluno;
	}

	public ArrayList<Object> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Object> listCursos) {
		this.cursos = listCursos;
	}

	public void setFlgPagamento(boolean flgPagamento) {
		FlgPagamento = flgPagamento;
	}
}
