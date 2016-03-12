package br.com.matricula.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.matricula.daos.CursoDao;
import br.com.matricula.daos.MatriculaDao;
import br.com.matricula.util.AcessoBD;
import br.com.matricula.util.TelaUtil;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class TelaPrincipal extends JDialog implements ActionListener
{
	private JButton btn_Curso;
	private JButton btn_Matricula;
	private JButton btn_Aluno;
	private JButton btn_Relatrio;
	private static ResourceBundle formatLanguage;
	private JPanel pn_Curso;
	private GridBagConstraints gbc_pn_Curso;
	private JPanel pn_Matricula;
	private GridBagConstraints gbc_pn_Matricula;
	private String tipoUsuario = "";
	private String nomeUsuario = "";

	/**
	 * Contrutor da Classe TelaPrincipal
	 * 
	 * @param Language
	 * @param p_NomeAcesso
	 * @param p_Tipo
	 */
	public TelaPrincipal(ResourceBundle Language, String p_NomeAcesso, String p_Tipo)
	{
		tipoUsuario = p_Tipo;
		nomeUsuario = p_NomeAcesso;
		formatLanguage = Language;
		// configurando ações da Janela
		getContentPane().setBackground(Color.WHITE);
		setTitle(formatLanguage.getString("main.title"));
		setSize(538, 330);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// instanciando GridBagLayout e adcionando na tela
		GridBagLayout containerGrid = new GridBagLayout();
		containerGrid.columnWidths = new int[] { 30, 120, 120, 120, 120, 30 };
		containerGrid.rowHeights = new int[] { 53, 41, 110, 66 };
		getContentPane().setLayout(containerGrid);

		// instanciando JLabel de Saudação e adcionando na tela
		JLabel lbl_Saudacao = new JLabel(formatLanguage.getString("main.lbl_Saudacao") + p_NomeAcesso);
		lbl_Saudacao.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Saudacao.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GridBagConstraints gbc_lbl_Saudacao = new GridBagConstraints();
		gbc_lbl_Saudacao.anchor = GridBagConstraints.SOUTH;
		gbc_lbl_Saudacao.gridwidth = 4;
		gbc_lbl_Saudacao.insets = new Insets(0, 10, 5, 5); // Insets(top,left,bottom,right)
		gbc_lbl_Saudacao.gridx = 1;
		gbc_lbl_Saudacao.gridy = 0;
		getContentPane().add(lbl_Saudacao, gbc_lbl_Saudacao);
		// instanciando JLabel de Ação e adcionando na tela
		JLabel lbl_acao = new JLabel(formatLanguage.getString("main.lbl_acao"));
		lbl_acao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		GridBagConstraints gbc_lbl_acao = new GridBagConstraints();
		gbc_lbl_acao.gridwidth = 2;
		gbc_lbl_acao.anchor = GridBagConstraints.NORTHEAST;
		gbc_lbl_acao.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_acao.gridx = 2;
		gbc_lbl_acao.gridy = 1;
		getContentPane().add(lbl_acao, gbc_lbl_acao);

		pn_Curso = new JPanel();
		gbc_pn_Curso = new GridBagConstraints();

		pn_Matricula = new JPanel();
		gbc_pn_Matricula = new GridBagConstraints();
		if (p_Tipo.equalsIgnoreCase("adm"))
		{

			gbc_pn_Curso.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Curso.fill = GridBagConstraints.BOTH;
			gbc_pn_Curso.gridx = 1;
			gbc_pn_Curso.gridy = 2;
			getContentPane().add(pn_Curso, gbc_pn_Curso);
			pn_Curso.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de CursoTO e adcionando ao JPanel de
			// CursoTO
			btn_Curso = new JButton(formatLanguage.getString("main.curso"));
			btn_Curso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Curso.add(btn_Curso, BorderLayout.CENTER);
			btn_Curso.addActionListener(this);

			// instanciando JPanel de Matricula e adcionando no Grid
			gbc_pn_Matricula.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Matricula.fill = GridBagConstraints.BOTH;
			gbc_pn_Matricula.gridx = 2;
			gbc_pn_Matricula.gridy = 2;
			getContentPane().add(pn_Matricula, gbc_pn_Matricula);
			pn_Matricula.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de Matricula e adcionando ao JPanel de
			// Matricula
			btn_Matricula = new JButton(formatLanguage.getString("main.matricula"));
			btn_Matricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Matricula.add(btn_Matricula, BorderLayout.CENTER);
			btn_Matricula.addActionListener(this);

			// instanciando JPanel de Aluno e adcionando no Grid
			JPanel pn_Aluno = new JPanel();
			GridBagConstraints gbc_pn_Aluno = new GridBagConstraints();
			gbc_pn_Aluno.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Aluno.fill = GridBagConstraints.BOTH;
			gbc_pn_Aluno.gridx = 3;
			gbc_pn_Aluno.gridy = 2;
			getContentPane().add(pn_Aluno, gbc_pn_Aluno);
			pn_Aluno.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de Aluno e adcionando ao JPanel de Aluno
			btn_Aluno = new JButton(formatLanguage.getString("main.aluno"));
			btn_Aluno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Aluno.add(btn_Aluno, BorderLayout.CENTER);
			btn_Aluno.addActionListener(this);

			// instanciando JPanel de Relatório e adcionando no Grid
			JPanel pn_Relatorio = new JPanel();
			GridBagConstraints gbc_pn_Relatorio = new GridBagConstraints();
			gbc_pn_Relatorio.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Relatorio.fill = GridBagConstraints.BOTH;
			gbc_pn_Relatorio.gridx = 4;
			gbc_pn_Relatorio.gridy = 2;
			getContentPane().add(pn_Relatorio, gbc_pn_Relatorio);
			pn_Relatorio.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de Relatório e adcionando ao JPanel de
			// Relatório
			btn_Relatrio = new JButton(formatLanguage.getString("main.relatorio"));
			btn_Relatrio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Relatorio.add(btn_Relatrio, BorderLayout.CENTER);
			btn_Relatrio.addActionListener(this);

		} else if (p_Tipo.equalsIgnoreCase("aluno"))
		{

			// instanciando JPanel de CursoTO e adcionando no Grid
			gbc_pn_Curso.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Curso.fill = GridBagConstraints.BOTH;
			gbc_pn_Curso.gridx = 2;
			gbc_pn_Curso.gridy = 2;
			getContentPane().add(pn_Curso, gbc_pn_Curso);
			pn_Curso.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de CursoTO e adcionando ao JPanel de
			// CursoTO
			btn_Curso = new JButton(formatLanguage.getString("main.curso"));
			btn_Curso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Curso.add(btn_Curso, BorderLayout.CENTER);
			btn_Curso.addActionListener(this);

			gbc_pn_Matricula.insets = new Insets(0, 0, 5, 5);
			gbc_pn_Matricula.fill = GridBagConstraints.BOTH;
			gbc_pn_Matricula.gridx = 3;
			gbc_pn_Matricula.gridy = 2;
			getContentPane().add(pn_Matricula, gbc_pn_Matricula);
			pn_Matricula.setLayout(new BorderLayout(0, 0));

			// instanciando JButton de Matricula e adcionando ao JPanel de
			// Matricula
			btn_Matricula = new JButton(formatLanguage.getString("main.matricula"));
			btn_Matricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			pn_Matricula.add(btn_Matricula, BorderLayout.CENTER);
			btn_Matricula.addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btn_Relatrio)
		{
			Relatorio r = new Relatorio(formatLanguage);
			r.setVisible(true);
		}
		if (e.getSource() == btn_Curso)
		{
			if (tipoUsuario.equals("Aluno"))
			{
				Connection connection = null;

				AcessoBD acessoBD = new AcessoBD();
				try
				{
					connection = acessoBD.obtemConexao();

					TelaUtil.gerarRelatorio(CursoDao.retornaResulSetCurso(connection), "relatorios/curso.jasper");
				} catch (SQLException e1)
				{

					e1.printStackTrace();
				}
			} else
			{

				Curso c = new Curso(formatLanguage, tipoUsuario);
				c.setVisible(true);
			}
		}
		if (e.getSource() == btn_Matricula)
		{
			if (tipoUsuario.equals("Aluno"))
			{
				Connection connection = null;

				AcessoBD acessoBD = new AcessoBD();
				try
				{
					connection = acessoBD.obtemConexao();

					TelaUtil.gerarRelatorio(MatriculaDao.retornaRelatorioPorAluno(connection, nomeUsuario), "relatorios/matricula_aluno.jasper");
				} catch (SQLException e1)
				{

					e1.printStackTrace();
				}
			} else
			{

				Matricula m = new Matricula(formatLanguage, tipoUsuario, nomeUsuario);
				m.setVisible(true);
			}
		}
		if (e.getSource() == btn_Aluno)
		{
			Alunos a = new Alunos(formatLanguage);
			a.setVisible(true);
		}
	}
}
