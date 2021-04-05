import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

class MyFrame2 extends JFrame {
	public MyFrame2() {
		//super("300x300 스윙 프레임 만들기");
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		
		// FlowLayout bl = new BorderLayout(30, 40);
		BorderLayout bl = new BorderLayout();
		//bl.setHgap(30);
		//bl.setVgap(40);
		
		contentPane.setLayout(bl);
		
		JButton button1 = new JButton();
		button1.setText("east");
		
		JButton button2 = new JButton();
		button2.setText("west");

		JButton button3 = new JButton();
		button3.setText("south");
		
		JButton button4 = new JButton();
		button4.setText("north");
		
		JButton button5 = new JButton();
		button5.setText("center");
		
		// contentPane.add(new JButton("Click1"));
		//contentPane.add(button1);
		contentPane.add(button1, BorderLayout.EAST);
		contentPane.add(button2, BorderLayout.WEST);
		contentPane.add(button3, BorderLayout.SOUTH);
		contentPane.add(button4, BorderLayout.NORTH);
		contentPane.add(button5);
		
		//add(button);
		
		setVisible(true);
	}
}

public class Hello2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame2 frame = new MyFrame2();
		
		//JButton button = new JButton("Click");
		//frame.setContentPane(button);
		
		//frame.add(button);
		
		//frame.revalidate();
		//frame.repaint();
		
	}

}
