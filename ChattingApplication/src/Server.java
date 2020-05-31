import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class Server extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea textArea;
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    Server(){
      
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground( new Color(57, 50, 92));
        p1.setBounds(0,0,400,50);
        add(p1);
        
       //icon
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5,15,25,25 );
       p1.add(l1);
       l1.addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent ae)
          {
              System.exit(0);
          }
       });
       
       //dp
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/man.png"));
       Image i5 = i4.getImage().getScaledInstance(75, 48, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
       JLabel l2 = new JLabel(i6);
       l2.setBounds(30,3,60,45 );
       p1.add(l2);
       
       //icon1 : video
       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i9= new ImageIcon(i8);
       JLabel l3 = new JLabel(i9);
       l3.setBounds(250,10,30,30 );
       p1.add(l3);
       //icon2:phone
       ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
       Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i12 = new ImageIcon(i11);
       JLabel l4 = new JLabel(i12);
       l4.setBounds(300,10,35,30 );
       p1.add(l4);
       //icon3:3dot
       ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3dot.png"));
       Image i14 = i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
       ImageIcon i15 = new ImageIcon(i14);
       JLabel l5 = new JLabel(i15);
       l5.setBounds(360,3,10,45 );
       p1.add(l5);
       
       //Name
       JLabel lname = new JLabel("David");
       lname.setFont(new Font("SAN_SERIF",Font.BOLD,18));
       lname.setForeground(Color.WHITE);
       lname.setBounds(90,5,100 ,20);
       p1.add(lname);
       
       //Active Status
       JLabel lstatus = new JLabel("Active Now");
       lstatus.setFont(new Font("SAN_SERIF",Font.PLAIN,11));
       lstatus.setForeground(Color.WHITE);
       lstatus.setBounds(95,21,100 ,20);
       p1.add(lstatus);
       
       //Message Typing
       t1 = new JTextField();
       t1.setBounds(2,560,300,40);
       t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       add(t1);
       
       //send Button
       b1 = new JButton("Send");
       b1.setBounds(305,560,90,39);
       b1.setBackground(new Color(57, 50, 92));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       add(b1);
       b1.addActionListener(this);
       
       //textArea
       textArea = new JTextArea();
       textArea.setBounds(0,50,395,510);
       textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
       textArea.setEditable(false);
       //textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);//Right me krne ke liye
       textArea.setLineWrap(true);
       textArea.setWrapStyleWord(true);
       //textArea.setBackground(Color.pink);
       add(textArea);
       
       
       
       setSize(400,600);
       setLocation(100,100);
       setLayout(null);
       //getContentPane().setBackground(Color.yellow);
       setUndecorated(true);
       setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        try{
        String str = t1.getText();
        
        textArea.setText(textArea.getText() + "\n\t\t\t" + str);
        dout.writeUTF(str);
        t1.setText("");
        }catch(Exception i)
        {
            System.out.println(i);
        }
        
    }
    public static void main(String args[])
    {
       new Server().setVisible(true);
       try{
           skt = new ServerSocket(5000);
           System.out.println("Server is Waiting for Client...");
           s = skt.accept();
           System.out.println("Connected....");
           
           //for receiving the data
           din = new DataInputStream(s.getInputStream());
           
           //sending the data
           dout = new DataOutputStream(s.getOutputStream());
           
           
           String msgRecv = "";
           while(!msgRecv.equals("quit"))
           {
              msgRecv = din.readUTF();
              textArea.setText(textArea.getText() + "\n" + msgRecv);
           }
           //colse the connection
           skt.close();
           s.close();
           
       }
       catch(Exception t)
       {
           System.out.println(t);
       }
    }
}
