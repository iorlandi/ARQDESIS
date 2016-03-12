package br.com.matricula.to;

public class EnderecoTO
{
	private int idEndereco;
	private String logradouro;
	private String cep;
	private String bairro;
	
	public EnderecoTO()
	{
		
	}
	
		
	public EnderecoTO(int idEndereco, String logradouro, String cep, String bairro)
	{
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.cep = cep;
		this.bairro = bairro;
	}
	public int getIdEndereco()
	{
		return idEndereco;
	}
	public void setIdEndereco(int idEndereco)
	{
		this.idEndereco = idEndereco;
	}
	public String getLogradouro()
	{
		return logradouro;
	}
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}
	public String getCep()
	{
		return cep;
	}
	public void setCep(String cep)
	{
		this.cep = cep;
	}
	public String getBairro()
	{
		return bairro;
	}
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}
	
}
