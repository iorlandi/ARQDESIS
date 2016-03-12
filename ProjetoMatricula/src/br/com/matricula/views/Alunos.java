package br.com.matricula.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.matricula.daos.AlunoDao;
import br.com.matricula.to.AlunoTO;
import br.com.matricula.to.EnderecoTO;
import br.com.matricula.util.AcessoBD;
import br.com.matricula.util.CryptoDummy;
import br.com.matricula.util.GravaTXT;
import br.com.matricula.util.Impressora;
import br.com.matricula.util.TelaUtil;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Alunos extends JDialog implements ActionListener
{
	private JTextField txt_Excluir_Codigo;
	private JTextField txt_Excluir_Nome;
	private JTextField txt_Excluir_Area;
	private JTextField txt_Excluir_Email;
	private JPanel pn_PesquisarExcluir;
	private JTextField txt_Excluir_Endereco;
	private JTextField txt_Excluir_Bairro;
	private JTextField txt_Editar_Ra;
	private JTextField txt_Editar_Nome;
	private JTextField txt_Editar_Email;
	private JTextField txt_Editar_Endereco;
	private JTextField txt_Editar_Bairro;
	private JPasswordField txt_Editar_Senha;
	private JTextField txt_Cadastrar_Nome;
	private JTextField txt_Cadastrar_Email;
	private JTextField txt_Cadastrar_Endereco;
	private JPasswordField pass_Cadastrar_Senha;
	private JTextField txt_Cadastrar_Bairro;
	private JFormattedTextField txt_Excluir_Cep;
	private JFormattedTextField txt_Editar_Cep;
	private JFormattedTextField txt_Cadastrar_Cep;
	private JFormattedTextField txt_Cadastrar_Cpf;
	private JFormattedTextField txt_Excluir_Cpf;
	private JFormattedTextField txt_Cadastrar_Rg;
	private JFormattedTextField txt_Excluir_Rg;
	private JFormattedTextField txt_Editar_Telefone;
	private JFormattedTextField txt_Cadastrar_Telefone;
	private JFormattedTextField txt_Excluir_Tel;
	private JFormattedTextField txt_Editar_Cpf;
	private JPasswordField txt_Excluir_Senha;
	private JFormattedTextField txt_Editar_Rg;
	private static ResourceBundle formatLanguage;
	final AlunoDao aluno = new AlunoDao();
	private JButton btn_Excluir_Excluir;
	private JButton btnCadastrar;
	private JButton btn_Editar_Consultar;
	private JButton btn_Editar_Alterar;
	private JCheckBox chckbx_validarSenha;

	/**
	 * Construtor da Classe Aluno
	 * 
	 * @param Language
	 */
	public Alunos(ResourceBundle Language)
	{

		formatLanguage = Language;
		// configurando ações da Janela
		TelaUtil.setaNimbus(this);
		setTitle(formatLanguage.getString("main.aluno"));
		setSize(684, 604);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// instanciando GridBagLayout e adicionando na tela
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 1.0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		// instanciando JTabbedPane Principal e adicionando na tela
		JTabbedPane tb_Principal = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tb_Principal = new GridBagConstraints();
		gbc_tb_Principal.gridheight = 0;
		gbc_tb_Principal.gridwidth = 2;
		gbc_tb_Principal.insets = new Insets(0, 0, 0, 5);
		gbc_tb_Principal.fill = GridBagConstraints.BOTH;
		gbc_tb_Principal.gridx = 0;
		gbc_tb_Principal.gridy = 0;
		getContentPane().add(tb_Principal, gbc_tb_Principal);

		// ----------------Iniciando Bloco de Aluno---------------------------//

		// instanciando JPanel Cadastrar e adicionando na tela
		JPanel pn_Cadastrar = new JPanel();
		pn_Cadastrar.setForeground(Color.WHITE);
		tb_Principal.addTab(formatLanguage.getString("main.cadastrar"), pn_Cadastrar);

		GridBagLayout gbl_pn_Cadastrar = new GridBagLayout();
		gbl_pn_Cadastrar.rowWeights = new double[] { 0.0, 0.0 };
		gbl_pn_Cadastrar.columnWeights = new double[] { 0.0 };
		gbl_pn_Cadastrar.columnWidths = new int[] { 346 };
		gbl_pn_Cadastrar.rowHeights = new int[] { 463, 0 };
		pn_Cadastrar.setLayout(gbl_pn_Cadastrar);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		pn_Cadastrar.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 61, 88, 210, 110, 211, 108, 0 };
		gbl_panel.rowHeights = new int[] { 60, 60, 60, 60, 60, 60, 87, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lbl_Cadastrar_Nome = new JLabel(formatLanguage.getString("curso.nome"));
		lbl_Cadastrar_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Nome = new GridBagConstraints();
		gbc_lbl_Cadastrar_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Cadastrar_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Nome.gridx = 1;
		gbc_lbl_Cadastrar_Nome.gridy = 0;
		panel.add(lbl_Cadastrar_Nome, gbc_lbl_Cadastrar_Nome);

		txt_Cadastrar_Nome = new JTextField();
		txt_Cadastrar_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Nome.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Nome = new GridBagConstraints();
		gbc_txt_Cadastrar_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Nome.gridwidth = 3;
		gbc_txt_Cadastrar_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Nome.gridx = 2;
		gbc_txt_Cadastrar_Nome.gridy = 0;
		panel.add(txt_Cadastrar_Nome, gbc_txt_Cadastrar_Nome);

		JLabel lbl_Cadastrar_Rg = new JLabel("RG");
		lbl_Cadastrar_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Rg = new GridBagConstraints();
		gbc_lbl_Cadastrar_Rg.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Cadastrar_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Rg.gridx = 1;
		gbc_lbl_Cadastrar_Rg.gridy = 1;
		panel.add(lbl_Cadastrar_Rg, gbc_lbl_Cadastrar_Rg);

		txt_Cadastrar_Rg = new JFormattedTextField(TelaUtil.formataRg());
		txt_Cadastrar_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Rg.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Rg = new GridBagConstraints();
		gbc_txt_Cadastrar_Rg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Rg.gridx = 2;
		gbc_txt_Cadastrar_Rg.gridy = 1;
		panel.add(txt_Cadastrar_Rg, gbc_txt_Cadastrar_Rg);

		JLabel lbl_Cadastrar_Cpf = new JLabel("CPF:");
		lbl_Cadastrar_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Cpf = new GridBagConstraints();
		gbc_lbl_Cadastrar_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Cpf.gridx = 3;
		gbc_lbl_Cadastrar_Cpf.gridy = 1;
		panel.add(lbl_Cadastrar_Cpf, gbc_lbl_Cadastrar_Cpf);

		txt_Cadastrar_Cpf = new JFormattedTextField(TelaUtil.formataCpf());
		txt_Cadastrar_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Cadastrar_Cpf = new GridBagConstraints();
		gbc_txt_Cadastrar_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Cpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Cpf.gridx = 4;
		gbc_txt_Cadastrar_Cpf.gridy = 1;
		panel.add(txt_Cadastrar_Cpf, gbc_txt_Cadastrar_Cpf);
		txt_Cadastrar_Cpf.setColumns(10);

		JLabel lbl_Cadastrar_Telefone = new JLabel(formatLanguage.getString("aluno.telefone"));
		lbl_Cadastrar_Telefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Telefone = new GridBagConstraints();
		gbc_lbl_Cadastrar_Telefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Cadastrar_Telefone.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Telefone.gridx = 1;
		gbc_lbl_Cadastrar_Telefone.gridy = 2;
		panel.add(lbl_Cadastrar_Telefone, gbc_lbl_Cadastrar_Telefone);

		txt_Cadastrar_Telefone = new JFormattedTextField(TelaUtil.formataTelefone());

		txt_Cadastrar_Telefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Telefone.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Telefone = new GridBagConstraints();
		gbc_txt_Cadastrar_Telefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Telefone.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Telefone.gridx = 2;
		gbc_txt_Cadastrar_Telefone.gridy = 2;
		panel.add(txt_Cadastrar_Telefone, gbc_txt_Cadastrar_Telefone);

		JLabel lbl_Cadastrar_Email = new JLabel("Email:");
		lbl_Cadastrar_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Email = new GridBagConstraints();
		gbc_lbl_Cadastrar_Email.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Email.gridx = 3;
		gbc_lbl_Cadastrar_Email.gridy = 2;
		panel.add(lbl_Cadastrar_Email, gbc_lbl_Cadastrar_Email);

		txt_Cadastrar_Email = new JTextField();
		txt_Cadastrar_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Email.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Email = new GridBagConstraints();
		gbc_txt_Cadastrar_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Email.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Email.gridx = 4;
		gbc_txt_Cadastrar_Email.gridy = 2;
		panel.add(txt_Cadastrar_Email, gbc_txt_Cadastrar_Email);

		JLabel lbl_Cadastrar_Endereco = new JLabel(formatLanguage.getString("aluno.endereco"));
		lbl_Cadastrar_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Endereco = new GridBagConstraints();
		gbc_lbl_Cadastrar_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Cadastrar_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Endereco.gridx = 1;
		gbc_lbl_Cadastrar_Endereco.gridy = 3;
		panel.add(lbl_Cadastrar_Endereco, gbc_lbl_Cadastrar_Endereco);

		txt_Cadastrar_Endereco = new JTextField();
		txt_Cadastrar_Endereco.setText("Logradouro");
		txt_Cadastrar_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Endereco.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Endereco = new GridBagConstraints();
		gbc_txt_Cadastrar_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Endereco.gridwidth = 3;
		gbc_txt_Cadastrar_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Endereco.gridx = 2;
		gbc_txt_Cadastrar_Endereco.gridy = 3;
		panel.add(txt_Cadastrar_Endereco, gbc_txt_Cadastrar_Endereco);

		JLabel lbl_Cadastrar_Cep = new JLabel("CEP:");
		lbl_Cadastrar_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Cep = new GridBagConstraints();
		gbc_lbl_Cadastrar_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Cadastrar_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Cep.gridx = 1;
		gbc_lbl_Cadastrar_Cep.gridy = 4;
		panel.add(lbl_Cadastrar_Cep, gbc_lbl_Cadastrar_Cep);

		txt_Cadastrar_Cep = new JFormattedTextField(TelaUtil.formataCep());
		txt_Cadastrar_Cep.setText("00644-400");
		txt_Cadastrar_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Cadastrar_Cep.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Cep = new GridBagConstraints();
		gbc_txt_Cadastrar_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Cep.gridx = 2;
		gbc_txt_Cadastrar_Cep.gridy = 4;
		panel.add(txt_Cadastrar_Cep, gbc_txt_Cadastrar_Cep);

		JLabel lbl_Cadastrar_Bairro = new JLabel(formatLanguage.getString("aluno.bairro"));
		lbl_Cadastrar_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Bairro = new GridBagConstraints();
		gbc_lbl_Cadastrar_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Bairro.gridx = 3;
		gbc_lbl_Cadastrar_Bairro.gridy = 4;
		panel.add(lbl_Cadastrar_Bairro, gbc_lbl_Cadastrar_Bairro);

		txt_Cadastrar_Bairro = new JTextField();
		txt_Cadastrar_Bairro.setText("Bairro de Teste");
		txt_Cadastrar_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Cadastrar_Bairro = new GridBagConstraints();
		gbc_txt_Cadastrar_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Bairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Bairro.gridx = 4;
		gbc_txt_Cadastrar_Bairro.gridy = 4;
		panel.add(txt_Cadastrar_Bairro, gbc_txt_Cadastrar_Bairro);
		txt_Cadastrar_Bairro.setColumns(10);

		JLabel lbl_Cadastrar_Senha = new JLabel(formatLanguage.getString("main.senha"));
		lbl_Cadastrar_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Cadastrar_Senha = new GridBagConstraints();
		gbc_lbl_Cadastrar_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Cadastrar_Senha.gridx = 1;
		gbc_lbl_Cadastrar_Senha.gridy = 5;
		panel.add(lbl_Cadastrar_Senha, gbc_lbl_Cadastrar_Senha);

		btnCadastrar = new JButton(formatLanguage.getString("main.cadastrar"));
		btnCadastrar.addActionListener(this);

		pass_Cadastrar_Senha = new JPasswordField();
		pass_Cadastrar_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		pass_Cadastrar_Senha.setColumns(10);
		GridBagConstraints gbc_txt_Cadastrar_Senha = new GridBagConstraints();
		gbc_txt_Cadastrar_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Cadastrar_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Cadastrar_Senha.gridx = 2;
		gbc_txt_Cadastrar_Senha.gridy = 5;
		panel.add(pass_Cadastrar_Senha, gbc_txt_Cadastrar_Senha);

		chckbx_validarSenha = new JCheckBox("Mostrar Senha");
		chckbx_validarSenha.addActionListener(this);
		chckbx_validarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbx_validarSenha = new GridBagConstraints();
		gbc_chckbx_validarSenha.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_validarSenha.gridx = 3;
		gbc_chckbx_validarSenha.gridy = 5;
		panel.add(chckbx_validarSenha, gbc_chckbx_validarSenha);

		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCadastrar.gridwidth = 7;
		gbc_btnCadastrar.gridx = 0;
		gbc_btnCadastrar.gridy = 6;
		panel.add(btnCadastrar, gbc_btnCadastrar);
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		// Aba Pesquisar/Excluir

		pn_PesquisarExcluir = new JPanel();
		pn_PesquisarExcluir.setForeground(Color.WHITE);
		tb_Principal.addTab(formatLanguage.getString("main.pesquisaExcluir"), pn_PesquisarExcluir);

		GridBagLayout gbl_pn_PesquisarExcluir = new GridBagLayout();
		gbl_pn_PesquisarExcluir.columnWidths = new int[] { 59, 66, 171, 0, 61, 140, 82, 0 };
		gbl_pn_PesquisarExcluir.rowHeights = new int[] { 76, 69, 70, 35, 116, 64, 64, 0, 0 };
		gbl_pn_PesquisarExcluir.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pn_PesquisarExcluir.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pn_PesquisarExcluir.setLayout(gbl_pn_PesquisarExcluir);

		pesquisarExcluir(pn_PesquisarExcluir);

		JLabel lbl_Excluir_Endereco = new JLabel(formatLanguage.getString("aluno.endereco"));
		lbl_Excluir_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Endereco.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Endereco = new GridBagConstraints();
		gbc_lbl_Excluir_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Endereco.gridx = 1;
		gbc_lbl_Excluir_Endereco.gridy = 4;
		pn_PesquisarExcluir.add(lbl_Excluir_Endereco, gbc_lbl_Excluir_Endereco);

		txt_Excluir_Endereco = new JTextField();
		txt_Excluir_Endereco.setToolTipText(formatLanguage.getString("aluno.endereco"));
		txt_Excluir_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Endereco.setEnabled(false);
		txt_Excluir_Endereco.setEditable(false);
		txt_Excluir_Endereco.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Endereco = new GridBagConstraints();
		gbc_txt_Excluir_Endereco.gridwidth = 4;
		gbc_txt_Excluir_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Endereco.gridx = 2;
		gbc_txt_Excluir_Endereco.gridy = 4;
		pn_PesquisarExcluir.add(txt_Excluir_Endereco, gbc_txt_Excluir_Endereco);

		JLabel lbl_Excluir_Area = new JLabel("Total de Vagas:");
		lbl_Excluir_Area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Area.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Area = new GridBagConstraints();
		gbc_lbl_Excluir_Area.anchor = GridBagConstraints.EAST;
		gbc_lbl_Excluir_Area.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Area.gridx = 3;
		gbc_lbl_Excluir_Area.gridy = 4;

		txt_Excluir_Area = new JTextField();
		txt_Excluir_Area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Area.setEnabled(false);
		txt_Excluir_Area.setEditable(false);
		txt_Excluir_Area.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Area = new GridBagConstraints();
		gbc_txt_Excluir_Area.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Area.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Area.gridx = 4;
		gbc_txt_Excluir_Area.gridy = 4;

		JLabel lbl_Excluir_Cep = new JLabel("CEP:");
		lbl_Excluir_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Cep.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Cep = new GridBagConstraints();
		gbc_lbl_Excluir_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Cep.gridx = 1;
		gbc_lbl_Excluir_Cep.gridy = 5;
		pn_PesquisarExcluir.add(lbl_Excluir_Cep, gbc_lbl_Excluir_Cep);

		txt_Excluir_Cep = new JFormattedTextField(TelaUtil.formataCep());
		txt_Excluir_Cep.setToolTipText("CEP");
		txt_Excluir_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Cep.setEnabled(false);
		txt_Excluir_Cep.setEditable(false);
		txt_Excluir_Cep.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Cep = new GridBagConstraints();
		gbc_txt_Excluir_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Cep.gridx = 2;
		gbc_txt_Excluir_Cep.gridy = 5;
		pn_PesquisarExcluir.add(txt_Excluir_Cep, gbc_txt_Excluir_Cep);

		JLabel lbl_Excluir_Bairro = new JLabel(formatLanguage.getString("aluno.bairro"));
		lbl_Excluir_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Bairro.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Bairro = new GridBagConstraints();
		gbc_lbl_Excluir_Bairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Bairro.gridx = 4;
		gbc_lbl_Excluir_Bairro.gridy = 5;
		pn_PesquisarExcluir.add(lbl_Excluir_Bairro, gbc_lbl_Excluir_Bairro);

		txt_Excluir_Bairro = new JTextField();
		txt_Excluir_Bairro.setToolTipText(formatLanguage.getString("aluno.bairro"));
		txt_Excluir_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Bairro.setEnabled(false);
		txt_Excluir_Bairro.setEditable(false);
		txt_Excluir_Bairro.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Bairro = new GridBagConstraints();
		gbc_txt_Excluir_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Bairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Bairro.gridx = 5;
		gbc_txt_Excluir_Bairro.gridy = 5;
		pn_PesquisarExcluir.add(txt_Excluir_Bairro, gbc_txt_Excluir_Bairro);

		JLabel lbl_Excluir_Senha = new JLabel(formatLanguage.getString("main.senha"));
		lbl_Excluir_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Excluir_Senha = new GridBagConstraints();
		gbc_lbl_Excluir_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Senha.anchor = GridBagConstraints.WEST;
		gbc_lbl_Excluir_Senha.gridx = 1;
		gbc_lbl_Excluir_Senha.gridy = 6;
		pn_PesquisarExcluir.add(lbl_Excluir_Senha, gbc_lbl_Excluir_Senha);

		txt_Excluir_Senha = new JPasswordField();
		txt_Excluir_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Senha.setColumns(10);
		txt_Excluir_Senha.setEnabled(false);
		GridBagConstraints gbc_txt_Excluir_Senha = new GridBagConstraints();
		gbc_txt_Excluir_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Senha.gridx = 2;
		gbc_txt_Excluir_Senha.gridy = 6;
		pn_PesquisarExcluir.add(txt_Excluir_Senha, gbc_txt_Excluir_Senha);

		btn_Excluir_Excluir = new JButton(formatLanguage.getString("main.excluir"));
		btn_Excluir_Excluir.setEnabled(false);
		btn_Excluir_Excluir.addActionListener(this);

		btn_Excluir_Excluir.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Excluir_Excluir = new GridBagConstraints();
		gbc_btn_Excluir_Excluir.fill = GridBagConstraints.BOTH;
		gbc_btn_Excluir_Excluir.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Excluir_Excluir.gridx = 3;
		gbc_btn_Excluir_Excluir.gridy = 7;
		pn_PesquisarExcluir.add(btn_Excluir_Excluir, gbc_btn_Excluir_Excluir);

		// Aba Editar-Alterar

		JPanel pn_Editar = new JPanel();
		pn_Editar.setForeground(Color.WHITE);
		tb_Principal.addTab(formatLanguage.getString("main.editar"), pn_Editar);
		GridBagLayout gbl_pn_Pesquisar = new GridBagLayout();
		gbl_pn_Pesquisar.columnWidths = new int[] { 346, 0 };
		gbl_pn_Pesquisar.rowHeights = new int[] { 463, 0, 0 };
		gbl_pn_Pesquisar.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pn_Pesquisar.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pn_Editar.setLayout(gbl_pn_Pesquisar);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		pn_Editar.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 45, 90, 140, 88, 216, 82, 0 };
		gbl_panel_1.rowHeights = new int[] { 31, 57, 60, 60, 60, 80, 0, 98, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lbl_Editar_Ra = new JLabel("RA:");
		lbl_Editar_Ra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Ra = new GridBagConstraints();
		gbc_lbl_Editar_Ra.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Ra.anchor = GridBagConstraints.NORTH;
		gbc_lbl_Editar_Ra.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Ra.gridx = 1;
		gbc_lbl_Editar_Ra.gridy = 1;
		panel_1.add(lbl_Editar_Ra, gbc_lbl_Editar_Ra);

		txt_Editar_Ra = new JTextField();
		txt_Editar_Ra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Editar_Ra.setColumns(10);
		GridBagConstraints gbc_txt_Editar_Ra = new GridBagConstraints();
		gbc_txt_Editar_Ra.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Ra.anchor = GridBagConstraints.NORTH;
		gbc_txt_Editar_Ra.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Ra.gridx = 2;
		gbc_txt_Editar_Ra.gridy = 1;
		panel_1.add(txt_Editar_Ra, gbc_txt_Editar_Ra);

		btn_Editar_Consultar = new JButton(formatLanguage.getString("curso.consultar"));
		btn_Editar_Consultar.addActionListener(this);
		btn_Editar_Consultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Editar_Consultar = new GridBagConstraints();
		gbc_btn_Editar_Consultar.gridwidth = 2;
		gbc_btn_Editar_Consultar.anchor = GridBagConstraints.NORTH;
		gbc_btn_Editar_Consultar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Editar_Consultar.gridx = 3;
		gbc_btn_Editar_Consultar.gridy = 1;
		panel_1.add(btn_Editar_Consultar, gbc_btn_Editar_Consultar);

		JLabel lbl_Editar_Nome = new JLabel(formatLanguage.getString("curso.nome"));
		lbl_Editar_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Nome = new GridBagConstraints();
		gbc_lbl_Editar_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Nome.gridx = 1;
		gbc_lbl_Editar_Nome.gridy = 2;
		panel_1.add(lbl_Editar_Nome, gbc_lbl_Editar_Nome);

		txt_Editar_Nome = new JTextField();
		txt_Editar_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Nome = new GridBagConstraints();
		gbc_txt_Editar_Nome.gridwidth = 3;
		gbc_txt_Editar_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Nome.gridx = 2;
		gbc_txt_Editar_Nome.gridy = 2;
		panel_1.add(txt_Editar_Nome, gbc_txt_Editar_Nome);
		txt_Editar_Nome.setColumns(10);

		JLabel lbl_Editar_Telefone = new JLabel(formatLanguage.getString("aluno.telefone"));
		lbl_Editar_Telefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Telefone = new GridBagConstraints();
		gbc_lbl_Editar_Telefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Telefone.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Telefone.gridx = 1;
		gbc_lbl_Editar_Telefone.gridy = 3;
		panel_1.add(lbl_Editar_Telefone, gbc_lbl_Editar_Telefone);

		txt_Editar_Telefone = new JFormattedTextField(TelaUtil.formataTelefone());
		txt_Editar_Telefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		GridBagConstraints gbc_txt_Editar_Telefone = new GridBagConstraints();
		gbc_txt_Editar_Telefone.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Telefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Telefone.gridx = 2;
		gbc_txt_Editar_Telefone.gridy = 3;
		panel_1.add(txt_Editar_Telefone, gbc_txt_Editar_Telefone);
		txt_Editar_Telefone.setColumns(10);

		JLabel lbl_Editar_Email = new JLabel("Email:");
		lbl_Editar_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Email = new GridBagConstraints();
		gbc_lbl_Editar_Email.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Email.anchor = GridBagConstraints.EAST;
		gbc_lbl_Editar_Email.gridx = 3;
		gbc_lbl_Editar_Email.gridy = 3;
		panel_1.add(lbl_Editar_Email, gbc_lbl_Editar_Email);

		txt_Editar_Email = new JTextField();
		txt_Editar_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Email = new GridBagConstraints();
		gbc_txt_Editar_Email.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Email.gridx = 4;
		gbc_txt_Editar_Email.gridy = 3;
		panel_1.add(txt_Editar_Email, gbc_txt_Editar_Email);
		txt_Editar_Email.setColumns(10);

		JLabel lbl_Editar_Endereco = new JLabel(formatLanguage.getString("aluno.endereco"));
		lbl_Editar_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Endereco = new GridBagConstraints();
		gbc_lbl_Editar_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Endereco.gridx = 1;
		gbc_lbl_Editar_Endereco.gridy = 4;
		panel_1.add(lbl_Editar_Endereco, gbc_lbl_Editar_Endereco);

		txt_Editar_Endereco = new JTextField();
		txt_Editar_Endereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Endereco = new GridBagConstraints();
		gbc_txt_Editar_Endereco.gridwidth = 3;
		gbc_txt_Editar_Endereco.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Endereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Endereco.gridx = 2;
		gbc_txt_Editar_Endereco.gridy = 4;
		panel_1.add(txt_Editar_Endereco, gbc_txt_Editar_Endereco);
		txt_Editar_Endereco.setColumns(10);

		JLabel lbl_Editar_Cep = new JLabel("CEP:");
		lbl_Editar_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Cep = new GridBagConstraints();
		gbc_lbl_Editar_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Cep.gridx = 1;
		gbc_lbl_Editar_Cep.gridy = 5;
		panel_1.add(lbl_Editar_Cep, gbc_lbl_Editar_Cep);

		txt_Editar_Cep = new JFormattedTextField(TelaUtil.formataCep());
		txt_Editar_Cep.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Cep = new GridBagConstraints();
		gbc_txt_Editar_Cep.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Cep.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Cep.gridx = 2;
		gbc_txt_Editar_Cep.gridy = 5;
		panel_1.add(txt_Editar_Cep, gbc_txt_Editar_Cep);
		txt_Editar_Cep.setColumns(10);

		JLabel lbl_Editar_Bairro = new JLabel(formatLanguage.getString("aluno.bairro"));
		lbl_Editar_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Bairro = new GridBagConstraints();
		gbc_lbl_Editar_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Bairro.anchor = GridBagConstraints.EAST;
		gbc_lbl_Editar_Bairro.gridx = 3;
		gbc_lbl_Editar_Bairro.gridy = 5;
		panel_1.add(lbl_Editar_Bairro, gbc_lbl_Editar_Bairro);

		txt_Editar_Bairro = new JTextField();
		txt_Editar_Bairro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Bairro = new GridBagConstraints();
		gbc_txt_Editar_Bairro.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Bairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Bairro.gridx = 4;
		gbc_txt_Editar_Bairro.gridy = 5;
		panel_1.add(txt_Editar_Bairro, gbc_txt_Editar_Bairro);
		txt_Editar_Bairro.setColumns(10);

		JLabel lbl_Editar_Cpf = new JLabel("CPF:");
		lbl_Editar_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Cpf = new GridBagConstraints();
		gbc_lbl_Editar_Cpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Cpf.gridx = 1;
		gbc_lbl_Editar_Cpf.gridy = 6;
		panel_1.add(lbl_Editar_Cpf, gbc_lbl_Editar_Cpf);

		txt_Editar_Cpf = new JFormattedTextField(TelaUtil.formataCpf());
		txt_Editar_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Editar_Cpf.setColumns(10);
		GridBagConstraints gbc_txt_Editar_Cpf = new GridBagConstraints();
		gbc_txt_Editar_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Cpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Cpf.gridx = 2;
		gbc_txt_Editar_Cpf.gridy = 6;
		panel_1.add(txt_Editar_Cpf, gbc_txt_Editar_Cpf);

		JLabel lbl_Editar_Rg = new JLabel("RG:");
		lbl_Editar_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Rg = new GridBagConstraints();
		gbc_lbl_Editar_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Editar_Rg.anchor = GridBagConstraints.EAST;
		gbc_lbl_Editar_Rg.gridx = 3;
		gbc_lbl_Editar_Rg.gridy = 6;
		panel_1.add(lbl_Editar_Rg, gbc_lbl_Editar_Rg);

		txt_Editar_Rg = new JFormattedTextField(TelaUtil.formataRg());
		txt_Editar_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Editar_Rg.setColumns(10);
		GridBagConstraints gbc_txt_Editar_Rg = new GridBagConstraints();
		gbc_txt_Editar_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Editar_Rg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Rg.gridx = 4;
		gbc_txt_Editar_Rg.gridy = 6;
		panel_1.add(txt_Editar_Rg, gbc_txt_Editar_Rg);

		JLabel lbl_Editar_Senha = new JLabel(formatLanguage.getString("main.senha"));
		lbl_Editar_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Editar_Senha = new GridBagConstraints();
		gbc_lbl_Editar_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Editar_Senha.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_Editar_Senha.gridx = 1;
		gbc_lbl_Editar_Senha.gridy = 7;
		panel_1.add(lbl_Editar_Senha, gbc_lbl_Editar_Senha);

		txt_Editar_Senha = new JPasswordField();
		txt_Editar_Senha.setEnabled(false);
		txt_Editar_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Editar_Senha = new GridBagConstraints();
		gbc_txt_Editar_Senha.insets = new Insets(0, 0, 0, 5);
		gbc_txt_Editar_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Editar_Senha.gridx = 2;
		gbc_txt_Editar_Senha.gridy = 7;
		panel_1.add(txt_Editar_Senha, gbc_txt_Editar_Senha);
		txt_Editar_Senha.setColumns(10);

		btn_Editar_Alterar = new JButton(formatLanguage.getString("main.atualizar"));
		btn_Editar_Alterar.setEnabled(false);
		btn_Editar_Alterar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Connection conn = null;
				if (txt_Editar_Endereco.getText().equals("") || txt_Editar_Bairro.getText().equals("")
						|| txt_Editar_Cep.getText().equals("") || txt_Editar_Nome.getText().equals("")
						|| txt_Editar_Rg.getText().equals("") || txt_Editar_Cpf.getText().equals("")
						|| txt_Editar_Telefone.getText().equals("") || txt_Editar_Email.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Insira todos os campos!");

				} else
				{
					try
					{
						if (txt_Editar_Senha.getText().equals("123"))
						{

							AcessoBD acessoBD = new AcessoBD();
							conn = acessoBD.obtemConexao();
							boolean alterarAluno = aluno.alterarAluno(Integer.parseInt(txt_Editar_Ra.getText()),
									txt_Editar_Nome.getText(), txt_Editar_Rg.getText(), txt_Editar_Cpf.getText(),
									txt_Editar_Telefone.getText(), conn);

							boolean alterarEnd = aluno.alterarEndereco(txt_Editar_Endereco.getText(),
									txt_Editar_Cep.getText(), txt_Editar_Bairro.getText(), conn);

							if (alterarAluno && alterarEnd)
							{
								JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso");
								dispose();
							}

						}
					} catch (Exception e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Aluno não alterado!");
						dispose();
					}
				}
			}

		});
		btn_Editar_Alterar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		GridBagConstraints gbc_btn_Editar_Alterar = new GridBagConstraints();
		gbc_btn_Editar_Alterar.gridwidth = 2;
		gbc_btn_Editar_Alterar.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Editar_Alterar.gridx = 3;
		gbc_btn_Editar_Alterar.gridy = 7;
		panel_1.add(btn_Editar_Alterar, gbc_btn_Editar_Alterar);

	}

	public void pesquisarExcluir(JPanel pn_Excluir)
	{
		JLabel lbl_Excuir_RA = new JLabel("RA:");
		lbl_Excuir_RA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Excuir_RA = new GridBagConstraints();
		gbc_lbl_Excuir_RA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excuir_RA.anchor = GridBagConstraints.SOUTH;
		gbc_lbl_Excuir_RA.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excuir_RA.gridx = 1;
		gbc_lbl_Excuir_RA.gridy = 0;
		pn_Excluir.add(lbl_Excuir_RA, gbc_lbl_Excuir_RA);

		txt_Excluir_Codigo = new JTextField();
		txt_Excluir_Codigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Excluir_Codigo = new GridBagConstraints();
		gbc_txt_Excluir_Codigo.anchor = GridBagConstraints.SOUTH;
		gbc_txt_Excluir_Codigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Codigo.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Codigo.gridx = 2;
		gbc_txt_Excluir_Codigo.gridy = 0;
		pn_Excluir.add(txt_Excluir_Codigo, gbc_txt_Excluir_Codigo);
		txt_Excluir_Codigo.setColumns(10);

		JButton btn_Excluir_Codigo = new JButton(formatLanguage.getString("curso.consultar"));
		btn_Excluir_Codigo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Connection connection = null;
				try
				{
					AcessoBD acessoBD = new AcessoBD();
					connection = acessoBD.obtemConexao();
					ArrayList<String> array = aluno.ConsultaAluno(connection,
							Integer.parseInt(txt_Excluir_Codigo.getText()));

					System.out.println(array);
					txt_Excluir_Nome.setText(array.get(0).toString());
					txt_Excluir_Email.setText(array.get(1).toString());
					txt_Excluir_Rg.setText(array.get(2).toString());
					txt_Excluir_Cpf.setText(array.get(3).toString());
					txt_Excluir_Tel.setText(array.get(4).toString());
					txt_Excluir_Endereco.setText(array.get(5).toString());
					txt_Excluir_Cep.setText(array.get(6).toString());
					txt_Excluir_Bairro.setText(array.get(7).toString());

					txt_Excluir_Senha.setEnabled(true);
					btn_Excluir_Excluir.setEnabled(true);
				} catch (Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado");
					dispose();
				}
			}
		});
		btn_Excluir_Codigo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Excluir_Codigo = new GridBagConstraints();
		gbc_btn_Excluir_Codigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_Excluir_Codigo.anchor = GridBagConstraints.SOUTH;
		gbc_btn_Excluir_Codigo.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Excluir_Codigo.gridx = 3;
		gbc_btn_Excluir_Codigo.gridy = 0;
		pn_Excluir.add(btn_Excluir_Codigo, gbc_btn_Excluir_Codigo);

		JLabel lbl_Excluir_Nome = new JLabel(formatLanguage.getString("curso.nome"));
		lbl_Excluir_Nome.setEnabled(false);
		lbl_Excluir_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Excluir_Nome = new GridBagConstraints();
		gbc_lbl_Excluir_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Nome.anchor = GridBagConstraints.SOUTH;
		gbc_lbl_Excluir_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Nome.gridx = 1;
		gbc_lbl_Excluir_Nome.gridy = 1;
		pn_Excluir.add(lbl_Excluir_Nome, gbc_lbl_Excluir_Nome);

		txt_Excluir_Nome = new JTextField();
		txt_Excluir_Nome.setEnabled(false);
		txt_Excluir_Nome.setEditable(false);
		txt_Excluir_Nome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Nome.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Nome = new GridBagConstraints();
		gbc_txt_Excluir_Nome.anchor = GridBagConstraints.SOUTH;
		gbc_txt_Excluir_Nome.gridwidth = 4;
		gbc_txt_Excluir_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Nome.gridx = 2;
		gbc_txt_Excluir_Nome.gridy = 1;
		pn_Excluir.add(txt_Excluir_Nome, gbc_txt_Excluir_Nome);

		JLabel lbl_Excluir_Rg = new JLabel("RG:");
		lbl_Excluir_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Rg.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Rg = new GridBagConstraints();
		gbc_lbl_Excluir_Rg.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Rg.gridx = 1;
		gbc_lbl_Excluir_Rg.gridy = 2;
		pn_Excluir.add(lbl_Excluir_Rg, gbc_lbl_Excluir_Rg);

		txt_Excluir_Rg = new JFormattedTextField(TelaUtil.formataRg());
		txt_Excluir_Rg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Rg.setEnabled(false);
		txt_Excluir_Rg.setEditable(false);
		txt_Excluir_Rg.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Rg = new GridBagConstraints();
		gbc_txt_Excluir_Rg.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Rg.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Rg.gridx = 2;
		gbc_txt_Excluir_Rg.gridy = 2;
		pn_Excluir.add(txt_Excluir_Rg, gbc_txt_Excluir_Rg);

		JLabel lbl_Excluir_Cpf = new JLabel("CPF:");
		lbl_Excluir_Cpf.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Excluir_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Cpf.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Cpf = new GridBagConstraints();
		gbc_lbl_Excluir_Cpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Cpf.gridx = 4;
		gbc_lbl_Excluir_Cpf.gridy = 2;
		pn_PesquisarExcluir.add(lbl_Excluir_Cpf, gbc_lbl_Excluir_Cpf);

		txt_Excluir_Cpf = new JFormattedTextField(TelaUtil.formataCpf());
		txt_Excluir_Cpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Cpf.setEnabled(false);
		txt_Excluir_Cpf.setEditable(false);
		txt_Excluir_Cpf.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Cpf = new GridBagConstraints();
		gbc_txt_Excluir_Cpf.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Cpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Cpf.gridx = 5;
		gbc_txt_Excluir_Cpf.gridy = 2;
		pn_PesquisarExcluir.add(txt_Excluir_Cpf, gbc_txt_Excluir_Cpf);

		JLabel lbl_Excluir_Tel = new JLabel(formatLanguage.getString("aluno.telefone"));
		lbl_Excluir_Tel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Tel.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Tel = new GridBagConstraints();
		gbc_lbl_Excluir_Tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Tel.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Tel.gridx = 1;
		gbc_lbl_Excluir_Tel.gridy = 3;
		pn_Excluir.add(lbl_Excluir_Tel, gbc_lbl_Excluir_Tel);

		txt_Excluir_Tel = new JFormattedTextField(TelaUtil.formataTelefone());
		txt_Excluir_Tel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Tel.setEnabled(false);
		txt_Excluir_Tel.setEditable(false);
		txt_Excluir_Tel.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Tel = new GridBagConstraints();
		gbc_txt_Excluir_Tel.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Tel.gridx = 2;
		gbc_txt_Excluir_Tel.gridy = 3;
		pn_Excluir.add(txt_Excluir_Tel, gbc_txt_Excluir_Tel);

		JLabel lbl_Excluir_Email = new JLabel("Email:");
		lbl_Excluir_Email.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Excluir_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Excluir_Email.setEnabled(false);
		GridBagConstraints gbc_lbl_Excluir_Email = new GridBagConstraints();
		gbc_lbl_Excluir_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Excluir_Email.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Excluir_Email.gridx = 4;
		gbc_lbl_Excluir_Email.gridy = 3;
		pn_Excluir.add(lbl_Excluir_Email, gbc_lbl_Excluir_Email);

		txt_Excluir_Email = new JTextField();
		txt_Excluir_Email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_Excluir_Email.setEnabled(false);
		txt_Excluir_Email.setEditable(false);
		txt_Excluir_Email.setColumns(10);
		GridBagConstraints gbc_txt_Excluir_Email = new GridBagConstraints();
		gbc_txt_Excluir_Email.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Excluir_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Excluir_Email.gridx = 5;
		gbc_txt_Excluir_Email.gridy = 3;
		pn_Excluir.add(txt_Excluir_Email, gbc_txt_Excluir_Email);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{

		if (event.getSource() == btnCadastrar)
		{
			boolean flagOk = false;
			EnderecoTO enderecoTO = new EnderecoTO();
			if (txt_Cadastrar_Endereco.getText().equals("") || txt_Cadastrar_Bairro.getText().equals("")
					|| txt_Cadastrar_Cep.getText().equals("") || txt_Cadastrar_Nome.getText().equals("")
					|| txt_Cadastrar_Rg.getText().equals("") || txt_Cadastrar_Cpf.getText().equals("")
					|| txt_Cadastrar_Telefone.getText().equals("") || txt_Cadastrar_Email.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Insira todos os campos!");

			} else
			{

				enderecoTO.setLogradouro(txt_Cadastrar_Endereco.getText());
				enderecoTO.setBairro(txt_Cadastrar_Bairro.getText());
				enderecoTO.setCep(txt_Cadastrar_Cep.getText());

				AlunoTO alunoTO = new AlunoTO();

				alunoTO.setNome(txt_Cadastrar_Nome.getText());
				alunoTO.setRg(txt_Cadastrar_Rg.getText());
				alunoTO.setCpf(txt_Cadastrar_Cpf.getText());
				alunoTO.setTelefone(txt_Cadastrar_Telefone.getText());
				alunoTO.setEmail(txt_Cadastrar_Email.getText());
				Connection connection = null;
				try
				{
					AcessoBD acessoBD = new AcessoBD();
					connection = acessoBD.obtemConexao();
					flagOk = AlunoDao.incluirAluno(connection, alunoTO, enderecoTO);
					System.out.println(flagOk);
					if (flagOk)
					{
						if (!pass_Cadastrar_Senha.getText().equals("") && !txt_Cadastrar_Nome.getText().equals(""))
						{
							GravaTXT gravaTXT = new GravaTXT();
							gravaTXT.gravarNovoTxt(pass_Cadastrar_Senha.getText(), txt_Cadastrar_Nome.getText(),
									"Aluno");
							try
							{
								leAcessoCriptografado(gravaTXT);
							} catch (Exception e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("Erro");
				}
			}

		}
		if (event.getSource() == btn_Editar_Consultar)
		{
			Connection connection = null;
			try
			{
				AcessoBD acessoBD = new AcessoBD();
				connection = acessoBD.obtemConexao();
				ArrayList<String> array = aluno.ConsultaAluno(connection, Integer.parseInt(txt_Editar_Ra.getText()));

				System.out.println(array);
				txt_Editar_Nome.setText(array.get(0).toString());
				txt_Editar_Email.setText(array.get(1).toString());
				txt_Editar_Rg.setText(array.get(2).toString());
				txt_Editar_Cpf.setText(array.get(3).toString());
				txt_Editar_Telefone.setText(array.get(4).toString());
				txt_Editar_Endereco.setText(array.get(5).toString());
				txt_Editar_Cep.setText(array.get(6).toString());
				txt_Editar_Bairro.setText(array.get(7).toString());

				btn_Editar_Alterar.setEnabled(true);
				txt_Editar_Senha.setEnabled(true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		if (event.getSource() == btn_Excluir_Excluir)
		{
			Connection conn = null;
			boolean flagExcluir = false;
			EnderecoTO enderecoTO = new EnderecoTO();

			enderecoTO.setLogradouro(txt_Excluir_Endereco.getText());
			enderecoTO.setBairro(txt_Excluir_Bairro.getText());
			enderecoTO.setCep(txt_Excluir_Cep.getText());

			AlunoTO alunoTO = new AlunoTO();

			alunoTO.setNome(txt_Excluir_Nome.getText());
			alunoTO.setRg(txt_Excluir_Rg.getText());
			alunoTO.setCpf(txt_Excluir_Cpf.getText());
			alunoTO.setTelefone(txt_Excluir_Tel.getText());
			alunoTO.setEmail(txt_Excluir_Email.getText());

			try
			{

				if (txt_Excluir_Senha.getText().equals("123"))
				{

					AcessoBD acessoBD = new AcessoBD();
					conn = acessoBD.obtemConexao();
					flagExcluir = AlunoDao.deletarAluno(Integer.parseInt(txt_Excluir_Codigo.getText()), conn);

					if (flagExcluir)
					{
						JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso");
						dispose();
					} else
					{
						JOptionPane.showMessageDialog(null,
								"Problema com a exclusão do aluno " + txt_Excluir_Nome.getText());

					}

				} else
				{
					JOptionPane.showMessageDialog(null, "Senha invalida");
				}

			} catch (Exception e)
			{

			}

		}
		if (event.getSource() == chckbx_validarSenha)
		{
			if (chckbx_validarSenha.isSelected())
			{
				pass_Cadastrar_Senha.setEchoChar('\u0000');
				
			}
			else
			{
				pass_Cadastrar_Senha.setEchoChar('*');
			}	
		}

	}

	private void leAcessoCriptografado(GravaTXT gravaTXT)
			throws UnsupportedEncodingException, IOException, ClassNotFoundException, Exception
	{
		String sMsgClara = GravaTXT.lerTC();
		String sMsgDecifrada = null;
		byte[] bMsgClara = null;
		byte[] bMsgDecifrada = null;
		byte[] bMsgCifrada = null;
		// Instancia objeto da classe Impressora
		Impressora prn = new Impressora();

		// Converte o texto String dado no equivalente byte[]
		bMsgClara = sMsgClara.getBytes("ISO-8859-1");
		// Imprime cabecalho da mensagem
		System.out.println("Mensagem Clara (Hexadecimal):");
		// Imprime o texto original em Hexadecimal
		System.out.print(prn.hexBytesToString(bMsgClara));

		/*
		 * Criptografia Dummy
		 * ------------------------------------------------------------
		 */
		// Imprime Texto
		System.out.println(">>> Cifrando com o algoritmo Dummy...");
		System.out.println("");
		// Instancia um objeto da classe CryptoDummy
		CryptoDummy cdummy = new CryptoDummy();
		System.out.println(">>> Decifrando com o algoritmo Dummy...");
		System.out.println("");
		bMsgCifrada = cifraMensagem(gravaTXT, bMsgClara, prn, cdummy);
		// Gera a decifra Dummy da mensagem dada, segundo a chave Dummy
		// simetrica gerada
		cdummy.geraDecifra(bMsgCifrada, new File("chave1.dummy"));
		// recebe o texto decifrado
		bMsgDecifrada = cdummy.getTextoDecifrado();
		// Converte o texto byte[] no equivalente String
		sMsgDecifrada = (new String(bMsgDecifrada, "ISO-8859-1"));
		// Imprime cabeÃ§alho da mensagem

		// Imprime texto

		System.out.println("Mensagem Decifrada (Hexadecimal):");
		// Imprime o texto decifrado em Hexadecimal
		System.out.print(prn.hexBytesToString(bMsgDecifrada));
		System.out.println();
		// Imprime cabecalho da mensagem
		System.out.println("Mensagem Decifrada (String):");
		// Imprime o texto decifrado em String
		System.out.println(sMsgDecifrada);

		JOptionPane.showMessageDialog(null, "Usuário criado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}

	/** Cifra Mensagem **/
	private byte[] cifraMensagem(GravaTXT gravaTXT, byte[] bMsgClara, Impressora prn, CryptoDummy cdummy)
			throws Exception
	{
		String sMsgCifrada;
		byte[] bMsgCifrada;
		// Gera a chave criptografica Dummy simetrica e nome do arquivo onde
		// sera armazenada
		cdummy.geraChave(new File("chave1.dummy"));
		// Gera a cifra Dummy da mensagem dada, com a chave Dummy simetrica dada
		cdummy.geraCifra(bMsgClara, new File("chave1.dummy"));
		// Recebe o texto cifrado

		bMsgCifrada = cdummy.getTextoCifrado();

		// Converte o texto byte[] no equivalente String
		sMsgCifrada = (new String(bMsgCifrada, "ISO-8859-1"));
		// Imprime cabecalho da mensagem
		System.out.println("Mensagem Cifrada (Hexadecimal):");
		// Imprime o texto cifrado em Hexadecimal
		System.out.print(prn.hexBytesToString(bMsgCifrada));
		System.out.println("");

		// Grava TXT Mensagem Cifrada
		String gravar_Dummy = "Cifrado Dummy";

		gravaTXT.gravarTCI(gravar_Dummy);
		gravaTXT.gravarTCI(prn.hexBytesToString(bMsgCifrada));

		return bMsgCifrada;

	}
}
