import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import java.util.concurrent.*;

public class GameScreen extends JFrame
{
	JButton b,b_exit,b_about; 
    Clip clip;  
    AudioInputStream audioInputStream; 
    String filePath;
	
	GameScreen()
	{
		try
		{
			filePath = "kbc.wav";
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
        	clip = AudioSystem.getClip();
        	clip.open(audioInputStream); 
        	clip.start();
        	clip.loop(Clip.LOOP_CONTINUOUSLY);
        	
			b = new JButton("PLAY");
			b.setBounds(225,275,300,65);
			b.setBorder(BorderFactory.createBevelBorder(1, Color.yellow, Color.yellow));
			b.setBackground(new Color(50,0,150));
			b.setForeground(Color.white);
			b.setFont(new Font("Arial",Font.BOLD,30));
			b.addActionListener(e -> {
				new QAScreen();
				clip.stop();
			});
			
			ImageIcon icon = new ImageIcon("kbc_transparent.png");
	  		JLabel l1 = new JLabel(icon);
	  		l1.setBounds(250,25,250,250);
	  		
	  		b_exit = new JButton("EXIT");
	  		b_exit.setBounds(600,350,100,40);
	  		b_exit.setBackground(new Color(50,0,150));
			b_exit.setForeground(Color.white);
	  		b_about = new JButton("ABOUT");
	  		b_about.setBounds(50,350,100,40);
	  		b_about.setBackground(new Color(50,0,150));
			b_about.setForeground(Color.white);
			
			b_exit.addActionListener(e -> {
				int a=JOptionPane.showConfirmDialog(this,"Are you sure?");  
				if(a==JOptionPane.YES_OPTION)
	    			dispose();
			});
			
			b_about.addActionListener(e -> JOptionPane.showMessageDialog(this,"This game is created by Jatin Teckchandani in Java."));
			
			add(b);add(l1);add(b_exit);add(b_about);
			setTitle("Kaun Banega Crorepati");
			getContentPane().add(new MyComponent());
			setSize(750,450);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		}
		catch(Exception ex)
		{
			System.out.println("Error with playing sound."); 
            ex.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
    	new GameScreen();
	}
	
	public class MyComponent extends JComponent
	{
	 	public void paint(Graphics g){
	 	Graphics2D g2d = (Graphics2D)g;
	  	GradientPaint gradient1 = new GradientPaint(0.0f, 450.0f, new Color(0, 0, 0),375.0f, 450.0f, new Color(200, 0, 200), true);
	  	g2d.setPaint(gradient1);
	  	g2d.fillRect(0,0,375,450);
	  	GradientPaint gradient2 = new GradientPaint(375.0f, 450.0f, new Color(200, 0, 200),750.0f, 450.0f, new Color(0, 0, 0), true);
	  	g2d.setPaint(gradient2);
	  	g2d.fillRect(375,0,750,450);
	  }
	}
}

class QAScreen extends JFrame
{
	JLabel p,jl;
	static int count=1;
	int used1=0,used2=0,used3=0,used4=0;
	Clip clip;  
    AudioInputStream audioInputStream; 
    String filePath;
    String ques="";
	String op1="";
	String op2="";
	String op3="";
	String op4="";
	String ans="";
	QAScreen()
	{
		try{
		filePath = "start.wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
        clip = AudioSystem.getClip();
        clip.open(audioInputStream); 
        clip.start();
        
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/kbc","root","");
    	Statement st = con.createStatement();
    	
		String str[] = {"16        Rs.7 Crore","15        Rs.1 Crore","14    Rs.50,00,000","13    Rs.25,00,000","12    Rs.12,50,000","11      Rs.6,40,000","10      Rs.3,20,000","9        Rs.1,60,000","8           Rs.80,000","7           Rs.40,000","6           Rs.20,000","5           Rs.10,000","4             Rs.5,000","3             Rs.3,000","2             Rs.2,000","1             Rs.1,000"};
		for(int i=0;i<16;i++)
		{
			p = new JLabel(str[i]);
			int y=(i*35)+100;
			p.setBounds(850,y,250,35);
			p.setFont(new Font("Arial",Font.PLAIN,25));
			if(i==0||i==6||i==11)
				p.setForeground(Color.white);
			else
				p.setForeground(new Color(218,165,32));
			add(p);
		}
		jl = new JLabel();
		jl.setBackground(Color.yellow);
		jl.setBounds(825,625,250,35);
		jl.setOpaque(true);
		add(jl);
		
		ImageIcon icon1 = new ImageIcon("fifty_fifty.png");
	  	JButton fifty = new JButton(icon1);
	  	fifty.setBounds(500,10,125,80);
	  	fifty.setOpaque(false);
		fifty.setContentAreaFilled(false);
		fifty.setBorderPainted(false);
	  	fifty.setBorder(BorderFactory.createEmptyBorder());
	  	ImageIcon icon2 = new ImageIcon("audience_poll.png");
	  	JButton poll = new JButton(icon2);
	  	poll.setBounds(650,10,125,80);
		poll.setContentAreaFilled(false);
		poll.setBorderPainted(false);
	  	poll.setBorder(BorderFactory.createEmptyBorder());
	  	ImageIcon icon3 = new ImageIcon("expert.png");
	  	JButton expert = new JButton(icon3);
	  	expert.setBounds(800,10,125,80);
		expert.setContentAreaFilled(false);
		expert.setBorderPainted(false);
	  	expert.setBorder(BorderFactory.createEmptyBorder());
	  	ImageIcon icon4 = new ImageIcon("flip.png");
	  	JButton flip = new JButton(icon4);
	  	flip.setBounds(950,10,125,80);
		flip.setContentAreaFilled(false);
		flip.setBorderPainted(false);
	  	flip.setBorder(BorderFactory.createEmptyBorder());
	  	ImageIcon icon5 = new ImageIcon("quit.png");
	  	JButton quit = new JButton(icon5);
	  	quit.setBounds(10,10,125,80);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
	  	quit.setBorder(BorderFactory.createEmptyBorder());
	  	
	  	ImageIcon icon6 = new ImageIcon("qa1.png");
	  	JButton l6 = new JButton(icon6);
	  	l6.setBounds(50,275,750,225);
		l6.setContentAreaFilled(false);
		l6.setBorderPainted(false);
	  	l6.setBorder(BorderFactory.createEmptyBorder());
	  	
	  	ImageIcon icon7 = new ImageIcon("qa2.png");
	  	JButton l7 = new JButton(icon7);
	  	l7.setBounds(25,450,400,100);
		l7.setContentAreaFilled(false);
		l7.setBorderPainted(false);
	  	l7.setBorder(BorderFactory.createEmptyBorder());
	  	JButton l8 = new JButton(icon7);
	  	l8.setBounds(425,450,400,100);
		l8.setContentAreaFilled(false);
		l8.setBorderPainted(false);
	  	l8.setBorder(BorderFactory.createEmptyBorder());
	  	JButton l9 = new JButton(icon7);
	  	l9.setBounds(25,525,400,100);
		l9.setContentAreaFilled(false);
		l9.setBorderPainted(false);
	  	l9.setBorder(BorderFactory.createEmptyBorder());
	  	JButton l10 = new JButton(icon7);
	  	l10.setBounds(425,525,400,100);
		l10.setContentAreaFilled(false);
		l10.setBorderPainted(false);
	  	l10.setBorder(BorderFactory.createEmptyBorder());
	  	
	  	ImageIcon icon8 = new ImageIcon("amitabh.png");
	  	JLabel l11 = new JLabel(icon8);
	  	l11.setBounds(150,55,450,300);
	  	
	  	ImageIcon icon9 = new ImageIcon("wait.png");
	  	ImageIcon icon10 = new ImageIcon("correct.png");
	  	ImageIcon cross = new ImageIcon("cross.png");
	  	JLabel done1 = new JLabel(cross);
	  	JLabel done2 = new JLabel(cross);
	  	JLabel done3 = new JLabel(cross);
	  	JLabel done4 = new JLabel(cross);
	  	done1.setVisible(false);
	  	done2.setVisible(false);
	  	done3.setVisible(false);
	  	done4.setVisible(false);
	  	
	  	ResultSet rs = st.executeQuery("Select * from qa");
	  	ResultSetMetaData md = rs.getMetaData();
		int colCount = md.getColumnCount();
		
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		
		String data[][] = new String[rowCount][colCount];
		int i=0;
		
	   	while(rs.next())
	   	{
			data[i][0]=String.valueOf(rs.getInt(1));
    		for(int l=1;l<colCount;l++)
    		{
    			data[i][l]=rs.getString(l+1);
    		}
    		i++;
	   	}
		
		Random rand = new Random();
	  	int val = rand.nextInt(4)+1;
	  	for(int j=0;j<rowCount;j++)
	  	{
	  		int temp = Integer.parseInt(data[j][0]);
	  		if(temp==val && data[j][7].equals("1"))
	  		{
	  			ques=data[j][1];
	  			op1=data[j][2];
	  			op2=data[j][3];
	  			op3=data[j][4];
	  			op4=data[j][5];
	  			ans=data[j][6];
	  			break;
	  		}
	  	}
		  	
		JLabel q = new JLabel("<html>"+ques+"</html>");
		q.setBounds(90,335,700,100);
		q.setOpaque(false);
	    q.setForeground(Color.white);
		q.setFont(new Font("Arial",Font.PLAIN,20));
		JLabel a = new JLabel("A: "+op1);
		a.setBounds(90,475,300,50);
		a.setOpaque(false);
		a.setForeground(Color.white);
		a.setFont(new Font("Arial",Font.PLAIN,20));
		JLabel b = new JLabel("B: "+op2);
		b.setBounds(500,475,300,50);
		b.setOpaque(false);
		b.setForeground(Color.white);
		b.setFont(new Font("Arial",Font.PLAIN,20));
		JLabel c = new JLabel("C: "+op3);
		c.setBounds(90,550,300,50);
		c.setOpaque(false);
		c.setForeground(Color.white);
		c.setFont(new Font("Arial",Font.PLAIN,20));
		JLabel d = new JLabel("D: "+op4);
		d.setBounds(500,550,300,50);
		d.setOpaque(false);
		d.setForeground(Color.white);
		d.setFont(new Font("Arial",Font.PLAIN,20));
	  	
		l7.addActionListener(e -> {
			l7.setIcon(icon9);
			if(op1.equals(ans))
			{
				try{l7.setIcon(icon10);
				filePath = "start.wav";
		        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream); 
		        clip.start();
				ans=nextQues(icon7,l7,l8,l9,l10,q,a,b,c,d,data,rowCount);}
				catch(Exception ex){}
			}	
			else if(op2.equals(ans))
			{
				l8.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op3.equals(ans))
			{
				l9.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else
			{
				l10.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			});
		l8.addActionListener(e -> {
			l8.setIcon(icon9);
			if(op1.equals(ans))
			{
				l7.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op2.equals(ans))
			{
				try{l8.setIcon(icon10);
				filePath = "start.wav";
		        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream); 
		        clip.start();
				ans=nextQues(icon7,l7,l8,l9,l10,q,a,b,c,d,data,rowCount);}
				catch(Exception ex){}
			}
			else if(op3.equals(ans))
			{
				l9.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else
			{
				l10.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			});
		l9.addActionListener(e -> {
			l9.setIcon(icon9);
			if(op1.equals(ans))
			{
				l7.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op2.equals(ans))
			{
				l8.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op3.equals(ans))
			{
				try{l9.setIcon(icon10);
				filePath = "start.wav";
		        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream); 
		        clip.start();
				ans=nextQues(icon7,l7,l8,l9,l10,q,a,b,c,d,data,rowCount);}
				catch(Exception ex){}
			}
			else
			{
				l10.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			});
		l10.addActionListener(e -> {
			l10.setIcon(icon9);
			if(op1.equals(ans))
			{
				l7.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op2.equals(ans))
			{
				l8.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else if(op3.equals(ans))
			{
				l9.setIcon(icon10);
				new Cheque2(count);
				count=1;
			}
			else
			{
				try{l10.setIcon(icon10);
				filePath = "start.wav";
		        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream); 
		        clip.start();
				ans=nextQues(icon7,l7,l8,l9,l10,q,a,b,c,d,data,rowCount);}
				catch(Exception ex){}
			}
			});
		
	  	quit.addActionListener(e -> {
				int op=JOptionPane.showConfirmDialog(this,"Are you sure you want to quit?");  
				if(op==JOptionPane.YES_OPTION)
	    			dispose();
	    		new Cheque(count);
			});
			
		fifty.addActionListener(e ->{
			if(used1==0)
			{
				if(op1.equals(ans))
				{
					c.setText("");
					d.setText("");
				}
				else if(op2.equals(ans))
				{
					a.setText("");
					d.setText("");
				}
				else if(op3.equals(ans))
				{
					a.setText("");
					b.setText("");
				}
				else
				{
					b.setText("");
					c.setText("");
				}
				done1.setBounds(500,10,125,80);
				done1.setVisible(true);
				used1++;
			}
		});
		
		expert.addActionListener(e ->{
			if(used3==0)
			{
				new exp(ans);
				done3.setBounds(800,10,125,80);
				done3.setVisible(true);
				used3++;
			}
		});
		
		poll.addActionListener(e -> {
			if(used2==0)
			{
				new aud(ans,op1,op2,op3,op4);
				done2.setBounds(650,10,125,80);
				done2.setVisible(true);
				used2++;
			}
		});
		
		flip.addActionListener(e ->{
			if(used4==0)
			{
				ans=change(q,a,b,c,d,count,val,data,rowCount);
				done4.setBounds(950,10,125,80);
				done4.setVisible(true);
				used4++;
			}
		});
		
		add(fifty);add(poll);add(expert);add(flip);add(quit);add(q);add(l6);add(a);add(b);add(c);add(d);add(l7);add(l8);add(l9);add(l10);add(l11);add(done1);add(done2);add(done3);add(done4);
		setTitle("Kaun Banega Crorepati");
		getContentPane().add(new MyComponent());
		setSize(1100,700);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		rs.close();
		st.close();
		con.close();
		}
		catch(Exception ex){}
	}	
	
	public String nextQues(ImageIcon icon7,JButton l7,JButton l8,JButton l9,JButton l10,JLabel q,JLabel a,JLabel b,JLabel c,JLabel d,String[][] data,int rc)
	{
		count++;
		if(count<=16)
		{
		  	Random rand = new Random();
		  	int val = rand.nextInt(4)+1;
		  	
		  	for(int j=0;j<rc;j++)
		  	{
		  		int temp = Integer.parseInt(data[j][0]);
		  		String ct = String.valueOf(count);
		  		if(temp==val && data[j][7].equals(ct))
		  		{
		  			ques=data[j][1];
		  			op1=data[j][2];
		  			op2=data[j][3];
		  			op3=data[j][4];
		  			op4=data[j][5];
		  			ans=data[j][6];
		  			break;
		  		}
		  	}
		  		
			q.setText("<html>"+ques+"</html>");
			a.setText("A: "+op1);
			b.setText("B: "+op2);
			c.setText("C: "+op3);
			d.setText("D: "+op4);
			
			l7.setIcon(icon7);l8.setIcon(icon7);l9.setIcon(icon7);l10.setIcon(icon7);
			
			int y = 700-(35*(count+1));
			jl.setBounds(825,y,250,35);
		}
		else
		{
			new Cheque(count);
			dispose();
		}
		return ans;
	}
	
	public String change(JLabel q,JLabel a,JLabel b,JLabel c,JLabel d,int count,int val,String[][] data,int rc)
	{
	  	Random rand = new Random();
	  	int val1 = rand.nextInt(4)+1;
	  	if(val1==val)
	  	{
	  		val1 = rand.nextInt(4)+1;
	  	}
	  	else
	  	{
		  	for(int j=0;j<rc;j++)
		  	{
		  		int temp = Integer.parseInt(data[j][0]);
		  		String ct = String.valueOf(count);
		  		if(temp==val && data[j][7].equals(ct))
		  		{
		  			ques=data[j][1];
		  			op1=data[j][2];
		  			op2=data[j][3];
		  			op3=data[j][4];
		  			op4=data[j][5];
		  			ans=data[j][6];
		  			break;
		  		}
		  	}
			  	
			q.setText("<html>"+ques+"</html>");
			a.setText("A: "+op1);
			b.setText("B: "+op2);
			c.setText("C: "+op3);
			d.setText("D: "+op4);
	  	}
		return ans;
	}
	
	public class MyComponent extends JComponent
	{
	 	public void paint(Graphics g){
	 	Graphics2D g2d = (Graphics2D)g;
	  	GradientPaint gradient1 = new GradientPaint(0.0f, 700.0f, new Color(0, 0, 0),550.0f, 700.0f, new Color(200, 0, 200), true);
	  	g2d.setPaint(gradient1);
	  	g2d.fillRect(0,0,550,700);
	  	GradientPaint gradient2 = new GradientPaint(550.0f, 700.0f, new Color(200, 0, 200),1100.0f, 700.0f, new Color(0, 0, 0), true);
	  	g2d.setPaint(gradient2);
	  	g2d.fillRect(550,0,1100,700);
	  	}
	   
		public boolean isOptimizedDrawingEnabled()
    	{
       		 return false;
   		}
	}
}

class exp extends JFrame
{
	JLabel l;
	exp(String ans)
	{
		l = new JLabel();
		l.setText("The correct answer is : "+ans);
		l.setForeground(Color.white);
		l.setBounds(25,50,400,50);
		l.setFont(new Font("Arial",Font.PLAIN,22));
		add(l);
		setTitle("Expert Opinion");
		getContentPane().setBackground(new Color(100, 0, 100));
		setSize(425,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class aud extends JFrame
{
	JLabel l;
	aud(String ans, String op1, String op2, String op3, String op4)
	{
		l = new JLabel();
		ImageIcon i1 = new ImageIcon("a_poll.jpg");
		ImageIcon i2 = new ImageIcon("b_poll.jpg");
		ImageIcon i3 = new ImageIcon("c_poll.jpg");
		ImageIcon i4 = new ImageIcon("d_poll.jpg");
		if(op1.equals(ans))
			l.setIcon(i1);
		else if(op2.equals(ans))
			l.setIcon(i2);
		else if(op3.equals(ans))
			l.setIcon(i3);
		else
			l.setIcon(i4);
		l.setBounds(0,0,402,332);
		l.setFont(new Font("Arial",Font.PLAIN,22));
		add(l);
		setTitle("Audience Poll");
		setSize(402,332);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class Cheque extends JFrame
{
	JLabel l;
	Cheque(int count)
	{	
		l = new JLabel();
		String str[] = {"Rs.7 Crore","Rs.1 Crore","Rs.50,00,000","Rs.25,00,000","Rs.12,50,000","Rs.6,40,000","Rs.3,20,000","Rs.1,60,000","Rs.80,000","Rs.40,000","Rs.20,000","Rs.10,000","Rs.5,000","Rs.3,000","Rs.2,000","Rs.1,000"};
		for(int i=0;i<16;i++)
		{
			if(count==1)
				l.setText("Congratulations! You Won : Rs.0");
			else
				if(i==(17-count))
					l.setText("Congratulations! You Won : "+str[i]);
		}
		l.setForeground(new Color(218,165,32));
		l.setBounds(25,50,500,50);
		l.setFont(new Font("Arial",Font.BOLD,25));
		add(l);
		getContentPane().setBackground(new Color(100, 0, 100));
		setTitle("Your Winnings");
		setSize(550,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class Cheque2 extends JFrame
{
	JLabel l;
	Cheque2(int count)
	{	
		l = new JLabel();
		String str[] = {"Rs.3,20,000","Rs.10,000"};
		if(count==1 || count==2 || count==3 || count==4)
			l.setText("<html>Afsos ye galat uttar hai<br>Congratulations! You Won : Rs.0</html>");
		else if(count==5 || count==6 || count==7 || count==8 || count==9)
			l.setText("<html>Afsos ye galat uttar hai<br>Congratulations! You Won : "+str[1]+"</html>");
		else
			l.setText("Congratulations! You Won : "+str[0]);
		l.setForeground(new Color(218,165,32));
		l.setBounds(25,25,500,100);
		l.setFont(new Font("Arial",Font.BOLD,25));
		add(l);
		getContentPane().setBackground(new Color(100, 0, 100));
		setTitle("Your Winnings");
		setSize(550,200);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}