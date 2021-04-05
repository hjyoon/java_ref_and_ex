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
		
		// ��ħ(serif)�� ������ ������ �۲�         Ex) ����ü
		JLabel label1 = new JLabel("SERIF");
		Font f1 = new Font(Font.SERIF, Font.PLAIN, 16);
		label1.setFont(f1);

		// ��ħ(serif)�� ������ �ʴ� ������ �۲�     Ex) ���ü
		JLabel label2 = new JLabel("SANS_SERIF");
		Font f2 = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		label2.setFont(f2);

		// ��ȭ���ڿ��� �ؽ�Ʈ ����� ���Ͽ� ���Ǵ� �۲�
		JLabel label3 = new JLabel("DIALOG");
		Font f3 = new Font(Font.DIALOG, Font.PLAIN, 16);
		label3.setFont(f3);

		// ��ȭ���ڿ��� �ؽ�Ʈ �Է��� ���Ͽ� ���Ǵ� �۲�
		JLabel label4 = new JLabel("DIALOG_INPUT");
		Font f4 = new Font(Font.DIALOG_INPUT, Font.ITALIC, 16);
		label4.setFont(f4);

		// �������� ������ �۲�
		JLabel label5 = new JLabel("MONOSPACED");
		Font f5 = new Font(Font.MONOSPACED, Font.BOLD, 16);
		label5.setFont(f5);

		JLabel label6 = new JLabel("������������� Regular");
		Font f6 = new Font("������������� Regular", Font.BOLD | Font.ITALIC, 16);
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



