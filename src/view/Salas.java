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

public class Salas extends JDialog {
	private JTextField inputOcup;

	public Salas() {
		getContentPane().setBackground(new Color(240, 240, 240));
		setTitle("Salas");
		setResizable(false);
		setBounds(100, 100, 614, 395);
		// setBounds(550,250,564,395);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);
		JLabel tipoSala = new JLabel("Categoria:");
		tipoSala.setBounds(18, 54, 52, 14);
		getContentPane().add(tipoSala);
		JLabel codSala = new JLabel("Código:");
		codSala.setBounds(24, 184, 46, 14);
		getContentPane().add(codSala);
		JLabel andarSala = new JLabel("Andar:");
		andarSala.setBounds(299, 184, 36, 14);
		getContentPane().add(andarSala);
		JLabel ocupSala = new JLabel("Ocupação máxima:");
		ocupSala.setBounds(299, 234, 92, 14);
		getContentPane().add(ocupSala);
		JLabel numSala = new JLabel("Número: ");
		numSala.setBounds(24, 271, 46, 14);
		getContentPane().add(numSala);

		inputOcup = new JTextField();
		inputOcup.setColumns(10);
		inputOcup.setBounds(390, 231, 112, 20);
		getContentPane().add(inputOcup);
		btnCreate = new JButton("");
		btnCreate.setBackground(new Color(240, 240, 240));
		btnCreate.setBorderPainted(false);
		btnCreate.setBorder(null);
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.setIcon(new ImageIcon(Salas.class.getResource("/img/create.png")));

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//atualizarFuncionario();
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
		btnUpdate.setIcon(new ImageIcon(Salas.class.getResource("/img/update.png")));
		btnUpdate.setBounds(353, 286, 83, 59);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// adicionarFuncionario();
			}
		});

		getContentPane().add(btnUpdate);

		btnDelete = new JButton("");
		btnDelete.setBackground(new Color(240, 240, 240));
		btnDelete.setBorderPainted(false);
		btnDelete.setBorder(null);
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setIcon(new ImageIcon(Salas.class.getResource("/img/delete.png")));
		btnDelete.setBounds(446, 286, 83, 59);
		getContentPane().add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 73, 362, 71);
		getContentPane().add(scrollPane);

		tblSalas = new JTable();
		scrollPane.setViewportView(tblSalas);

		tblSalas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//setarCaixasTexto();
			}
		});

		JButton btnPesquisar = new JButton("");
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Salas.class.getResource("/img/search.png")));
		btnPesquisar.setBounds(520, 55, 36, 23);
		getContentPane().add(btnPesquisar);

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//btnBuscarFuncionario();
			}
		});

		JLabel IDSala = new JLabel("ID:");
		IDSala.setBounds(446, 58, 15, 14);
		getContentPane().add(IDSala);

		inputID = new JTextField();
		inputID.setEnabled(false);
		inputID.setBounds(464, 55, 46, 20);
		getContentPane().add(inputID);
		inputID.setColumns(10);
		
		inputCategoria = new JComboBox();
		inputCategoria.setModel(new DefaultComboBoxModel(new String[] {"", "Sala de reunião", "Sala de conferência", "Espaço de eventos", "Escritório privado"}));
		inputCategoria.setBounds(74, 50, 362, 22);
		getContentPane().add(inputCategoria);
		
		inputCod = new JComboBox();
		inputCod.setModel(new DefaultComboBoxModel(new String[] {"", "REU", "CONF", "EVENT", "PRIVE"}));
		inputCod.setBounds(62, 180, 146, 22);
		getContentPane().add(inputCod);
		
		inputAndar = new JComboBox();
		inputAndar.setModel(new DefaultComboBoxModel(new String[] {"", "Subsolo", "Térreo", "1° andar", "2° andar", "3° andar"}));
		inputAndar.setBounds(341, 180, 165, 22);
		getContentPane().add(inputAndar);
		
		inputNum = new JTextField();
		inputNum.setBounds(71, 268, 86, 20);
		getContentPane().add(inputNum);
		inputNum.setColumns(10);

	}

	DAO dao = new DAO();
	public JButton btnCreate;
	public JButton btnUpdate;
	public JButton btnDelete;
	private JTable tblSalas;
	private JTextField inputID;
	private JComboBox inputCategoria;
	private JComboBox inputCod;
	private JComboBox inputAndar;
	private JTextField inputNum;

	/* private void adicionarFuncionario() {
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
		else if(inputOcup.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"email do usuário é obrigatorio!");
			inputOcup.requestFocus();
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
					executarSQL.setString(5, inputOcup.getText());
					
					
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

	private void setarCaixasTexto() {

		// Criar uma variavel para receber a linha da tabela

		int setarLinha = tblSalas.getSelectedRow();

		inputNome.setText(tblSalas.getModel().getValueAt(setarLinha, 1).toString());
		inputID.setText(tblSalas.getModel().getValueAt(setarLinha, 0).toString());
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

			
			
			
			// Executar o comando SQL e exibir o resultado no formulario Salas(todos
			// os seus dados)

			ResultSet resultadoExecucao = executarSQL.executeQuery();

			if (resultadoExecucao.next()) {
				// Preencher os campos do Formulario
				inputLogin.setText(resultadoExecucao.getString(3));
				inputSenha.setText(resultadoExecucao.getString(4));
				inputPerfil.setSelectedItem(resultadoExecucao.getString(5));
				inputOcup.setText(resultadoExecucao.getString(6));

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
		executarSQL.setString(4, inputOcup.getText());
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
		inputOcup.setText(null);

		inputNome.requestFocus();
	} */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Salas dialog = new Salas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}