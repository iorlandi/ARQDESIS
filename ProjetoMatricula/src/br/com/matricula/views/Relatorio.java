package br.com.matricula.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.com.matricula.daos.CursoDao;
import br.com.matricula.daos.MatriculaDao;
import br.com.matricula.to.CursoTO;
import br.com.matricula.util.AcessoBD;
import br.com.matricula.util.TelaUtil;
import datechooser.beans.DateChooserCombo;

public class Relatorio extends JDialog implements MouseListener, ActionListener
{
	private static ResourceBundle formatLanguage;
	private JTable table_Curso;
	private JButton btnGerarMatriculas;
	private DateChooserCombo dateCh_DataInicial;
	private DateChooserCombo dateCh_DataFinal;

	/**
	 * Contrutor da Classe Relatorio
	 * 
	 * @param formatLanguage
	 */
	public Relatorio(ResourceBundle Language)
	{
		formatLanguage = Language;
		// configurando ações da Janela
		getContentPane().setBackground(Color.WHITE);
		setTitle(formatLanguage.getString("main.relatorio"));
		setSize(684, 540);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// instanciando GridBagLayout e adcionando na tela
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		getContentPane().setLayout(gridBagLayout);

		// instanciando JTabbedPane Principal e adcionando na tela
		JTabbedPane tb_Principal = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tb_Principal = new GridBagConstraints();
		gbc_tb_Principal.gridheight = 0;
		gbc_tb_Principal.gridwidth = 2;
		gbc_tb_Principal.insets = new Insets(0, 0, 0, 5);
		gbc_tb_Principal.fill = GridBagConstraints.BOTH;
		gbc_tb_Principal.gridx = 0;
		gbc_tb_Principal.gridy = 0;
		getContentPane().add(tb_Principal, gbc_tb_Principal);

		TabRelatorio(tb_Principal);

	}

