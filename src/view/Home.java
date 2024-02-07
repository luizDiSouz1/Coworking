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
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class Home extends JDialog{
	private JButton btnUser;
	
	public Home() {
		setTitle("Home");
		setBounds(new Rectangle(0, 0, 540, 423));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/logo.png")));
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setFont(new Font("Source Code Pro Medium", Font.BOLD, 14));
		getContentPane().setLayout(null);
		
		btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setVerifyInputWhenFocusTarget(false);
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JButton btnReserve = new JButton("");
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(350, 40, 98, 88);
		getContentPane().add(btnReserve);
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
	

