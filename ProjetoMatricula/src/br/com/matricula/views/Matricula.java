package br.com.matricula.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import br.com.matricula.daos.CursoDao;
import br.com.matricula.daos.MatriculaDao;
import br.com.matricula.to.CursoTO;
import br.com.matricula.to.MatriculaTO;
import br.com.matricula.util.AcessoBD;
import br.com.matricula.util.TelaUtil;

public class Matricula extends JDialog implements MouseListener
{

	private static final Logger LOGGER = Logger.getLogger(Matricula.class);
	private JTextField txtValorMatricula;
	private JFormattedTextField txtDataMatricula;
	private JTable tbCursosDisponiveis;
	private JTextField txtRA;
	private JTextField txtCodMatricula;
	private JTextField txtStatusDeMatricula;
	private JTextField txtStatusDePagamento;
	private JTabbedPane tb_Principal;
	private JFormattedTextField txtDataDeMatricula;
	private JPasswordField passFieldStatusSenha;
	private static ResourceBundle formatLanguage;
	private JTable table_Curso;
	private JTable table_Matriculados;
	private ArrayList<Object> listCursos;
	private ArrayList<MatriculaTO> listMatriculas;
	private float valorTotal;
	private JButton btnCancelarMatricula;
	private JButton btnCadastrar;
	private JTable table_CursosCancelar;

	/**
	 * Contrutor da Classe Matricula
	 * 
	 * @param Language
	 * @param p_TipoUsuario
	 * @param p_NomeUsuario
	 */
	public Matricula(ResourceBundle Language, String p_TipoUsuario, String p_NomeUsuario)
	{
		// configurando ações da Janela
		LOGGER.info("Iniciando tela de Matricula com usuario " + p_NomeUsuario);
		formatLanguage = Language;
		TelaUtil.setaNimbus(this);
		setTitle(formatLanguage.getString("main.matricula"));
		setSize(684, 604);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// instanciando GridBagLayout e adcionando na tela
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 1.0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		// ------------------TABEDD PRINCIPAL------------------------
		// ----------------------------------------------------------

		// instanciando JTabbedPane Principal e adcionando na tela
		tb_Principal = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tb_Principal = new GridBagConstraints();
		gbc_tb_Principal.gridheight = 0;
		gbc_tb_Principal.gridwidth = 2;
		gbc_tb_Principal.insets = new Insets(0, 0, 0, 5);
		gbc_tb_Principal.fill = GridBagConstraints.BOTH;
		gbc_tb_Principal.gridx = 0;
		gbc_tb_Principal.gridy = 0;
		getContentPane().add(tb_Principal, gbc_tb_Principal);

		// ------------------ADICIONAR PANES NO TABBED
		// PRINCIPAL------------------------
		// ----------------------------------------------------------
		TabRelatorio(tb_Principal);
		TabCancelar(tb_Principal);

	}

	public void TabCancelar(JTabbedPane tb_Cancelar)
	{
		JPanel pn_Cancelar = new JPanel();
		tb_Cancelar.addTab(formatLanguage.getString("matricula.pn_Cancelar"), null, pn_Cancelar, null);
		GridBagLayout gbl_pn_Cancelar = new GridBagLayout();
		gbl_pn_Cancelar.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_pn_Cancelar.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_pn_Cancelar.columnWidths = new int[] { 39, 95, 133, 37, 46, 72, 147, 39 };
		gbl_pn_Cancelar.rowHeights = new int[] { 116, 63, 81, 52, 50, 37, 45, 45 };
		pn_Cancelar.setLayout(gbl_pn_Cancelar);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		pn_Cancelar.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblCodigoDaMatricula = new JLabel(formatLanguage.getString("matricula.lblCodigoDaMatricula"));
		panel.add(lblCodigoDaMatricula, BorderLayout.CENTER);
		lblCodigoDaMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		txtCodMatricula = new JTextField();
		txtCodMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtCodMatricula = new GridBagConstraints();
		gbc_txtCodMatricula.gridwidth = 2;
		gbc_txtCodMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_txtCodMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodMatricula.gridx = 2;
		gbc_txtCodMatricula.gridy = 0;
		pn_Cancelar.add(txtCodMatricula, gbc_txtCodMatricula);
		txtCodMatricula.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		pn_Cancelar.add(scrollPane, gbc_scrollPane);

		table_CursosCancelar = new JTable();
		table_CursosCancelar.setColumnSelectionAllowed(true);
		table_CursosCancelar.setSurrendersFocusOnKeystroke(true);
		table_CursosCancelar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_CursosCancelar.setAutoCreateRowSorter(true);
		table_CursosCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		scrollPane.setViewportView(table_CursosCancelar);

		table_CursosCancelar.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Cursos" })
		{
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});

