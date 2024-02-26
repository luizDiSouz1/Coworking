package view;

import javax.swing.JDialog;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputEmail;
	private JTextField inputLogin;
	private JPasswordField inputSenha;

	public Funcionarios() {
		getContentPane().setBackground(new Color(240, 240, 240));
		setTitle("Funcionários");
		setResizable(false);
		setBounds(100, 100, 614, 395);
		// setBounds(550,250,564,395);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(24, 58, 46, 14);
		getContentPane().add(nomeFunc);
		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(24, 184, 46, 14);
		getContentPane().add(loginFunc);
		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(299, 184, 46, 14);
		getContentPane().add(senhaFunc);
		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(299, 234, 46, 14);
		getContentPane().add(emailFunc);
		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(24, 271, 46, 14);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(74, 55, 362, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);

		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNaTabela();
			}
		});

		inputEmail = new JTextField();
		inputEmail.setColumns(10);
		inputEmail.setBounds(355, 231, 200, 20);
		getContentPane().add(inputEmail);
		inputLogin = new JTextField();
		inputLogin.setColumns(10);
		inputLogin.setBounds(57, 181, 200, 20);
		getContentPane().add(inputLogin);
		inputSenha = new JPasswordField();
		inputSenha.setBounds(353, 181, 200, 20);
		getContentPane().add(inputSenha);
		inputPerfil = new JComboBox();
		inputPerfil.setModel(
				new DefaultComboBoxModel(new String[] { " ", "Administrador", "Gerência", "Atendimento", "Suporte" }));
		inputPerfil.setBounds(57, 267, 106, 22);
		getContentPane().add(inputPerfil);
		btnCreate = new JButton("");
		btnCreate.setBackground(new Color(240, 240, 240));
		btnCreate.setBorderPainted(false);
		btnCreate.setBorder(null);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarFuncionario();
			}
		});

		btnCreate.setBounds(254, 286, 92, 59);
		getContentPane().add(btnCreate);

		// DECLARAÇÃO ABAIXO DO BOTÃO
		btnUpdate = new JButton("");

		btnUpdate.setBackground(new Color(240, 240, 240));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		btnUpdate.setBounds(353, 286, 83, 59);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
			}
		});

		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setBackground(new Color(240, 240, 240));
		btnDelete.setBorderPainted(false);
		btnDelete.setBorder(null);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		btnDelete.setBounds(446, 286, 83, 59);
		getContentPane().add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 73, 362, 71);
		getContentPane().add(scrollPane);

		tblFuncionarios = new JTable();
		scrollPane.setViewportView(tblFuncionarios);

		tblFuncionarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(520, 55, 36, 23);
		getContentPane().add(btnPesquisar);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBuscarFuncionario();
			}
		});

		JLabel IDFunc = new JLabel("ID:");
		IDFunc.setBounds(446, 58, 15, 14);
		getContentPane().add(IDFunc);

		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(464, 55, 46, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);

	}

	DAO dao = new DAO();
	private JComboBox inputPerfil;
	public JButton btnCreate;
	public JButton btnUpdate;
	public JButton btnDelete;
	private JTable tblFuncionarios;
	private JTextField inputID;

	private void adicionarFuncionario() {
		String create = "insert into funcionario (nomeFunc, login, senha, perfil, email) values (?, ?, md5(?), ?, ?)";
		
		if(inputNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Nome do usuário obrigatório!");
			inputNome.requestFocus();
		}
		
		else if(inputLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"Login do usuário obrigatoria!");
			inputLogin.requestFocus();
		}
		
		else if(inputSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null,"Senha do usuário obrigatoria!");
			inputSenha.requestFocus();
		}
		else if(inputEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"email do usuário é obrigatorio!");
			inputEmail.requestFocus();
		}
		
		else {
				try {
					// Estabelecer a conexao
					Connection conexaoBanco = dao.conectar();
					PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);
					// Substituir os pontos de interrogação pelo conteudo das caixas de texto
					// (inputs)
					executarSQL.setString(1, inputNome.getText());
					executarSQL.setString(2, inputLogin.getText());
					executarSQL.setString(3, inputSenha.getText());
					executarSQL.setString(4, inputPerfil.getSelectedItem().toString());
					executarSQL.setString(5, inputEmail.getText());
					
					
					// Executar os comandos SQL e inserir o funcionario no banco de dados
					executarSQL.executeUpdate();
					JOptionPane.showMessageDialog(null, "Usuario cadstrado com sucesso");
					limparCampos();
					conexaoBanco.close();
				}
		
				catch (SQLIntegrityConstraintViolationException error) {
					JOptionPane.showMessageDialog(null, "Login em uso. \nEscolha outro nome de usuário");
					limparCampos();
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
	}

	private void buscarFuncionarioNaTabela() {
		String readTabela = "select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario"
				+ " where nomeFunc like ?;";

		try

		{
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);

			// Substituir o ? pelo conteúdo da caixa de texto
			executarSQL.setString(1, inputNome.getText() + "%");

			// Executar o comando SQL

			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utilização da bibliotece rs2xml para "popular"
			// a tabela

			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCaixasTexto() {

		// Criar uma variavel para receber a linha da tabela

		int setarLinha = tblFuncionarios.getSelectedRow();

		inputNome.setText(tblFuncionarios.getModel().getValueAt(setarLinha, 1).toString());
		inputID.setText(tblFuncionarios.getModel().getValueAt(setarLinha, 0).toString());
	}

	private void btnBuscarFuncionario() {

		String readBtn = "select * from funcionario where idFuncionario = ?;";

		try

		{
			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a execução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readBtn);

			// Substituir o o ponto de interrogação pelo conteúdo da caixa de texto(nome)
			executarSQL.setString(1, inputID.getText());

			
			
			
			// Executar o comando SQL e exibir o resultado no formulario funcionarios(todos
			// os seus dados)

			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				// Preencher os campos do Formulario
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
				inputEmail.setText(resultadoExecucao.getString(6));

			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

	}
	
	//INICIO DO METODO PARA ATUALIZAR FUNCIONARIO
	
	private void atualizarFuncionario() {
		
		String update = "update funcionario set nomeFunc = ?, login = ?, senha = md5(?), email = ?, perfil = ? where idFuncionario = ?;";
		
	try {
		//Estabelecer a conexão
		
		// Estabelecer a conexão
		Connection conexaoBanco = dao.conectar();

		// Preparar a execução dos comandos SQL
		PreparedStatement executarSQL = conexaoBanco.prepareStatement(update);

		//Substituir os pontos de interrogação pelo conteúdo das caixas de texto
		//inputs
		
		executarSQL.setString(1, inputNome.getText());
		executarSQL.setString(2, inputLogin.getText());
		executarSQL.setString(3, inputSenha.getText());
		executarSQL.setString(4, inputEmail.getText());
		executarSQL.setString(5, inputPerfil.getSelectedItem().toString());
		executarSQL.setString(6, inputID.getText());
		
		//Executar os comandos SQL e atualizar o funcionario no banco de dados
		executarSQL.executeUpdate();
		
		JOptionPane.showMessageDialog(null, "usuario atualizado com sucesso!");
		limparCampos();
		
		conexaoBanco.close();
	}
	
	catch(SQLIntegrityConstraintViolationException error) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro. \nO login e/ou email já estão sendo usados");
	}
	
	catch(Exception e) {
		System.out.println(e);
	}

}
	
	
	
	
	private void limparCampos() {
		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		// inputPerfil.setSelectedItem(null);
		inputPerfil.setSelectedIndex(-1);
		inputEmail.setText(null);

		inputNome.requestFocus();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}