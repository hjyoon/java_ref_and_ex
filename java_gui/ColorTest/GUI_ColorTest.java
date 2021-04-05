import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame {
	public MyFrame() {
		setSize(450, 225);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Frame Test");
		
		Container contentPane = getContentPane();
		
		//FlowLayout fl = new FlowLayout();
		GridLayout gl = new GridLayout();
		gl.setRows(4);
		gl.setColumns(99999);
		
		contentPane.setLayout(gl);
		
		JButton b1 = new JButton("BLACK");
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.BLACK);

		JButton b2 = new JButton("WHITE");
		b2.setForeground(Color.WHITE);
		b2.setBackground(Color.WHITE);

		JButton b3 = new JButton("RED");
		b3.setForeground(Color.RED);
		b3.setBackground(Color.RED);

		JButton b4 = new JButton("GREEN");
		b4.setForeground(Color.GREEN);
		b4.setBackground(Color.GREEN);

		JButton b5 = new JButton("BLUE");
		b5.setForeground(Color.BLUE);
		b5.setBackground(Color.BLUE);

		JButton b6 = new JButton("YELLOW");
		b6.setForeground(Color.YELLOW);
		b6.setBackground(Color.YELLOW);

		JButton b7 = new JButton("CYAN");
		b7.setForeground(Color.CYAN);
		b7.setBackground(Color.CYAN);

		JButton b8 = new JButton("MAGENTA");
		b8.setForeground(Color.MAGENTA);
		b8.setBackground(Color.MAGENTA);

		JButton b9 = new JButton("GRAY");
		b9.setForeground(Color.GRAY);
		b9.setBackground(Color.GRAY);

		JButton b10 = new JButton("LIGHT_GRAY");
		b10.setForeground(Color.LIGHT_GRAY);
		b10.setBackground(Color.LIGHT_GRAY);

		JButton b11 = new JButton("DARK_GRAY");
		b11.setForeground(Color.DARK_GRAY);
		b11.setBackground(Color.DARK_GRAY);

		JButton b12 = new JButton("PINK");
		b12.setForeground(Color.PINK);
		b12.setBackground(Color.PINK);

		JButton b13 = new JButton("ORANGE");
		b13.setForeground(Color.ORANGE);
		b13.setBackground(Color.ORANGE);

		/*
		Color.BLACK : new Color(0,0,0)
		Color.WHITE : new Color(255,255,255)
		Color.RED : new Color(255,0,0)
		Color.GREEN : new Color(0,255,0)
		Color.BLUE : new Color(0,0,255)
		Color.YELLOW : new Color(255,255,0)
		Color.CYAN : new Color(0,255,255)
		Color.MAGENTA : new Color(255,0,255)
		Color.GRAY : new Color(128,128,128)
		Color.LIGHT_GRAY : new Color(192,192,192)
		Color.DARK_GRAY : new Color(64,64,64)
		Color.PINK : new Color(255, 175, 175)
		Color.ORANGE : new Color(255,200,0);
		*/
		
		contentPane.add(b1);
		contentPane.add(b2);
		contentPane.add(b3);
		contentPane.add(b4);
		contentPane.add(b5);
		contentPane.add(b6);
		contentPane.add(b7);
		contentPane.add(b8);
		contentPane.add(b9);
		contentPane.add(b10);
		contentPane.add(b11);
		contentPane.add(b12);
		contentPane.add(b13);

		setVisible(true);
	}
}

public class GUI_ColorTest {
	public static void main(String[] args) {
		//MyFrame f = new MyFrame();
		MyFrame s = new MyFrame();
	}
}



