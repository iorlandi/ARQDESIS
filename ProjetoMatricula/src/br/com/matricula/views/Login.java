package br.com.matricula.views;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import br.com.matricula.util.GravaTXT;
import br.com.matricula.util.TelaUtil;

public class Login extends JFrame implements ActionListener, MouseListener
{
	private static final Logger LOGGER = Logger.getLogger(Login.class);
	private JTextField txt_Usuario;
	private JPasswordField ps_Senha;
	private JLabel lbl_Acessar;
	private JMenuBar menuBar;
	private JMenu mn_Sobre;
	private JMenuItem mntm_SobreOSistema;
	private JMenu mn_Linguagem;
	private JMenuItem mntm_Ingles;
	private JMenuItem mntm_Portugues;
	private JMenuItem mntm_Espanhol;
	private static ResourceBundle formatLanguage;
	private JLabel lbl_Senha;
	private JLabel lbl_Usuario;
	public int linguagem;
	private JLabel lbl_NovoUsuario;

	/** Construtor da class Login **/
	public Login()
	{
		// configurando ações da Janela
		LOGGER.info("Iniciando tela de Login");
		formatLanguage = ResourceBundle.getBundle("BR");
		TelaUtil.setaNimbus(this);
		setTitle("Acessar Sistema de Matricula");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 259);

		// instanciando GridBagLayout e adcionando na tela
		GridBagLayout containerGrid = new GridBagLayout();
		containerGrid.columnWidths = new int[]
		{ 114, 74, 244, 57, 0 };
		containerGrid.rowHeights = new int[]
		{ 0, 0, 0, 0, 32, 0 };
		containerGrid.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		containerGrid.rowWeights = new double[]
		{ 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(containerGrid);

		lbl_Usuario = new JLabel("Usuário: ");
		lbl_Usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Usuario = new GridBagConstraints();
		gbc_lbl_Usuario.anchor = GridBagConstraints.EAST;
		gbc_lbl_Usuario.gridwidth = 2;
		gbc_lbl_Usuario.fill = GridBagConstraints.VERTICAL;
		gbc_lbl_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Usuario.gridx = 0;
		gbc_lbl_Usuario.gridy = 1;
		getContentPane().add(lbl_Usuario, gbc_lbl_Usuario);

		// criando e posicionado o JTextField para Usuário
		txt_Usuario = new JTextField();
		txt_Usuario.setText("Ad");
		txt_Usuario.setToolTipText("Digite o nome de usuário");
		txt_Usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Usuario = new GridBagConstraints();
		gbc_txt_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Usuario.gridx = 2;
		gbc_txt_Usuario.gridy = 1;
		getContentPane().add(txt_Usuario, gbc_txt_Usuario);
		txt_Usuario.setColumns(10);

		lbl_Senha = new JLabel("Senha: ");
		lbl_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Senha = new GridBagConstraints();
		gbc_lbl_Senha.anchor = GridBagConstraints.EAST;
		gbc_lbl_Senha.gridwidth = 2;
		gbc_lbl_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Senha.gridx = 0;
		gbc_lbl_Senha.gridy = 2;
		getContentPane().add(lbl_Senha, gbc_lbl_Senha);

		// criando e posicionado o JPasswordField para Senha
		ps_Senha = new JPasswordField();
		ps_Senha.setText("Ad");
		ps_Senha.setToolTipText("Digite a senha de acesso");
		ps_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_ps_Senha = new GridBagConstraints();
		gbc_ps_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_ps_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_ps_Senha.gridx = 2;
		gbc_ps_Senha.gridy = 2;
		getContentPane().add(ps_Senha, gbc_ps_Senha);

		// criando e posicionado o botão e acessar

		lbl_Acessar = new JLabel();
		lbl_Acessar.setIcon(new ImageIcon(Login.class
				.getResource("/Imagem/icone_entrar.png")));
		lbl_Acessar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lbl_Acessar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbl_Acessar.addMouseListener(this);
		GridBagConstraints gbc_btn_Acessar = new GridBagConstraints();
		gbc_btn_Acessar.gridwidth = 2;
		gbc_btn_Acessar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Acessar.gridx = 1;
		gbc_btn_Acessar.gridy = 3;
		getContentPane().add(lbl_Acessar, gbc_btn_Acessar);
		
		lbl_NovoUsuario = new JLabel();
		lbl_NovoUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lbl_NovoUsuario.setText("<html><a href=>Novo Usuario</a></html>");
		GridBagConstraints gbc_lbl_NovoUsuario = new GridBagConstraints();
		gbc_lbl_NovoUsuario.anchor = GridBagConstraints.WEST;
		gbc_lbl_NovoUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_NovoUsuario.gridx = 2;
		gbc_lbl_NovoUsuario.gridy = 4;
		getContentPane().add(lbl_NovoUsuario, gbc_lbl_NovoUsuario);
		lbl_NovoUsuario.addMouseListener(this);
		

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mn_Linguagem = new JMenu("Linguagem");
		mn_Linguagem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mn_Linguagem);

