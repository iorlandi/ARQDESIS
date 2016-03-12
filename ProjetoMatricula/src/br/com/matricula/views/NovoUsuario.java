package br.com.matricula.views;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.matricula.util.CryptoDummy;
import br.com.matricula.util.GravaTXT;
import br.com.matricula.util.Impressora;
import br.com.matricula.util.TelaUtil;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class NovoUsuario extends JDialog implements ActionListener
{
	private JTextField txt_Usuario;
	private JPasswordField passWdField_Senha;
	private JButton btn_Salvar;
	private static ResourceBundle formatLanguage;
	private JLabel lbl_TipoDeUsuario;
	private JComboBox comboBox_Tipo;
	private JCheckBox chckbx_Senha;
	private JPasswordField passField_Senha_Adm;
	private JLabel lbl_Senha_Adm;

	public NovoUsuario(ResourceBundle Language)
	{
		formatLanguage = Language;
		TelaUtil.setaNimbus(this);
		setTitle(formatLanguage.getString("novo.title"));
		setModal(true);
		setSize(424, 217);
		setResizable(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 99, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 44, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lbl_Usuario = new JLabel(formatLanguage.getString("novo.usuario"));
		lbl_Usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Usuario = new GridBagConstraints();
		gbc_lbl_Usuario.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lbl_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Usuario.gridx = 1;
		gbc_lbl_Usuario.gridy = 0;
		getContentPane().add(lbl_Usuario, gbc_lbl_Usuario);

		txt_Usuario = new JTextField();
		txt_Usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_txt_Usuario = new GridBagConstraints();
		gbc_txt_Usuario.anchor = GridBagConstraints.SOUTH;
		gbc_txt_Usuario.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Usuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Usuario.gridx = 2;
		gbc_txt_Usuario.gridy = 0;
		getContentPane().add(txt_Usuario, gbc_txt_Usuario);
		txt_Usuario.setColumns(10);

		JLabel lbl_Senha = new JLabel(formatLanguage.getString("novo.senha"));
		lbl_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Senha = new GridBagConstraints();
		gbc_lbl_Senha.anchor = GridBagConstraints.EAST;
		gbc_lbl_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Senha.gridx = 1;
		gbc_lbl_Senha.gridy = 1;
		getContentPane().add(lbl_Senha, gbc_lbl_Senha);

		passWdField_Senha = new JPasswordField();
		passWdField_Senha.setEchoChar('*');
		passWdField_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passWdField_Senha.setColumns(10);
		GridBagConstraints gbc_txt_Senha = new GridBagConstraints();
		gbc_txt_Senha.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_Senha.gridx = 2;
		gbc_txt_Senha.gridy = 1;
		getContentPane().add(passWdField_Senha, gbc_txt_Senha);


		btn_Salvar = new JButton(formatLanguage.getString("novo.salvar"));
		btn_Salvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn_Salvar.addActionListener(this);
		
		chckbx_Senha = new JCheckBox(formatLanguage.getString("novo.mostrarSenha"));
		chckbx_Senha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		chckbx_Senha.addActionListener(this);
		GridBagConstraints gbc_chckbx_Senha = new GridBagConstraints();
		gbc_chckbx_Senha.anchor = GridBagConstraints.WEST;
		gbc_chckbx_Senha.insets = new Insets(0, 0, 5, 0);
		gbc_chckbx_Senha.gridx = 3;
		gbc_chckbx_Senha.gridy = 1;
		getContentPane().add(chckbx_Senha, gbc_chckbx_Senha);
		
		lbl_Senha_Adm = new JLabel(formatLanguage.getString("novo.senhaAdm"));
		lbl_Senha_Adm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Senha_Adm = new GridBagConstraints();
		gbc_lbl_Senha_Adm.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Senha_Adm.anchor = GridBagConstraints.EAST;
		gbc_lbl_Senha_Adm.gridx = 1;
		gbc_lbl_Senha_Adm.gridy = 2;
		getContentPane().add(lbl_Senha_Adm, gbc_lbl_Senha_Adm);
		
		passField_Senha_Adm = new JPasswordField();
		passField_Senha_Adm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_passField_Senha_Adm = new GridBagConstraints();
		gbc_passField_Senha_Adm.insets = new Insets(0, 0, 5, 5);
		gbc_passField_Senha_Adm.fill = GridBagConstraints.HORIZONTAL;
		gbc_passField_Senha_Adm.gridx = 2;
		gbc_passField_Senha_Adm.gridy = 2;
		getContentPane().add(passField_Senha_Adm, gbc_passField_Senha_Adm);
		
		btn_Salvar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_btn_Salvar = new GridBagConstraints();
		gbc_btn_Salvar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Salvar.gridx = 2;
		gbc_btn_Salvar.gridy = 3;
		getContentPane().add(btn_Salvar, gbc_btn_Salvar);
		TelaUtil.AcaoTeclaButton(this, btn_Salvar, KeyEvent.VK_ENTER);

	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == btn_Salvar)
		{
			if (passField_Senha_Adm.getText().equals("") || !passField_Senha_Adm.getText().equals("123"))
			{
				JOptionPane.showMessageDialog(null, formatLanguage.getString("novo.ErroSenha"));
			}
			else
			{	
			
				if (!passWdField_Senha.getText().equals("") && !txt_Usuario.getText().equals(""))
				{
					GravaTXT gravaTXT = new GravaTXT();
					gravaTXT.gravarNovoTxt(passWdField_Senha.getText(), txt_Usuario.getText(),"Adm" );
					try
					{
						leAcessoCriptografado(gravaTXT);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, formatLanguage.getString("novo.ErroCadastro"));
				}	
			}
		}
		
		if (event.getSource() == chckbx_Senha)
		{
			if (chckbx_Senha.isSelected())
			{
				System.out.println("Selecionado");
				passWdField_Senha.setEchoChar('\u0000');
				
			}
			else
			{
				passWdField_Senha.setEchoChar('*');
			}	
		}
		

	}

	private void leAcessoCriptografado(GravaTXT gravaTXT)
			throws UnsupportedEncodingException, IOException,
			ClassNotFoundException, Exception
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
		// Imprime cabe√ßalho da mensagem

		// Imprime texto

		System.out.println("Mensagem Decifrada (Hexadecimal):");
		// Imprime o texto decifrado em Hexadecimal
		System.out.print(prn.hexBytesToString(bMsgDecifrada));
		System.out.println();
		// Imprime cabecalho da mensagem
		System.out.println("Mensagem Decifrada (String):");
		// Imprime o texto decifrado em String
		System.out.println(sMsgDecifrada);

		
		JOptionPane.showMessageDialog(null, "Usu·rio criado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		this.dispose();
	}
	
	/**Cifra Mensagem**/
	private byte[] cifraMensagem(GravaTXT gravaTXT, byte[] bMsgClara,
			Impressora prn, CryptoDummy cdummy) throws Exception
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
