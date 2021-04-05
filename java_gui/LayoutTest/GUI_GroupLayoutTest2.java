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
        //setBounds(100, 100, 300, 200); // setSize() 대신 사용 가능
        
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
        
        //gl.setAutoCreateGaps(true);   // 컴포넌트들 사이에 갭을 자동으로 넣어줌
        //gl.setAutoCreateContainerGaps(true);  // 컴포넌트와 컨테이너 사이에 갭을 자동으로 넣어줌
        //gl.setHonorsVisibility(false);    // 컴포넌트의 크기 및 위치를 지정할 때 컴포넌트의 가시성을 고려할지 여부를 설정합니다. 
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING) // 두번째 인자로 false를 주면, 해당 그룹의 공간이 최소한의 공간만 가짐.(더 늘어나지 않음) (디폴트는 true)
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
                    //.addComponent(false, yes2Button, 0, GroupLayout.PREFERRED_SIZE, 400) // 세번째 인자부터 순서대로 mix, preferred, max
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
           
        gl.linkSize(SwingConstants.HORIZONTAL, yesButton, noButton);    // 너비가 가장 큰 컴포넌트에 따라 설정됨.
        //gl.linkSize(SwingConstants.VERTICAL, yesButton, noButton);    // 높이가 가장 큰 컴포넌트에 따라 설정됨.

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
        
        //pack(); // setSize() 보다 우선순위가 높음
        setLocationRelativeTo(null);    // 윈도우를 화면의 가운데에 띄움
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
