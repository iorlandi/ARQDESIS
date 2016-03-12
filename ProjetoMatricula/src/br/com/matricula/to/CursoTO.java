package br.com.matricula.to;

import java.sql.Timestamp;
import java.util.Date;

public class CursoTO
{
	private int idCurso;
	private String nome;
	private Timestamp horario;
	private Date dataInicial;
	private Date dataFinal;
	private int quantidadeVaga;
	private float valor;
	
	public CursoTO()
	{
		
	}
	
	public CursoTO(String nome, Timestamp horario, Date dataInicial, Date dataFinal, int quantidadeVaga,
			float valor)
	{
		this.nome = nome;
		this.horario = horario;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.quantidadeVaga = quantidadeVaga;
		this.valor = valor;
	}
	public int getIdCurso()
	{
		return idCurso;
	}
	public void setIdCurso(int idCurso)
	{
		this.idCurso = idCurso;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public Date getHorario()
	{
		return horario;
	}
	public void setHorario(Timestamp horario)
	{
		this.horario = horario;
	}
	public Date getDataInicial()
	{
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial)
	{
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal()
	{
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal)
	{
		this.dataFinal = dataFinal;
	}
	public int getQuantidadeVaga()
	{
		return quantidadeVaga;
	}
	public void setQuantidadeVaga(int quantidadeVaga)
	{
		this.quantidadeVaga = quantidadeVaga;
	}
	public float getValor()
	{
		return valor;
	}
	public void setValor(float valor)
	{
		this.valor = valor;
	}
	
	
}
