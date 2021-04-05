import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

class MyFrame extends JFrame {
	public MyFrame() {
		//super("300x300 스윙 프레임 만들기");
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		
		// FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 30, 40);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(30);
		fl.setVgap(40);
		
		contentPane.setLayout(fl);
		
		JButton button1 = new JButton();
		button1.setText("Click1");
		
		JButton button2 = new JButton();
		button2.setText("Click2");

		JButton button3 = new JButton();
		button3.setText("Click3");
		
		JButton button4 = new JButton();
		button4.setText("Click4");
		
		// contentPane.add(new JButton("Click1"));
		contentPane.add(button1);
		contentPane.add(button2);
		contentPane.add(button3);
		contentPane.add(button4);
		
		//add(button);
		
		setVisible(true);
	}
}

public class Hello {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame frame = new MyFrame();
		
		//JButton button = new JButton("Click");
		//frame.setContentPane(button);
		
		//frame.add(button);
		
		//frame.revalidate();
		//frame.repaint();
		
	}

}
