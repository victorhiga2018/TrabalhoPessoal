package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ConnectionFactory;

public class ViewLogin {

	private JFrame frame;
	private JTextField textFieldNome;
	
	Connection conn = null;
	ResultSet rs = null;
	private JPasswordField passwordFieldSenha;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin window = new ViewLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 404, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblNewLabel.setBounds(135, 21, 105, 41);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNome.setBounds(49, 102, 46, 14);
		frame.getContentPane().add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(111, 100, 201, 20);
		frame.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSenha.setBounds(49, 130, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(111, 128, 201, 20);
		frame.getContentPane().add(passwordFieldSenha);

		JSeparator separator = new JSeparator();
		separator.setBounds(49, 173, 294, 2);
		frame.getContentPane().add(separator);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String query="select * from login where nome_login=? and senha_login=?";
				
				try (Connection conn = ConnectionFactory.getConnection() ;
					PreparedStatement pst = conn.prepareStatement(query);){					
					pst.setString(1, textFieldNome.getText());
					pst.setString(2, passwordFieldSenha.getText());
					rs = pst.executeQuery();
					if(rs.next()) {
						ViewTelaPrincipal viewTelaPrincipal = new ViewTelaPrincipal();
						JOptionPane.showMessageDialog(null, "Bem vindo!");
						
						viewTelaPrincipal.setVisible(true);
						frame.disable();
					
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuario e Senha Incorretos!");
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnEntrar.setBounds(111, 194, 88, 23);
		frame.getContentPane().add(btnEntrar);

		
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame frmLoginSistema = new JFrame("Sair");
				if (JOptionPane.showConfirmDialog(frmLoginSistema, "TEM CERTEZA QUE DESEJA SAIR?",
						"Login Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		
		
		btnSair.setBounds(224, 194, 88, 23);
		frame.getContentPane().add(btnSair);
		
	
	}
}
