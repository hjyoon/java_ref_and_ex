import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame {
	public MyFrame() {
		setSize(800, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Frame Test");
		
		Container contentPane = getContentPane();
		
		GridLayout gl = new GridLayout();
		gl.setRows(9);
		gl.setColumns(99999);
		
		contentPane.setLayout(gl);
		
		// 삐침(serif)을 가지는 가변폭 글꼴         Ex) 명조체
		JLabel label1 = new JLabel("SERIF");
		Font f1 = new Font(Font.SERIF, Font.PLAIN, 16);
		label1.setFont(f1);

		// 삐침(serif)을 가지지 않는 가변폭 글꼴     Ex) 고딕체
		JLabel label2 = new JLabel("SANS_SERIF");
		Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		label2.setFont(f2);

		// 대화상자에서 텍스트 출력을 위하여 사용되는 글꼴
		JLabel label3 = new JLabel("DIALOG");
		Font f3 = new Font(Font.DIALOG, Font.PLAIN, 16);
		label3.setFont(f3);

		// 대화상자에서 텍스트 입력을 위하여 사용되는 글꼴
		JLabel label4 = new JLabel("DIALOG_INPUT");
		Font f4 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 16);
		label4.setFont(f4);

		// 고정폭을 가지는 글꼴
		JLabel label5 = new JLabel("MONOSPACED");
		Font f5 = new Font(Font.MONOSPACED, Font.BOLD, 16);
		label5.setFont(f5);

		JLabel label6 = new JLabel("나눔스퀘어라운드 Regular");
		Font f6 = new Font("나눔스퀘어라운드 Regular", Font.BOLD | Font.ITALIC, 16);
		label6.setFont(f6);
		
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		contentPane.add(label4);
		contentPane.add(label5);
		contentPane.add(label6);

//		for (int i = 0; i < 10; i++)
//			contentPane.add(new JButton("Button" + i));

		setVisible(true);
	}
}

public class GUI_FontTest {
	public static void main(String[] args) {
		//MyFrame f = new MyFrame();
		MyFrame s = new MyFrame();

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] f = e.getAllFonts();
		for(int i=0; i<f.length; i++) {
			System.out.println(f[i].getFontName());
		}
	}
}



