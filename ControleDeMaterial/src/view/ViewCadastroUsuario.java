package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import dao.ConnectionFactory;

public class ViewCadastroUsuario {

	private JFrame frame;
	private JTextField textFieldNomeCadastro;

	/**
	 * Launch the application.
	 */
	
	Connection conn = null;
	ResultSet rs = null;
	private JPasswordField passwordFieldSenha;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroUsuario window = new ViewCadastroUsuario();
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
	public ViewCadastroUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(50, 88, 62, 21);
		frame.getContentPane().add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSenha.setBounds(50, 126, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		textFieldNomeCadastro = new JTextField();
		textFieldNomeCadastro.setBounds(99, 89, 259, 20);
		frame.getContentPane().add(textFieldNomeCadastro);
		textFieldNomeCadastro.setColumns(10);
		

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(99, 124, 259, 20);
		frame.getContentPane().add(passwordFieldSenha);
		
		JLabel lblCadastroDeUsuario = new JLabel("Cadastro de Usuario");
		lblCadastroDeUsuario.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblCadastroDeUsuario.setBounds(112, 32, 216, 33);
		frame.getContentPane().add(lblCadastroDeUsuario);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String query = "INSERT INTO login(nome_login, senha_login) VALUES(?,?)";
				try (Connection conn = ConnectionFactory.getConnection() ;
						PreparedStatement pst = (PreparedStatement) conn.prepareStatement(query);){					
						pst.setString(1, textFieldNomeCadastro.getText());
						pst.setString(2, passwordFieldSenha.getText());
						pst.execute();
						pst.close();
				
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
						ViewTelaPrincipal vtp = new ViewTelaPrincipal();
						vtp.setVisible(true);
						frame.setVisible(false);
						
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
					
				}
			});
		
		btnNewButton.setBounds(99, 184, 123, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(232, 184, 126, 23);
		frame.getContentPane().add(btnCancelar);
		
	}

	public void setVisible(boolean b) {
		ViewCadastroUsuario vcu = new ViewCadastroUsuario();
		vcu.frame.setVisible(true);
		
	}
}
