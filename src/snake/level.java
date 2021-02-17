package snake;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Label;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class level extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					level frame = new level();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public level() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 153));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecione o nível");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel.setBounds(153, 66, 181, 32);
		panel.add(lblNewLabel);
		
		JButton btnEasy = new JButton("F\u00E1cil");
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Tela.tamTabuleiro = 60;
				Tela.velocidadeSnake = 125;
				Tela.dificuldade = 1;
				Tela.main(null);
			}
		});
		btnEasy.setForeground(Color.WHITE);
		btnEasy.setBackground(Color.DARK_GRAY);
		btnEasy.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnEasy.setBounds(163, 109, 120, 23);
		panel.add(btnEasy);
		
		JLabel lblNewLabel_1 = new JLabel("Snake");
		lblNewLabel_1.setFont(new Font("Britannic Bold", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(180, 30, 88, 38);
		panel.add(lblNewLabel_1);
		
		JButton btnMedium = new JButton("M\u00E9dio");
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Tela.tamTabuleiro = 50;
				Tela.velocidadeSnake = 100;
				Tela.dificuldade = 2;
				Tela.main(null);
			}
		});
		btnMedium.setForeground(Color.WHITE);
		btnMedium.setBackground(Color.DARK_GRAY);
		btnMedium.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnMedium.setBounds(163, 143, 120, 23);
		panel.add(btnMedium);
		
		JButton btnHard = new JButton("Dif\u00EDcil");
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Tela.tamTabuleiro = 40;
				Tela.velocidadeSnake = 75;
				Tela.dificuldade = 3;
				Tela.main(null);
				
			}
		});
		btnHard.setForeground(Color.WHITE);
		btnHard.setBackground(Color.DARK_GRAY);
		btnHard.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnHard.setBounds(163, 175, 120, 23);
		panel.add(btnHard);
	}
}
