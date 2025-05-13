package KALKULATORJAVA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class KALKULATOR {

	JFrame frame;
	private JTextField textField;
	double first;
	double second;
	double result;
	String operation;
	String answer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KALKULATOR window = new KALKULATOR();
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
	public KALKULATOR() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 191, 89, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("%");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="%";
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(112, 191, 89, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uF0E7");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String backSpace = null;
				if (textField.getText().length()>0) {
					StringBuilder str = new StringBuilder (textField.getText());
					str.deleteCharAt(textField.getText().length()-1);
					backSpace=str.toString();
					textField.setText(backSpace);
					
				}
			}
		});
		btnNewButton_2.setFont(new Font("Windings", Font.BOLD, 18));
		btnNewButton_2.setBounds(230, 191, 89, 34);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("/");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="/";
			}
		});
		btnNewButton_3.setBounds(335, 191, 89, 34);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_4.getText();
				textField.setText(number);
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_4.setBounds(10, 236, 89, 34);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("4");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_5.getText();
				textField.setText(number);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_5.setBounds(10, 281, 89, 30);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("1");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_6.getText();
				textField.setText(number);
				
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_6.setBounds(10, 322, 89, 36);
		frame.getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("00");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_7.getText();
				textField.setText(number);
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_7.setBounds(10, 369, 89, 35);
		frame.getContentPane().add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("0");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_8.getText();
				textField.setText(number);
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_8.setBounds(112, 369, 89, 35);
		frame.getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("2");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_9.getText();
				textField.setText(number);
			}
			
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_9.setBounds(112, 322, 89, 36);
		frame.getContentPane().add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("5");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_10.getText();
				textField.setText(number);
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_10.setBounds(112, 279, 89, 34);
		frame.getContentPane().add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("8");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_11.getText();
				textField.setText(number);
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_11.setBounds(112, 236, 89, 34);
		frame.getContentPane().add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("9");
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_12.getText();
				textField.setText(number);
			}
		});
		btnNewButton_12.setBounds(230, 236, 89, 34);
		frame.getContentPane().add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("6");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_13.getText();
				textField.setText(number);
			}
		});
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_13.setBounds(230, 281, 89, 30);
		frame.getContentPane().add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("3");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_14.getText();
				textField.setText(number);
			}
		});
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_14.setBounds(230, 322, 89, 36);
		frame.getContentPane().add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton(".");
		btnNewButton_15.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = textField.getText()+btnNewButton_15.getText();
				textField.setText(number);
				
			}
		});
		btnNewButton_15.setBounds(230, 366, 89, 38);
		frame.getContentPane().add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("X");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="*";
			}
		});
		btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_16.setBounds(335, 236, 89, 34);
		frame.getContentPane().add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("-");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	first=Double.parseDouble(textField.getText());
			textField.setText("");
			operation="-";
			}
		});
		btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_17.setBounds(335, 281, 89, 30);
		frame.getContentPane().add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("+");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first=Double.parseDouble(textField.getText());
				textField.setText("");
				operation="+";
			}
		});
		btnNewButton_18.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_18.setBounds(335, 326, 89, 29);
		frame.getContentPane().add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("=");
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args) {
				String answer;
				second =Double.parseDouble(textField.getText());
				if(operation == "+") {
					result=first+second;
					answer= String.format("%.2f", result);
					textField.setText(answer);}
				else if (operation=="-") {
					result=first-second;
					answer= String.format("%.2f", result);
					textField.setText(answer);}
				else if (operation=="*") {
					result=first*second;
					answer= String.format("%.2f", result);
					textField.setText(answer);}
				else if (operation=="/") {
					result=first/second;
					answer= String.format("%.2f", result);
					textField.setText(answer);}
				else if (operation=="%") {
					result=first%second;
					answer= String.format("%.2f", result);
					textField.setText(answer);


					
				}
			}
		});
		btnNewButton_19.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_19.setBounds(335, 369, 89, 35);
		frame.getContentPane().add(btnNewButton_19);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(13, 11, 411, 75);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
