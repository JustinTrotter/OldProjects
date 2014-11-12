/* 
*    Name:  Justin Trotter
*    Current Date:  4/10/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Driver implements ActionListener {
	int result = 0;

	JFrame frame1 = new JFrame("Abacus");
    
	JPanel panel0 = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JLabel label0 = new JLabel("Enter Two Numbers:");
	JLabel label4 = new JLabel("");
	JLabel label1 = new JLabel("Enter First Number:");
	JLabel label2 = new JLabel("Enter Second Number:");
	JLabel label3 = new JLabel("Total:");
	JLabel label5 = new JLabel("" + result);
	
	JTextField text0 = new JTextField("0", 20);
	JTextField text1 = new JTextField("0", 20);
	JButton button0 = new JButton("Calculate");
	
	public Driver()
	{
		panel0.setLayout(new GridLayout(4,2));
		panel0.add(label0);
		panel0.add(label4);
		panel0.add(label1);
		panel0.add(text0);
		panel0.add(label2);
		panel0.add(text1);
		panel0.add(label3);
		panel0.add(label5);

		panel1.add(button0);
		button0.addActionListener(this);
		
		panel2.add(panel0);
		panel2.add(panel1);
	
		frame1.add(panel2);
		frame1.setSize(475,175);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame1.setVisible(true);
	}
	public static void main(String[] args){
		new Driver();
	}
	
	public void actionPerformed(ActionEvent e) {
		int first = Integer.parseInt(text0.getText());
		int second = Integer.parseInt(text1.getText());
		int result = first + second;
		label5.setText("" + result);
	}
	
}
