import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Racing extends Thread implements KeyListener
{
	JFrame f;
	JLayeredPane pane;
	JLabel back,car,arrow,timer;
	
	Racing()
	{
		f=new JFrame("Racing");
		f.setSize(740,530);
		f.setVisible(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setResizable(false);
		
		back=new JLabel();
		back.setBounds(0,0,740,500);
		
		car=new JLabel();
		car.setBounds(431,445,36,57);
		
		arrow=new JLabel();
		arrow.setBounds(280,250,200,150);
		
		timer=new JLabel(new ImageIcon("timer.png"));
		timer.setBounds(600,10,50,20);
		
		pane=f.getLayeredPane();
		pane.add(back,new Integer(1));
		pane.add(car,new Integer(2));
		pane.add(arrow,new Integer(2));
		pane.add(timer,new Integer(2));
		
		car.setIcon(new ImageIcon("car_mask.png"));
		arrow.setIcon(new ImageIcon("arrow.png"));
		
		f.addKeyListener(this);
	}
	int time_min=00,time_sec=00;
	public void run()
	{
		while(true)
		{
			try
			{
				timer.setText(time_min+":"+time_sec);
				back.setIcon(new ImageIcon("race1.png"));
				Thread.sleep(100);
				time_sec++;
				timer.setText(time_min+":"+time_sec);
				back.setIcon(new ImageIcon("race2.png"));
				Thread.sleep(100);
				time_sec++;
				timer.setText(time_min+":"+time_sec);
				time_min=time_sec==60?(time_min+=1):time_min;
				time_sec=time_sec==60?time_sec=0:time_sec;
			}catch(Exception e){}
		}
	}
	
	public void keyPressed(KeyEvent ke)
	{
		pane.remove(arrow);
		int c=ke.getKeyCode();
		if(c==ke.VK_LEFT && car.getX()>=185)
		{
			car.setBounds((car.getX()-3),(car.getY()),36,57);
		}
		else if(c==ke.VK_RIGHT && car.getX()<=520)
		{
			car.setBounds((car.getX()+3),(car.getY()),36,57);
		}
		else if(c==ke.VK_UP && car.getY()>=0)
		{
			car.setBounds((car.getX()),(car.getY()-3),36,57);
		}
		else if(c==ke.VK_DOWN && car.getY()<=445)
		{
			car.setBounds((car.getX()),(car.getY()+3),36,57);
		}
	}
	
	public void keyReleased(KeyEvent ke1){}
	public void keyTyped(KeyEvent ke2){}
	
	public static void main(String args[])
	{
		new Racing();
		Racing rt1=new Racing();
		rt1.start();
	}
}