		mntm_Ingles = new JMenuItem();
		mntm_Ingles.setIcon(new ImageIcon(Login.class
				.getResource("/Imagem/Bandeira_EUA.jpg")));
		mntm_Ingles.setToolTipText(formatLanguage
				.getString("login.mntm_Ingles"));
		mntm_Ingles.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntm_Ingles.addActionListener(this);
		mn_Linguagem.add(mntm_Ingles);

		mntm_Portugues = new JMenuItem();
		mntm_Portugues.setIcon(new ImageIcon(Login.class
				.getResource("/Imagem/Bandeira_Brasil.jpg")));
		mntm_Portugues.setToolTipText(formatLanguage
				.getString("login.mntm_Portugues"));
		mntm_Portugues.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntm_Portugues.addActionListener(this);
		mn_Linguagem.add(mntm_Portugues);

		mntm_Espanhol = new JMenuItem();
		mntm_Espanhol.setIcon(new ImageIcon(Login.class
				.getResource("/Imagem/bandeira_Espanha.jpg")));
		mntm_Espanhol.setToolTipText(formatLanguage
				.getString("login.mntm_Espanhol"));
		mntm_Espanhol.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntm_Espanhol.addActionListener(this);
		mn_Linguagem.add(mntm_Espanhol);

		mn_Sobre = new JMenu("Sobre ");
		mn_Sobre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mn_Sobre);

		mntm_SobreOSistema = new JMenuItem("Sobre o Sistema");
		mntm_SobreOSistema.addActionListener(this);
		mntm_SobreOSistema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mn_Sobre.add(mntm_SobreOSistema);

	}

	public void FormatandoLinguagem()
	{
		
		setTitle(formatLanguage.getString("login.title"));
		mntm_SobreOSistema.setText(formatLanguage
				.getString("login.mntm_SobreOSistema"));
		mn_Linguagem.setText(formatLanguage.getString("login.mn_Linguagem"));
		mntm_Ingles.setToolTipText(formatLanguage
				.getString("login.mntm_Ingles"));
		mntm_Portugues.setToolTipText(formatLanguage
				.getString("login.mntm_Portugues"));
		mntm_Espanhol.setToolTipText(formatLanguage
				.getString("login.mntm_Espanhol"));
		mn_Sobre.setText(formatLanguage.getString("login.mn_Sobre"));
		lbl_Senha.setText(formatLanguage.getString("main.senha"));
		lbl_Usuario.setText(formatLanguage.getString("login.lbl_Usuario"));
		txt_Usuario.setToolTipText(formatLanguage
				.getString("login.txt_Usuario"));
		ps_Senha.setToolTipText(formatLanguage.getString("login.ps_Senha"));
		lbl_NovoUsuario.setText(formatLanguage.getString("login.lbl_NovoUsuario"));
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mntm_Portugues)
		{
			formatLanguage = ResourceBundle.getBundle("BR");
			FormatandoLinguagem();
			linguagem = 0;
		}

		if (e.getSource() == mntm_Ingles)
		{
			formatLanguage = ResourceBundle.getBundle("EN");
			FormatandoLinguagem();
			linguagem = 1;
		}
		if (e.getSource() == mntm_Espanhol)
		{
			formatLanguage = ResourceBundle.getBundle("ES");
			FormatandoLinguagem();
			linguagem = 2;
		}

		if (e.getSource() == mntm_SobreOSistema)
		{

			SobreSistema sobre = new SobreSistema(formatLanguage);
			sobre.setVisible(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

		if (e.getSource() == lbl_Acessar)
		{
			try
			{
				System.out.println(TelaUtil.leAcessoCriptografado(
						txt_Usuario.getText(), ps_Senha.getText()));
				;
				String tipo = TelaUtil.leAcessoCriptografado(
						txt_Usuario.getText(), ps_Senha.getText());
				if (!tipo.equals("null"))
				{
					this.dispose();
					TelaPrincipal tlP = new TelaPrincipal(formatLanguage,
							txt_Usuario.getText(), tipo);
					tlP.setVisible(true);

				}
			} catch (Exception e1)
			{
				JOptionPane.showMessageDialog(null, formatLanguage.getString("login.ErroAcesso"),
						formatLanguage.getString("main.Atencao"), JDialog.DO_NOTHING_ON_CLOSE);
				txt_Usuario.setText("");
				ps_Senha.setText("");
				txt_Usuario.requestFocus();
				e1.printStackTrace();
			}

		}

		if (e.getSource() == lbl_NovoUsuario)
		{
			NovoUsuario novoUsuario = new NovoUsuario(formatLanguage);
			novoUsuario.setVisible(true);
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

}
