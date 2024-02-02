package view;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DAO;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JPasswordField inputSenha;
	private JTextField inputLogin;
	
	public Login() {
		addWindowListener(new WindowAdapter(){
			public void windowActivate(WindowEvent e) {
				statusConexaoBanco();
			}
		});
		
		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 450, 321));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		
		JLabel txtLogin = new JLabel("Login");
		txtLogin.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtLogin.setBounds(114, 72, 32, 24);
		getContentPane().add(txtLogin);
		
		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtSenha.setBounds(114, 118, 43, 24);
		getContentPane().add(txtSenha);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(167, 121, 110, 20);
		getContentPane().add(inputSenha);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(167, 75, 110, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(178, 191, 84, 20);
		getContentPane().add(btnLogin);
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Arial Black", Font.PLAIN, 14));
		tituloLogin.setBounds(173, 11, 116, 24);
		getContentPane().add(tituloLogin);
		
		JLabel imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(24, 191, 54, 66);
		getContentPane().add(imgDatabase);
	}
	
	DAO dao = new DAO();
	
	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();
			
			if(conexaoBanco == null) {
				//Escolher a imagem para quando não ha conexão
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOff.png")));
			}
			
			else {
				//Trocar a imagem se houve conexão
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOn.png")));
			}
		
		conexaoBanco.close();
	}
	
	catch(Exception e) {
		System.out.println(e);
	}
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Login dialog = new Login();
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
