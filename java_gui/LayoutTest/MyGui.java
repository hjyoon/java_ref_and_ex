import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;

class ConfirmButtonActionListener implements ActionListener {
   JTextField text;
   JLabel label;
   JTable table;
   Container contentPane;
   
   String[] excp = { "a", "an", "the", "is", "are", "am", "isn't", "aren't", "in", "at", "on", "beneath", "into",
         "out", "for", "to", "from", "along", "across", "through", "between","of","as","by","be","with","can?ㅤㅅㅖㄳ","can't"};

   ConfirmButtonActionListener(JTextField text, JLabel label,Container contentPane) {
      this.text = text;
      this.label = label;
      this.contentPane=contentPane;
   }

   public void actionPerformed(ActionEvent e)
   {
 
      String filedir=text.getText();
      FileReader fr=null;
      try {
         fr= new FileReader(filedir);
         } 
      catch (IOException e1) {
         e1.printStackTrace();
         }
      label.setText("it works!"+filedir);
      
      Scanner in=new Scanner (fr);
      Object[][] data=new Object[1000][2];
      int knum=0;
      while(in.hasNext())
      {
         String input=in.nextLine();
         input=input.toLowerCase();
         String[] words=input.split(" |\\.|\\,|\"|!");
         
         for(int k=0;k<words.length;k++)
         {
            int exyes=0;
            System.out.println("++"+words[k]);
            if(words[k].equals(""))
               continue;
            for(String i:excp)
            {
               if(words[k].equals(i))
               {
                  exyes=1;
                  break;
               }
            }
            if(exyes==0)
            {
               int i;
               int sig=0;
               for(i=0;i<data.length;i++)
               {
                  if(data[i][0]==null)
                  {
                     break;
                  }
                  else if(words[k].equals(data[i][0]))
                  {
                     int temp=(Integer)data[i][1];
                     data[i][1]=(Integer)(temp+1);
                     sig=1;
                     System.out.println(data[i][0]+" "+data[i][1]);
                     break;
                  }
               }
               if(sig==0)
               {
                  data[knum][0]=words[k];
                  data[knum][1]=1;
                  knum++;
               }
            }
         }
      }
     
      for(int i=0;i<knum;i++)
      {
         for(int j=i+1;j<knum;j++)
         {
            if((Integer)data[i][1]<(Integer)data[j][1])
            {
               Object[] temp=new Object[2];
               temp=data[i];
               data[i]=data[j];
               data[j]=temp;
            }
         }
      }
      
      String colName[]= {"문자열","횟수"};
      DefaultTableModel model =new DefaultTableModel(colName,0);
      model.setColumnCount(0);
      table=new JTable(data,colName);
      JScrollPane scrollPane= new JScrollPane(table);
      contentPane.add(scrollPane,BorderLayout.CENTER);
      
      try 
      {
         fr.close();
      } 
      catch (IOException e1)
      {
         e1.printStackTrace();
      }
   }
}

public class MyGui {
   public static void main(String[] args) {
      JFrame frame=new JFrame("MyGui");
      frame.setPreferredSize(new Dimension(400,300));
      frame.setLocation(500, 100);
      
      Container contentPane=frame.getContentPane();
      JPanel panel=new JPanel();
      contentPane.add(panel,BorderLayout.NORTH);
      GridLayout layout=new GridLayout(1,3);
      panel.setLayout(layout);
      
      JLabel label=new JLabel("insert file directory");
      JTextField text=new JTextField();
      JButton button=new JButton("확인");
      panel.add(label);
      panel.add(text);  
      panel.add(button);

      ActionListener listener = new ConfirmButtonActionListener(text, label,contentPane);
      button.addActionListener(listener);
      
      frame.pack();
      frame.setVisible(true);
      
   }
}