		JButton btnConsultar = new JButton(formatLanguage.getString("matricula.btnConsultar"));
		btnConsultar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Connection connection = null;
				AcessoBD acessoBD = new AcessoBD();
				try
				{
					connection = acessoBD.obtemConexao();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MatriculaTO matricula = new MatriculaTO();
				matricula = MatriculaDao.ConsultaMatricula(connection, Integer.parseInt(txtCodMatricula.getText()));

				DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
				dateFormat.format(matricula.getDataMatricula());
				txtDataDeMatricula.setText(dateFormat.format(matricula.getDataMatricula()));
				if (matricula.isFlgPagamento())
				{
					txtStatusDePagamento.setText("Pago");
				} else
				{
					txtStatusDePagamento.setText("Aguardando Pagamento");
				}
				if (matricula.isFlgMatricula())
				{
					txtStatusDeMatricula.setText("Aberta");
				} else
				{
					txtStatusDeMatricula.setText("Cancelada");
				}

				btnCancelarMatricula.setEnabled(true);

				for (MatriculaTO list : listMatriculas)
				{
					if (list.getIdMatricula() == Integer.parseInt(txtCodMatricula.getText()))
					{
						Object[] array = list.getCursos().toArray();
						DefaultTableModel dtm = (DefaultTableModel) table_CursosCancelar.getModel();
						dtm.addRow(array);
					}
				}

			}
		});
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.gridwidth = 2;
		gbc_btnConsultar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultar.gridx = 4;
		gbc_btnConsultar.gridy = 0;
		pn_Cancelar.add(btnConsultar, gbc_btnConsultar);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		pn_Cancelar.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblCursosMatriculados = new JLabel(formatLanguage.getString("matricula.CursosMatriculados"));
		panel_1.add(lblCursosMatriculados, BorderLayout.CENTER);
		lblCursosMatriculados.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JLabel lblDataDeMatricula = new JLabel(formatLanguage.getString("matricula.DataDeMatricula"));
		lblDataDeMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDataDeMatricula = new GridBagConstraints();
		gbc_lblDataDeMatricula.anchor = GridBagConstraints.EAST;
		gbc_lblDataDeMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDeMatricula.gridx = 5;
		gbc_lblDataDeMatricula.gridy = 1;
		pn_Cancelar.add(lblDataDeMatricula, gbc_lblDataDeMatricula);

		txtDataDeMatricula = new JFormattedTextField(TelaUtil.formataData());
		txtDataDeMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDataDeMatricula.setEditable(false);
		GridBagConstraints gbc_txtDataDeMatricula = new GridBagConstraints();
		gbc_txtDataDeMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataDeMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataDeMatricula.gridx = 6;
		gbc_txtDataDeMatricula.gridy = 1;
		pn_Cancelar.add(txtDataDeMatricula, gbc_txtDataDeMatricula);
		txtDataDeMatricula.setColumns(10);

		JLabel lblStatusMatricula = new JLabel(formatLanguage.getString("matricula.lblStatusMatricula"));
		lblStatusMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatusMatricula = new GridBagConstraints();
		gbc_lblStatusMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStatusMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusMatricula.gridx = 2;
		gbc_lblStatusMatricula.gridy = 3;
		pn_Cancelar.add(lblStatusMatricula, gbc_lblStatusMatricula);

		txtStatusDeMatricula = new JTextField();
		txtStatusDeMatricula.setEditable(false);
		txtStatusDeMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtStatusDeMatricula = new GridBagConstraints();
		gbc_txtStatusDeMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStatusDeMatricula.gridwidth = 4;
		gbc_txtStatusDeMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_txtStatusDeMatricula.gridx = 3;
		gbc_txtStatusDeMatricula.gridy = 3;
		pn_Cancelar.add(txtStatusDeMatricula, gbc_txtStatusDeMatricula);
		txtStatusDeMatricula.setColumns(10);

		JLabel lblStatusPagamento = new JLabel(formatLanguage.getString("matricula.lblStatusPagamento"));
		lblStatusPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatusPagamento = new GridBagConstraints();
		gbc_lblStatusPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblStatusPagamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusPagamento.gridx = 2;
		gbc_lblStatusPagamento.gridy = 4;
		pn_Cancelar.add(lblStatusPagamento, gbc_lblStatusPagamento);

		txtStatusDePagamento = new JTextField();
		txtStatusDePagamento.setEditable(false);
		txtStatusDePagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtStatusDePagamento.setColumns(10);
		GridBagConstraints gbc_txtStatusDePagamento = new GridBagConstraints();
		gbc_txtStatusDePagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStatusDePagamento.gridwidth = 4;
		gbc_txtStatusDePagamento.insets = new Insets(0, 0, 5, 5);
		gbc_txtStatusDePagamento.gridx = 3;
		gbc_txtStatusDePagamento.gridy = 4;
		pn_Cancelar.add(txtStatusDePagamento, gbc_txtStatusDePagamento);

		JLabel lblStatusSenha = new JLabel(formatLanguage.getString("main.senha"));
		lblStatusSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStatusSenha = new GridBagConstraints();
		gbc_lblStatusSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusSenha.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblStatusSenha.gridx = 2;
		gbc_lblStatusSenha.gridy = 5;
		pn_Cancelar.add(lblStatusSenha, gbc_lblStatusSenha);

		passFieldStatusSenha = new JPasswordField();
		passFieldStatusSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_passFieldStatusSenha = new GridBagConstraints();
		gbc_passFieldStatusSenha.anchor = GridBagConstraints.SOUTH;
		gbc_passFieldStatusSenha.gridwidth = 3;
		gbc_passFieldStatusSenha.insets = new Insets(0, 0, 5, 5);
		gbc_passFieldStatusSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_passFieldStatusSenha.gridx = 3;
		gbc_passFieldStatusSenha.gridy = 5;
		pn_Cancelar.add(passFieldStatusSenha, gbc_passFieldStatusSenha);

		JLabel lblValorTotalCancelar = new JLabel((String) null);
		lblValorTotalCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblValorTotalCancelar = new GridBagConstraints();
		gbc_lblValorTotalCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorTotalCancelar.gridx = 2;
		gbc_lblValorTotalCancelar.gridy = 6;
		pn_Cancelar.add(lblValorTotalCancelar, gbc_lblValorTotalCancelar);

		btnCancelarMatricula = new JButton(formatLanguage.getString("matricula.btnCancelarMatricula"));
		btnCancelarMatricula.setEnabled(false);
		btnCancelarMatricula.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				boolean status = false;
				Connection conn = null;
				AcessoBD acessoBD = new AcessoBD();
				try
				{
					conn = acessoBD.obtemConexao();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (passFieldStatusSenha.getText().equals("123"))
				{
					status = MatriculaDao.deletarMatricula(Integer.parseInt(txtCodMatricula.getText()), conn);
					if (status)
					{
						JOptionPane.showMessageDialog(null, "Matricula Deletada com Sucesso!");
						dispose();
					} else
					{
						JOptionPane.showMessageDialog(null, "Erro ao Excluir Matricula!");
						dispose();
					}
				} else
				{
					JOptionPane.showMessageDialog(null, "SENHA INCORRETA!");
				}

			}
		});
		btnCancelarMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnCancelarMatricula = new GridBagConstraints();
		gbc_btnCancelarMatricula.gridwidth = 3;
		gbc_btnCancelarMatricula.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelarMatricula.gridx = 3;
		gbc_btnCancelarMatricula.gridy = 7;
		pn_Cancelar.add(btnCancelarMatricula, gbc_btnCancelarMatricula);

	}

	public void TabRelatorio(JTabbedPane tb_Cadastrar)
	{
		// instanciando JPanel Informatica e adcionando no JTabbedPane Cadastrar
		JPanel pn_Cadastrar = new JPanel();
		tb_Cadastrar.addTab("Cadastrar", pn_Cadastrar);
		GridBagLayout gbl_pn_Cadastrar = new GridBagLayout();
		gbl_pn_Cadastrar.columnWidths = new int[] { 32, 95, 118, 0, 0, 0, 82, 118, 66, 8, 0 };
		gbl_pn_Cadastrar.rowHeights = new int[] { -8, 12, 0, 42, 23, 63, 81, 50, 0, -73, 0, -8, 50, 53, 50, 36, 32, 0 };
		gbl_pn_Cadastrar.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_pn_Cadastrar.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pn_Cadastrar.setLayout(gbl_pn_Cadastrar);

		JLabel lblCurso = new JLabel(formatLanguage.getString("main.curso"));
		lblCurso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		pn_Cadastrar.add(lblCurso, gbc_lblNewLabel);
		GridBagConstraints gbc_scrollPane_Matriculados = new GridBagConstraints();
		gbc_scrollPane_Matriculados.anchor = GridBagConstraints.NORTH;
		gbc_scrollPane_Matriculados.gridwidth = 2;
		gbc_scrollPane_Matriculados.gridheight = 5;
		gbc_scrollPane_Matriculados.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Matriculados.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Matriculados.gridx = 7;
		gbc_scrollPane_Matriculados.gridy = 2;
		Panel scrollPane_Matriculados = new Panel();
		pn_Cadastrar.add(scrollPane_Matriculados, gbc_scrollPane_Matriculados);
		scrollPane_Matriculados.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneMatriculados = new JScrollPane();
		scrollPane_Matriculados.add(scrollPaneMatriculados, BorderLayout.CENTER);

		table_Matriculados = new JTable();
		table_Matriculados.setColumnSelectionAllowed(true);
		table_Matriculados.setSurrendersFocusOnKeystroke(true);
		table_Matriculados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Matriculados.setAutoCreateRowSorter(true);
		table_Matriculados.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_Matriculados.addMouseListener(this);
		scrollPaneMatriculados.setViewportView(table_Curso);

		table_Matriculados
				.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Cursos Matriculados" })
				{
					boolean[] columnEditables = new boolean[] { false };

					public boolean isCellEditable(int row, int column)
					{
						return columnEditables[column];
					}
				});
		table_Matriculados.getColumnModel().getColumn(0).setPreferredWidth(725);
		table_Matriculados.getColumnModel().getColumn(0).setMinWidth(500);
		scrollPaneMatriculados.setViewportView(table_Matriculados);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.gridheight = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		pn_Cadastrar.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_Curso = new JScrollPane();
		panel_1.add(scrollPane_Curso);

		table_Curso = new JTable();
		table_Curso.setColumnSelectionAllowed(true);
		table_Curso.setSurrendersFocusOnKeystroke(true);
		table_Curso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Curso.setAutoCreateRowSorter(true);
		table_Curso.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		table_Curso.addMouseListener(this);
		scrollPane_Curso.setViewportView(table_Curso);

		table_Curso.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "Cursos" })
		{
			boolean[] columnEditables = new boolean[] { false };

			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		});
		table_Curso.getColumnModel().getColumn(0).setPreferredWidth(725);
		table_Curso.getColumnModel().getColumn(0).setMinWidth(500);
		scrollPane_Curso.setViewportView(table_Curso);

		JLabel lblMatriculados = new JLabel(formatLanguage.getString("matricula.CursosMatriculados"));
		lblMatriculados.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblMatriculados = new GridBagConstraints();
		gbc_lblMatriculados.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatriculados.gridx = 6;
		gbc_lblMatriculados.gridy = 5;
		pn_Cadastrar.add(lblMatriculados, gbc_lblMatriculados);

		JButton btnRemoverLinha = new JButton(formatLanguage.getString("matricula.removerLinhas"));
		btnRemoverLinha.addMouseListener(new MouseAdapter()
		{

			public void mouseClicked(MouseEvent arg0)
			{
				CursoTO cursoTO = new CursoTO();
				Connection connection = null;
				AcessoBD acessoBD = new AcessoBD();
				try
				{
					connection = acessoBD.obtemConexao();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int row = table_Matriculados.getSelectedRow();
				DefaultTableModel dtm = (DefaultTableModel) table_Matriculados.getModel();
				System.out.println(table_Matriculados.getValueAt(row, 0));
				CursoTO curso = CursoDao.ConsultaCurso(connection, table_Matriculados.getValueAt(row, 0).toString());
				curso.getValor();
				float valor = Float.parseFloat(txtValorMatricula.getText().toString());
				float calc = valor - curso.getValor();
				valorTotal = valorTotal - curso.getValor();
				txtValorMatricula.setText("" + calc);
				dtm.removeRow(row);

			}
		});
		btnRemoverLinha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRemoverLinha = new GridBagConstraints();
		gbc_btnRemoverLinha.gridwidth = 2;
		gbc_btnRemoverLinha.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemoverLinha.gridx = 7;
		gbc_btnRemoverLinha.gridy = 7;
		pn_Cadastrar.add(btnRemoverLinha, gbc_btnRemoverLinha);

		JPanel panel_GerarMatriculas = new JPanel();
		GridBagConstraints gbc_panel_GerarMatriculas = new GridBagConstraints();
		gbc_panel_GerarMatriculas.insets = new Insets(0, 0, 5, 5);
		gbc_panel_GerarMatriculas.fill = GridBagConstraints.BOTH;
		gbc_panel_GerarMatriculas.gridx = 6;
		gbc_panel_GerarMatriculas.gridy = 9;
		pn_Cadastrar.add(panel_GerarMatriculas, gbc_panel_GerarMatriculas);
		panel_GerarMatriculas.setLayout(new BorderLayout(0, 0));

		JLabel lblRA = new JLabel("RA:");
		lblRA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblRA = new GridBagConstraints();
		gbc_lblRA.anchor = GridBagConstraints.EAST;
		gbc_lblRA.insets = new Insets(0, 0, 5, 5);
		gbc_lblRA.gridx = 2;
		gbc_lblRA.gridy = 10;
		pn_Cadastrar.add(lblRA, gbc_lblRA);

		txtRA = new JTextField();
		txtRA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txtRA = new GridBagConstraints();
		gbc_txtRA.gridwidth = 4;
		gbc_txtRA.insets = new Insets(0, 0, 5, 5);
		gbc_txtRA.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRA.gridx = 3;
		gbc_txtRA.gridy = 10;
		pn_Cadastrar.add(txtRA, gbc_txtRA);
		txtRA.setColumns(10);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 11;
		pn_Cadastrar.add(panel, gbc_panel);

		btnCadastrar = new JButton(formatLanguage.getString("main.cadastrar"));
		btnCadastrar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

		
				Connection connection = null;
				AcessoBD acessoBD = new AcessoBD();
				try
				{
					connection = acessoBD.obtemConexao();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int row = table_Matriculados.getSelectedRow();
				DefaultTableModel tableModel = (DefaultTableModel) table_Matriculados.getModel();
				ArrayList<String> arrayList = new ArrayList<String>();
				for (int i = 0; i < tableModel.getRowCount(); i++)
				{
					arrayList.add((String) tableModel.getValueAt(row, 1));
				}
				System.out.println(tableModel.getRowCount() + " arrayList " + arrayList);
				
				MatriculaTO matricula = new MatriculaTO();
				matricula.setIdAluno(Integer.parseInt(txtRA.getText()));
				matricula.setValorMatricula(Float.parseFloat(txtValorMatricula.getText()));
				MatriculaDao.cadastraMatricula(connection, matricula.getIdAluno(), matricula.getValorMatricula(),
						txtDataMatricula.getText(), 0, 1);

				matricula.setIdMatricula(MatriculaDao.ConsultaIdMatricula(connection, matricula.getIdAluno(),
						matricula.getValorMatricula()));
				matricula.setCursos(listCursos);
				
				System.out.println("CHEGANDO NO FINAL");
				dispose();
			}
		});
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btnCadastrar = new GridBagConstraints();
		gbc_btnCadastrar.fill = GridBagConstraints.BOTH;
		gbc_btnCadastrar.gridwidth = 4;
		gbc_btnCadastrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCadastrar.gridx = 3;
		gbc_btnCadastrar.gridy = 12;
		pn_Cadastrar.add(btnCadastrar, gbc_btnCadastrar);

		JLabel lblValorMatricula = new JLabel(formatLanguage.getString("matricula.lblValorMatricula"));
		lblValorMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblValorMatricula = new GridBagConstraints();
		gbc_lblValorMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblValorMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorMatricula.gridx = 1;
		gbc_lblValorMatricula.gridy = 14;
		pn_Cadastrar.add(lblValorMatricula, gbc_lblValorMatricula);

		txtValorMatricula = new JTextField();
		txtValorMatricula.setText("R$");
		txtValorMatricula.setEnabled(false);
		txtValorMatricula.setEditable(false);
		txtValorMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtValorMatricula.setColumns(10);

		GridBagConstraints gbc_txtValorMatricula = new GridBagConstraints();
		gbc_txtValorMatricula.gridwidth = 2;
		gbc_txtValorMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_txtValorMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValorMatricula.gridx = 2;
		gbc_txtValorMatricula.gridy = 14;
		pn_Cadastrar.add(txtValorMatricula, gbc_txtValorMatricula);

		JLabel lblDataFinal = new JLabel(formatLanguage.getString("matricula.DataDeMatricula"));
		lblDataFinal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDataFinal = new GridBagConstraints();
		gbc_lblDataFinal.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataFinal.gridx = 6;
		gbc_lblDataFinal.gridy = 14;
		pn_Cadastrar.add(lblDataFinal, gbc_lblDataFinal);

		txtDataMatricula = new JFormattedTextField(TelaUtil.formataData());
		txtDataMatricula.setText(getDateTime());
		txtDataMatricula.setEnabled(false);
		txtDataMatricula.setEditable(false);
		txtDataMatricula.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtDataMatricula.setColumns(10);
		GridBagConstraints gbc_txtDataMatricula = new GridBagConstraints();
		gbc_txtDataMatricula.gridwidth = 2;
		gbc_txtDataMatricula.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDataMatricula.insets = new Insets(0, 0, 5, 5);
		gbc_txtDataMatricula.gridx = 7;
		gbc_txtDataMatricula.gridy = 14;
		pn_Cadastrar.add(txtDataMatricula, gbc_txtDataMatricula);

		carregandoCurso();
		DefaultTableModel dtm = (DefaultTableModel) table_Matriculados.getModel();
		dtm.removeRow(0);

	}

	/** Metodo que carrega o curso no JTable **/
	private void carregandoCurso()
	{
		Connection connection = null;

		try
		{
			AcessoBD acessoBD = new AcessoBD();
			connection = acessoBD.obtemConexao();
			DefaultTableModel dtm = (DefaultTableModel) table_Curso.getModel();
			while (dtm.getRowCount() > 0)
			{
				dtm.removeRow(0);
			}
			for (CursoTO cursoTO : CursoDao.consultaTodosCurso(connection))
			{
				adcionaLinhas(dtm, cursoTO);
			}

		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Erro na consulta de curso");
			e.printStackTrace();
		}
	}

	private void adcionaLinhas(DefaultTableModel dtm, CursoTO cursoTO)
	{
		dtm.addRow(new Object[] { (cursoTO.getNome()) });

	}

	public void mouseClicked(MouseEvent event)
	{
		if (event.getSource() == table_Curso)
		{
			int row = table_Curso.getSelectedRow();
			DefaultTableModel dtm = (DefaultTableModel) this.table_Matriculados.getModel();
			
			dtm.addRow(new String[] { table_Curso.getValueAt(row, 0).toString() });

			CursoTO cursoTO = new CursoTO();
			Connection connection = null;
			AcessoBD acessoBD = new AcessoBD();

			try
			{
				connection = acessoBD.obtemConexao();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cursoTO = CursoDao.ConsultaCurso(connection, table_Curso.getValueAt(row, 0).toString());
			valorTotal += cursoTO.getValor();
			txtValorMatricula.setText("" + valorTotal);


		}
	}

	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	private static String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
