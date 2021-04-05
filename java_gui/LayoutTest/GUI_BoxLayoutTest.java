import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame2 extends JFrame {
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JSlider slider;
	public MyFrame2() {
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Frame Test");
		//setBounds(100, 100, 300, 200); // setSize() 대신 사용 가능
		
		Container contentPane = getContentPane();
		
		JPanel p1 = new JPanel();
		BoxLayout fl = new BoxLayout(p1, BoxLayout.Y_AXIS);
		
		//contentPane.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		p1.setLayout(fl);
		p1.setBackground(Color.LIGHT_GRAY);
		
		b1 = new JButton("Button 1");
		b2 = new JButton("Button 2");
		b3 = new JButton("Button 3");
		b4 = new JButton("Button 4");
		
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		
		JPanel p2 = new JPanel();
		
		slider = new JSlider(0, 10);
		
		// setMajorTickSpacing()와 setMinorTickSpacing()에 영향받음
		slider.setSnapToTicks(true);	
		
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		//slider.setLabelTable(table);
		
		//slider.setValueIsAdjusting(true);
		slider.setValue(10);
		slider.setOrientation(SwingConstants.HORIZONTAL);
		
		//slider.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				float pos = (float)(slider.getValue()) / 10.0f;

			    //b1.setAlignmentX(pos);
			    //b2.setAlignmentX(pos);
			    //b3.setAlignmentX(pos);
			    b4.setAlignmentX(pos);

			    //b1.revalidate();
			    //b2.revalidate();
			    //b3.revalidate();
			    b4.revalidate();
			    //b4.invalidate();
			    //b4.repaint();
			    
			}
		});
		
		p2.add(slider);
		
		
		
		contentPane.add(p1, BorderLayout.CENTER);
		contentPane.add(p2, BorderLayout.SOUTH);

		//setLocationRelativeTo(null);	// 윈도우를 화면의 가운데에 띄움
		setVisible(true);
	}
}

public class GUI_BoxLayoutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame2 f = new MyFrame2();
	}

}
