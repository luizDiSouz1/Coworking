package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

public class Sobre extends JDialog  {
	public Sobre() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setBounds(173, 49, 43, 113);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		/*Jlabel - classe, 0 "titulo" pode utilizar todos os componentes dela = new (criar um novo objeto) + Jlabel(construtor que inicia sempre
		 * 	que comecar um novo objeto)
		 */
		
		getContentPane().add(titulo);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/* o comando extends faz com que a classe 'Sobre' herde os atributos/metodoa JDialog */ 