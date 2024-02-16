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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.DateFormat;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JPanel;

public class Home extends JDialog{
	private JButton btnUser;
	public JPanel panelUsuario;
	public JLabel txtUsuarioLogado;
	
	
	JLabel txtData;
	public JLabel txtPerfilLogado;
	
	public Home() {
		addWindowListener(new WindowAdapter(){
			public void windowActivated(WindowEvent e) {
				Date dataSistema = new Date();
				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);
				txtData.setText(formatadorData.format(dataSistema));
			}
		});
		
		setTitle("Home");
		setBounds(new Rectangle(0, 0, 540, 423));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setFont(new Font("Source Code Pro Medium", Font.BOLD, 14));
		getContentPane().setLayout(null);
		
		btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setVerifyInputWhenFocusTarget(false);
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(114, 72, 32, 24);
		getContentPane().add(btnUser);
		
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios user = new Funcionarios();
				user.setVisible(true);
			}
		});
		
	
		
		btnUser.setBounds(46, 40, 98, 88);
		getContentPane().add(btnUser);
		
		JButton btnRoom = new JButton("");
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(200, 40, 89, 88);
		getContentPane().add(btnRoom);
		
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salas user = new Salas();
				user.setVisible(true);
			}
		});
		
		JButton btnReserve = new JButton("");
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(350, 40, 98, 88);
		getContentPane().add(btnReserve);
		
		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 258, 511, 60);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setBounds(10, 0, 129, 24);
		panelUsuario.add(txtUsuarioLogado);
		
		txtData = new JLabel("");
		txtData.setBounds(195, 16, 285, 19);
		panelUsuario.add(txtData);
		
		txtPerfilLogado = new JLabel("");
		txtPerfilLogado.setBounds(10, 35, 152, 14);
		panelUsuario.add(txtPerfilLogado);
		
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 	{
				Reservas user = new Reservas();
				user.setVisible(true);
			}
		});
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Home dialog = new Home();
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
	

