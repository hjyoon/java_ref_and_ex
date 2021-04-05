import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class MyFrame5 extends JFrame {
	public MyFrame5() {
		//super("300x300 스윙 프레임 만들기");
		setTitle("300x300 스윙 프레임 만들기");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		
		//BoxLayout bl = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		BoxLayout bl = new BoxLayout(contentPane, BoxLayout.X_AXIS);
		//cl.setRows(1);
		//cl.setColumns(99999);
		//bl.setHgap(30);
		//bl.setVgap(40);
		
		contentPane.setLayout(bl);
		
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

public class Hello5 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame5 frame = new MyFrame5();
		
		//JButton button = new JButton("Click");
		//frame.setContentPane(button);
		
		//frame.add(button);
		
		//frame.revalidate();
		//frame.repaint();
		
	}

}
