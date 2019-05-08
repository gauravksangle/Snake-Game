import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH=500;
	public static final int HEIGHT=500;
	
	private Thread thread;
	
	private boolean running;
	private boolean right=true, left=false, up=false, down=false;
	
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apple apple;
	private ArrayList<Apple> apples;
	
	private Random r;
	
	private int xCoor=10,yCoor=10,size=15;
	private int ticks=0;
	
	JMenuBar MenuBar;
	JMenu Game,Help;
	JPanel Panel;
	
	public GamePanel() {
		// TODO Auto-generated constructor stub
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		
		snake=new ArrayList<BodyPart>();
		
		apples=new ArrayList<Apple>();
		
		r=new Random();
		
		
		Game=new JMenu("Game");
		Help=new JMenu("Help");
		
		MenuBar=new JMenuBar();
		
		MenuBar.add(Game);
		MenuBar.add(Help);
		Panel=new JPanel();
		
		Panel.add(MenuBar);
		
		start();
	}
	
	
	public void start()
	{
		running=true;
		thread=new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		if(snake.size()==0)
		{
			b=new BodyPart(xCoor, yCoor, 10);
		}
		
		ticks++;
		if(ticks>250000)
		{
			if(right)xCoor++;
			if(left)xCoor--;
			if(up)yCoor--;
			if(down)yCoor++;
			
			ticks=0;
			
			b=new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			if(snake.size()>size)
			{
				snake.remove(0);
			}
			
		}
		
		if(apples.size()==0)
		{
			int xCoor=r.nextInt(49);
			int yCoor=r.nextInt(49);
			
			apple=new Apple(xCoor, yCoor, 10);
			apples.add(apple);
			
		}
		
		for(int i=0;i<apples.size();i++)
		{
			if(xCoor==apples.get(i).getxCoor() && yCoor==apples.get(i).getyCoor())
			{
				size++;
				apples.remove(i);
				i++;
			}
		}
		
		for(int i=0;i<snake.size();i++)
		{
			if(xCoor==snake.get(i).getxCoor() && yCoor==snake.get(i).getyCoor())
			{
				if(i!=snake.size()-1)
				{
					//System.out.println("GAME OVER"); 
					JOptionPane.showMessageDialog(null, "GAME OVER", "GAME OVER", JOptionPane.OK_OPTION);
					
					stop();
				}
			}
		}
		
		
		if(xCoor<0 || xCoor>49 || yCoor<0 || yCoor>49)
		{
			//System.out.println("GAME OVER");
			JOptionPane.showMessageDialog(null, "GAME OVER", "GAME OVER", JOptionPane.OK_OPTION);
			//GameOver gmv=new GameOver();
			//Graphics g = null;
			//gmv.draw(g);
			/*Font f1=new Font("DialogInput", Font.BOLD, 20);
			g.drawString("GAME OVER", 25, 25);
			g.setColor(Color.RED);
			g.setFont(f1);*/
			stop();
		}
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i=0;i<WIDTH/10;i++)
		{
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		
		for(int i=0;i<WIDTH/10;i++)
		{
			g.drawLine(0,i*10,HEIGHT,i*10);
		}
		
		for(int i=0;i<snake.size();i++)
		{
			snake.get(i).draw(g);
		}
		
		for(int i=0;i<apples.size();i++)
		{
			apples.get(i).draw(g);
		}
		
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running)
		{
			tick();
			repaint();
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT && !left)
		{
			right=true;
			up=false;
			down=false;
		}
		
		if(key==KeyEvent.VK_LEFT && !right)
		{
			left=true;
			up=false;
			down=false;
		}
		
		if(key==KeyEvent.VK_DOWN && !up)
		{
			down=true;
			left=false;
			right=false;
		}
		
		if(key==KeyEvent.VK_UP && !down)
		{
			up=true;
			left=false;
			right=false;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}