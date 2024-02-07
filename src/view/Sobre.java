package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Sobre extends JDialog  {
	public Sobre() {
		setTitle("Sobre");
		setBounds(new Rectangle(0, 0, 540, 540));
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setFont(new Font("Source Code Pro Medium", Font.BOLD, 14));
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("Sobre o software");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(10, 59, 593, 30);
		titulo.setBackground(new Color(255, 255, 255));
		titulo.setFont(new Font("Stencil", Font.PLAIN, 18));
		/*Jlabel - classe, 0 "titulo" pode utilizar todos os componentes dela = new (criar um novo objeto) + Jlabel(construtor que inicia sempre
		 * 	que comecar um novo objeto)
		 */
		
		getContentPane().add(titulo);
		
		JLabel descricao = new JLabel("O software coWorking trata-se de um protótipo cujo objetivo é");
		descricao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descricao.setHorizontalAlignment(SwingConstants.CENTER);
		descricao.setBounds(-7, 100, 593, 35);
		getContentPane().add(descricao);
		
		JLabel descrição2 = new JLabel("possibilitar o gerenciamento de reservas de salas em um espaço colaborativo.\r\n\r\n");
		descrição2.setHorizontalAlignment(SwingConstants.CENTER);
		descrição2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrição2.setBounds(-7, 146, 593, 23);
		getContentPane().add(descrição2);
		
		JLabel versao = new JLabel("Versão: 1.0.0\r\n\r\n");
		versao.setHorizontalAlignment(SwingConstants.CENTER);
		versao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		versao.setBounds(3, 180, 573, 30);
		getContentPane().add(versao);
		
		JLabel atualizacao = new JLabel("Ultima atualização: 31/01/2024");
		atualizacao.setHorizontalAlignment(SwingConstants.CENTER);
		atualizacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		atualizacao.setBounds(3, 218, 583, 47);
		getContentPane().add(atualizacao);
		
		JLabel imgMIT = new JLabel("");
		imgMIT.setIcon(new ImageIcon(Sobre.class.getResource("/img/mitLicense.png")));
		imgMIT.setBounds(528, 306, 48, 53);
		getContentPane().add(imgMIT);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				
				catch(Exception e) {
					e.printStackTrace();
				}
			}
	
		});
	}
}


/* o comando extends faz com que a classe 'Sobre' herde os atributos/metodoa JDialog */ 