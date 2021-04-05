import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame5 extends JFrame {
	Container contentPane;
	GroupLayout gl;
	
	JLabel label;
    JTextField textField;
    JCheckBox caseCheckBox;
    JCheckBox wrapCheckBox;
    JCheckBox wholeCheckBox;
    JCheckBox backCheckBox;
    JButton findButton;
    JButton cancelButton;
	
	public MyFrame5() {
		//setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Frame Test");
		//setBounds(100, 100, 300, 200); // setSize() ��� ��� ����
		
		contentPane = getContentPane();
		
		label = new JLabel("Find What:");
		textField = new JTextField();
		caseCheckBox = new JCheckBox("Match Case");
		wrapCheckBox = new JCheckBox("Wrap Around");
		wholeCheckBox = new JCheckBox("Whole Words");
		backCheckBox = new JCheckBox("Search Backwards");
		findButton = new JButton("Find");
		cancelButton = new JButton("Cancel");

        //Font test_f = findButton.getFont();
        //test_f.deriveFont(20);
        //findButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        
        gl = new GroupLayout(contentPane);
		
		contentPane.setLayout(gl);
		
		gl.setAutoCreateGaps(true);   // ������Ʈ�� ���̿� ���� �ڵ����� �־���
		gl.setAutoCreateContainerGaps(true);  // ������Ʈ�� �����̳� ���̿� ���� �ڵ����� �־���
        //gl.setHonorsVisibility(false);    // ������Ʈ�� ũ�� �� ��ġ�� ������ �� ������Ʈ�� ���ü��� ������� ���θ� �����մϴ�. 
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addComponent(label)
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(textField)
                .addGroup(gl.createSequentialGroup()
                    .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(caseCheckBox)
                        .addComponent(wholeCheckBox)
                    )
                    .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(wrapCheckBox)
                        .addComponent(backCheckBox)
                    )
                )
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(findButton)
                .addComponent(cancelButton)
            )
        );
           
        gl.linkSize(SwingConstants.HORIZONTAL, findButton, cancelButton);

        gl.setVerticalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label)
                .addComponent(textField)
                .addComponent(findButton)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl.createSequentialGroup()
                    .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(caseCheckBox)
                        .addComponent(wrapCheckBox)
                    )
                    .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(wholeCheckBox)
                        .addComponent(backCheckBox)
                    )
                )
                .addComponent(cancelButton)
            )
        );
		
		pack();
		setLocationRelativeTo(null);	// �����츦 ȭ���� ����� ���
		setVisible(true); 
	}
}

public class GUI_GroupLayoutTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MyFrame5 f = new MyFrame5();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame5 f = new MyFrame5();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
