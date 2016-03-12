package br.com.matricula.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.MaskFormatter;

import br.com.matricula.daos.MatriculaDao;
import datechooser.beans.DateChooserCombo;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaUtil implements KeyListener
{
	public static String[] camposSplits;
	public static ResourceBundle BUNDLE;

	public static Integer LANGUAGE = 0;
	
	
	public static void gerarRelatorio(ResultSet resultset, String p_caminhoJasper)
	{
		try
		{
			JRResultSetDataSource setDataSource = new JRResultSetDataSource(resultset);
			
			JasperPrint print = JasperFillManager.fillReport(p_caminhoJasper, new HashMap(),	setDataSource);
			JasperViewer jasperViewer = new JasperViewer(print, true);
			
			JDialog viewDialog =  new JDialog(new javax.swing.JFrame(),"VisualizaÁ„o do RelatÛrio", true); 
			viewDialog.setSize(890,600);   
			viewDialog.setLocationRelativeTo(null);  
			viewDialog.getContentPane().add(jasperViewer.getContentPane());
			viewDialog.setVisible(true);
	
		} catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problema com a geraÁ„o do relatorio");
		}

	}
	public static Dimension dateDimesion(DateChooserCombo chooserCombo)
	{
		
		Dimension size = chooserCombo.getCalendarPreferredSize();
		size.width += 80;
		return size;
	}

	public static void setaNimbus(Component comp)
	{
		ColorUIResource backgroundUI = new ColorUIResource(0xAFBDD4);
		ColorUIResource lightBackgroundUI = new ColorUIResource(0x3b5998);
		ColorUIResource textUI = new ColorUIResource(0x141823);
		ColorUIResource controlBackgroundUI = new ColorUIResource(0x6d84b4);
		ColorUIResource infoBackgroundUI = new ColorUIResource(0x3b5998);
		ColorUIResource infoUI = new ColorUIResource(0x3b5998);
		ColorUIResource focusUI = new ColorUIResource(0x3b5998);

		UIManager.put("control", backgroundUI); // tela de fundo
		UIManager.put("nimbusBase", lightBackgroundUI);// componentes
														// selecionados
		UIManager.put("text", textUI); // texto
		UIManager.put("nimbusBlueGrey", controlBackgroundUI);// botÙes, menus,
																// bordas de
																// campos
		UIManager.put("info", infoUI); // campos de informaÁ„o
		UIManager.put("nimbusInfoBlue", infoBackgroundUI);// n„o sei
		UIManager.put("nimbusFocus", focusUI);// borda o campo em foco
		// UIManager.put("nimbusLightBackground", lightBackgroundUI); // fundo
		// de campos

		UIManager.LookAndFeelInfo[] looks = UIManager
				.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo look : looks)
		{
			if (look.getClassName().matches("(?i).*nimbus.*"))
			{
				try
				{
					UIManager.setLookAndFeel(look.getClassName());
					SwingUtilities.updateComponentTreeUI(comp);
					return;
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/** Metodo que formata Telefone **/

	public static MaskFormatter formataTelefone()
	{
		MaskFormatter mask = null;
		try
		{
			mask = new MaskFormatter("(##)####-####");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mask;

	}

	/** Metodo que formata Data **/

	public static MaskFormatter formataData()
	{
		MaskFormatter mask = null;
		try
		{
			mask = new MaskFormatter("##/##/##");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mask;

	}

	/**
	 * Metodo que formata Cep
	 * **/

	public static MaskFormatter formataCep()
	{
		MaskFormatter mask = null;
		try
		{
			mask = new MaskFormatter("#####-###");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mask;

	}

	/**
	 * Metodo que formata Cpf
	 * **/

	public static MaskFormatter formataCpf()
	{
		MaskFormatter mask = null;
		try
		{
			mask = new MaskFormatter("###.###.###-##");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mask;

	}

	/**
	 * Metodo que le e valida acesso conforme senha cifrada
	 **/
	public static String leAcessoCriptografado(String p_Login, String p_Senha)
			throws Exception
	{
		GravaTXT gravar = new GravaTXT();
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
		bMsgCifrada = cifraMensagem(gravar, bMsgClara, prn, cdummy);
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

		camposSplits = sMsgDecifrada.split("[\\W]");
		for (int i = 0; i < camposSplits.length; i++)
		{

			if (camposSplits[i].equals(p_Login))
			{
				if (camposSplits[i + 1].equals(p_Senha))
				{
					return camposSplits[i + 2];

				}
			}
		}

		return null;

	}

	/** Metodo que crifa mensagem **/
	private static byte[] cifraMensagem(GravaTXT gravar, byte[] bMsgClara,
			Impressora prn, CryptoDummy cdummy) throws IOException,
			ClassNotFoundException, Exception, UnsupportedEncodingException
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

		gravar.gravarTCI(gravar_Dummy);
		gravar.gravarTCI(prn.hexBytesToString(bMsgCifrada));

		return bMsgCifrada;
	}

	/**
	 * Metodo que formata Rg
	 * **/

	public static MaskFormatter formataRg()
	{
		MaskFormatter mask = null;
		try
		{
			mask = new MaskFormatter("##.###.###-#");

		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return mask;

	}

	public static void AcaoTeclaButton(JDialog p_Comp, final JButton p_Button,
			int p_Tecla)
	{

		InputMap inputMap = p_Comp.getRootPane().getInputMap(
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(p_Tecla, 0), "forward");
		p_Comp.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW,
				inputMap);
		p_Comp.getRootPane().getActionMap().put("forward", new AbstractAction()
		{
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event)
			{
				p_Button.doClick();
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}
}
