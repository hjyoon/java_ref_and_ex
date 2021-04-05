import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

class MyFrame5 extends JFrame {
    Container contentPane;      // 컨텐트팬을 저장하기 위한 변수
    GroupLayout gl;     // 레이아웃을 저장하기 위한 변수
    
    // 멤버변수로 버튼 선언
    JLabel label;       // 현재 입력한 숫자를 표시하는 레이블
    JLabel expression;  // 이전에 입력한 식을 표시하는 레이블
    JButton button_0;   // 숫자 0 버튼
    JButton button_1;   // 숫자 1 버튼
    JButton button_2;   // 숫자 2 버튼
    JButton button_3;   // 숫자 3 버튼
    JButton button_4;   // 숫자 4 버튼
    JButton button_5;   // 숫자 5 버튼
    JButton button_6;   // 숫자 6 버튼
    JButton button_7;   // 숫자 7 버튼
    JButton button_8;   // 숫자 8 버튼
    JButton button_9;   // 숫자 9 버튼
    JButton sign;   // 부호 버튼
    JButton dot;    // 소숫점 버튼
    JButton equal;  // = 버튼
    JButton plus;   // + 버튼
    JButton minus;  // - 버튼
    JButton mul;    // x 버튼
    JButton div;    // / 버튼
    JButton del;    // del 버튼
    JButton reset;  // reset 버튼
    JButton cls;    // clear 버튼

    // 버튼의 크기를 설정하기 위한 변수
    final static int btnWidth = 90;
    final static int btnHeight = 90;
    final static int btnTestSize = 25;     

    double acc = 0;             // 누적된 연산식의 결과값을 저장
    boolean ready = false;      // 숫자를 입력받기위해 저장하는 상태값
    char last_operator = 0;     // 마지막으로 입력한 연산자를 저장
    double last_operand = 0;    // 마지막으로 입력한 값을 저장
    
    public MyFrame5() {
        //setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MyCalc");
        //setBounds(100, 100, 300, 200); // setSize() 대신 사용 가능
        
        contentPane = getContentPane();
        
        // 버튼에 객체를 할당한다.
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

        // 텍스트의 폰트를 설정
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

        // 버튼 색상 설정
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

        // 버튼에 이벤트 리스너를 할당한다.
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
        
        // GroupLayout 객체 생성
        gl = new GroupLayout(contentPane);
        
        // GroupLayout 설정 한다
        contentPane.setLayout(gl);
        
        gl.setAutoCreateGaps(true);   // 컴포넌트들 사이에 갭을 자동으로 넣어줌
        gl.setAutoCreateContainerGaps(true);  // 컴포넌트와 컨테이너 사이에 갭을 자동으로 넣어줌
        //gl.setHonorsVisibility(false);    // 컴포넌트의 크기 및 위치를 지정할 때 컴포넌트의 가시성을 고려할지 여부를 설정합니다. 
        
        // 컴포넌트의 수평 위치를 설정
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
           
        //gl.linkSize(SwingConstants.HORIZONTAL, yesButton, noButton);    // 너비가 가장 큰 컴포넌트에 따라 설정됨.
        //gl.linkSize(SwingConstants.VERTICAL, yesButton, noButton);    // 높이가 가장 큰 컴포넌트에 따라 설정됨.

        // 컴포넌트의 수직 위치를 설정
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
        
        pack(); // setSize() 보다 우선순위가 높음
        setLocationRelativeTo(null);    // 윈도우를 화면의 가운데에 띄움
        setVisible(true);   // 윈도우를 실제로 화면에 보이도록 함
    }

    // 마지막으로 계산된 값을 저장하기 위한 메서드
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
            if(b.getText().equals("reset")) {       // reset 버튼이 눌렸을때 0으로 초기화 하고, 누적된 값도 0으로 초기화 한다.
                label.setText("0");
                expression.setText("");
                acc = 0;
                ready = false;
                last_operator = 0;
            }
            else if(b.getText().equals("clear")) {      // clear 버튼이 눌렸을때 0으로 초기화 한다.
                label.setText("0");
            }
            else if(b.getText().equals("del")) {        // del 버튼이 눌렸을때 마지막으로 입력한 숫자 하나를 지운다.
                if(label.getText().length() == 1) {
                    label.setText("0");
                }
                else if(label.getText().length() > 0) {
                    label.setText(label.getText().substring(0, label.getText().length()-1));
                }
            }
            else if(b.getText().charAt(0) >= '0' && b.getText().charAt(0) <= '9') {     // 숫자 버튼을 입력하면, 눌려진 숫자를 출력한다.
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
            else if(b.getText().equals("=")) {      // = 버튼이 눌리면, 현재 눌려진 값과 연산자를 계산하여 결과를 출력한다.
                last_operand = Double.parseDouble(label.getText());
                calc();
                expression.setText("");
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = 0;
                ready = true;
            }
            else if(b.getText().equals("+")) {      // + 버튼이 눌리면, 현재 눌려진 값과 덧셈 연산을 한다.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " + ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '+';
                ready = true;
            }
            else if(b.getText().equals("-")) {      // - 버튼이 눌리면, 현재 눌려진 값과 뺄셈 연산을 한다.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " - ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '-';
                ready = true;
            }
            else if(b.getText().equals("*")) {      // * 버튼이 눌리면, 현재 눌려진 값과 곱셈 연산을 한다.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " * ");
                calc();
                System.out.println(acc);
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '*';
                ready = true;
            }
            else if(b.getText().equals("/")) {      // / 버튼이 눌리면, 현재 눌려진 값과 나눗셈 연산을 한다.
                last_operand = Double.parseDouble(label.getText());
                expression.setText(expression.getText() + last_operand + " / ");
                calc();
                String res = acc + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
                last_operator = '/';
                ready = true;
            }
            else if(b.getText().equals("+/-")) {        // +/- 버튼이 눌리면, 현재 눌려진 값의 부호를 바꾼다.
                last_operand = Double.parseDouble(label.getText());
                last_operand *= -1;
                String res = last_operand + "";
                label.setText(res.replaceFirst("\\.0+$", ""));
            }
            else if(b.getText().equals(".")) {      // . 버튼이 눌리면, 소숫점을 출력한다.
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