	public void TabRelatorio(JTabbedPane tb_Cadastrar)
	{
		// instanciando JPanel Informatica e adcionando no JTabbedPane Cadastrar
		JPanel pn_Relatorio = new JPanel();
		tb_Cadastrar.addTab(formatLanguage.getString("main.relatorio"), pn_Relatorio);
		GridBagLayout gbl_pn_Relatorio = new GridBagLayout();
		gbl_pn_Relatorio.columnWidths = new int[] { 62, 58, 150, 0, 80, 150, 87, 0 };
		gbl_pn_Relatorio.rowHeights = new int[] { 48, 79, 55, 81, 0 };
		gbl_pn_Relatorio.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pn_Relatorio.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		pn_Relatorio.setLayout(gbl_pn_Relatorio);

		JPanel panel_Relatorio = new JPanel();
		panel_Relatorio.setBorder(BorderFactory
				.createTitledBorder(formatLanguage.getString("relatorio.pnlRelatorio")));
		GridBagConstraints gbc_panel_Relatorio = new GridBagConstraints();
		gbc_panel_Relatorio.gridheight = 2;
		gbc_panel_Relatorio.gridwidth = 7;
		gbc_panel_Relatorio.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Relatorio.fill = GridBagConstraints.BOTH;
		gbc_panel_Relatorio.gridx = 0;
		gbc_panel_Relatorio.gridy = 1;
		pn_Relatorio.add(panel_Relatorio, gbc_panel_Relatorio);
		GridBagLayout gbl_panel_Relatorio = new GridBagLayout();
		gbl_panel_Relatorio.columnWidths = new int[] { 55, 103, 158, 123, 158, 84, 0 };
		gbl_panel_Relatorio.rowHeights = new int[] { 20, 0, 0, 119, 0, 55, 0 };
		gbl_panel_Relatorio.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Relatorio.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_Relatorio.setLayout(gbl_panel_Relatorio);

		JLabel lblSelecioneCurso = new JLabel(formatLanguage.getString("relatorio.lblSelecioneCurso"));
		GridBagConstraints gbc_lblSelecioneCurso = new GridBagConstraints();
		gbc_lblSelecioneCurso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecioneCurso.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSelecioneCurso.gridx = 1;
		gbc_lblSelecioneCurso.gridy = 1;
		panel_Relatorio.add(lblSelecioneCurso, gbc_lblSelecioneCurso);
		lblSelecioneCurso.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JScrollPane scrollPane_Curso = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Curso = new GridBagConstraints();
		gbc_scrollPane_Curso.gridwidth = 3;
		gbc_scrollPane_Curso.gridheight = 3;
		gbc_scrollPane_Curso.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Curso.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Curso.gridx = 2;
		gbc_scrollPane_Curso.gridy = 1;
		panel_Relatorio.add(scrollPane_Curso, gbc_scrollPane_Curso);

		table_Curso = new JTable();
		table_Curso.setColumnSelectionAllowed(true);
		table_Curso.setSurrendersFocusOnKeystroke(true);
		table_Curso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Curso.setAutoCreateRowSorter(true);
		table_Curso.setFont(new Font("Segoe UI", Font.PLAIN, 17));
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
		table_Curso.addMouseListener(this);

		JLabel lblDataIni = new JLabel(formatLanguage.getString("relatorio.lblDataIni"));
		GridBagConstraints gbc_lblDataIni = new GridBagConstraints();
		gbc_lblDataIni.anchor = GridBagConstraints.EAST;
		gbc_lblDataIni.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataIni.gridx = 1;
		gbc_lblDataIni.gridy = 5;
		panel_Relatorio.add(lblDataIni, gbc_lblDataIni);
		lblDataIni.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		dateCh_DataInicial = new DateChooserCombo();
		dateCh_DataInicial.setCalendarPreferredSize(TelaUtil.dateDimesion(dateCh_DataInicial));
		dateCh_DataInicial.setFieldFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_dateCh_DataInicial = new GridBagConstraints();
		gbc_dateCh_DataInicial.insets = new Insets(0, 0, 0, 5);
		gbc_dateCh_DataInicial.fill = GridBagConstraints.BOTH;
		gbc_dateCh_DataInicial.gridx = 2;
		gbc_dateCh_DataInicial.gridy = 5;
		panel_Relatorio.add(dateCh_DataInicial, gbc_dateCh_DataInicial);

		JLabel lblDataFinal = new JLabel(formatLanguage.getString("relatorio.lblDataFinal"));
		GridBagConstraints gbc_lblDataFinal = new GridBagConstraints();
		gbc_lblDataFinal.anchor = GridBagConstraints.EAST;
		gbc_lblDataFinal.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataFinal.gridx = 3;
		gbc_lblDataFinal.gridy = 5;
		panel_Relatorio.add(lblDataFinal, gbc_lblDataFinal);
		lblDataFinal.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		dateCh_DataFinal = new DateChooserCombo();
		GridBagConstraints gbc_dateCh_DataFinal = new GridBagConstraints();
		gbc_dateCh_DataFinal.insets = new Insets(0, 0, 0, 5);
		gbc_dateCh_DataFinal.fill = GridBagConstraints.BOTH;
		gbc_dateCh_DataFinal.gridx = 4;
		gbc_dateCh_DataFinal.gridy = 5;
		panel_Relatorio.add(dateCh_DataFinal, gbc_dateCh_DataFinal);
		dateCh_DataFinal.setCalendarPreferredSize(TelaUtil.dateDimesion(dateCh_DataFinal));
		dateCh_DataFinal.setFieldFont(new Font("Segoe UI", Font.PLAIN, 14));

		btnGerarMatriculas = new JButton(formatLanguage.getString("relatorio.btnGerarMatriculas"));
		btnGerarMatriculas.setIcon(new ImageIcon(Relatorio.class.getResource("/Imagem/PDF.png")));
		GridBagConstraints gbc_btnGerarMatriculas = new GridBagConstraints();
		gbc_btnGerarMatriculas.gridwidth = 4;
		gbc_btnGerarMatriculas.insets = new Insets(0, 0, 0, 5);
		gbc_btnGerarMatriculas.gridx = 2;
		gbc_btnGerarMatriculas.gridy = 3;
		pn_Relatorio.add(btnGerarMatriculas, gbc_btnGerarMatriculas);
		btnGerarMatriculas.addActionListener(this);
		btnGerarMatriculas.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		carregandoCurso();

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

	@Override
	public void mouseClicked(MouseEvent event)
	{
		if (event.getSource() == table_Curso)
		{
			int row = table_Curso.getSelectedRow();
			DefaultTableModel tableModel = (DefaultTableModel) this.table_Curso.getModel();
			System.out.println(tableModel.getValueAt(row, 0));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Connection connection = null;
		if (event.getSource() == btnGerarMatriculas)
		{
		
			Date dataIni = dateCh_DataInicial.getSelectedDate().getTime();  
			Date dataFinal = dateCh_DataFinal.getSelectedDate().getTime();  
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			
			System.err.println(formato.format(dataIni));
			AcessoBD acessoBD = new AcessoBD();
			try
			{
				connection = acessoBD.obtemConexao();

				TelaUtil.gerarRelatorio(MatriculaDao.retornaRelatorio(connection,
						String.valueOf(formato.format(dataIni)), String.valueOf(formato.format(dataFinal))), "relatorios/matricula_cancel.jasper");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
