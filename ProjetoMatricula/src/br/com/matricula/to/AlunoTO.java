package br.com.matricula.to;

public class AlunoTO
{
	private int idAluno;
	private int idEndereco;
	private String nome;
	private String email;
	private String rg;
	private String cpf;
	private String telefone;
	
	public AlunoTO()
	{
		
	}

	
	
	
	public AlunoTO(int idAluno, int idEndereco, String nome, String email, String rg, String cpf, String telefone)
	{

		this.idEndereco = idEndereco;
		this.nome = nome;
		this.email = email;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = telefone;
	}




	public int getIdAluno()
	{
		return idAluno;
	}




	public void setIdAluno(int idAluno)
	{
		this.idAluno = idAluno;
	}




	public int getIdEndereco()
	{
		return idEndereco;
	}




	public void setIdEndereco(int idEndereco)
	{
		this.idEndereco = idEndereco;
	}




	public String getNome()
	{
		return nome;
	}




	public void setNome(String nome)
	{
		this.nome = nome;
	}




	public String getEmail()
	{
		return email;
	}




	public void setEmail(String email)
	{
		this.email = email;
	}




	public String getRg()
	{
		return rg;
	}




	public void setRg(String rg)
	{
		this.rg = rg;
	}




	public String getCpf()
	{
		return cpf;
	}




	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}




	public String getTelefone()
	{
		return telefone;
	}




	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}




	
}
