import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame5 extends JFrame {
    Container contentPane;
    GroupLayout gl;
    
    JLabel label;
    JTextField textField;
    JTextArea textArea;
    JTextArea textArea2;
    JButton yesButton;
    JButton yes2Button;
    JButton yes3Button;
    JButton yes4Button;
    JButton noButton;
    JButton cancelButton;
    
    public MyFrame5() {
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Frame Test");
        //setBounds(100, 100, 300, 200); // setSize() ��� ��� ����
        
        contentPane = getContentPane();
        
        label = new JLabel("Are you OK?????????????????????");
        textField = new JTextField();
        textArea = new JTextArea();
        textArea2 = new JTextArea();
        yesButton = new JButton("Yes");
        yes2Button = new JButton("Yes2");
        yes3Button = new JButton("Yes3");
        yes4Button = new JButton("Yes4");
        noButton = new JButton("No");
        cancelButton = new JButton("Cancel");

        yes3Button.setFont(new Font(Font.SERIF, Font.PLAIN, 50));
        
        gl = new GroupLayout(contentPane);
        
        contentPane.setLayout(gl);
        
        //gl.setAutoCreateGaps(true);   // ������Ʈ�� ���̿� ���� �ڵ����� �־���
        //gl.setAutoCreateContainerGaps(true);  // ������Ʈ�� �����̳� ���̿� ���� �ڵ����� �־���
        //gl.setHonorsVisibility(false);    // ������Ʈ�� ũ�� �� ��ġ�� ������ �� ������Ʈ�� ���ü��� ������� ���θ� �����մϴ�. 
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING) // �ι�° ���ڷ� false�� �ָ�, �ش� �׷��� ������ �ּ����� ������ ����.(�� �þ�� ����) (����Ʈ�� true)
                .addComponent(textArea)
                .addGroup(gl.createSequentialGroup()
                    //.addContainerGap()
                    .addComponent(yesButton)
                    //.addPreferredGap(yes3Button, yes4Button, LayoutStyle.ComponentPlacement.UNRELATED, 50,100)
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,50,100)
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,50,100)
                    //.addGap(20).addGap(20).addGap(20)
                    //.addGap(10, 50, 100)
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,10,50)
                    //.addContainerGap()
                    //.addComponent(yes2Button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    //.addComponent(false, yes2Button, 0, GroupLayout.PREFERRED_SIZE, 400) // ����° ���ں��� ������� mix, preferred, max
                    .addComponent(yes2Button, 0, GroupLayout.PREFERRED_SIZE, 400)
                    .addComponent(yes3Button, 0, GroupLayout.PREFERRED_SIZE, 400)
                    //.addContainerGap()
                    .addComponent(yes4Button)
                    //.addContainerGap()
                )
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(noButton)
                .addComponent(textArea2)
            )
        );
           
        gl.linkSize(SwingConstants.HORIZONTAL, yesButton, noButton);    // �ʺ� ���� ū ������Ʈ�� ���� ������.
        //gl.linkSize(SwingConstants.VERTICAL, yesButton, noButton);    // ���̰� ���� ū ������Ʈ�� ���� ������.

        gl.setVerticalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(textArea)
                .addComponent(noButton)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl.createSequentialGroup()
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(yesButton)
                        .addComponent(yes2Button, 0, GroupLayout.PREFERRED_SIZE, 400)
                        .addComponent(yes3Button, 0, GroupLayout.PREFERRED_SIZE, 400)
                        .addComponent(yes4Button)
                    )
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                )
                .addGroup(gl.createSequentialGroup()
                    .addComponent(textArea2)
                )
            )
        );
        
        //pack(); // setSize() ���� �켱������ ����
        setLocationRelativeTo(null);    // �����츦 ȭ���� ����� ���
        setVisible(true); 
    }
}

public class GUI_GroupLayoutTest2 {

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
