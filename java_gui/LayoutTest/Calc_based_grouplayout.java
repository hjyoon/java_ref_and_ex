import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame5 extends JFrame {
    Container contentPane;      // ����Ʈ���� �����ϱ� ���� ����
    GroupLayout gl;     // ���̾ƿ��� �����ϱ� ���� ����
    
    // ��������� ��ư ����
    JLabel label;       // ���� �Է��� ���ڸ� ǥ���ϴ� ���̺�
    JLabel expression;  // ������ �Է��� ���� ǥ���ϴ� ���̺�
    JButton button_0;   // ���� 0 ��ư
    JButton button_1;   // ���� 1 ��ư
    JButton button_2;   // ���� 2 ��ư
    JButton button_3;   // ���� 3 ��ư
    JButton button_4;   // ���� 4 ��ư
    JButton button_5;   // ���� 5 ��ư
    JButton button_6;   // ���� 6 ��ư
    JButton button_7;   // ���� 7 ��ư
    JButton button_8;   // ���� 8 ��ư
    JButton button_9;   // ���� 9 ��ư
    JButton sign;   // ��ȣ ��ư
    JButton dot;    // �Ҽ��� ��ư
    JButton equal;  // = ��ư
    JButton plus;   // + ��ư
    JButton minus;  // - ��ư
    JButton mul;    // x ��ư
    JButton div;    // / ��ư
    JButton del;    // del ��ư
    JButton reset;  // reset ��ư
    JButton cls;    // clear ��ư

    // ��ư�� ũ�⸦ �����ϱ� ���� ����
    final static int btnWidth = 90;
    final static int btnHeight = 90;
    final static int btnTestSize = 25;     

    double acc = 0;             // ������ ������� ������� ����
    boolean ready = false;      // ���ڸ� �Է¹ޱ����� �����ϴ� ���°�
    char last_operator = 0;     // ���������� �Է��� �����ڸ� ����
    double last_operand = 0;    // ���������� �Է��� ���� ����
    
    public MyFrame5() {
        //setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MyCalc");
        //setBounds(100, 100, 300, 200); // setSize() ��� ��� ����
        
        contentPane = getContentPane();
        
        // ��ư�� ��ü�� �Ҵ��Ѵ�.
        label = new JLabel("0", SwingConstants.RIGHT);
        expression = new JLabel("", SwingConstants.RIGHT);
        button_0 = new JButton("0");
        button_1 = new JButton("1");
        button_2 = new JButton("2");
        button_3 = new JButton("3");
        button_4 = new JButton("4");
        button_5 = new JButton("5");
        button_6 = new JButton("6");
        button_7 = new JButton("7");
        button_8 = new JButton("8");
        button_9 = new JButton("9");
        sign = new JButton("+/-");
        dot = new JButton(".");
        equal = new JButton("=");
        plus = new JButton("+");
        minus = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        del = new JButton("del");
        reset = new JButton("reset");
        cls = new JButton("clear");

        // �ؽ�Ʈ�� ��Ʈ�� ����
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        expression.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        cls.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        reset.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        del.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        div.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        button_7.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_8.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_9.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        mul.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        button_4.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_5.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_6.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        minus.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        button_1.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_2.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        button_3.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        plus.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        sign.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        button_0.setFont(new Font(Font.DIALOG, Font.BOLD, btnTestSize));
        dot.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));
        equal.setFont(new Font(Font.DIALOG, Font.PLAIN, btnTestSize));

        // ��ư ���� ����
        expression.setForeground(Color.GRAY);
        cls.setForeground(Color.BLACK);
        cls.setBackground(Color.LIGHT_GRAY);
        reset.setForeground(Color.BLACK);
        reset.setBackground(Color.LIGHT_GRAY);
        del.setForeground(Color.BLACK);
        del.setBackground(Color.LIGHT_GRAY);
        div.setForeground(Color.BLACK);
        div.setBackground(Color.LIGHT_GRAY);
        button_7.setForeground(Color.BLACK);
        button_7.setBackground(Color.WHITE);
        button_8.setForeground(Color.BLACK);
        button_8.setBackground(Color.WHITE);
        button_9.setForeground(Color.BLACK);
        button_9.setBackground(Color.WHITE);
        mul.setForeground(Color.BLACK);
        mul.setBackground(Color.LIGHT_GRAY);
        button_4.setForeground(Color.BLACK);
        button_4.setBackground(Color.WHITE);
        button_5.setForeground(Color.BLACK);
        button_5.setBackground(Color.WHITE);
        button_6.setForeground(Color.BLACK);
        button_6.setBackground(Color.WHITE);
        minus.setForeground(Color.BLACK);
        minus.setBackground(Color.LIGHT_GRAY);
        button_1.setForeground(Color.BLACK);
        button_1.setBackground(Color.WHITE);
        button_2.setForeground(Color.BLACK);
        button_2.setBackground(Color.WHITE);
        button_3.setForeground(Color.BLACK);
        button_3.setBackground(Color.WHITE);
        plus.setForeground(Color.BLACK);
        plus.setBackground(Color.LIGHT_GRAY);
        sign.setForeground(Color.BLACK);
        sign.setBackground(Color.LIGHT_GRAY);
        button_0.setForeground(Color.BLACK);
        button_0.setBackground(Color.WHITE);
        dot.setForeground(Color.BLACK);
        dot.setBackground(Color.LIGHT_GRAY);
        equal.setForeground(Color.BLACK);
        equal.setBackground(Color.LIGHT_GRAY);

        // ��ư�� �̺�Ʈ �����ʸ� �Ҵ��Ѵ�.
        cls.addActionListener(new MyActionListener());
        reset.addActionListener(new MyActionListener());
        del.addActionListener(new MyActionListener());
        div.addActionListener(new MyActionListener());
        button_7.addActionListener(new MyActionListener());
        button_8.addActionListener(new MyActionListener());
        button_9.addActionListener(new MyActionListener());
        mul.addActionListener(new MyActionListener());
        button_4.addActionListener(new MyActionListener());
        button_5.addActionListener(new MyActionListener());
        button_6.addActionListener(new MyActionListener());
        minus.addActionListener(new MyActionListener());
        button_1.addActionListener(new MyActionListener());
        button_2.addActionListener(new MyActionListener());
        button_3.addActionListener(new MyActionListener());
        plus.addActionListener(new MyActionListener());
        sign.addActionListener(new MyActionListener());
        button_0.addActionListener(new MyActionListener());
        dot.addActionListener(new MyActionListener());
        equal.addActionListener(new MyActionListener());
        
        // GroupLayout ��ü ����
        gl = new GroupLayout(contentPane);
        
        // GroupLayout ���� �Ѵ�
        contentPane.setLayout(gl);
        
        gl.setAutoCreateGaps(true);   // ������Ʈ�� ���̿� ���� �ڵ����� �־���
        gl.setAutoCreateContainerGaps(true);  // ������Ʈ�� �����̳� ���̿� ���� �ڵ����� �־���
        //gl.setHonorsVisibility(false);    // ������Ʈ�� ũ�� �� ��ġ�� ������ �� ������Ʈ�� ���ü��� ������� ���θ� �����մϴ�. 
        
        // ������Ʈ�� ���� ��ġ�� ����
        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(expression, 0, 0, Short.MAX_VALUE)
                .addComponent(label, 0, 0, Short.MAX_VALUE)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(gl.createSequentialGroup()
                        .addComponent(cls, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(reset, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(del, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(div, 0, btnWidth, Short.MAX_VALUE)
                    )
                    .addGroup(gl.createSequentialGroup()
                        .addComponent(button_7, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_8, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_9, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(mul, 0, btnWidth, Short.MAX_VALUE)
                    )
                    .addGroup(gl.createSequentialGroup()
                        .addComponent(button_4, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_5, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_6, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(minus, 0, btnWidth, Short.MAX_VALUE)
                    )
                    .addGroup(gl.createSequentialGroup()
                        .addComponent(button_1, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_2, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_3, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(plus, 0, btnWidth, Short.MAX_VALUE)
                    )
                    .addGroup(gl.createSequentialGroup()
                        .addComponent(sign, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(button_0, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(dot, 0, btnWidth, Short.MAX_VALUE)
                        .addComponent(equal, 0, btnWidth, Short.MAX_VALUE)
                    )
                )
            )
        );
           
        //gl.linkSize(SwingConstants.HORIZONTAL, yesButton, noButton);    // �ʺ� ���� ū ������Ʈ�� ���� ������.
        //gl.linkSize(SwingConstants.VERTICAL, yesButton, noButton);    // ���̰� ���� ū ������Ʈ�� ���� ������.

        // ������Ʈ�� ���� ��ġ�� ����
        gl.setVerticalGroup(gl.createSequentialGroup()
            .addComponent(expression, 0, 25, Short.MAX_VALUE)
            .addComponent(label, 0, 50, Short.MAX_VALUE)
            .addGap(10, 20, 100)
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(cls, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(reset, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(del, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(div, 0, btnHeight, Short.MAX_VALUE)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(button_7, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_8, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_9, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(mul, 0, btnHeight, Short.MAX_VALUE)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(button_4, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_5, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_6, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(minus, 0, btnHeight, Short.MAX_VALUE)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(button_1, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_2, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_3, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(plus, 0, btnHeight, Short.MAX_VALUE)
            )
            .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(sign, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(button_0, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(dot, 0, btnHeight, Short.MAX_VALUE)
                .addComponent(equal, 0, btnHeight, Short.MAX_VALUE)
            )
        );
        
        pack(); // setSize() ���� �켱������ ����
        setLocationRelativeTo(null);    // �����츦 ȭ���� ����� ���
        setVisible(true);   // �����츦 ������ ȭ�鿡 ���̵��� ��
    }

    // ���������� ���� ���� �����ϱ� ���� �޼���
    void calc() {
        if(last_operator == '+') {
            acc += last_operand;
        }
        else if(last_operator == '-') {
            acc -= last_operand;
        }
        else if(last_operator == '*') {
            if(last_operand == 0) {
                acc = 0;
            }
            else {
                acc *= last_operand;
            }
        }
        else if(last_operator == '/') {
            acc /= last_operand;
        }
        else {
            acc = last_operand;
        }
    }

    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("reset")) {       // reset ��ư�� �������� 0���� �ʱ�ȭ �ϰ�, ������ ���� 0���� �ʱ�ȭ �Ѵ�.
                label.setText("0");
                expression.setText("");
                acc = 0;
                ready = false;
                last_operator = 0;
            }
            else if(b.getText().equals("clear")) {      // clear ��ư�� �������� 0���� �ʱ�ȭ �Ѵ�.
                label.setText("0");
            }
            else if(b.getText().equals("del")) {        // del ��ư�� �������� ���������� �Է��� ���� �ϳ��� �����.
                if(label.getText().length() == 1) {
                    label.setText("0");
                }
                else if(label.getText().length() > 0) {
                    label.setText(label.getText().substring(0, label.getText().length()-1));
                }
            }
            else if(b.getText().charAt(0) >= '0' && b.getText().charAt(0) <= '9') {     // ���� ��ư�� �Է��ϸ�, ������ ���ڸ� ����Ѵ�.
                if(ready == true) {
                    label.setText(b.getText());
                    ready = false;
                }
                else if(label.getText().equals("0")) {
                    label.setText(b.getText());
                }
                else {
                    label.setText(label.getText() + b.getText());
                }
            }
            else if(b.getText().equals("=")) {      // = ��ư�� ������, ���� ������ ���� �����ڸ� ����Ͽ� ����� ����Ѵ�.
                last_operand = Double.parseDouble(label.getText());
                calc();
                expression.setText("");
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = 0;
                ready = true;
            }
            else if(b.getText().equals("+")) {      // + ��ư�� ������, ���� ������ ���� ���� ������ �Ѵ�.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " + ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '+';
                ready = true;
            }
            else if(b.getText().equals("-")) {      // - ��ư�� ������, ���� ������ ���� ���� ������ �Ѵ�.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " - ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '-';
                ready = true;
            }
            else if(b.getText().equals("*")) {      // * ��ư�� ������, ���� ������ ���� ���� ������ �Ѵ�.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " * ");
                calc();
                System.out.println(acc);
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '*';
                ready = true;
            }
            else if(b.getText().equals("/")) {      // / ��ư�� ������, ���� ������ ���� ������ ������ �Ѵ�.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " / ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '/';
                ready = true;
            }
            else if(b.getText().equals("+/-")) {        // +/- ��ư�� ������, ���� ������ ���� ��ȣ�� �ٲ۴�.
                last_operand = Double.parseDouble(label.getText());
                last_operand *= -1;
                String res = last_operand + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
            }
            else if(b.getText().equals(".")) {      // . ��ư�� ������, �Ҽ����� ����Ѵ�.
                String res = label.getText();
                if(!res.contains(".")) {
                    label.setText(label.getText() + ".");
                }
            }
        }
    }
}

public class Calc_based_grouplayout {

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
