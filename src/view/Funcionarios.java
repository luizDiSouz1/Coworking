package view;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Funcionarios extends JDialog{
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputEmail;
	private JTextField inputPerfil;
	private JPasswordField inputSenha;
	
	public Funcionarios() {
		setTitle("Funcionarios");
		setResizable (false);
		setBounds(new Rectangle(0, 0, 540, 423));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(39, 53, 52, 14);
		getContentPane().add(nomeFunc);
		
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(43, 153, 52, 14);
		getContentPane().add(loginFunc);
		
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(283, 153, 43, 14);
		getContentPane().add(senhaFunc);
		
		JLabel emailFunc = new JLabel("Email:");
		emailFunc.setBounds(39, 250, 34, 14);
		getContentPane().add(emailFunc);
		
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(283, 250, 43, 14);
		getContentPane().add(perfilFunc);
		
		inputNome = new JTextField();
		inputNome.setBounds(82, 50, 350, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(82, 150, 187, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputEmail = new JTextField();
		inputEmail.setBounds(83, 247, 102, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);
		
		inputPerfil = new JTextField();
		inputPerfil.setBounds(336, 247, 96, 20);
		getContentPane().add(inputPerfil);
		inputPerfil.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(336, 150, 96, 20);
		getContentPane().add(inputSenha);
		
		JLabel imgCreate = new JLabel("");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(248, 317, 58, 56);
		getContentPane().add(imgCreate);
		
		JLabel imgUpdate = new JLabel("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(329, 309, 58, 64);
		getContentPane().add(imgUpdate);
		
		JLabel imgDelete = new JLabel("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(397, 317, 64, 56);
		getContentPane().add(imgDelete);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
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