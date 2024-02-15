package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

public class Login extends JDialog {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	
	public Login() {
		
		addWindowListener(new WindowAdapter(){
			public void windowActivated(WindowEvent e) {
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
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Arial Black", Font.PLAIN, 14));
		tituloLogin.setBounds(173, 11, 115, 24);
		getContentPane().add(tituloLogin);
		
		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(24, 191, 54, 66);
		getContentPane().add(imgDatabase);
		
		//Acessar o botão "entrar" com a tecla "enter"
		getRootPane().setDefaultButton(btnLogin);
		
		//Validação dos campos utilizando a biblioteca Atxy2k
		
		//Validação do campo inputLogn
		RestrictedTextField validarLogin = new RestrictedTextField(inputLogin,"abcdefghijklmnopqrstuvwxyz0123456789_-.");
		
		
		//Determinar o uso de alguns caracteres especiais e alfanumerico(_ - .)
		validarLogin.setOnlyCustomCharacters(true);
		
		
		
		//Limitar a somente 20 caracteres no campo login
		validarLogin.setLimit(20);
		
		//Validação do campo inputSenha
		
		RestrictedTextField validarSenha = new RestrictedTextField(inputSenha);
		
		//Limitar a somente 15 caracteres no campo de senha
		validarSenha.setLimit(15);
		
		//Desativar a tecla espaço no campo senha
		
	}
	
	DAO dao = new DAO();
	private JLabel imgDatabase;
	
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
	
	private void logar() {
		String read = "select * from funcionario where login=? and senha=md5(?)";
		
		//validacao do login do usuario
		if(inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Login do usuário obrigatório!");
			inputLogin.requestFocus();
		}
		
		else if(inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null,"Senha do usuário obrigatoria!");
			inputSenha.requestFocus();
		}
		
		
		else {	
	
		try {
			//Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();
			
			//Preparar a execução do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(read);
			
			//Atribuir valores de login e senha
			//substituir as interregações ? ? pelo conteúdo da caixa de texto(input)
			executarSQL.setString(1, inputLogin.getText());
			executarSQL.setString(2, inputSenha.getText());
			
			//Executar os comandos SQL e de acordo
			ResultSet resultadoExecucao = executarSQL.executeQuery();
			
			//validação do funcionário(autenticação)
			//resultadoExecucao.next() significa que o login e a senha existem, ou seja, correspondem
			
			if(resultadoExecucao.next()){
				Home home = new Home();
				home.setVisible(true);
				
				home.txtUsuarioLogado.setText("Usuário: " + resultadoExecucao.getString(2));
				
				//fechar a janela de login assim que a janela HOME abrir automaticamente
				
				dispose();
			}
			
			else {
				//criar um alerta (pop-up) que informe ao usuário que login e/ou senha estão invalidos
				
				JOptionPane.showInternalMessageDialog(null, "usuarios e/ou senha incorretas");
				inputLogin.setText(null);
				inputSenha.setText(null);
				inputLogin.requestFocus();
			}
			
			
			conexaoBanco.close();
		}
	
		
		catch(Exception e) {
			System.out.println(e);
		
	}
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
