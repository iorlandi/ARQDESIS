package br.com.matricula.views;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import br.com.matricula.util.TelaUtil;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.util.ResourceBundle;

public class SobreSistema extends JDialog
{
	public SobreSistema(ResourceBundle Language)
	{
		TelaUtil.setaNimbus(this);
		setTitle(Language.getString("SobreSistema.title"));
		setModal(true);
		setSize(340, 299);
		setResizable(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 294, 0 };
		gbl_panel.rowHeights = new int[] { 0, 73, 141, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lbl_Sobre = new JLabel(Language.getString("SobreSistema.lbl_Sobre"));
		lbl_Sobre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Sobre = new GridBagConstraints();
		gbc_lbl_Sobre.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Sobre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Sobre.gridheight = 2;
		gbc_lbl_Sobre.gridx = 1;
		gbc_lbl_Sobre.gridy = 0;
		panel.add(lbl_Sobre, gbc_lbl_Sobre);

		JLabel lbl_Desen = new JLabel(Language.getString("SobreSistema.lbl_Desen"));
		lbl_Desen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GridBagConstraints gbc_lbl_Desen = new GridBagConstraints();
		gbc_lbl_Desen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_Desen.gridx = 1;
		gbc_lbl_Desen.gridy = 2;
		panel.add(lbl_Desen, gbc_lbl_Desen);
	}
}
