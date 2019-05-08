import java.awt.Color;
import java.awt.Graphics;

public class Apple {

	private int xCoor,yCoor,width,height;
	
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public Apple(int xCoor,int yCoor,int tileSize) {
		// TODO Auto-generated constructor stub
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		this.width=tileSize;
		this.height=tileSize;
				
	}
	
	
	public void tick() {
		// TODO Auto-generated method stub

	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(xCoor*width,yCoor*height , width, height);
	}
	
}
