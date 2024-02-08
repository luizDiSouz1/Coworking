package view;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class Salas extends JDialog{
	
	public Salas() {
		setTitle("Salas");
		setResizable (false);
		setBounds(new Rectangle(0, 0, 540, 423));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/room.png"))); 
		getContentPane().setLayout(null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					Salas dialog = new Salas();
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