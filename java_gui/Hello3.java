import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

class MyFrame3 extends JFrame {
	public MyFrame3() {
		//super("300x300 스윙 프레임 만들기");
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		
		// GridLayout gl = new GridLayout(4, 2);
		// GridLayout gl = new GridLayout(4, 2, 30, 40);
		GridLayout gl = new GridLayout();
		gl.setRows(8);
		gl.setColumns(99999);
		//gl.setHgap(30);
		//gl.setVgap(40);
		
		contentPane.setLayout(gl);
		
		JButton button1 = new JButton();
		button1.setText("1");
		
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

public class Hello3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame3 frame = new MyFrame3();
		
		//JButton button = new JButton("Click");
		//frame.setContentPane(button);
		
		//frame.add(button);
		
		//frame.revalidate();
		//frame.repaint();
		
	}

}
