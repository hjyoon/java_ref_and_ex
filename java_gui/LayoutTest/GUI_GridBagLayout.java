import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame3 extends JFrame {
	Container contentPane;
	GridBagLayout gbl;
	GridBagConstraints constraint;
	
	public MyFrame3() {
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Frame Test");
		//setBounds(100, 100, 300, 200); // setSize() ��� ��� ����
		
		contentPane = getContentPane();
		
		gbl= new GridBagLayout();
		constraint = new GridBagConstraints();
		
		contentPane.setLayout(gbl);
		
		// ������ �й��ϴ� ����, ��� 0�̸� ����� ����.
		//constraint.ipadx = 0;  // default // internal padding ���� ??
		//constraint.ipady = 0;  // default
		//constraint.anchor = GridBagConstraints.LAST_LINE_START;
		constraint.anchor = GridBagConstraints.WEST;
		//constraint.anchor = GridBagConstraints.SOUTHWEST;
		
		// ���� top, left, bottom, right
		// Insets��ü�� �����̳��� ��踦 ǥ���� ���Դϴ�.
		// �̰��� �����̳ʰ� �� ������ ������ ������ �� �Ǵ� ������ �����մϴ�.
		// ������ ���, ���� ����, �Ǵ� Ÿ��Ʋ�Դϴ�.
		constraint.insets = new Insets(10,10,10,10); // external padding ���� ??
		
		constraint.fill = GridBagConstraints.BOTH;
		//constraint.fill = GridBagConstraints.HORIZONTAL;
		//constraint.fill = GridBagConstraints.VERTICAL;
		//constraint.fill = GridBagConstraints.NONE; // default
		//constraint.weightx = 0.0; // default
		//constraint.weighty = 0.0; // default
		
		constraint.weightx = 1.0;	// 0.0 �Ǵ� 1.0 �� ��, ������������?
		constraint.weighty = 1.0; // 0.0 �Ǵ� 1.0 �� ��, ������������?
		constraint.gridx = 0; // ������ġ x
		constraint.gridy = 0; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b1 = new JButton("0,0");
		contentPane.add(b1, constraint);
		
		constraint.gridx = 1; // ������ġ x
		constraint.gridy = 0; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b2 = new JButton("1,0");
		contentPane.add(b2, constraint);
		
		constraint.gridx = 2; // ������ġ x
		constraint.gridy = 0; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b3 = new JButton("2,0");
		contentPane.add(b3, constraint);
		
		constraint.gridx = 3; // ������ġ x
		constraint.gridy = 0; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b4 = new JButton("3,0");
		contentPane.add(b4, constraint);
		
		constraint.gridx = 4; // ������ġ x
		constraint.gridy = 0; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b5 = new JButton("4,0");
		contentPane.add(b5, constraint);
		
		constraint.gridx = 0; // ������ġ x
		constraint.gridy = 1; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b6 = new JButton("0,1");
		contentPane.add(b6, constraint);
		
		constraint.gridx = 0; // ������ġ x
		constraint.gridy = 2; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b7 = new JButton("0,2");
		contentPane.add(b7, constraint);
		
		constraint.gridx = 0; // ������ġ x
		constraint.gridy = 3; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b8 = new JButton("0,3");
		contentPane.add(b8, constraint);
		
		constraint.gridx = 0; // ������ġ x
		constraint.gridy = 4; // ������ġ y
		constraint.gridwidth = 1; // �����̳� �ʺ�
		constraint.gridheight = 1; // �����̳� ����
		
		JButton b9 = new JButton("0,4");
		contentPane.add(b9, constraint);
		
		constraint.anchor = GridBagConstraints.WEST;
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1.0;	// 0.0 ~ 1.0 �� ��, ������������?
		constraint.weighty = 1.0; // 0.0 ~ 1.0 �� ��, ������������?
		//constraint.ipadx = 100;  // default // internal padding ���� ??
		//constraint.ipady = 20;  // default
		constraint.gridx = 1; // ������ġ x
		constraint.gridy = 1; // ������ġ y
		constraint.gridwidth = 2; // �����̳� �ʺ�
		constraint.gridheight = 2; // �����̳� ����
		
		
		JTextField test1 = new JTextField(10);
		contentPane.add(test1, constraint);
		
		constraint.anchor = GridBagConstraints.EAST;
		constraint.weightx = 1.0;	// 0.0 �Ǵ� 1.0 �� ���� �����µ�?
		constraint.weighty = 1.0; // 0.0 �Ǵ� 1.0 �� ���� �����µ�?
		constraint.ipadx = 0;  // default // internal padding ���� ??
		constraint.ipady = 0;  // default
		constraint.gridx = 1; // ������ġ x
		constraint.gridy = 1; // ������ġ y
		constraint.gridwidth = 2; // �����̳� �ʺ�
		constraint.gridheight = 2; // �����̳� ����
		
		JButton test2 = new JButton("TEST2");
		contentPane.add(test2, constraint);

		setLocationRelativeTo(null);	// �����츦 ȭ���� ����� ���
		setVisible(true);
	}
}

public class GUI_GridBagLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame3 f = new MyFrame3();
	}

}
