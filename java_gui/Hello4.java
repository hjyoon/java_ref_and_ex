import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyFrame4 extends JFrame {
	public MyFrame4() {
		//super("300x300 스윙 프레임 만들기");
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		
		// GridLayout gl = new GridLayout(4, 2);
		// GridLayout gl = new GridLayout(4, 2, 30, 40);
		CardLayout cl = new CardLayout();
		//cl.setRows(1);
		//cl.setColumns(99999);
		cl.setHgap(30);
		cl.setVgap(40);
		
		contentPane.setLayout(cl);
		
		JButton button1 = new JButton();
		button1.setText("1");
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.next(contentPane);
			}
		};
		
		JButton button2 = new JButton();
		button2.setText("2");

		JButton button3 = new JButton();
		button3.setText("3");
		
		JButton button4 = new JButton();
		button4.setText("4");
		
		JButton button5 = new JButton();
		button5.setText("5");
		
		JButton button6 = new JButton();
		button6.setText("6");
		
		button1.addActionListener(al);
		button2.addActionListener(al);
		button3.addActionListener(al);
		button4.addActionListener(al);
		button5.addActionListener(al);
		button6.addActionListener(al);
		
		// contentPane.add(new JButton("Click1"));
		//contentPane.add(button1);
		contentPane.add(button1);
		contentPane.add(button2);
		contentPane.add(button3);
		contentPane.add(button4);
		contentPane.add(button5);
		contentPane.add(button6);
		
		//add(button);
		
		setVisible(true);
	}
}

public class Hello4 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame4 frame = new MyFrame4();
		
		//JButton button = new JButton("Click");
		//frame.setContentPane(button);
		
		//frame.add(button);
		
		//frame.revalidate();
		//frame.repaint();
		
	}

}
