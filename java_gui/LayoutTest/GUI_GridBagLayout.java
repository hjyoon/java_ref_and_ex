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
		//setBounds(100, 100, 300, 200); // setSize() 대신 사용 가능
		
		contentPane = getContentPane();
		
		gbl= new GridBagLayout();
		constraint = new GridBagConstraints();
		
		contentPane.setLayout(gbl);
		
		// 여백을 분배하는 변수, 모두 0이면 가운데로 모임.
		//constraint.ipadx = 0;  // default // internal padding 설정 ??
		//constraint.ipady = 0;  // default
		//constraint.anchor = GridBagConstraints.LAST_LINE_START;
		constraint.anchor = GridBagConstraints.WEST;
		//constraint.anchor = GridBagConstraints.SOUTHWEST;
		
		// 각각 top, left, bottom, right
		// Insets객체는 컨테이너의 경계를 표현한 것입니다.
		// 이것은 컨테이너가 각 엣지에 남기지 않으면 안 되는 공간을 지정합니다.
		// 공간은 경계, 공백 공간, 또는 타이틀입니다.
		constraint.insets = new Insets(10,10,10,10); // external padding 설정 ??
		
		constraint.fill = GridBagConstraints.BOTH;
		//constraint.fill = GridBagConstraints.HORIZONTAL;
		//constraint.fill = GridBagConstraints.VERTICAL;
		//constraint.fill = GridBagConstraints.NONE; // default
		//constraint.weightx = 0.0; // default
		//constraint.weighty = 0.0; // default
		
		constraint.weightx = 1.0;	// 0.0 또는 1.0 의 값, 분할지정비율?
		constraint.weighty = 1.0; // 0.0 또는 1.0 의 값, 분할지정비율?
		constraint.gridx = 0; // 시작위치 x
		constraint.gridy = 0; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b1 = new JButton("0,0");
		contentPane.add(b1, constraint);
		
		constraint.gridx = 1; // 시작위치 x
		constraint.gridy = 0; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b2 = new JButton("1,0");
		contentPane.add(b2, constraint);
		
		constraint.gridx = 2; // 시작위치 x
		constraint.gridy = 0; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b3 = new JButton("2,0");
		contentPane.add(b3, constraint);
		
		constraint.gridx = 3; // 시작위치 x
		constraint.gridy = 0; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b4 = new JButton("3,0");
		contentPane.add(b4, constraint);
		
		constraint.gridx = 4; // 시작위치 x
		constraint.gridy = 0; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b5 = new JButton("4,0");
		contentPane.add(b5, constraint);
		
		constraint.gridx = 0; // 시작위치 x
		constraint.gridy = 1; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b6 = new JButton("0,1");
		contentPane.add(b6, constraint);
		
		constraint.gridx = 0; // 시작위치 x
		constraint.gridy = 2; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b7 = new JButton("0,2");
		contentPane.add(b7, constraint);
		
		constraint.gridx = 0; // 시작위치 x
		constraint.gridy = 3; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b8 = new JButton("0,3");
		contentPane.add(b8, constraint);
		
		constraint.gridx = 0; // 시작위치 x
		constraint.gridy = 4; // 시작위치 y
		constraint.gridwidth = 1; // 컨테이너 너비
		constraint.gridheight = 1; // 컨테이너 높이
		
		JButton b9 = new JButton("0,4");
		contentPane.add(b9, constraint);
		
		constraint.anchor = GridBagConstraints.WEST;
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 1.0;	// 0.0 ~ 1.0 의 값, 분할지정비율?
		constraint.weighty = 1.0; // 0.0 ~ 1.0 의 값, 분할지정비율?
		//constraint.ipadx = 100;  // default // internal padding 설정 ??
		//constraint.ipady = 20;  // default
		constraint.gridx = 1; // 시작위치 x
		constraint.gridy = 1; // 시작위치 y
		constraint.gridwidth = 2; // 컨테이너 너비
		constraint.gridheight = 2; // 컨테이너 높이
		
		
		JTextField test1 = new JTextField(10);
		contentPane.add(test1, constraint);
		
		constraint.anchor = GridBagConstraints.EAST;
		constraint.weightx = 1.0;	// 0.0 또는 1.0 의 값을 가지는듯?
		constraint.weighty = 1.0; // 0.0 또는 1.0 의 값을 가지는듯?
		constraint.ipadx = 0;  // default // internal padding 설정 ??
		constraint.ipady = 0;  // default
		constraint.gridx = 1; // 시작위치 x
		constraint.gridy = 1; // 시작위치 y
		constraint.gridwidth = 2; // 컨테이너 너비
		constraint.gridheight = 2; // 컨테이너 높이
		
		JButton test2 = new JButton("TEST2");
		contentPane.add(test2, constraint);

		setLocationRelativeTo(null);	// 윈도우를 화면의 가운데에 띄움
		setVisible(true);
	}
}

public class GUI_GridBagLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame3 f = new MyFrame3();
	}

}
